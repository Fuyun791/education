package com.education.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.education.config.WebLogAspect;
import com.education.service.IRedisService;
import com.education.until.TempTest;
import com.github.pagehelper.PageHelper;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.education.entity.OnlineCourseDiscuss;
import com.education.mapper.OnlineCourseDiscussMapper;
import com.education.service.IOnlineCourseDiscussService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dell
 * @since 2020-05-28
 */
@Service
public class OnlineCourseDiscussServiceImpl extends ServiceImpl<OnlineCourseDiscussMapper, OnlineCourseDiscuss> implements IOnlineCourseDiscussService {

    private static Lock lock = new ReentrantLock();

    private static final Logger logger = LoggerFactory.getLogger(OnlineCourseDiscussServiceImpl.class);

    private static ExecutorService executor = new ThreadPoolExecutor(1, 3, 2000L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(100), new ThreadFactoryBuilder().setNameFormat("redis-discuss-pool-%d").build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    private final OnlineCourseDiscussMapper onlineCourseDiscussMapper;

    private final IRedisService redisService;

    @Autowired
    public OnlineCourseDiscussServiceImpl(OnlineCourseDiscussMapper onlineCourseDiscussMapper, IRedisService redisService) {
        this.onlineCourseDiscussMapper = onlineCourseDiscussMapper;
        this.redisService = redisService;
    }

    @Override
    public List<OnlineCourseDiscuss> findOnlineCourseDiscuss(OnlineCourseDiscuss onlineCourseDiscuss, Integer pageStart, Integer pageSize) {
        //这里根据具体的条件进行扩充
        QueryWrapper<OnlineCourseDiscuss> queryWrapper = new QueryWrapper<>(onlineCourseDiscuss);
        PageHelper.startPage(pageStart, pageSize);
        return onlineCourseDiscussMapper.selectList(queryWrapper);
    }

    @Override
    public List<OnlineCourseDiscuss> findDiscussByCourseId(Long onlineCourseId, Integer pageStart, Integer pageSize) {
        String key = "onlineCourseDiscuss:" + onlineCourseId;
        //开始得位置
        int start = (pageStart - 1) * pageSize;
        int end = pageStart * pageSize - 1;
        List<OnlineCourseDiscuss> onlineCourseDiscussList = new ArrayList<>();
        if (redisService.listSize(key) > start) {
            List<String> discussList = redisService.range(key, start, end);
            //  转换类型
            for (String discuss : discussList) {
                onlineCourseDiscussList.add(JSON.parseObject(discuss, OnlineCourseDiscuss.class));
            }
            //  第一层遍历
            onlineCourseDiscussList.forEach(onlineCourseDiscuss -> {
                if (onlineCourseDiscuss.getDiscussChild()) {
                    String keyNum = key + ":" + onlineCourseDiscuss.getId() + "_" + onlineCourseDiscuss.getDiscussPerson();
                    List<String> discuss = redisService.range(keyNum, 0, 10);
                    List<OnlineCourseDiscuss> courseDiscusses = new ArrayList<>();
                    //  将内层评论取出
                    for (String data : discuss) {
                        courseDiscusses.add(JSON.parseObject(data, OnlineCourseDiscuss.class));
                    }
                    onlineCourseDiscuss.setOnlineCourseDiscussList(courseDiscusses);
                }
            });
        } else {
            // sql查出的一级评论
            onlineCourseDiscussList = onlineCourseDiscussMapper.findDiscussByCourseId(onlineCourseId, 0L);
            // 提交给线程池执行
            executor.execute(new CourseDiscuss(key, onlineCourseDiscussList, redisService));
            // 遍历通过一级去找二级评论，这地方其实变复杂了我一开始是直接用mybatis把一级二级都查出来。
            // 但因为我用的是list类型，而且还要把对象类型转为String，所以只能拆开
            onlineCourseDiscussList.forEach(onlineCourseDiscuss -> {
                if (onlineCourseDiscuss.getDiscussChild()) {
                    List<OnlineCourseDiscuss> courseDiscusses = onlineCourseDiscussMapper
                            .findDiscussByCourseId(onlineCourseId, onlineCourseDiscuss.getId());
                    String keyTwo = key + ":" + onlineCourseDiscuss.getId() + "_" + onlineCourseDiscuss.getDiscussPerson();
                    courseDiscusses.forEach(discuss -> {
                        redisService.rightPush(keyTwo, JSON.toJSONString(discuss));
                    });
                    onlineCourseDiscuss.setOnlineCourseDiscussList(courseDiscusses);
                }
            });
        }
        return onlineCourseDiscussList;
    }

    private static class CourseDiscuss implements Runnable {

        private String name;
        private List<OnlineCourseDiscuss> onlineCourseDiscussList;
        private IRedisService redisService;

        CourseDiscuss(String name, List<OnlineCourseDiscuss> onlineCourseDiscussList, IRedisService redisService) {
            this.name = name;
            this.onlineCourseDiscussList = onlineCourseDiscussList;
            this.redisService = redisService;
        }

        @Override
        public void run() {
            onlineCourseDiscussList.forEach(onlineCourseDiscuss -> {
                redisService.rightPush(name, JSON.toJSONString(onlineCourseDiscuss));
            });
        }
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
    @Override
    public int insertOnlineCourseDiscuss(OnlineCourseDiscuss onlineCourseDiscuss, Long indexOf) {
        int result = 0;
        //直接先判断用户id和用户name有没有对上，防止恶意添加评论
        if (onlineCourseDiscussMapper.isExist(onlineCourseDiscuss.getDiscussPerson(), onlineCourseDiscuss.getDiscussPersonName()) > 0) {
            //  先判断是否有回复某一用户，有回复说明必定是二级评论，要先判断是否有parent否则说明不是二级评论，直接返回，是二级评论了才进行判断id和nam是否对上
            boolean bool = (onlineCourseDiscuss.getDiscussToPerson() == null && "".equals(onlineCourseDiscuss.getDiscussToPersonName())) || (onlineCourseDiscuss.getDiscussParent() != 0 && onlineCourseDiscussMapper
                    .isExist(onlineCourseDiscuss.getDiscussToPerson(), onlineCourseDiscuss.getDiscussToPersonName()) > 0);
            if (bool) {
                result = 1;
            }
        }
//        if (TempTest.isExist(onlineCourseDiscuss.getDiscussPerson(),onlineCourseDiscuss.getDiscussPersonName()) > 0) {
//            //  如果==null就不用在意，直接执行，如果不是就要进行判断是否存在再决定是否执行
//            if (onlineCourseDiscuss.getDiscussToPerson() == null || TempTest.isExist(onlineCourseDiscuss.getDiscussToPerson(),onlineCourseDiscuss.getDiscussToPersonName()) > 0) {
//                result = 1;
//            }
//        }
        if (result == 0) {
            return 0;
        }
        //  外层key
        String key = "onlineCourseDiscuss:" + onlineCourseDiscuss.getOnlineCourseId();
        //  判断是否是子评论
        if (onlineCourseDiscuss.getDiscussParent() != 0) {
            //  获取父评论
            OnlineCourseDiscuss courseDiscuss = JSON.parseObject(redisService.indexList(key, indexOf), OnlineCourseDiscuss.class);
            if (courseDiscuss == null || !onlineCourseDiscuss.getDiscussParent().equals(courseDiscuss.getId())) {
                logger.error("没有父评论，有人恶意添加评论");
                return 0;
            }
            //  判断是否是第一次添加子评论
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(key).append(":").append(courseDiscuss.getId()).append("_");
            if (redisService.keys(stringBuilder + "*").size() == 0) {
                courseDiscuss.setDataModified(LocalDateTime.now());
                courseDiscuss.setDiscussChild(true);
                //  是的话将父评论中字段设为有孩子及子评论
                //  onlineCourseDiscussMapper.updateById(courseDiscuss);
                redisService.set(key, indexOf, JSON.toJSONString(courseDiscuss));
            }
            //  评论子Key
            key = stringBuilder.append(courseDiscuss.getDiscussPerson()).toString();
        }
        try {
            //  加锁进行一个同步操作
            try {
                lock.lock();
                onlineCourseDiscuss.setDataCreate(LocalDateTime.now());
                onlineCourseDiscuss.setDataModified(LocalDateTime.now());
                result = onlineCourseDiscussMapper.insert(onlineCourseDiscuss);
                redisService.rightPush(key, JSON.toJSONString(onlineCourseDiscuss));
//                result = TempTest.isExist(onlineCourseDiscuss);
//                redisService.rightPush(key, JSON.toJSONString(onlineCourseDiscuss));
            } finally {
                lock.unlock();
            }
        } catch (Exception e) {
            result = 0;
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateOnlineCourseDiscuss(OnlineCourseDiscuss onlineCourseDiscuss) {
        return onlineCourseDiscussMapper.updateById(onlineCourseDiscuss);
    }

    @Override
    public int deleteOnlineCourseDiscuss(int id) {
        return onlineCourseDiscussMapper.deleteById(id);
    }

}

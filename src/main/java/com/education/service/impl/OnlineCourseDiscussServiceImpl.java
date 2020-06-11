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
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
import org.springframework.util.StringUtils;

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

    private static ExecutorService executor = new ThreadPoolExecutor(2, 7, 2000L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(100), new ThreadFactoryBuilder().setNameFormat("redis-discuss-pool-%d").build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    private final OnlineCourseDiscussMapper onlineCourseDiscussMapper;

    private final IRedisService redisService;

    private static final String MODEL = "star";

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
    public OnlineCourseDiscuss getDiscussByCourseId(Long onlineCourseId, Long id, Integer discussPerson, Integer pageStart, Integer pageSize) {
        String hashKey = id + "_" + discussPerson;
        String key = "onlineCourseDiscuss:" + onlineCourseId;
        String keyTime = "onlineCourseDiscussTime:" + onlineCourseId + ":" + hashKey;
        long start = (pageStart - 1) * pageSize;
        long end = pageStart * pageSize - 1;
        OnlineCourseDiscuss onlineCourseDiscuss = new OnlineCourseDiscuss();
        if (redisService.sizeSetSort(keyTime) > start) {
            onlineCourseDiscuss = JSON.parseObject(String.valueOf(redisService.getHashValue(key, hashKey)), OnlineCourseDiscuss.class);
            key = key + ":" + hashKey;
            Set<String> discussSet = redisService.reverseRange(keyTime, start, end);
            List<OnlineCourseDiscuss> onlineCourseDiscussList = new ArrayList<>();
            for (String discuss : discussSet) {
                onlineCourseDiscussList.add(JSON.parseObject(String.valueOf(redisService.getHashValue(key, discuss)), OnlineCourseDiscuss.class));
            }
            onlineCourseDiscuss.setOnlineCourseDiscussList(onlineCourseDiscussList);
        }
        return onlineCourseDiscuss;
    }

    @Override
    public List<OnlineCourseDiscuss> findDiscussByCourseId(Long onlineCourseId, Integer pageStart, Integer pageSize,String model) {
        //这里把star加上去就可以了
        String key = "onlineCourseDiscuss:" + onlineCourseId;
        String keyTime = "onlineCourseDiscussTime:" + onlineCourseId;
        String keyStar = "onlineCourseDiscussStar:" + onlineCourseId;
        String keyEnd = keyTime;
        if (MODEL.equals(model)) {
            keyEnd = keyStar;
        }
        //开始得位置
        long start = (pageStart - 1) * pageSize;
        long end = pageStart * pageSize - 1;
        List<OnlineCourseDiscuss> onlineCourseDiscussList = new ArrayList<>();
        if (redisService.sizeSetSort(keyEnd) > start) {
            Set<String> discussTimeList = redisService.reverseRange(keyEnd, start, end);
            //  转换类型
            for (String discussTime : discussTimeList) {
                onlineCourseDiscussList.add(JSON.parseObject(String.valueOf(redisService.getHashValue(key, discussTime)), OnlineCourseDiscuss.class));
            }
            //  第一层遍历
            String finalKeyEnd = keyEnd;
            onlineCourseDiscussList.forEach(onlineCourseDiscuss -> {
                if (onlineCourseDiscuss.getDiscussChild()) {
                    StringBuilder stringBuilder = new StringBuilder(":").append(onlineCourseDiscuss.getId()).append("_").append(onlineCourseDiscuss.getDiscussPerson());
                    String keyNum = key + stringBuilder.toString();
                    String keyNumTime = finalKeyEnd + stringBuilder.toString();
                    Set<String> discussSet = redisService.reverseRange(keyNumTime, 0, 5);
                    List<OnlineCourseDiscuss> courseDiscusses = new ArrayList<>();
                    //  将内层评论取出
                    for (String discuss : discussSet) {
                        courseDiscusses.add(JSON.parseObject(String.valueOf(redisService.getHashValue(keyNum, discuss)), OnlineCourseDiscuss.class));
                    }
                    onlineCourseDiscuss.setOnlineCourseDiscussList(courseDiscusses);
                }
            });
        } else {
            // sql查出的一级评论
            onlineCourseDiscussList = onlineCourseDiscussMapper.findDiscussByCourseId(onlineCourseId, 0L);
            // 提交给线程池执行
            executor.execute(new CourseDiscuss(key, keyTime, keyStar, onlineCourseDiscussList, redisService));
            // 但因为我用的是list类型，而且还要把对象类型转为String，所以只能拆开
            onlineCourseDiscussList.forEach(onlineCourseDiscuss -> {
                if (onlineCourseDiscuss.getDiscussChild()) {
                    List<OnlineCourseDiscuss> courseDiscusses = onlineCourseDiscussMapper
                            .findDiscussByCourseId(onlineCourseId, onlineCourseDiscuss.getId());
                    StringBuilder stringBuilder = new StringBuilder(":").append(onlineCourseDiscuss.getId()).append("_").append(onlineCourseDiscuss.getDiscussPerson());
                    String keyTimeTwo = keyTime + stringBuilder.toString();
                    String keyTwo = key + stringBuilder.toString();
                    String keyStarTwo = keyStar + stringBuilder.toString();
                    executor.execute(new CourseDiscuss(keyTwo,keyTimeTwo,keyStarTwo,courseDiscusses,redisService));
                    onlineCourseDiscuss.setOnlineCourseDiscussList(courseDiscusses);
                }
            });
        }
        return onlineCourseDiscussList;
    }

    private static class CourseDiscuss implements Runnable {

        private String name;
        private String keyTime;
        private String keyStar;
        private List<OnlineCourseDiscuss> onlineCourseDiscussList;
        private IRedisService redisService;

        CourseDiscuss(String name, String keyTime, String keyStar, List<OnlineCourseDiscuss> onlineCourseDiscussList, IRedisService redisService) {
            this.name = name;
            this.keyTime = keyTime;
            this.keyStar = keyStar;
            this.onlineCourseDiscussList = onlineCourseDiscussList;
            this.redisService = redisService;
        }

        @Override
        public void run() {
            onlineCourseDiscussList.forEach(onlineCourseDiscuss -> {
                String key = String.valueOf(onlineCourseDiscuss.getId()) + "_" + onlineCourseDiscuss.getDiscussPerson();
                redisService.putHash(name, key, JSON.toJSONString(onlineCourseDiscuss));
                redisService.addSetSort(keyStar,key,onlineCourseDiscuss.getStar());
                redisService.addSetSort(keyTime, key, onlineCourseDiscuss.getDataModified().toInstant(ZoneOffset.of("+8")).toEpochMilli());
            });
        }
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
    @Override
    public int insertOnlineCourseDiscuss(OnlineCourseDiscuss onlineCourseDiscuss, Integer discussToPersonId) {
        int result = 0;
        //直接先判断用户id和用户name有没有对上，防止恶意添加评论
        if (onlineCourseDiscussMapper.isExist(onlineCourseDiscuss.getDiscussPerson(), onlineCourseDiscuss.getDiscussPersonName()) > 0) {
            //  先判断是否有回复某一用户，有回复说明必定是二级评论，要先判断是否有parent否则说明不是二级评论，直接返回，是二级评论了才进行判断id和nam是否对上
            boolean bool = (onlineCourseDiscuss.getDiscussToPerson() == null && StringUtils.isEmpty(onlineCourseDiscuss.getDiscussToPersonName())) || (onlineCourseDiscuss.getDiscussParent() != 0 && onlineCourseDiscussMapper
                    .isExist(onlineCourseDiscuss.getDiscussToPerson(), onlineCourseDiscuss.getDiscussToPersonName()) > 0);
            if (bool) {
                result = 1;
            }
        }
        if (result == 0) {
            logger.error("某一用户不存在");
            return 0;
        }
        //  外层key
        String key = "onlineCourseDiscuss:" + onlineCourseDiscuss.getOnlineCourseId();
        String keyTime = "onlineCourseDiscussTime:" + onlineCourseDiscuss.getOnlineCourseId();
        String keyStar = "onlineCourseDiscussStar:" + onlineCourseDiscuss.getOnlineCourseId();
        if (onlineCourseDiscuss.getDiscussParent() != 0) {
            //  获取父评论
            String hashKey = onlineCourseDiscuss.getDiscussParent() + "_" + discussToPersonId;
            OnlineCourseDiscuss courseDiscuss = JSON.parseObject(String.valueOf(redisService.getHashValue(key, hashKey)), OnlineCourseDiscuss.class);
            if (courseDiscuss == null) {
                logger.error("无父评论,恶意添加");
                return 0;
            }
            //  判断是否是第一次添加子评论
            StringBuilder stringBuilder = new StringBuilder()
                    .append(":").append(courseDiscuss.getId())
                    .append("_").append(courseDiscuss.getDiscussPerson());
            if (redisService.keys(key + stringBuilder).size() == 0) {
                courseDiscuss.setDiscussChild(true);
                //  是的话将父评论中字段设为有孩子及子评论
                onlineCourseDiscussMapper.updateById(courseDiscuss);
                redisService.putHash(key, hashKey, JSON.toJSONString(courseDiscuss));
            }
            //  评论子Key
            key += stringBuilder;
            keyTime += stringBuilder;
            keyStar += stringBuilder;
        }
        //  判断是否是子评论
        lock.lock();
        try {
            try {
                onlineCourseDiscuss.setDataCreate(LocalDateTime.now());
                onlineCourseDiscuss.setDataModified(LocalDateTime.now());
                result = onlineCourseDiscussMapper.insert(onlineCourseDiscuss);
                String str = onlineCourseDiscuss.getId() + "_" + onlineCourseDiscuss.getDiscussPerson();
                redisService.putHash(key, str, JSON.toJSONString(onlineCourseDiscuss));
                redisService.addSetSort(keyStar,str,onlineCourseDiscuss.getStar());
                redisService.addSetSort(keyTime, str, onlineCourseDiscuss.getDataModified().toInstant(ZoneOffset.of("+8")).toEpochMilli());
            } catch (Exception e) {
                result = 0;
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
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

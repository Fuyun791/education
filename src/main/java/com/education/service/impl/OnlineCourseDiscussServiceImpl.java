package com.education.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.education.service.IRedisService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.education.entity.OnlineCourseDiscuss;
import com.education.mapper.OnlineCourseDiscussMapper;
import com.education.service.IOnlineCourseDiscussService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
        List<OnlineCourseDiscuss> onlineCourseDiscussList = new ArrayList<>();
        if (redisService.listSize(key) > start) {
            List<String> discussList = redisService.range(key,start , pageSize);
            for (String discuss : discussList) {
                onlineCourseDiscussList.add(JSON.parseObject(discuss,OnlineCourseDiscuss.class));
            }
            onlineCourseDiscussList.forEach(onlineCourseDiscuss -> {
                if (onlineCourseDiscuss.getDiscussChild()) {
                    String keyNum = key + ":" + onlineCourseDiscuss.getDiscussPerson();
                    List<OnlineCourseDiscuss> courseDiscusses = JSON.parseArray(redisService.getByKey(keyNum),OnlineCourseDiscuss.class);
                    onlineCourseDiscuss.setOnlineCourseDiscussList(courseDiscusses);
                }
            });
        } else {
            onlineCourseDiscussList = onlineCourseDiscussMapper.findDiscussByCourseId(onlineCourseId, 0L);
            List<OnlineCourseDiscuss> finalOnlineCourseDiscussList = onlineCourseDiscussList;
            new Thread(() -> {
                finalOnlineCourseDiscussList.forEach(onlineCourseDiscuss -> {
                    redisService.rightPush(key,JSON.toJSONString(onlineCourseDiscuss));
                });
            }).start();
            onlineCourseDiscussList.forEach(onlineCourseDiscuss -> {
                if (onlineCourseDiscuss.getDiscussChild()) {
                    List<OnlineCourseDiscuss> courseDiscusses = onlineCourseDiscussMapper
                            .findDiscussByCourseId(onlineCourseId, onlineCourseDiscuss.getId());
                    redisService.set(key + ":" + onlineCourseDiscuss.getDiscussPerson(),JSON.toJSONString(courseDiscusses));
                    onlineCourseDiscuss.setOnlineCourseDiscussList(courseDiscusses);
                }
            });
        }
        return onlineCourseDiscussList;
    }

    @Override
    public int insertOnlineCourseDiscuss(OnlineCourseDiscuss onlineCourseDiscuss) {
        return onlineCourseDiscussMapper.insert(onlineCourseDiscuss);
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

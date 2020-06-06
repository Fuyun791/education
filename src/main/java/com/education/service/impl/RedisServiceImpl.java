package com.education.service.impl;

import com.education.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author dell
 */
@Service
public class RedisServiceImpl implements IRedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, String value, Long time, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key, value, time, timeUnit);
    }

    @Override
    public Boolean setIfAbsent(String key, String value) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value);
    }

    @Override
    public Boolean setIfAbsent(String key, String value, Long time, TimeUnit timeUnit) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value, time, timeUnit);
    }

    @Override
    public String getByKey(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public String getAndSet(String key, String value) {
        return stringRedisTemplate.opsForValue().getAndSet(key, value);
    }

    @Override
    public Long rightPush(String key, String value) {
        return stringRedisTemplate.opsForList().rightPush(key, value);
    }

    @Override
    public List<String> range(String key, Integer start, Integer end) {
        return stringRedisTemplate.opsForList().range(key,start,end);
    }

    @Override
    public Long listSize(String key) {
        return stringRedisTemplate.opsForList().size(key);
    }

    @Override
    public String indexList(String key, Long index) {
        return stringRedisTemplate.opsForList().index(key,index);
    }

    @Override
    public Set<String> keys(String pattern) {
        return stringRedisTemplate.keys(pattern);
    }

    @Override
    public void set(String key,Long index,String value) {
        stringRedisTemplate.opsForList().set(key,index,value);
    }

}

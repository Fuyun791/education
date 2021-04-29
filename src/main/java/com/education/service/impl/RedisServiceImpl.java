package com.education.service.impl;

import com.education.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

  private final StringRedisTemplate stringRedisTemplate;

  @Autowired
  public RedisServiceImpl(StringRedisTemplate stringRedisTemplate) {
    this.stringRedisTemplate = stringRedisTemplate;
  }

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
  public Long leftPush(String key, String value) {
    return stringRedisTemplate.opsForList().leftPush(key, value);
  }

  @Override
  public List<String> range(String key, Long start, Long end) {
    return stringRedisTemplate.opsForList().range(key, start, end);
  }

  @Override
  public Long listSize(String key) {
    return stringRedisTemplate.opsForList().size(key);
  }

  @Override
  public String indexList(String key, Long index) {
    return stringRedisTemplate.opsForList().index(key, index);
  }

  @Override
  public Set<String> keys(String pattern) {
    return stringRedisTemplate.keys(pattern);
  }

  @Override
  public void set(String key, Long index, String value) {
    stringRedisTemplate.opsForList().set(key, index, value);
  }

  @Override
  public Boolean addSetSort(String key, String value, double score) {
    return stringRedisTemplate.opsForZSet().add(key, value, score);
  }

  @Override
  public Long remove(String key, Object... values) {
    return stringRedisTemplate.opsForZSet().remove(key, values);
  }

  @Override
  public Set<String> reverseRange(String key, long start, long end) {
    return stringRedisTemplate.opsForZSet().reverseRange(key, start, end);
  }

  @Override
  public Set<String> rangeSetSort(String key, long start, long end) {
    return stringRedisTemplate.opsForZSet().range(key, start, end);
  }

  @Override
  public Long rank(String key, Object o) {
    return stringRedisTemplate.opsForZSet().rank(key, o);
  }

  @Override
  public Double incrementScore(String key, String value, double delta) {
    return stringRedisTemplate.opsForZSet().incrementScore(key, value, delta);
  }

  @Override
  public Long sizeSetSort(String key) {
    return stringRedisTemplate.opsForZSet().size(key);
  }

  @Override
  public void putHash(String key, String hashKey, String value) {
    stringRedisTemplate.opsForHash().put(key, hashKey, value);
  }

  @Override
  public Object getHashValue(String key, Object hashKey) {
    return stringRedisTemplate.opsForHash().get(key, hashKey);
  }

  @Override
  public Boolean hasHashKey(String key, Object hashKey) {
    return stringRedisTemplate.opsForHash().hasKey(key, hashKey);
  }

}

package com.education.service;

import java.util.concurrent.TimeUnit;

/**
 * @author dell
 */
public interface IRedisService {

    /**
     * 添加redis
     * @param key
     * @param value
     */
    void set(String key, String value);

    /**
     * 添加redis并设置时间
     * @param key
     * @param value
     * @param time
     * @param timeUnit
     */
    void set(String key, String value, Long time, TimeUnit timeUnit);

    /**
     * 如果存在就添加返回true
     * @param key
     * @param value
     * @return
     */
    Boolean setIfAbsent(String key, String value);

    /**
     * 如果存在就添加，添加的同时设置时间
     * @param key
     * @param value
     * @param time
     * @param timeUnit
     * @return
     */
    Boolean setIfAbsent(String key, String value, Long time, TimeUnit timeUnit);

    /**
     * 根据key获取redis的value
     * @param key
     * @return
     */
    String getByKey(String key);

    /**
     * 获取原来的value,同时设置新的value
     * @param key
     * @param value
     * @return
     */
    String getAndSet(String key, String value);

}

package com.education.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.education.entity.PopedomInfo;
import com.education.entity.RolePopedom;
import com.education.mapper.AdminInfoMapper;
import com.education.service.IPopedomInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RolePopedomControllerTest {

    @Autowired
    private IPopedomInfoService popedomInfoService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AdminInfoMapper adminInfoMapper;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private RolePopedomController rolePopedomController;


    @Test
    void findRolePopedom() {
        String popedomInfoList = stringRedisTemplate.opsForValue().get("popedomInfo:2");
        List<PopedomInfo> popedomInfoList1 = JSON.parseArray(popedomInfoList,PopedomInfo.class);
        System.out.println(JSON.toJSONString(popedomInfoList1));
    }

}
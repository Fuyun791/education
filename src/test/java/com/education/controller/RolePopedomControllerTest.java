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
    void insertRolePopedom() {
        List<PopedomInfo> popedomInfoList = popedomInfoService.findPopedomInfo(new PopedomInfo(), 0, 50);
        Long roleId = 1L;
        for (PopedomInfo popedomInfo : popedomInfoList) {
            RolePopedom rolePopedom = new RolePopedom();
            rolePopedom.setRoleId(roleId);
            rolePopedom.setPopedomId(popedomInfo.getId());
            rolePopedom.setPopedomStatus(0);
            rolePopedom.setWorkAuthorize(true);
            rolePopedom.setDataCreate(LocalDateTime.now());
            rolePopedom.setDataModified(LocalDateTime.now());
            rolePopedomController.insertRolePopedom(rolePopedom);
        }
    }

    @Test
    void insertPopedom2() {
        QueryWrapper<PopedomInfo> queryWrapper = new QueryWrapper<>();
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        queryWrapper.in("id", list);
        List<PopedomInfo> popedomInfoList = popedomInfoService.list(queryWrapper);
        int length = popedomInfoList.size();
        for (int i = 0; i < length; i++) {
            if (popedomInfoList.get(i).getPopedomChild() != 0) {
                queryWrapper.clear();
                queryWrapper.eq("popedom_parent",popedomInfoList.get(i).getId());
                List<PopedomInfo> popedomInfoList1 = popedomInfoService.list(queryWrapper);
                popedomInfoList.addAll(popedomInfoList1);
            }
        }
        System.out.println(JSON.toJSONString(popedomInfoList,SerializerFeature.PrettyFormat));
        for (PopedomInfo popedomInfo : popedomInfoList) {
            RolePopedom rolePopedom = new RolePopedom();
            rolePopedom.setRoleId(2L);
            rolePopedom.setPopedomId(popedomInfo.getId());
            rolePopedom.setPopedomStatus(1);
            rolePopedom.setWorkAuthorize(true);
            rolePopedom.setDataCreate(LocalDateTime.now());
            rolePopedom.setDataModified(LocalDateTime.now());
            rolePopedomController.insertRolePopedom(rolePopedom);
        }
    }

    @Test
    void findRolePopedom() {
        String popedomInfoList = stringRedisTemplate.opsForValue().get("popedomInfo:2");
        List<PopedomInfo> popedomInfoList1 = JSON.parseArray(popedomInfoList,PopedomInfo.class);
        System.out.println(JSON.toJSONString(popedomInfoList1));
    }

    @Test
    void testRedis() {
        Object popedomInfoList = redisTemplate.opsForValue().get("popedomInfo:3");
        System.out.println(JSON.toJSONString(popedomInfoList,SerializerFeature.PrettyFormat));
    }
}
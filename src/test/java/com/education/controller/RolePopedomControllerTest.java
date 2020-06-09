package com.education.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.education.entity.OnlineCourseDiscuss;
import com.education.entity.PopedomInfo;
import com.education.entity.RespBody;
import com.education.entity.RolePopedom;
import com.education.mapper.AdminInfoMapper;
import com.education.service.IPopedomInfoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RolePopedomControllerTest {

    @Autowired
    private IPopedomInfoService popedomInfoService;

    @Autowired
    private CommonController commonController;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


//    @Test
//    void findRolePopedom() {
//        for (int i = 0; i < 21 + 1; i++) {
//            stringRedisTemplate.opsForList().rightPop("onlineCourseDiscuss:2:1_17920206");
//        }
//    }

    @Test
    void test() {

    }

//    @DisplayName("语句覆盖")
//    @ParameterizedTest
//    @CsvFileSource(resources = "/whiteBox.csv", numLinesToSkip = 1)
//    void conditionalCoverage(Long onlineCourseId, Integer discussPerson,
//                             Long discussParent, String discussPersonName,
//                             String discussText, Integer discussToPerson,
//                             String discussToPersonName,
//                             Long indexOf, Integer expect) {
//        OnlineCourseDiscuss onlineCourseDiscuss = genertor(onlineCourseId, discussPerson, discussParent, discussPersonName, discussText, discussToPerson, discussToPersonName, indexOf, expect);
//        RespBody respBody = commonController.insertCourseDiscuss(onlineCourseDiscuss, indexOf);
//        assertEquals(expect, respBody.getStatus());
//    }
//
//    private OnlineCourseDiscuss genertor(Long onlineCourseId, Integer discussPerson, Long discussParent, String discussPersonName, String discussText, Integer discussToPerson, String discussToPersonName, Long indexOf, Integer expect) {
//        OnlineCourseDiscuss onlineCourseDiscuss = new OnlineCourseDiscuss();
//        onlineCourseDiscuss.setOnlineCourseId(onlineCourseId);
//        onlineCourseDiscuss.setDiscussPerson(discussPerson);
//        onlineCourseDiscuss.setDiscussText(discussText);
//        onlineCourseDiscuss.setDiscussParent(discussParent);
//        onlineCourseDiscuss.setDiscussPersonName(discussPersonName);
//        onlineCourseDiscuss.setDiscussToPerson(discussToPerson);
//        onlineCourseDiscuss.setDiscussToPersonName(discussToPersonName);
//        return onlineCourseDiscuss;
//    }
//
//    @DisplayName("路径覆盖")
//    @ParameterizedTest
//    @CsvFileSource(resources = "/whiteBox_lu_jin.csv", numLinesToSkip = 1)
//    void lujingCoverage(Long onlineCourseId, Integer discussPerson,
//                             Long discussParent, String discussPersonName,
//                             String discussText, Integer discussToPerson,
//                             String discussToPersonName,
//                             Long indexOf, Integer expect) {
//        OnlineCourseDiscuss onlineCourseDiscuss = genertor(onlineCourseId, discussPerson, discussParent, discussPersonName, discussText, discussToPerson, discussToPersonName, indexOf, expect);
//        RespBody respBody = commonController.insertCourseDiscuss(onlineCourseDiscuss, indexOf);
//        assertEquals(expect, respBody.getStatus());
//    }

}
package com.education.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.education.entity.CourseInfo;
import com.education.entity.TeacherInfo;
import com.education.mapper.CourseInfoMapper;
import com.education.service.ICourseInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springfox.documentation.swagger2.mappers.SerializableParameterFactories;

import java.io.Serializable;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CourseInfoControllerTest {

    @Autowired
    private ICourseInfoService courseInfoService;

    @Autowired
    private CourseInfoMapper courseInfoMapper;

    @Test
    void findCourseInfoList() {
        JSONObject teacherInfos = courseInfoService.findCourseInfoList(1809312,null,null);
        System.out.println(JSON.toJSONString(teacherInfos, SerializerFeature.PrettyFormat));
    }
}
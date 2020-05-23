package com.education.controller;

import com.education.entity.RespBody;
import com.education.entity.StudentInfo;
import com.education.mapper.StudentInfoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StudentInfoControllerTest {

    @Autowired
    private StudentInfoController studentInfoController;

    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @Test
    void findStudentInfo() {
    }


    @Test
    void findStudent() {
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setStudentSex("男");
        studentInfo.setCollegeId(8L);
        studentInfo.setSpecialtyId(2L);
        List<StudentInfo> studentInfoList = studentInfoMapper.findStudentInfoList(studentInfo);
        System.out.println();
    }
}
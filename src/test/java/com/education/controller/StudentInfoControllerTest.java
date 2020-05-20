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

    @CsvSource({
            "17920207,梁小云,男,15057320042",
            "17920208,张晓云,男,15012415487",
            "17920209,罗小美,女,15487211356"
    })
    @ParameterizedTest
    void insertStudentInfo(int num, String name, String sex, String phone) {
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setStudentNum(num);
        studentInfo.setStudentName(name);
        studentInfo.setStudentSex(sex);
        studentInfo.setStudentPhone(phone);
        studentInfo.setCollegeId(8L);
        studentInfo.setSpecialtyId(2L);
        studentInfo.setFacultyId(1L);
        studentInfo.setClassId(12L);
        assertEquals(studentInfoController.insertStudentInfo(studentInfo).getStatus(), RespBody.ok().getStatus());
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
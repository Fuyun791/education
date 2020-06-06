package com.education.controller;

import com.education.entity.RespBody;

import com.education.entity.StudentSigninInfo;
import com.education.service.IStudentSigninInfoService;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 学生考勤表 前端控制器
 * </p>
 *
 * @author dell
 * @since 2020-05-31
 */
@Api(tags = "StudentSigninInfoController", description = "考勤详情管理")
@RestController
@RequestMapping("/education/student-signin-info")
public class StudentSigninInfoController {

    private final IStudentSigninInfoService studentSigninInfoService;

    @Autowired
    public StudentSigninInfoController(IStudentSigninInfoService studentSigninInfoService) {
        this.studentSigninInfoService = studentSigninInfoService;
    }

    @ApiOperation("查询考勤详情")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RespBody findStudentSigninInfo(StudentSigninInfo studentSigninInfo,
                                          @RequestParam(value = "collegeId") Long collegeId,
                                          @RequestParam(value = "teacherNumber") Long teacherNumber,
                                          @RequestParam(value = "pageStart", defaultValue = "1") Integer pageStart,
                                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        List<StudentSigninInfo> studentSigninInfoList = studentSigninInfoService.findStudentSigninInfo(studentSigninInfo, collegeId, teacherNumber, pageStart, pageSize);
        PageInfo<StudentSigninInfo> pageInfo = new PageInfo<>(studentSigninInfoList);
        return RespBody.ok(pageInfo);
    }

    @ApiOperation("添加考勤详情")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public RespBody insertStudentSigninInfo(StudentSigninInfo studentSigninInfo) {
        int result = studentSigninInfoService.insertStudentSigninInfo(studentSigninInfo);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("修改考勤详情")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RespBody updateStudentSigninInfo(StudentSigninInfo studentSigninInfo) {
        int result = studentSigninInfoService.updateStudentSigninInfo(studentSigninInfo);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("删除考勤详情")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public RespBody deleteStudentSigninInfo(@RequestParam("id") int id) {
        int result = studentSigninInfoService.deleteStudentSigninInfo(id);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

}

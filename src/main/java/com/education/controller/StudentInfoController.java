package com.education.controller;

import com.education.entity.AdminInfo;
import com.education.entity.RespBody;

import com.education.entity.StudentInfo;
import com.education.service.IStudentInfoService;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
    *  前端控制器
    * </p>
 *
 * @author dell
 * @since 2020-05-16
 */
@Api(tags = "StudentInfoController", description = "学生管理")
@RestController
@RequestMapping("/education/student-info")
public class StudentInfoController {

    private final IStudentInfoService studentInfoService;

    @Autowired
    public StudentInfoController(IStudentInfoService studentInfoService) {
        this.studentInfoService = studentInfoService;
    }

    @ApiOperation("查询学生")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RespBody findStudentInfo(StudentInfo studentInfo,
                                 @RequestParam(value = "pageStart",defaultValue = "1")Integer pageStart,
                                 @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize){
        List<StudentInfo> studentInfoList = studentInfoService.findStudentInfo(studentInfo, pageStart, pageSize);
        PageInfo<StudentInfo> pageInfo = new PageInfo<>(studentInfoList);
        return RespBody.ok(pageInfo);
    }

    @ApiOperation("返回学生信息")
    @RequestMapping(value = "/list-studentInfo", method = RequestMethod.GET)
    public RespBody findStudentInfo(@RequestParam("studentNum")Integer studentNum){
        StudentInfo studentInfo = studentInfoService.getStudentInfo(studentNum);
        return RespBody.ok(studentInfo);
    }

    @ApiOperation("查询学生包含学校专业")
    @RequestMapping(value = "/list-student", method = RequestMethod.GET)
    public RespBody findStudentInfoList(StudentInfo studentInfo,
                                    @RequestParam(value = "pageStart",defaultValue = "1")Integer pageStart,
                                    @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize){
        List<StudentInfo> studentInfoList = studentInfoService.findStudentInfoList(studentInfo, pageStart, pageSize);
        PageInfo<StudentInfo> pageInfo = new PageInfo<>(studentInfoList);
        return RespBody.ok(pageInfo);
    }

    @ApiOperation("添加学生")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public RespBody insertStudentInfo(StudentInfo studentInfo) {
        studentInfo.setDataCreate(LocalDateTime.now());
        studentInfo.setDataModified(LocalDateTime.now());
        int result = studentInfoService.insertStudentInfo(studentInfo);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("修改学生")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RespBody updateStudentInfo(StudentInfo studentInfo) {
        studentInfo.setDataModified(LocalDateTime.now());
        int result = studentInfoService.updateStudentInfo(studentInfo);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("修改学生头像")
    @RequestMapping(value = "/update-studentPic", method = RequestMethod.POST)
    public RespBody updateStudentPic(AdminInfo adminInfo) {
        adminInfo.setDataModified(LocalDateTime.now());
        int result = studentInfoService.updateStudentPic(adminInfo);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("删除学生")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public RespBody deleteStudentInfo(@RequestParam("id")int id) {
        int result = studentInfoService.deleteStudentInfo(id);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

}

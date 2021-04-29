package com.education.controller;

import com.alibaba.fastjson.JSONObject;
import com.education.entity.RespBody;

import com.education.entity.StudentExam;
import com.education.entity.StudentInfo;
import com.education.service.IStudentExamService;

import com.github.pagehelper.PageHelper;
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
 * 前端控制器
 * </p>
 *
 * @author dell
 * @since 2020-05-28
 */
@Api(tags = "StudentExamController", description = "学生考试管理")
@RestController
@RequestMapping("/education/student-exam")
public class StudentExamController {

  private final IStudentExamService studentExamService;

  @Autowired
  public StudentExamController(IStudentExamService studentExamService) {
    this.studentExamService = studentExamService;
  }

  @ApiOperation("查询学生考试")
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public RespBody findStudentExam(StudentExam studentExam,
      @RequestParam(value = "pageStart", defaultValue = "1") Integer pageStart,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    List<StudentExam> studentExamList = studentExamService
        .findStudentExam(studentExam, pageStart, pageSize);
    PageInfo<StudentExam> pageInfo = new PageInfo<>(studentExamList);
    return RespBody.ok(pageInfo);
  }

  @ApiOperation("添加学生考试")
  @RequestMapping(value = "/insert", method = RequestMethod.POST)
  public RespBody insertStudentExam(StudentExam studentExam) {
    int result = studentExamService.insertStudentExam(studentExam);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("修改学生考试")
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public RespBody updateStudentExam(StudentExam studentExam) {
    int result = studentExamService.updateStudentExam(studentExam);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("删除学生考试")
  @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
  public RespBody deleteStudentExam(@RequestParam("id") int id) {
    int result = studentExamService.deleteStudentExam(id);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("查询学生考试分数")
  @RequestMapping(value = "/list-StudentExamResultList")
  public RespBody findStudentExamResultList(StudentInfo studentInfo,
      @RequestParam(value = "pageStart", defaultValue = "1") Integer pageStart,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    List<StudentInfo> studentExamList = studentExamService
        .findStudentExamResultList(studentInfo, pageStart, pageSize);
    PageInfo<StudentInfo> pageInfo = new PageInfo<>(studentExamList);
    return RespBody.ok(pageInfo);
  }

  @ApiOperation("查询学生考试绩点")
  @RequestMapping(value = "/list-StudentExamResultList2")
  public RespBody findStudentExamResultList2(StudentInfo studentInfo,
      @RequestParam(value = "pageStart", defaultValue = "1") Integer pageStart,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    List<StudentInfo> studentExamList = studentExamService
        .findStudentExamResultList2(studentInfo, pageStart, pageSize);
    PageInfo<StudentInfo> pageInfo = new PageInfo<>(studentExamList);
    return RespBody.ok(pageInfo);
  }

  //测试绩点
//    @ApiOperation("测试绩点")
//    @RequestMapping(value = "/list-StudentExamResultList3")
//    public RespBody findStudentExamResultList3 (StudentInfo studentInfo,
//                                                @RequestParam(value = "pageStart",defaultValue = "1")Integer pageStart,
//                                                @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize){
//        JSONObject result = studentExamService.findStudentExamResultList3(studentInfo);
//        return RespBody.ok(result);
//    }
}

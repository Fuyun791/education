package com.education.controller;

import com.education.entity.RespBody;

import com.education.entity.StudentCourse;
import com.education.service.IStudentCourseService;

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
 * 学生与课程的连接 前端控制器
 * </p>
 *
 * @author dell
 * @since 2020-05-23
 */
@Api(tags = "StudentCourseController", description = "学生课程管理")
@RestController
@RequestMapping("/education/student-course")
public class StudentCourseController {

  private final IStudentCourseService studentCourseService;

  @Autowired
  public StudentCourseController(IStudentCourseService studentCourseService) {
    this.studentCourseService = studentCourseService;
  }

  @ApiOperation("查询学生课程")
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public RespBody findStudentCourse(StudentCourse studentCourse,
      @RequestParam(value = "pageStart", defaultValue = "1") Integer pageStart,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    List<StudentCourse> studentCourseList = studentCourseService
        .findStudentCourse(studentCourse, pageStart, pageSize);
    PageInfo<StudentCourse> pageInfo = new PageInfo<>(studentCourseList);
    return RespBody.ok(pageInfo);
  }

  @ApiOperation("添加学生课程")
  @RequestMapping(value = "/insert", method = RequestMethod.POST)
  public RespBody insertStudentCourse(StudentCourse studentCourse) {
    int result = studentCourseService.insertStudentCourse(studentCourse);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("修改学生课程")
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public RespBody updateStudentCourse(StudentCourse studentCourse) {
    int result = studentCourseService.updateStudentCourse(studentCourse);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("删除学生课程")
  @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
  public RespBody deleteStudentCourse(@RequestParam("id") int id) {
    int result = studentCourseService.deleteStudentCourse(id);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

}

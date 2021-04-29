package com.education.controller;

import com.alibaba.fastjson.JSONObject;
import com.education.entity.CourseTimeInfo;
import com.education.entity.RespBody;

import com.education.entity.CourseInfo;
import com.education.entity.TeacherInfo;
import com.education.service.ICourseInfoService;

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
 * 课程表 前端控制器
 * </p>
 *
 * @author dell
 * @since 2020-05-23
 */
@Api(tags = "CourseInfoController", description = "课程管理")
@RestController
@RequestMapping("/education/course-info")
public class CourseInfoController {

  private final ICourseInfoService courseInfoService;

  @Autowired
  public CourseInfoController(ICourseInfoService courseInfoService) {
    this.courseInfoService = courseInfoService;
  }

  @ApiOperation("查询课程")
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public RespBody findCourseInfo(CourseInfo courseInfo,
      @RequestParam(value = "pageStart", defaultValue = "1") Integer pageStart,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    List<CourseInfo> courseInfoList = courseInfoService
        .findCourseInfo(courseInfo, pageStart, pageSize);
    PageInfo<CourseInfo> pageInfo = new PageInfo<>(courseInfoList);
    return RespBody.ok(pageInfo);
  }

  @ApiOperation("查询与课程有关的所有信息,教师课表")
  @RequestMapping(value = "/list-course", method = RequestMethod.GET)
  public RespBody findCourseInfoList(
      @RequestParam(value = "teacherNumber", required = false) Integer teacherNumber,
      @RequestParam(value = "teacherName", required = false) String teacherName,
      @RequestParam(value = "courseName", required = false) String courseNaem) {
    JSONObject result = courseInfoService
        .findCourseInfoList(teacherNumber, teacherName, courseNaem);
    return RespBody.ok(result);
  }

  @ApiOperation("查询学生课表")
  @RequestMapping(value = "/list-student-course", method = RequestMethod.GET)
  public RespBody findStudentCourseList(
      @RequestParam(value = "studentNum", required = false) Integer studentNum,
      @RequestParam(value = "studentName", required = false) String studentName,
      @RequestParam(value = "courseName", required = false) String courseNaem) {
    JSONObject result = courseInfoService
        .findStudentCourseList(studentNum, studentName, courseNaem);
    return RespBody.ok(result);
  }

  @ApiOperation("添加课程")
  @RequestMapping(value = "/insert", method = RequestMethod.POST)
  public RespBody insertCourseInfo(CourseInfo courseInfo) {
    int result = courseInfoService.insertCourseInfo(courseInfo);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("修改课程")
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public RespBody updateCourseInfo(CourseInfo courseInfo) {
    int result = courseInfoService.updateCourseInfo(courseInfo);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("删除课程")
  @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
  public RespBody deleteCourseInfo(@RequestParam("id") int id) {
    int result = courseInfoService.deleteCourseInfo(id);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  //我加的，查询全校课表,先不写参数
  @ApiOperation("查询全校课表")
  @RequestMapping(value = "/list-allCourseList", method = RequestMethod.GET)
  public RespBody findAllCourseList(
      @RequestParam(value = "pageStart", defaultValue = "1") Integer pageStart,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    List<TeacherInfo> allCourseList = courseInfoService.findAllCourseList(pageStart, pageSize);
    PageInfo<TeacherInfo> pageInfo = new PageInfo<>(allCourseList);
    return RespBody.ok(pageInfo);
  }
}

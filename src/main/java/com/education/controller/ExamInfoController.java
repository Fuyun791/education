package com.education.controller;

import com.education.entity.*;

import com.education.service.IExamInfoService;

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
    *  前端控制器
    * </p>
 *
 * @author dell
 * @since 2020-05-25
 */
@Api(tags = "ExamInfoController", description = "考试管理管理")
@RestController
@RequestMapping("/education/exam-info")
public class ExamInfoController {

    private final IExamInfoService examInfoService;

    @Autowired
    public ExamInfoController(IExamInfoService examInfoService) {
        this.examInfoService = examInfoService;
    }

    @ApiOperation("查询考试管理")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RespBody findExamInfo(ExamInfo examInfo,
                                 @RequestParam(value = "pageStart",defaultValue = "1")Integer pageStart,
                                 @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize){
        List<ExamInfo> examInfoList = examInfoService.findExamInfo(examInfo, pageStart, pageSize);
        PageInfo<ExamInfo> pageInfo = new PageInfo<>(examInfoList);
        return RespBody.ok(pageInfo);
    }

    @ApiOperation("添加考试管理")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public RespBody insertExamInfo(ExamInfo examInfo) {
        int result = examInfoService.insertExamInfo(examInfo);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("修改考试管理")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RespBody updateExamInfo(ExamInfo examInfo) {
        int result = examInfoService.updateExamInfo(examInfo);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("删除考试管理")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public RespBody deleteExamInfo(@RequestParam("id")int id) {
        int result = examInfoService.deleteExamInfo(id);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("各科目考试安排列表")
    @RequestMapping(value = "/list-examSubjectPlanList",method = RequestMethod.GET)
    public RespBody findExamInfoList(ExamInfo examInfo,
                                     @RequestParam(value = "pageStart",defaultValue = "1")Integer pageStart,
                                     @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize){
        List<ExamInfo> examInfoList = examInfoService.findExamSubjectPlanList(examInfo,pageStart,pageSize);
        PageInfo<ExamInfo> pageInfo = new PageInfo<>(examInfoList);
        return RespBody.ok(pageInfo);
    }

    @ApiOperation("教室考试安排列表")
    @RequestMapping(value = "/list-examClassRoomPlanList",method = RequestMethod.GET)
    public RespBody findExamClassRoomPlanList(ClassRoomInfo classRoomInfo,
                                              @RequestParam(value = "pageStart",defaultValue = "1")Integer pageStart,
                                              @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize){
        List<ClassRoomInfo> examInfoList = examInfoService.findExamClassRoomPlanList(classRoomInfo,pageStart,pageSize);
        PageInfo<ClassRoomInfo> pageInfo = new PageInfo<>(examInfoList);
        return RespBody.ok(pageInfo);
    }

    @ApiOperation("教师监考安排列表")
    @RequestMapping(value = "/list-examTeacherPlanList",method = RequestMethod.GET)
    public RespBody findExamTeacherPlanList(TeacherInfo teacherInfo,
                                            @RequestParam(value = "pageStart",defaultValue = "1")Integer pageStart,
                                            @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize){
        List<TeacherInfo> examInfoList = examInfoService.findExamTeacherPlanList(teacherInfo,pageStart,pageSize);
        PageInfo<TeacherInfo> pageInfo = new PageInfo<>(examInfoList);
        return RespBody.ok(pageInfo);
    }

    @ApiOperation("学生考试安排列表")
    @RequestMapping(value = "/list-examStudentPlanList",method = RequestMethod.GET)
    public RespBody findExamStudentPlanList(StudentInfo studentInfo,
                                            @RequestParam(value = "pageStart",defaultValue = "1")Integer pageStart,
                                            @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize){
        List<StudentInfo> studentInfoList = examInfoService.findExamStudentPlanList(studentInfo,pageStart,pageSize);
        PageInfo<StudentInfo> pageInfo = new PageInfo<>(studentInfoList);
        return RespBody.ok(pageInfo);
    }
}

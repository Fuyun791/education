package com.education.controller;

import com.education.entity.RespBody;

import com.education.entity.TeacherInfo;
import com.education.service.ITeacherInfoService;

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
 * @since 2020-05-18
 */
@Api(tags = "TeacherInfoController", description = "教师管理")
@RestController
@RequestMapping("/education/teacher-info")
public class TeacherInfoController {

    private final ITeacherInfoService teacherInfoService;

    @Autowired
    public TeacherInfoController(ITeacherInfoService teacherInfoService) {
        this.teacherInfoService = teacherInfoService;
    }

    @ApiOperation("查询教师")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RespBody findTeacherInfo(TeacherInfo teacherInfo,
                                 @RequestParam(value = "pageStart",defaultValue = "1")Integer pageStart,
                                 @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize){
        List<TeacherInfo> teacherInfoList = teacherInfoService.findTeacherInfo(teacherInfo, pageStart, pageSize);
        PageInfo<TeacherInfo> pageInfo = new PageInfo<>(teacherInfoList);
        return RespBody.ok(pageInfo);
    }

    @ApiOperation("查询教师(包含学院，院系)")
    @RequestMapping(value = "/list-teacher", method = RequestMethod.GET)
    public RespBody findTeacherInfoList(TeacherInfo teacherInfo,
                                    @RequestParam(value = "pageStart",defaultValue = "1")Integer pageStart,
                                    @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize){
        List<TeacherInfo> teacherInfoList = teacherInfoService.findTeacherInfoList(teacherInfo, pageStart, pageSize);
        PageInfo<TeacherInfo> pageInfo = new PageInfo<>(teacherInfoList);
        return RespBody.ok(pageInfo);
    }

    @ApiOperation("添加教师")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public RespBody insertTeacherInfo(TeacherInfo teacherInfo) {
        int result = teacherInfoService.insertTeacherInfo(teacherInfo);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("修改教师")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RespBody updateTeacherInfo(TeacherInfo teacherInfo) {
        int result = teacherInfoService.updateTeacherInfo(teacherInfo);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("删除教师")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public RespBody deleteTeacherInfo(@RequestParam("id")int id) {
        int result = teacherInfoService.deleteTeacherInfo(id);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

}

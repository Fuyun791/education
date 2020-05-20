package com.education.controller;

import com.education.entity.RespBody;

import com.education.entity.ClassInfo;
import com.education.service.IClassInfoService;

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
    * 班级表 前端控制器
    * </p>
 *
 * @author dell
 * @since 2020-05-16
 */
@Api(tags = "ClassInfoController", description = "班级管理")
@RestController
@RequestMapping("/education/class-info")
public class ClassInfoController {

    private final IClassInfoService classInfoService;

    @Autowired
    public ClassInfoController(IClassInfoService classInfoService) {
        this.classInfoService = classInfoService;
    }

    @ApiOperation("查询班级")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RespBody findClassInfo(ClassInfo classInfo,
                                 @RequestParam(value = "pageStart",defaultValue = "1")Integer pageStart,
                                 @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize){
        List<ClassInfo> classInfoList = classInfoService.findClassInfo(classInfo, pageStart, pageSize);
        PageInfo<ClassInfo> pageInfo = new PageInfo<>(classInfoList);
        return RespBody.ok(pageInfo);
    }

    @ApiOperation("添加班级")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public RespBody insertClassInfo(ClassInfo classInfo) {
        classInfo.setDataCreate(LocalDateTime.now());
        classInfo.setDataModified(LocalDateTime.now());
        int result = classInfoService.insertClassInfo(classInfo);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("修改班级")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RespBody updateClassInfo(ClassInfo classInfo) {
        classInfo.setDataModified(LocalDateTime.now());
        int result = classInfoService.updateClassInfo(classInfo);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("删除班级")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public RespBody deleteClassInfo(@RequestParam("id")int id) {
        int result = classInfoService.deleteClassInfo(id);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

}

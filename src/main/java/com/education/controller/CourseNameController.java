package com.education.controller;

import com.education.entity.RespBody;

import com.education.entity.CourseName;
import com.education.service.ICourseNameService;

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
 * @since 2020-05-23
 */
@Api(tags = "CourseNameController", description = "课程名管理")
@RestController
@RequestMapping("/education/course-name")
public class CourseNameController {

    private final ICourseNameService courseNameService;

    @Autowired
    public CourseNameController(ICourseNameService courseNameService) {
        this.courseNameService = courseNameService;
    }

    @ApiOperation("查询课程名")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RespBody findCourseName(CourseName courseName,
                                 @RequestParam(value = "pageStart",defaultValue = "1")Integer pageStart,
                                 @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize){
        List<CourseName> courseNameList = courseNameService.findCourseName(courseName, pageStart, pageSize);
        PageInfo<CourseName> pageInfo = new PageInfo<>(courseNameList);
        return RespBody.ok(pageInfo);
    }

    @ApiOperation("添加课程名")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public RespBody insertCourseName(CourseName courseName) {
        int result = courseNameService.insertCourseName(courseName);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("修改课程名")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RespBody updateCourseName(CourseName courseName) {
        int result = courseNameService.updateCourseName(courseName);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("删除课程名")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public RespBody deleteCourseName(@RequestParam("id")int id) {
        int result = courseNameService.deleteCourseName(id);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

}

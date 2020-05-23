package com.education.controller;

import com.education.entity.RespBody;

import com.education.entity.CourseTimeInfo;
import com.education.service.ICourseTimeInfoService;

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
    * 课程时间联系表 前端控制器
    * </p>
 *
 * @author dell
 * @since 2020-05-23
 */
@Api(tags = "CourseTimeInfoController", description = "课程时间管理")
@RestController
@RequestMapping("/education/course-time-info")
public class CourseTimeInfoController {

    private final ICourseTimeInfoService courseTimeInfoService;

    @Autowired
    public CourseTimeInfoController(ICourseTimeInfoService courseTimeInfoService) {
        this.courseTimeInfoService = courseTimeInfoService;
    }

    @ApiOperation("查询课程时间")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RespBody findCourseTimeInfo(CourseTimeInfo courseTimeInfo,
                                 @RequestParam(value = "pageStart",defaultValue = "1")Integer pageStart,
                                 @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize){
        List<CourseTimeInfo> courseTimeInfoList = courseTimeInfoService.findCourseTimeInfo(courseTimeInfo, pageStart, pageSize);
        PageInfo<CourseTimeInfo> pageInfo = new PageInfo<>(courseTimeInfoList);
        return RespBody.ok(pageInfo);
    }

    @ApiOperation("添加课程时间")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public RespBody insertCourseTimeInfo(CourseTimeInfo courseTimeInfo) {
        int result = courseTimeInfoService.insertCourseTimeInfo(courseTimeInfo);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("修改课程时间")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RespBody updateCourseTimeInfo(CourseTimeInfo courseTimeInfo) {
        int result = courseTimeInfoService.updateCourseTimeInfo(courseTimeInfo);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("删除课程时间")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public RespBody deleteCourseTimeInfo(@RequestParam("id")int id) {
        int result = courseTimeInfoService.deleteCourseTimeInfo(id);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

}

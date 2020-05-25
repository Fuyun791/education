package com.education.controller;

import com.education.entity.RespBody;

import com.education.entity.OnlineCourseHour;
import com.education.service.IOnlineCourseHourService;

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
    * 在线课程课时表 前端控制器
    * </p>
 *
 * @author dell
 * @since 2020-05-24
 */
@Api(tags = "OnlineCourseHourController", description = "在线课程课时管理")
@RestController
@RequestMapping("/education/online-course-hour")
public class OnlineCourseHourController {

    private final IOnlineCourseHourService onlineCourseHourService;

    @Autowired
    public OnlineCourseHourController(IOnlineCourseHourService onlineCourseHourService) {
        this.onlineCourseHourService = onlineCourseHourService;
    }

    @ApiOperation("查询在线课程课时")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RespBody findOnlineCourseHour(OnlineCourseHour onlineCourseHour,
                                 @RequestParam(value = "pageStart",defaultValue = "1")Integer pageStart,
                                 @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize){
        List<OnlineCourseHour> onlineCourseHourList = onlineCourseHourService.findOnlineCourseHour(onlineCourseHour, pageStart, pageSize);
        PageInfo<OnlineCourseHour> pageInfo = new PageInfo<>(onlineCourseHourList);
        return RespBody.ok(pageInfo);
    }

    @ApiOperation("添加在线课程课时")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public RespBody insertOnlineCourseHour(OnlineCourseHour onlineCourseHour) {
        int result = onlineCourseHourService.insertOnlineCourseHour(onlineCourseHour);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("修改在线课程课时")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RespBody updateOnlineCourseHour(OnlineCourseHour onlineCourseHour) {
        int result = onlineCourseHourService.updateOnlineCourseHour(onlineCourseHour);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("删除在线课程课时")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public RespBody deleteOnlineCourseHour(@RequestParam("id")int id) {
        int result = onlineCourseHourService.deleteOnlineCourseHour(id);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

}

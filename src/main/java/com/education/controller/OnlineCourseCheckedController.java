package com.education.controller;

import com.education.entity.RespBody;

import com.education.entity.OnlineCourseChecked;
import com.education.service.IOnlineCourseCheckedService;

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
    * 课程审核表 前端控制器
    * </p>
 *
 * @author dell
 * @since 2020-05-24
 */
@Api(tags = "OnlineCourseCheckedController", description = "在线课程审核管理")
@RestController
@RequestMapping("/education/online-course-checked")
public class OnlineCourseCheckedController {

    private final IOnlineCourseCheckedService onlineCourseCheckedService;

    @Autowired
    public OnlineCourseCheckedController(IOnlineCourseCheckedService onlineCourseCheckedService) {
        this.onlineCourseCheckedService = onlineCourseCheckedService;
    }

    @ApiOperation("查询在线课程审核")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RespBody findOnlineCourseChecked(OnlineCourseChecked onlineCourseChecked,
                                 @RequestParam(value = "pageStart",defaultValue = "1")Integer pageStart,
                                 @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize){
        List<OnlineCourseChecked> onlineCourseCheckedList = onlineCourseCheckedService.findOnlineCourseChecked(onlineCourseChecked, pageStart, pageSize);
        PageInfo<OnlineCourseChecked> pageInfo = new PageInfo<>(onlineCourseCheckedList);
        return RespBody.ok(pageInfo);
    }

    @ApiOperation("添加在线课程审核")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public RespBody insertOnlineCourseChecked(OnlineCourseChecked onlineCourseChecked) {
        int result = onlineCourseCheckedService.insertOnlineCourseChecked(onlineCourseChecked);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("修改在线课程审核")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RespBody updateOnlineCourseChecked(OnlineCourseChecked onlineCourseChecked) {
        int result = onlineCourseCheckedService.updateOnlineCourseChecked(onlineCourseChecked);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("删除在线课程审核")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public RespBody deleteOnlineCourseChecked(@RequestParam("id")int id) {
        int result = onlineCourseCheckedService.deleteOnlineCourseChecked(id);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

}

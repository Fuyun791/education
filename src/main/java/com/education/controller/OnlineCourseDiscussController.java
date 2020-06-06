package com.education.controller;

import com.education.entity.RespBody;

import com.education.entity.OnlineCourseDiscuss;
import com.education.service.IOnlineCourseDiscussService;

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
 * @since 2020-05-28
 */
@Api(tags = "OnlineCourseDiscussController", description = "在线课程讨论管理")
@RestController
@RequestMapping("/education/online-course-discuss")
public class OnlineCourseDiscussController {

    private final IOnlineCourseDiscussService onlineCourseDiscussService;

    @Autowired
    public OnlineCourseDiscussController(IOnlineCourseDiscussService onlineCourseDiscussService) {
        this.onlineCourseDiscussService = onlineCourseDiscussService;
    }

    @ApiOperation("查询在线课程讨论")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RespBody findOnlineCourseDiscuss(OnlineCourseDiscuss onlineCourseDiscuss,
                                 @RequestParam(value = "pageStart",defaultValue = "1")Integer pageStart,
                                 @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize){
        List<OnlineCourseDiscuss> onlineCourseDiscussList = onlineCourseDiscussService.findOnlineCourseDiscuss(onlineCourseDiscuss, pageStart, pageSize);
        PageInfo<OnlineCourseDiscuss> pageInfo = new PageInfo<>(onlineCourseDiscussList);
        return RespBody.ok(pageInfo);
    }

    @ApiOperation("添加在线课程讨论")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public RespBody insertOnlineCourseDiscuss(OnlineCourseDiscuss onlineCourseDiscuss,@RequestParam("indexOf")Long indexOf) {
        int result = onlineCourseDiscussService.insertOnlineCourseDiscuss(onlineCourseDiscuss,indexOf);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("修改在线课程讨论")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RespBody updateOnlineCourseDiscuss(OnlineCourseDiscuss onlineCourseDiscuss) {
        int result = onlineCourseDiscussService.updateOnlineCourseDiscuss(onlineCourseDiscuss);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("删除在线课程讨论")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public RespBody deleteOnlineCourseDiscuss(@RequestParam("id")int id) {
        int result = onlineCourseDiscussService.deleteOnlineCourseDiscuss(id);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

}

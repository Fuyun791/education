package com.education.controller;

import com.education.entity.OnlineCourseStar;
import com.education.entity.RespBody;

import com.education.entity.OnlineCourseDiscuss;
import com.education.service.IOnlineCourseDiscussService;

import com.education.service.impl.OnlineCourseStarServiceImpl;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
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

    private final OnlineCourseStarServiceImpl onlineCourseStarService;

    @Autowired
    public OnlineCourseDiscussController(IOnlineCourseDiscussService onlineCourseDiscussService, OnlineCourseStarServiceImpl onlineCourseStarService) {
        this.onlineCourseDiscussService = onlineCourseDiscussService;
        this.onlineCourseStarService = onlineCourseStarService;
    }

    @ApiOperation("根据在线课表id返回其评论区")
    @RequestMapping(value = "/list-discuss", method = RequestMethod.GET)
    public RespBody findOnlineCourseInfo(@RequestParam("onlineCourseId") Long onlineCourseId,
                                         @RequestParam(value = "pageStart", defaultValue = "1") Integer pageStart,
                                         @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                         @RequestParam(value = "model", defaultValue = "time") String model) {
        List<OnlineCourseDiscuss> onlineCourseDiscussList = onlineCourseDiscussService.findDiscussByCourseId(onlineCourseId, pageStart, pageSize, model);
        return RespBody.ok(onlineCourseDiscussList);
    }

    @ApiOperation("根据课程号获取用户点赞的set")
    @RequestMapping(value = "/list-star", method = RequestMethod.GET)
    public RespBody getOnlineCourseStar(String discussPerson, Long onlineCourseId) {
        Set<String> onlineCourseStarList = onlineCourseStarService.getOnlineCourseStar(discussPerson, onlineCourseId);
        return RespBody.ok(onlineCourseStarList);
    }

    @ApiOperation("添加评论,discussToPerson代表在此person下评论，此参数不代表内部的回复")
    @RequestMapping(value = "/add-discuss", method = RequestMethod.POST)
    public RespBody insertCourseDiscuss(OnlineCourseDiscuss onlineCourseDiscuss,
                                        @RequestParam(value = "discussToPersonId", required = false) Integer discussToPersonId) {
        int result = onlineCourseDiscussService.insertOnlineCourseDiscuss(onlineCourseDiscuss, discussToPersonId);
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

    @ApiOperation("进行用户点赞")
    @RequestMapping(value = "/click-star", method = RequestMethod.POST)
    public RespBody insertOnlineCourseStar(OnlineCourseStar onlineCourseStar,
                                           @RequestParam("discussParent") Long discussParent,
                                           @RequestParam("discussPerson") Integer discussPerson,
                                           @RequestParam("onlineCourseId") Long onlineCourseId) {
        int result = onlineCourseStarService.insertOnlineCourseStar(onlineCourseStar, discussParent, discussPerson, onlineCourseId);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("删除在线课程讨论")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public RespBody deleteOnlineCourseDiscuss(@RequestParam("id") int id) {
        int result = onlineCourseDiscussService.deleteOnlineCourseDiscuss(id);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

}

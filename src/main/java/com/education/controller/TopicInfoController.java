package com.education.controller;

import com.education.entity.RespBody;

import com.education.entity.TopicInfo;
import com.education.service.ITopicInfoService;

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
 * @since 2020-06-02
 */
@Api(tags = "TopicInfoController", description = "用户管理")
@RestController
@RequestMapping("/education/topic-info")
public class TopicInfoController {

    private final ITopicInfoService topicInfoService;

    @Autowired
    public TopicInfoController(ITopicInfoService topicInfoService) {
        this.topicInfoService = topicInfoService;
    }

    @ApiOperation("查询用户")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RespBody findTopicInfo(TopicInfo topicInfo,
                                 @RequestParam(value = "pageStart",defaultValue = "1")Integer pageStart,
                                 @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize){
        List<TopicInfo> topicInfoList = topicInfoService.findTopicInfo(topicInfo, pageStart, pageSize);
        PageInfo<TopicInfo> pageInfo = new PageInfo<>(topicInfoList);
        return RespBody.ok(pageInfo);
    }

    @ApiOperation("添加用户")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public RespBody insertTopicInfo(TopicInfo topicInfo) {
        int result = topicInfoService.insertTopicInfo(topicInfo);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("修改用户")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RespBody updateTopicInfo(TopicInfo topicInfo) {
        int result = topicInfoService.updateTopicInfo(topicInfo);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("删除用户")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public RespBody deleteTopicInfo(@RequestParam("id")int id) {
        int result = topicInfoService.deleteTopicInfo(id);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("文章题目、简介、图片列表")
    @RequestMapping(value = "/list-TopicInfoList")
    public RespBody findTopicInfoList(TopicInfo topicInfo){
        List<TopicInfo> topicInfoList = topicInfoService.findTopicInfoList(topicInfo);
        return RespBody.ok(topicInfoList);
    }

    @ApiOperation("文章题目、内容列表")
    @RequestMapping(value = "/list-TopicContentList")
    public RespBody findTopicContentList(TopicInfo topicInfo){
        List<TopicInfo> topicContentList = topicInfoService.findTopicContentList(topicInfo);
        return RespBody.ok(topicContentList);
    }
}

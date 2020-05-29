package com.education.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.education.entity.OnlineCourseDiscuss;
import com.education.entity.OnlineCourseInfo;
import com.education.entity.RespBody;
import com.education.service.IOnlineCourseDiscussService;
import com.education.service.IOnlineCourseInfoService;
import com.education.service.IOnlineEpisodesService;
import com.education.service.IRedisService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author dell
 */
@Api(tags = "CommonController", description = "公共API")
@RestController
@RequestMapping("/education/common")
public class CommonController {

    private final IOnlineCourseInfoService onlineCourseInfoService;

    private final IOnlineEpisodesService onlineEpisodesService;

    private final IOnlineCourseDiscussService onlineCourseDiscussService;

    @Autowired
    public CommonController(IOnlineCourseInfoService onlineCourseInfoService, IOnlineEpisodesService onlineEpisodesService, IOnlineCourseDiscussService onlineCourseDiscussService, IRedisService redisService) {
        this.onlineCourseInfoService = onlineCourseInfoService;
        this.onlineEpisodesService = onlineEpisodesService;
        this.onlineCourseDiscussService = onlineCourseDiscussService;
    }

    @ApiOperation("根据相应状态查询在线课程")
    @RequestMapping(value = "/list-online-course", method = RequestMethod.GET)
    public RespBody findOnlineCourseList(@RequestParam(value = "teacherId",required = false) Integer teacherId,
                                         @RequestParam(value = "isShare",required = false) Boolean isShare,
                                         @RequestParam("collegeId") Long collegeId,
                                         @RequestParam(value = "checkedStatus", required = false) Integer checkedStatus,
                                         @RequestParam(value = "checkedResult", required = false) Boolean checkedResult,
                                         @RequestParam(value = "pageStart", defaultValue = "1")Integer pageState,
                                         @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize) {
        List<OnlineCourseInfo> onlineCourseInfoList = onlineCourseInfoService.findOnlineCourseList(teacherId,isShare,collegeId,checkedStatus,checkedResult,pageState,pageSize);
        PageInfo<OnlineCourseInfo> pageInfo = new PageInfo<>(onlineCourseInfoList);
        return RespBody.ok(pageInfo);
    }

    @ApiOperation("根据在线课表id返回其评论区")
    @RequestMapping(value = "/list-discuss", method = RequestMethod.GET)
    public RespBody findOnlineCourseInfo(@RequestParam("onlineCourseId")Long onlineCourseId,
                                         @RequestParam(value = "pageStart",defaultValue = "1")Integer pageStart,
                                         @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize){
        List<OnlineCourseDiscuss> onlineCourseDiscussList = onlineCourseDiscussService.findDiscussByCourseId(onlineCourseId,pageStart,pageSize);;
        return RespBody.ok(onlineCourseDiscussList);
    }

}

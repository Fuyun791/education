package com.education.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.education.entity.*;
import com.education.service.IOnlineCourseDiscussService;
import com.education.service.IOnlineCourseInfoService;
import com.education.service.IOnlineEpisodesService;
import com.education.service.IRedisService;
import com.education.service.impl.OnlineClassifyServiceImpl;
import com.education.service.impl.OnlineCourseStarServiceImpl;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
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

  private final OnlineClassifyServiceImpl onlineClassifyService;

  @Autowired
  public CommonController(IOnlineCourseInfoService onlineCourseInfoService,
      IOnlineEpisodesService onlineEpisodesService,
      IOnlineCourseDiscussService onlineCourseDiscussService, IRedisService redisService,
      OnlineClassifyServiceImpl onlineClassifyService) {
    this.onlineCourseInfoService = onlineCourseInfoService;
    this.onlineEpisodesService = onlineEpisodesService;
    this.onlineCourseDiscussService = onlineCourseDiscussService;
    this.onlineClassifyService = onlineClassifyService;
  }

  @ApiOperation("查询在线课程分类")
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public RespBody findAllOnlineClassify(OnlineClassify onlineClassify,
      @RequestParam(value = "pageStart", defaultValue = "1") Integer pageStart,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    List<OnlineClassify> onlineClassifyList = onlineClassifyService
        .findOnlineClassify(onlineClassify, pageStart, pageSize);
    PageInfo<OnlineClassify> pageInfo = new PageInfo<>(onlineClassifyList);
    return RespBody.ok(pageInfo);
  }

  @ApiOperation("根据相应状态查询在线课程")
  @RequestMapping(value = "/list-online-course", method = RequestMethod.GET)
  public RespBody findOnlineCourseList(
      @RequestParam(value = "teacherId", required = false) Integer teacherId,
      @RequestParam(value = "isShare", required = false) Boolean isShare,
      @RequestParam(value = "collegeId", required = false) Long collegeId,
      @RequestParam(value = "checkedStatus", required = false) Integer checkedStatus,
      @RequestParam(value = "checkedResult", required = false) Boolean checkedResult,
      @RequestParam(value = "pageStart", defaultValue = "1") Integer pageState,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    List<OnlineCourseInfo> onlineCourseInfoList = onlineCourseInfoService
        .findOnlineCourseList(teacherId, isShare, collegeId, checkedStatus, checkedResult,
            pageState, pageSize);
    PageInfo<OnlineCourseInfo> pageInfo = new PageInfo<>(onlineCourseInfoList);
    return RespBody.ok(pageInfo);
  }

  @ApiOperation("显示内层的评论")
  @RequestMapping(value = "list-discussPerson", method = RequestMethod.GET)
  public RespBody getDiscussByCourseId(@RequestParam(value = "onlineCourseId") Long onlineCourseId,
      @RequestParam(value = "id") Long id,
      @RequestParam("discussPerson") Integer discussPerson,
      @RequestParam(value = "pageStart", defaultValue = "1") Integer pageStart,
      @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
    OnlineCourseDiscuss onlineCourseDiscuss = onlineCourseDiscussService
        .getDiscussByCourseId(onlineCourseId, id, discussPerson, pageStart, pageSize);
    return RespBody.ok(onlineCourseDiscuss);
  }

  @ApiOperation("根据课程id返回其章节")
  @RequestMapping(value = "/list-episodes-hour", method = RequestMethod.GET)
  public RespBody findEpisodesByCourseId(
      @RequestParam(value = "onlineCourseId") Long onlineCourseId) {
    List<OnlineEpisodes> onlineCourseInfoList = onlineEpisodesService
        .findEpisodesByCourseId(onlineCourseId, null);
    return RespBody.ok(onlineCourseInfoList);
  }

}

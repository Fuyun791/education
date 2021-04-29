package com.education.controller;

import com.education.entity.RespBody;

import com.education.entity.OnlineCourseStar;
import com.education.service.IOnlineCourseStarService;

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
 * @since 2020-06-08
 */
@Api(tags = "OnlineCourseStarController", description = "用户点赞管理")
@RestController
@RequestMapping("/education/online-course-star")
public class OnlineCourseStarController {

  private final IOnlineCourseStarService onlineCourseStarService;

  @Autowired
  public OnlineCourseStarController(IOnlineCourseStarService onlineCourseStarService) {
    this.onlineCourseStarService = onlineCourseStarService;
  }

  @ApiOperation("查询用户点赞")
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public RespBody findOnlineCourseStar(OnlineCourseStar onlineCourseStar,
      @RequestParam(value = "pageStart", defaultValue = "1") Integer pageStart,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    List<OnlineCourseStar> onlineCourseStarList = onlineCourseStarService
        .findOnlineCourseStar(onlineCourseStar, pageStart, pageSize);
    PageInfo<OnlineCourseStar> pageInfo = new PageInfo<>(onlineCourseStarList);
    return RespBody.ok(pageInfo);
  }

  @ApiOperation("根据课程号获取用户点赞的set")
  @RequestMapping(value = "/list-star", method = RequestMethod.GET)
  public RespBody getOnlineCourseStar(String discussPerson, Long onlineCourseId) {
    Set<String> onlineCourseStarList = onlineCourseStarService
        .getOnlineCourseStar(discussPerson, onlineCourseId);
    return RespBody.ok(onlineCourseStarList);
  }

  @ApiOperation("修改用户点赞")
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public RespBody updateOnlineCourseStar(OnlineCourseStar onlineCourseStar) {
    int result = onlineCourseStarService.updateOnlineCourseStar(onlineCourseStar);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("删除用户点赞")
  @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
  public RespBody deleteOnlineCourseStar(@RequestParam("id") int id) {
    int result = onlineCourseStarService.deleteOnlineCourseStar(id);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

}

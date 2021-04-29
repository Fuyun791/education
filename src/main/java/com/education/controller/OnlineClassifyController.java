package com.education.controller;

import com.education.entity.RespBody;

import com.education.entity.OnlineClassify;
import com.education.service.IOnlineClassifyService;

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
 * 前端控制器
 * </p>
 *
 * @author dell
 * @since 2020-06-11
 */
@Api(tags = "OnlineClassifyController", description = "在线课程分类管理")
@RestController
@RequestMapping("/education/online-classify")
public class OnlineClassifyController {

  private final IOnlineClassifyService onlineClassifyService;

  @Autowired
  public OnlineClassifyController(IOnlineClassifyService onlineClassifyService) {
    this.onlineClassifyService = onlineClassifyService;
  }

  @ApiOperation("查询在线课程分类")
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public RespBody findOnlineClassify(OnlineClassify onlineClassify,
      @RequestParam(value = "pageStart", defaultValue = "1") Integer pageStart,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    List<OnlineClassify> onlineClassifyList = onlineClassifyService
        .findOnlineClassify(onlineClassify, pageStart, pageSize);
    PageInfo<OnlineClassify> pageInfo = new PageInfo<>(onlineClassifyList);
    return RespBody.ok(pageInfo);
  }

  @ApiOperation("添加在线课程分类")
  @RequestMapping(value = "/insert", method = RequestMethod.POST)
  public RespBody insertOnlineClassify(OnlineClassify onlineClassify) {
    int result = onlineClassifyService.insertOnlineClassify(onlineClassify);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("修改在线课程分类")
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public RespBody updateOnlineClassify(OnlineClassify onlineClassify) {
    int result = onlineClassifyService.updateOnlineClassify(onlineClassify);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("删除在线课程分类")
  @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
  public RespBody deleteOnlineClassify(@RequestParam("id") int id) {
    int result = onlineClassifyService.deleteOnlineClassify(id);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

}

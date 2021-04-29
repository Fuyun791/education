package com.education.controller;

import com.education.entity.RespBody;

import com.education.entity.OnlineEpisodes;
import com.education.service.IOnlineEpisodesService;

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
 * @since 2020-05-24
 */
@Api(tags = "OnlineEpisodesController", description = "在线课程章节管理")
@RestController
@RequestMapping("/education/online-episodes")
public class OnlineEpisodesController {

  private final IOnlineEpisodesService onlineEpisodesService;

  @Autowired
  public OnlineEpisodesController(IOnlineEpisodesService onlineEpisodesService) {
    this.onlineEpisodesService = onlineEpisodesService;
  }

  @ApiOperation("查询在线课程章节")
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public RespBody findOnlineEpisodes(OnlineEpisodes onlineEpisodes,
      @RequestParam(value = "pageStart", defaultValue = "1") Integer pageStart,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    List<OnlineEpisodes> onlineEpisodesList = onlineEpisodesService
        .findOnlineEpisodes(onlineEpisodes, pageStart, pageSize);
    PageInfo<OnlineEpisodes> pageInfo = new PageInfo<>(onlineEpisodesList);
    return RespBody.ok(pageInfo);
  }

  @ApiOperation("添加在线课程章节")
  @RequestMapping(value = "/insert", method = RequestMethod.POST)
  public RespBody insertOnlineEpisodes(OnlineEpisodes onlineEpisodes) {
    int result = onlineEpisodesService.insertOnlineEpisodes(onlineEpisodes);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("修改在线课程章节")
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public RespBody updateOnlineEpisodes(OnlineEpisodes onlineEpisodes) {
    int result = onlineEpisodesService.updateOnlineEpisodes(onlineEpisodes);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("删除在线课程章节")
  @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
  public RespBody deleteOnlineEpisodes(@RequestParam("id") int id) {
    int result = onlineEpisodesService.deleteOnlineEpisodes(id);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

}

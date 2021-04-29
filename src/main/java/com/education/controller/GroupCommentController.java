package com.education.controller;

import com.education.entity.RespBody;

import com.education.entity.GroupComment;
import com.education.service.IGroupCommentService;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dell
 * @since 2020-06-04
 */
@Api(tags = "GroupCommentController", description = "用户管理")
@RestController
@RequestMapping("/education/group-comment")
public class GroupCommentController {

  private final IGroupCommentService groupCommentService;

  @Autowired
  public GroupCommentController(IGroupCommentService groupCommentService) {
    this.groupCommentService = groupCommentService;
  }

  @ApiOperation("查询用户")
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public RespBody findGroupComment(GroupComment groupComment,
      @RequestParam(value = "pageStart", defaultValue = "1") Integer pageStart,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    List<GroupComment> groupCommentList = groupCommentService
        .findGroupComment(groupComment, pageStart, pageSize);
    PageInfo<GroupComment> pageInfo = new PageInfo<>(groupCommentList);
    return RespBody.ok(pageInfo);
  }

  @ApiOperation("添加用户")
  @RequestMapping(value = "/insert", method = RequestMethod.POST)
  public RespBody insertGroupComment(GroupComment groupComment) {
    int result = groupCommentService.insertGroupComment(groupComment);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("修改用户")
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public RespBody updateGroupComment(GroupComment groupComment) {
    int result = groupCommentService.updateGroupComment(groupComment);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("删除用户")
  @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
  public RespBody deleteGroupComment(@RequestParam("id") int id) {
    int result = groupCommentService.deleteGroupComment(id);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("话题评论列表")
  @RequestMapping(value = "/list-GroupCommentList")
  public RespBody findGroupCommentList(GroupComment groupComment) {
    List<GroupComment> groupCommentList = groupCommentService.findGroupCommentList(groupComment);
    return RespBody.ok(groupCommentList);
  }

  @ApiOperation("插入一条评论")
  @RequestMapping(value = "/insertGroupComment")
  public RespBody insertGroupComment(@RequestParam("topicId") int topicId,
      @RequestParam("studentNum") int studentNum,
      @RequestParam("commentContent") String commentContent,
      @RequestParam("dataCreate") String dataCreate,
      @RequestParam("dataModified") String dataModified) {
    int result = groupCommentService
        .insertGroupComment(topicId, studentNum, commentContent, dataCreate, dataModified);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

}

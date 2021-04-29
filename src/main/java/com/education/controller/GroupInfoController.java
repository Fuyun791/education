package com.education.controller;

import com.education.entity.RespBody;

import com.education.entity.GroupInfo;
import com.education.entity.StudentInfo;
import com.education.service.IGroupInfoService;

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
 * @since 2020-06-02
 */
@Api(tags = "GroupInfoController", description = "用户管理")
@RestController
@RequestMapping("/education/group-info")
public class GroupInfoController {

  private final IGroupInfoService groupInfoService;

  @Autowired
  public GroupInfoController(IGroupInfoService groupInfoService) {
    this.groupInfoService = groupInfoService;
  }

  @ApiOperation("查询用户")
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public RespBody findGroupInfo(GroupInfo groupInfo,
      @RequestParam(value = "pageStart", defaultValue = "1") Integer pageStart,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    List<GroupInfo> groupInfoList = groupInfoService.findGroupInfo(groupInfo, pageStart, pageSize);
    PageInfo<GroupInfo> pageInfo = new PageInfo<>(groupInfoList);
    return RespBody.ok(pageInfo);
  }

  @ApiOperation("添加用户")
  @RequestMapping(value = "/insert", method = RequestMethod.POST)
  public RespBody insertGroupInfo(GroupInfo groupInfo) {
    int result = groupInfoService.insertGroupInfo(groupInfo);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("修改用户")
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public RespBody updateGroupInfo(GroupInfo groupInfo) {
    int result = groupInfoService.updateGroupInfo(groupInfo);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("删除用户")
  @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
  public RespBody deleteGroupInfo(@RequestParam("id") int id) {
    int result = groupInfoService.deleteGroupInfo(id);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("智能推荐小组列表")
  @RequestMapping(value = "/list-AdviceGroupList")
  public RespBody findAdviceGroupList(StudentInfo studentInfo) {
    List<StudentInfo> adviceGroupList = groupInfoService.findAdviceGroupList(studentInfo);
    return RespBody.ok(adviceGroupList);
  }

  @ApiOperation("我的关注小组列表")
  @RequestMapping(value = "/list-MyGroupList")
  public RespBody findMyGroupList(StudentInfo studentInfo) {
    List<StudentInfo> myGroupList = groupInfoService.findMyGroupList(studentInfo);
    return RespBody.ok(myGroupList);
  }

  @ApiOperation("取消关注一个小组")
  @RequestMapping(value = "/deleteMyGroup")
  public RespBody deleteMyGroup(@RequestParam("studentNum") int studentNum,
      @RequestParam("groupId") int groupId) {
    int result = groupInfoService.deleteMyGroup(studentNum, groupId);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }
}

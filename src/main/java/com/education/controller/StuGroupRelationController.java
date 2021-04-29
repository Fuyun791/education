package com.education.controller;

import com.education.entity.RespBody;

import com.education.entity.StuGroupRelation;
import com.education.service.IStuGroupRelationService;

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
 * @since 2020-06-05
 */
@Api(tags = "StuGroupRelationController", description = "用户管理")
@RestController
@RequestMapping("/education/stu-group-relation")
public class StuGroupRelationController {

  private final IStuGroupRelationService stuGourpRelationService;

  @Autowired
  public StuGroupRelationController(IStuGroupRelationService stuGourpRelationService) {
    this.stuGourpRelationService = stuGourpRelationService;
  }

  @ApiOperation("查询用户")
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public RespBody findStuGroupRelation(StuGroupRelation stuGourpRelation,
      @RequestParam(value = "pageStart", defaultValue = "1") Integer pageStart,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    List<StuGroupRelation> stuGourpRelationList = stuGourpRelationService
        .findStuGroupRelation(stuGourpRelation, pageStart, pageSize);
    PageInfo<StuGroupRelation> pageInfo = new PageInfo<>(stuGourpRelationList);
    return RespBody.ok(pageInfo);
  }

  @ApiOperation("添加用户")
  @RequestMapping(value = "/insert", method = RequestMethod.POST)
  public RespBody insertStuGroupRelation(StuGroupRelation stuGourpRelation) {
    int result = stuGourpRelationService.insertStuGroupRelation(stuGourpRelation);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("修改用户")
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public RespBody updateStuGroupRelation(StuGroupRelation stuGourpRelation) {
    int result = stuGourpRelationService.updateStuGroupRelation(stuGourpRelation);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("删除用户")
  @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
  public RespBody deleteStuGroupRelation(@RequestParam("id") int id) {
    int result = stuGourpRelationService.deleteStuGroupRelation(id);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("关注一个小组")
  @RequestMapping(value = "/insertMyGroup")
  public RespBody deleteMyGroup(@RequestParam("studentNum") int studentNum,
      @RequestParam("groupId") int groupId,
      @RequestParam("joinTime") String joinTime,
      @RequestParam("dataCreate") String dataCreate,
      @RequestParam("dataModified") String dataModified) {
    int result = stuGourpRelationService
        .insertMyGroup(studentNum, groupId, joinTime, dataCreate, dataModified);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();

  }

  @ApiOperation("查询是否有关注过这个小组")
  @RequestMapping(value = "/list-theGroup")
  public RespBody findTheGroup(@RequestParam("studentNum") int studentNum,
      @RequestParam("groupId") int groupId) {
    List<StuGroupRelation> theGroup = stuGourpRelationService.findTheGroup(studentNum, groupId);
    return RespBody.ok(theGroup);
  }

}

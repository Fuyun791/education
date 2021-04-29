package com.education.controller;

import com.education.entity.RespBody;

import com.education.entity.RolePopedom;
import com.education.service.IRolePopedomService;

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
 * @since 2020-05-11
 */
@Api(tags = "RolePopedomController", description = "角色权限管理")
@RestController
@RequestMapping("/education/role-popedom")
public class RolePopedomController {

  private final IRolePopedomService rolePopedomService;

  @Autowired
  public RolePopedomController(IRolePopedomService rolePopedomService) {
    this.rolePopedomService = rolePopedomService;
  }

  @ApiOperation("查询角色权限")
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public RespBody findRolePopedom(RolePopedom rolePopedom,
      @RequestParam(value = "pageStart", defaultValue = "1") Integer pageStart,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    List<RolePopedom> rolePopedomList = rolePopedomService
        .findRolePopedom(rolePopedom, pageStart, pageSize);
    PageInfo<RolePopedom> pageInfo = new PageInfo<>(rolePopedomList);
    return RespBody.ok(pageInfo);
  }

  @ApiOperation("添加角色权限")
  @RequestMapping(value = "/insert", method = RequestMethod.POST)
  public RespBody insertRolePopedom(RolePopedom rolePopedom) {
    int result = rolePopedomService.insertRolePopedom(rolePopedom);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("修改角色权限")
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public RespBody updateRolePopedom(RolePopedom rolePopedom) {
    int result = rolePopedomService.updateRolePopedom(rolePopedom);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("删除角色权限")
  @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
  public RespBody deleteRolePopedom(@RequestParam("id") int id) {
    int result = rolePopedomService.deleteRolePopedom(id);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

}

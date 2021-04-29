package com.education.controller;

import com.education.entity.RespBody;

import com.education.entity.SigninInfo;
import com.education.service.ISigninInfoService;

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
 * @since 2020-05-31
 */
@Api(tags = "SigninInfoController", description = "班次管理管理")
@RestController
@RequestMapping("/education/signin-info")
public class SigninInfoController {

  private final ISigninInfoService signinInfoService;

  @Autowired
  public SigninInfoController(ISigninInfoService signinInfoService) {
    this.signinInfoService = signinInfoService;
  }

  @ApiOperation("查询班次管理")
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public RespBody findSigninInfo(SigninInfo signinInfo,
      @RequestParam(value = "collegeId") Long collegeId,
      @RequestParam(value = "teacherNumber") Long teacherNumber,
      @RequestParam(value = "pageStart", defaultValue = "1") Integer pageStart,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    List<SigninInfo> signinInfoList = signinInfoService
        .findSigninInfo(signinInfo, collegeId, teacherNumber, pageStart, pageSize);
    PageInfo<SigninInfo> pageInfo = new PageInfo<>(signinInfoList);
    return RespBody.ok(pageInfo);
  }

  @ApiOperation("添加班次管理")
  @RequestMapping(value = "/insert", method = RequestMethod.POST)
  public RespBody insertSigninInfo(SigninInfo signinInfo) {
    int result = signinInfoService.insertSigninInfo(signinInfo);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("修改班次管理")
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public RespBody updateSigninInfo(SigninInfo signinInfo) {
    int result = signinInfoService.updateSigninInfo(signinInfo);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("删除班次管理")
  @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
  public RespBody deleteSigninInfo(@RequestParam("id") int id) {
    int result = signinInfoService.deleteSigninInfo(id);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

}

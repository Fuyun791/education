package com.education.controller;

import com.education.entity.RespBody;

import com.education.entity.SigninStatusInfo;
import com.education.service.ISigninStatusInfoService;

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
@Api(tags = "SigninStatusInfoController", description = "考勤统计管理")
@RestController
@RequestMapping("/education/signin-status-info")
public class SigninStatusInfoController {

  private final ISigninStatusInfoService signinStatusInfoService;

  @Autowired
  public SigninStatusInfoController(ISigninStatusInfoService signinStatusInfoService) {
    this.signinStatusInfoService = signinStatusInfoService;
  }

  @ApiOperation("查询考勤统计")
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public RespBody findSigninStatusInfo(SigninStatusInfo signinStatusInfo,
      @RequestParam(value = "pageStart", defaultValue = "1") Integer pageStart,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    //  传入课程id和日期
    List<SigninStatusInfo> signinStatusInfoList = signinStatusInfoService
        .findSigninStatusInfo(signinStatusInfo, pageStart, pageSize);
    PageInfo<SigninStatusInfo> pageInfo = new PageInfo<>(signinStatusInfoList);
    return RespBody.ok(pageInfo);
  }

  @ApiOperation("添加考勤统计")
  @RequestMapping(value = "/insert", method = RequestMethod.POST)
  public RespBody insertSigninStatusInfo(SigninStatusInfo signinStatusInfo) {
    int result = signinStatusInfoService.insertSigninStatusInfo(signinStatusInfo);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("修改考勤统计")
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public RespBody updateSigninStatusInfo(SigninStatusInfo signinStatusInfo) {
    int result = signinStatusInfoService.updateSigninStatusInfo(signinStatusInfo);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("删除考勤统计")
  @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
  public RespBody deleteSigninStatusInfo(@RequestParam("id") int id) {
    int result = signinStatusInfoService.deleteSigninStatusInfo(id);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

}

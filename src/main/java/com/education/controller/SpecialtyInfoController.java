package com.education.controller;

import com.education.entity.RespBody;

import com.education.entity.SpecialtyInfo;
import com.education.service.ISpecialtyInfoService;

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
 * 专业表 前端控制器
 * </p>
 *
 * @author dell
 * @since 2020-05-16
 */
@Api(tags = "SpecialtyInfoController", description = "专业管理")
@RestController
@RequestMapping("/education/specialty-info")
public class SpecialtyInfoController {

  private final ISpecialtyInfoService specialtyInfoService;

  @Autowired
  public SpecialtyInfoController(ISpecialtyInfoService specialtyInfoService) {
    this.specialtyInfoService = specialtyInfoService;
  }

  @ApiOperation("查询专业")
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public RespBody findSpecialtyInfo(SpecialtyInfo specialtyInfo,
      @RequestParam(value = "pageStart", defaultValue = "1") Integer pageStart,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    List<SpecialtyInfo> specialtyInfoList = specialtyInfoService
        .findSpecialtyInfo(specialtyInfo, pageStart, pageSize);
    PageInfo<SpecialtyInfo> pageInfo = new PageInfo<>(specialtyInfoList);
    return RespBody.ok(pageInfo);
  }

  @ApiOperation("根据其所在的学院 查询专业包含院系")
  @RequestMapping(value = "/list-specialty", method = RequestMethod.GET)
  public RespBody findSpecialtyInfoList(SpecialtyInfo specialtyInfo,
      @RequestParam(value = "collegeId") Integer collegeId,
      @RequestParam(value = "pageStart", defaultValue = "1") Integer pageStart,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    List<SpecialtyInfo> specialtyInfoList = specialtyInfoService
        .findSpecialtyInfoList(specialtyInfo, collegeId, pageStart, pageSize);
    PageInfo<SpecialtyInfo> pageInfo = new PageInfo<>(specialtyInfoList);
    return RespBody.ok(pageInfo);
  }

  @ApiOperation("添加专业")
  @RequestMapping(value = "/insert", method = RequestMethod.POST)
  public RespBody insertSpecialtyInfo(SpecialtyInfo specialtyInfo) {
    int result = specialtyInfoService.insertSpecialtyInfo(specialtyInfo);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("修改专业")
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public RespBody updateSpecialtyInfo(SpecialtyInfo specialtyInfo) {
    int result = specialtyInfoService.updateSpecialtyInfo(specialtyInfo);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("删除专业")
  @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
  public RespBody deleteSpecialtyInfo(@RequestParam("id") int id) {
    int result = specialtyInfoService.deleteSpecialtyInfo(id);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

}

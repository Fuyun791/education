package com.education.controller;

import com.education.entity.RespBody;

import com.education.entity.FacultyInfo;
import com.education.service.IFacultyInfoService;

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
 * 院系表 前端控制器
 * </p>
 *
 * @author dell
 * @since 2020-05-16
 */
@Api(tags = "FacultyInfoController", description = "院系管理")
@RestController
@RequestMapping("/education/faculty-info")
public class FacultyInfoController {

  private final IFacultyInfoService facultyInfoService;

  @Autowired
  public FacultyInfoController(IFacultyInfoService facultyInfoService) {
    this.facultyInfoService = facultyInfoService;
  }

  @ApiOperation("查询院系")
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public RespBody findFacultyInfo(FacultyInfo facultyInfo,
      @RequestParam(value = "pageStart", defaultValue = "1") Integer pageStart,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    List<FacultyInfo> facultyInfoList = facultyInfoService
        .findFacultyInfo(facultyInfo, pageStart, pageSize);
    PageInfo<FacultyInfo> pageInfo = new PageInfo<>(facultyInfoList);
    return RespBody.ok(pageInfo);
  }

  @ApiOperation("添加院系")
  @RequestMapping(value = "/insert", method = RequestMethod.POST)
  public RespBody insertFacultyInfo(FacultyInfo facultyInfo) {
    int result = facultyInfoService.insertFacultyInfo(facultyInfo);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("修改院系")
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public RespBody updateFacultyInfo(FacultyInfo facultyInfo) {
    int result = facultyInfoService.updateFacultyInfo(facultyInfo);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("删除院系")
  @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
  public RespBody deleteFacultyInfo(@RequestParam("id") int id) {
    int result = facultyInfoService.deleteFacultyInfo(id);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

}

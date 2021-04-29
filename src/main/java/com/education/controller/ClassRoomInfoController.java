package com.education.controller;

import com.education.entity.RespBody;

import com.education.entity.ClassRoomInfo;
import com.education.service.IClassRoomInfoService;

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
 * 教室表 前端控制器
 * </p>
 *
 * @author dell
 * @since 2020-05-23
 */
@Api(tags = "ClassRoomInfoController", description = "教室管理")
@RestController
@RequestMapping("/education/class-room-info")
public class ClassRoomInfoController {

  private final IClassRoomInfoService classRoomInfoService;

  @Autowired
  public ClassRoomInfoController(IClassRoomInfoService classRoomInfoService) {
    this.classRoomInfoService = classRoomInfoService;
  }

  @ApiOperation("查询教室")
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public RespBody findClassRoomInfo(ClassRoomInfo classRoomInfo,
      @RequestParam(value = "pageStart", defaultValue = "1") Integer pageStart,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    List<ClassRoomInfo> classRoomInfoList = classRoomInfoService
        .findClassRoomInfo(classRoomInfo, pageStart, pageSize);
    PageInfo<ClassRoomInfo> pageInfo = new PageInfo<>(classRoomInfoList);
    return RespBody.ok(pageInfo);
  }

  @ApiOperation("添加教室")
  @RequestMapping(value = "/insert", method = RequestMethod.POST)
  public RespBody insertClassRoomInfo(ClassRoomInfo classRoomInfo) {
    int result = classRoomInfoService.insertClassRoomInfo(classRoomInfo);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("修改教室")
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public RespBody updateClassRoomInfo(ClassRoomInfo classRoomInfo) {
    int result = classRoomInfoService.updateClassRoomInfo(classRoomInfo);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("删除教室")
  @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
  public RespBody deleteClassRoomInfo(@RequestParam("id") int id) {
    int result = classRoomInfoService.deleteClassRoomInfo(id);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

}

package com.education.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.education.entity.RespBody;

import com.education.entity.AdminInfo;
import com.education.entity.TeacherInfo;
import com.education.service.IAdminInfoService;

import com.education.service.ITeacherInfoService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 主要分主管理员和子管理员 前端控制器
 * </p>
 *
 * @author dell
 * @since 2020-05-10
 */
@Api(tags = "AdminInfoController", description = "权限用户管理")
@RestController
@RequestMapping("/education/admin-info")
public class AdminInfoController {

  /**
   * 采用构造器注入的方式，1.避免循环注入2.非ioc环境也可以通过New实例化对象,缺点增加了不必要的代码
   */
  private final IAdminInfoService adminInfoService;

  private final ITeacherInfoService teacherInfoService;

  @Autowired
  public AdminInfoController(IAdminInfoService adminInfoService,
      ITeacherInfoService teacherInfoService) {
    this.adminInfoService = adminInfoService;
    this.teacherInfoService = teacherInfoService;
  }

  @ApiOperation("查询权限用户")
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public RespBody findAdminInfo(AdminInfo adminInfo,
      @RequestParam(value = "pageStart", defaultValue = "1") Integer pageStart,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    List<AdminInfo> adminInfoList = adminInfoService.findAdminInfo(adminInfo, pageStart, pageSize);
    PageInfo<AdminInfo> pageInfo = new PageInfo<>(adminInfoList);
    return RespBody.ok(pageInfo);
  }

  @ApiOperation("根据用户id查找其所拥有的菜单")
  @RequestMapping(value = "/findByAdminId", method = RequestMethod.GET)
  public RespBody findByAdminId(Long adminId) {
    return RespBody.ok(adminInfoService.findAdminAndPopedomInfoById(adminId));
  }

  @ApiOperation("根据教师id返回teacherInfo")
  @RequestMapping(value = "/findTeacherInfo", method = RequestMethod.GET)
  public RespBody findByTeacherId(@RequestParam("teacherPhone") String teacherPhone) {
    QueryWrapper<TeacherInfo> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("teacher_phone", teacherPhone);
    return RespBody.ok(teacherInfoService.getOne(queryWrapper));
  }

  @ApiOperation("添加权限用户")
  @RequestMapping(value = "/insert", method = RequestMethod.POST)
  public RespBody insertAdminInfo(AdminInfo adminInfo) {
    if (adminInfo.getPassword() != null) {
      int result = adminInfoService.insertAdminInfo(adminInfo);
      if (result == 1) {
        return RespBody.ok();
      }
    }
    return RespBody.error();
  }

  @ApiOperation("修改权限用户")
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public RespBody updateAdminInfo(AdminInfo adminInfo) {
    if (adminInfo.getPassword() != null) {
      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      String password = passwordEncoder.encode(adminInfo.getPassword().trim());
      adminInfo.setPassword(password);
    }
    adminInfo.setDataModified(LocalDateTime.now());
    int result = adminInfoService.updateAdminInfo(adminInfo);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

  @ApiOperation("删除权限用户")
  @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
  public RespBody deleteAdminInfo(@RequestParam("id") int id) {
    int result = adminInfoService.deleteAdminInfo(id);
    if (result == 1) {
      return RespBody.ok();
    }
    return RespBody.error();
  }

}

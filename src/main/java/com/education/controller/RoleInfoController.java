package com.education.controller;

import com.education.entity.RespBody;

import com.education.entity.RoleInfo;
import com.education.service.IRoleInfoService;

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
    *  前端控制器
    * </p>
 *
 * @author dell
 * @since 2020-05-10
 */
@Api(tags = "RoleInfoController", description = "角色管理")
@RestController
@RequestMapping("/education/role-info")
public class RoleInfoController {

    @Autowired
    private IRoleInfoService roleInfoService;

    @ApiOperation("查询角色")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RespBody findRoleInfo(RoleInfo roleInfo,
                                 @RequestParam(value = "pageStart",defaultValue = "1")Integer pageStart,
                                 @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize){
        List<RoleInfo> roleInfoList = roleInfoService.findRoleInfo(roleInfo, pageStart, pageSize);
        PageInfo<RoleInfo> pageInfo = new PageInfo<>(roleInfoList);
        return RespBody.ok(pageInfo);
    }

    @ApiOperation("添加角色")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public RespBody insertRoleInfo(RoleInfo roleInfo) {
        int result = roleInfoService.insertRoleInfo(roleInfo);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("修改角色")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RespBody updateRoleInfo(RoleInfo roleInfo) {
        int result = roleInfoService.updateRoleInfo(roleInfo);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("删除角色")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public RespBody deleteRoleInfo(@RequestParam("id")int id) {
        int result = roleInfoService.deleteRoleInfo(id);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

}

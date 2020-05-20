package com.education.controller;

import com.education.entity.RespBody;

import com.education.entity.PopedomInfo;
import com.education.service.IPopedomInfoService;

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
    * 访问权限表 前端控制器
    * </p>
 *
 * @author dell
 * @since 2020-05-10
 */
@Api(tags = "PopedomInfoController", description = "访问权限管理")
@RestController
@RequestMapping("/education/popedom-info")
public class PopedomInfoController {

    private final IPopedomInfoService popedomInfoService;

    @Autowired
    public PopedomInfoController(IPopedomInfoService popedomInfoService) {
        this.popedomInfoService = popedomInfoService;
    }

    @ApiOperation("查询访问权限")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RespBody findPopedomInfo(PopedomInfo popedomInfo,
                                 @RequestParam(value = "pageStart",defaultValue = "1")Integer pageStart,
                                 @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize){
        List<PopedomInfo> popedomInfoList = popedomInfoService.findPopedomInfo(popedomInfo, pageStart, pageSize);
        PageInfo<PopedomInfo> pageInfo = new PageInfo<>(popedomInfoList);
        return RespBody.ok(pageInfo);
    }

    @ApiOperation("添加访问权限")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public RespBody insertPopedomInfo(PopedomInfo popedomInfo) {
        int result = popedomInfoService.insertPopedomInfo(popedomInfo);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("修改访问权限")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RespBody updatePopedomInfo(PopedomInfo popedomInfo) {
        int result = popedomInfoService.updatePopedomInfo(popedomInfo);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("删除访问权限")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public RespBody deletePopedomInfo(@RequestParam("id")int id) {
        int result = popedomInfoService.deletePopedomInfo(id);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

}

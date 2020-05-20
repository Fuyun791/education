package com.education.controller;

import com.education.entity.RespBody;

import com.education.entity.CollegeInfo;
import com.education.service.ICollegeInfoService;

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
 * @since 2020-05-16
 */
@Api(tags = "CollegeInfoController", description = "院校管理")
@RestController
@RequestMapping("/education/college-info")
public class CollegeInfoController {

    private final ICollegeInfoService collegeInfoService;

    @Autowired
    public CollegeInfoController(ICollegeInfoService collegeInfoService) {
        this.collegeInfoService = collegeInfoService;
    }

    @ApiOperation("查询院校")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RespBody findCollegeInfo(CollegeInfo collegeInfo,
                                 @RequestParam(value = "pageStart",defaultValue = "1")Integer pageStart,
                                 @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize){
        List<CollegeInfo> collegeInfoList = collegeInfoService.findCollegeInfo(collegeInfo, pageStart, pageSize);
        PageInfo<CollegeInfo> pageInfo = new PageInfo<>(collegeInfoList);
        return RespBody.ok(pageInfo);
    }

    @ApiOperation("添加院校")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public RespBody insertCollegeInfo(CollegeInfo collegeInfo) {
        int result = collegeInfoService.insertCollegeInfo(collegeInfo);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("修改院校")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RespBody updateCollegeInfo(CollegeInfo collegeInfo) {
        int result = collegeInfoService.updateCollegeInfo(collegeInfo);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("删除院校")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public RespBody deleteCollegeInfo(@RequestParam("id")int id) {
        int result = collegeInfoService.deleteCollegeInfo(id);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

}

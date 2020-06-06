package com.education.controller;

import com.education.entity.OnlineEpisodes;
import com.education.entity.RespBody;

import com.education.entity.OnlineCourseInfo;
import com.education.service.IOnlineCourseInfoService;

import com.education.service.IOnlineEpisodesService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
    * 在线课程表，外键暂不添加 前端控制器
    * </p>
 *
 * @author dell
 * @since 2020-05-24
 */
@Api(tags = "OnlineCourseInfoController", description = "在线课程管理")
@RestController
@RequestMapping("/education/online-course-info")
public class OnlineCourseInfoController {

    private final IOnlineCourseInfoService onlineCourseInfoService;

    private final IOnlineEpisodesService onlineEpisodesService;

    @Autowired
    public OnlineCourseInfoController(IOnlineCourseInfoService onlineCourseInfoService, IOnlineEpisodesService onlineEpisodesService) {
        this.onlineCourseInfoService = onlineCourseInfoService;
        this.onlineEpisodesService = onlineEpisodesService;
    }

    @ApiOperation("查询在线课程")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RespBody findOnlineCourseInfo(OnlineCourseInfo onlineCourseInfo,
                                         @RequestParam(value = "pageStart",defaultValue = "1")Integer pageStart,
                                         @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize){
        List<OnlineCourseInfo> onlineCourseInfoList = onlineCourseInfoService.findOnlineCourseInfo(onlineCourseInfo, pageStart, pageSize);
        PageInfo<OnlineCourseInfo> pageInfo = new PageInfo<>(onlineCourseInfoList);
        return RespBody.ok(pageInfo);
    }

    @ApiOperation("根据相应状态查询在线课程")
    @RequestMapping(value = "/list-online-course", method = RequestMethod.GET)
    public RespBody findOnlineCourseList(@RequestParam(value = "teacherId",required = false) Integer teacherId,
                                         @RequestParam(value = "isShare",required = false) Boolean isShare,
                                         @RequestParam("collegeId") Long collegeId,
                                         @RequestParam(value = "checkedStatus", required = false) Integer checkedStatus,
                                         @RequestParam(value = "checkedResult", required = false) Boolean checkedResult,
                                         @RequestParam(value = "pageStart", defaultValue = "1")Integer pageState,
                                         @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize) {
        List<OnlineCourseInfo> onlineCourseInfoList = onlineCourseInfoService.findOnlineCourseList(teacherId,isShare,collegeId,checkedStatus,checkedResult,pageState,pageSize);
        PageInfo<OnlineCourseInfo> pageInfo = new PageInfo<>(onlineCourseInfoList);
        return RespBody.ok(pageInfo);
    }

    @ApiOperation("根据课程id返回其章节")
    @RequestMapping(value = "/list-episodes-hour", method = RequestMethod.GET)
    public RespBody findEpisodesByCourseId(@RequestParam(value = "onlineCourseId")Long onlineCourseId, @RequestParam("collegeId")Long collegeId) {
        List<OnlineEpisodes> onlineCourseInfoList = onlineEpisodesService.findEpisodesByCourseId(onlineCourseId,collegeId);
        return RespBody.ok(onlineCourseInfoList);
    }

    @ApiOperation("添加在线课程")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public RespBody insertOnlineCourseInfo(OnlineCourseInfo onlineCourseInfo) {
        onlineCourseInfo.setDataCreate(LocalDateTime.now());
        onlineCourseInfo.setDataModified(LocalDateTime.now());
        int result = onlineCourseInfoService.insertOnlineCourseInfo(onlineCourseInfo);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("修改在线课程")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RespBody updateOnlineCourseInfo(OnlineCourseInfo onlineCourseInfo) {
        onlineCourseInfo.setDataModified(LocalDateTime.now());
        int result = onlineCourseInfoService.updateOnlineCourseInfo(onlineCourseInfo);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("删除在线课程")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public RespBody deleteOnlineCourseInfo(@RequestParam("id")int id) {
        int result = onlineCourseInfoService.deleteOnlineCourseInfo(id);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

}

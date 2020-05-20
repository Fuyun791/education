package com.education.controller;

import com.education.entity.RespBody;
import com.education.entity.WebLog;
import com.education.service.IWebLogService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author dell
 */
@Api(tags = "WebLogController", description = "日志管理")
@RestController
@RequestMapping("/education/web-log")
public class WebLogController {

    private final IWebLogService webLogService;

    @Autowired
    public WebLogController(IWebLogService webLogService) {
        this.webLogService = webLogService;
    }

    @ApiOperation("查询日志")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RespBody findAdminInfo(WebLog webLog,
                                  @RequestParam(value = "pageStart",defaultValue = "1")Integer pageStart,
                                  @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize){
        List<WebLog> adminInfoList = webLogService.selectLog(webLog, pageStart, pageSize);
        PageInfo<WebLog> pageInfo = new PageInfo<>(adminInfoList);
        return RespBody.ok(pageInfo);
    }

}

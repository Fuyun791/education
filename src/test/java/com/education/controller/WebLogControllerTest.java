package com.education.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.education.entity.WebLog;
import com.education.service.IWebLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class WebLogControllerTest {

    @Autowired
    private IWebLogService webLogService;

    @Test
    void findAdminInfo() {
        WebLog webLog = new WebLog();
        int pageStart = 0;
        int pageSize = 10;
        List<WebLog> adminInfoList = webLogService.selectLog(webLog, pageStart, pageSize);
        System.out.println(JSON.toJSONString(adminInfoList, SerializerFeature.PrettyFormat));
    }
}
package com.education.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.education.entity.AdminInfo;
import com.education.entity.RespBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AdminInfoControllerTest {

    @Autowired
    private AdminInfoController adminInfoController;

    @Test
    void insertAdminInfo() {
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setAdminName("管理员测试");
        adminInfo.setAdminNumber(2);
        adminInfo.setPassword("123456");
        adminInfo.setPhone("15054213549");
        adminInfo.setEmail("164835021528@qq.com");
        adminInfo.setRoleId(2L);
        adminInfo.setCollegeId(1);
        assertEquals(adminInfoController.insertAdminInfo(adminInfo).getStatus(), RespBody.ok().getStatus());
    }

    @Test
    void findAdminInfo() {
        System.out.println(JSON.toJSONString(adminInfoController.findAdminInfo(new AdminInfo(),0,10), SerializerFeature.PrettyFormat));
    }

    @Test
    void updateAdminInfo() {
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setId(4L);
        adminInfo.setPassword("1357902486");
        assertEquals(adminInfoController.updateAdminInfo(adminInfo).getStatus(), RespBody.ok().getStatus());
    }
}
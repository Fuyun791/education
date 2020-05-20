package com.education.service.impl;

import com.education.entity.PopedomInfo;
import com.education.service.IPopedomInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PopedomInfoServiceImplTest {

    @Autowired
    private IPopedomInfoService popedomInfoService;

    @Test
    void findPopedomMenuByRoleId() {
        List<PopedomInfo> popedomInfoList = popedomInfoService.findPopedomMenuByRoleId(2L);
        System.out.println();
    }
}
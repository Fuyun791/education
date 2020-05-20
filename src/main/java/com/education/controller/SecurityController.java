package com.education.controller;

import com.education.entity.RespBody;
import com.education.entity.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dell
 */
@Api(tags = "SecurityController", description = "Security接口")
@RestController
@RequestMapping("/education/security-info")
public class SecurityController {

    @ApiOperation("要求登录")
    @RequestMapping(value = "/needLogin", method = RequestMethod.GET)
    public RespBody needLogin() {
        return RespBody.error(ResultCode.UNAUTHORIZED);
    }

}

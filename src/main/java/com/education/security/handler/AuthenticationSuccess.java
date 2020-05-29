package com.education.security.handler;

import com.alibaba.fastjson.JSON;
import com.education.entity.RespBody;
import com.education.entity.SecurityUserDetails;
import com.education.service.IRedisService;
import com.education.until.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author dell
 */
@Component
public class AuthenticationSuccess extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private IRedisService redisService;

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out=response.getWriter();
        UserDetails selfUserDetails = (UserDetails) authentication.getPrincipal();
        SecurityUserDetails securityUserDetails = (SecurityUserDetails) selfUserDetails;
        if (securityUserDetails.getRoleId() == 4L) {
            StringBuilder stringBuilder = new StringBuilder();
            String jwtToken = UUID.randomUUID().toString().replace("-", "");
            redisService.set("Authorization:" + jwtToken, selfUserDetails.getUsername(),10L, TimeUnit.DAYS);
            stringBuilder.append(securityUserDetails.getCollegeId());
            stringBuilder.append("_");
            stringBuilder.append(jwtToken);
            out.write(JSON.toJSONString(RespBody.ok(stringBuilder.toString())));
        } else {
            // 创建 token ，并返回 ，设置过期时间为 3600 为1小时
            String jwtToken = JwtTokenUtil.generateToken(selfUserDetails.getUsername(), 3600);
            out.write(JSON.toJSONString(RespBody.ok(jwtToken)));
        }
        out.flush();
        out.close();
        //super.handle(request, response, authentication);
    }
}

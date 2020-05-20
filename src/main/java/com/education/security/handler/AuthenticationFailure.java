package com.education.security.handler;

import com.alibaba.fastjson.JSON;
import com.education.entity.RespBody;
import com.education.entity.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author dell
 */
@Component
public class AuthenticationFailure extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(AuthenticationFailure.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        String userId = request.getParameter("username");
        String password = request.getParameter("password");
        logger.info("有人使用" + userId + "使用密码" + password + "尝试了登录");
        PrintWriter out = response.getWriter();
        RespBody respBody = RespBody.error(ResultCode.VALIDATE_FAILED);
        out.write(JSON.toJSONString(respBody));
        out.flush();
        out.close();
    }
}

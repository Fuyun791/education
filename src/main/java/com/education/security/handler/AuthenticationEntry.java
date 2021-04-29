package com.education.security.handler;

import com.alibaba.fastjson.JSON;
import com.education.entity.RespBody;
import com.education.entity.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 没有进行登录认证
 *
 * @author dell
 */
@Component
public class AuthenticationEntry implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException e) throws IOException, ServletException {
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");
    response.getWriter().println(JSON.toJSONString(RespBody.error(ResultCode.UNAUTHORIZED)));
    response.getWriter().flush();
  }
}

package com.education.security.handler;

import com.alibaba.fastjson.JSON;
import com.education.entity.RespBody;
import com.education.entity.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 无权限访问
 *
 * @author dell
 */
@Component
public class AuthenticationDenied implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException e) throws IOException, ServletException {
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Cache-Control", "no-cache");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");
    PrintWriter out = response.getWriter();
    out.println(JSON.toJSONString(RespBody.error(ResultCode.FORBIDDEN)));
    out.flush();
    out.close();
  }

}

package com.education.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 全局异常,暂不使用
 * @author dell
 */
@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public RespBody error(Exception e) {
//        e.printStackTrace();
//        return RespBody.error();
//    }
}
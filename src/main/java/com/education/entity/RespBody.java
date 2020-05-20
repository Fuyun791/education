package com.education.entity;

import lombok.Builder;
import lombok.Getter;

/**
 * @author dell
 */
@Builder(toBuilder = true)
@Getter
public class RespBody {

    private Integer status;
    private String msg;
    private Object result;

    public static RespBody ok() {
        return new RespBody(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    public static RespBody ok(Object obj) {
        return new RespBody(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), obj);
    }

    public static RespBody error() {
        return new RespBody(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMessage(), null);
    }

    public static RespBody error(ResultCode resultCode) {
        return new RespBody(resultCode.getCode(), resultCode.getMessage(), null);
    }

    public static RespBody unauthorized() {
        return new RespBody(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), null);
    }

    public static RespBody validateError() {
        return error(ResultCode.VALIDATE_FAILED);
    }

    public static RespBody forbidden() {
        return new RespBody(ResultCode.FORBIDDEN.getCode(),ResultCode.FORBIDDEN.getMessage(),null);
    }


    public RespBody() {
    }

    private RespBody(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.result = obj;
    }

}

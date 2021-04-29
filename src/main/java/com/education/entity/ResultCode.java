package com.education.entity;

public enum ResultCode {
  /**
   * 枚举类 定义常规操作的返回参数
   */
  SUCCESS(200, "操作成功"),

  FAILED(500, "操作失败"),

  VALIDATE_FAILED(404, "请输入正确的账号或密码"),

  UNAUTHORIZED(401, "暂未登录或token已经过期"),

  FORBIDDEN(403, "没有相关权限");


  private Integer code;

  private String message;

  ResultCode(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  public Integer getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
package com.distribute.exception;

import com.distribute.constant.ApiExceptionCode;

public class ApiException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private Integer code;

  public ApiException(Integer code, String msg) {
    super(msg);
    this.code = code;
  }

  public ApiException(String msg) {
    super(msg);
    this.code = ApiExceptionCode.NOT_FOUND.getValue();
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

}
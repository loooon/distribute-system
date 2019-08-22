package com.distribute.exception;


import com.distribute.constant.ApiExceptionCode;

public class ResourceNotFoundException extends ApiException {

  private static final long serialVersionUID = 1L;

  public ResourceNotFoundException(String msg) {
    super(ApiExceptionCode.RESOURCE_NOT_FOUND.getValue(), msg);
  }
}
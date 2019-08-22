package com.distribute.handler;

import com.distribute.exception.ApiException;
import com.distribute.response.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

  private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ResponseBody
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public ResponseMessage handleException(HttpServletRequest request, Exception ex) {
    if (ex instanceof ApiException) {
      log.warn(ex.getMessage(), ex);
      ApiException apiException = (ApiException) ex;
      return new ResponseMessage(apiException.getCode(), apiException.getMessage());
    } else {
      log.error(ex.getMessage(), ex);
      return new ResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }
  }
}
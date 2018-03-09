package org.yyf.springBootDemo.aop;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

  @ExceptionHandler(RuntimeException.class)
  @ResponseBody
  public String handleAllRunEx(RuntimeException e) {
    log.error("server error",e);
    return e.getMessage();
  }
}

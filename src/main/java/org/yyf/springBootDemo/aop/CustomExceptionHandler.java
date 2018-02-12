package org.yyf.springBootDemo.aop;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(RuntimeException.class)
  @ResponseBody
  public String handleAllRunEx(RuntimeException e) {
    return e.getMessage();
  }
}

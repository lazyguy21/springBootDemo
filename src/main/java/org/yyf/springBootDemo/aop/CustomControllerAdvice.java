package org.yyf.springBootDemo.aop;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class CustomControllerAdvice {

  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler(BindException.class)
  public String haa(BindException e) {
    List<FieldError> fieldErrors = e.getFieldErrors();
    fieldErrors.forEach(item -> item.toString());
    log.error("error occurs !", e);
    return "error occurs";
  }
}

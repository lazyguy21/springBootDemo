package org.yyf.springBootDemo.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.yyf.springBootDemo.domain.RequestDomain;

/**
 * Created by tobi on 2017/7/19.
 */
@Service
public class ValidateService {

  public void v2(@Validated RequestDomain requestDomain) {
    System.out.println("hello!");
  }
}

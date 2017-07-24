package org.yyf.springBootDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yyf.springBootDemo.domain.RequestDomain;
import org.yyf.springBootDemo.domain.User;
import org.yyf.springBootDemo.service.ValidateService;

import javax.validation.constraints.NotNull;

/**
 * Created by tobi on 2017/4/14.
 */
@RequestMapping
@RestController
@Validated
public class ValidateController {
  @Autowired
  ValidateService validateService;

  @GetMapping("/user/validate")
  public User user(@Validated @NotNull(message = "用户名不能为空！") String src) {
    User user = new User();
    user.setId(123L);
    user.setName("zhangshan");
    return user;
  }

  @GetMapping("/v")
  public User user(@Validated RequestDomain requestDomain) {
    User user = new User();
    user.setId(123L);
    user.setName("zhangshan");
    return user;
  }

  @GetMapping("/v2")
  public User v2(RequestDomain requestDomain) {
    System.out.println("in controller");
    validateService.v2(requestDomain);
    return null;
  }
}

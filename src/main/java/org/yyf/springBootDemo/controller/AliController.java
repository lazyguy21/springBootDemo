package org.yyf.springBootDemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by @author yyf on 2018/8/19.
 */
@RestController
@RequestMapping("/bc_funder")
public class AliController {
  @RequestMapping("/t")
  public void t(HttpServletRequest request, String a) {
    System.out.println(request.getParameterMap());
    System.out.println(a);
  }

}

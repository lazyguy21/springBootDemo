package org.yyf.springBootDemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

  @RequestMapping("/ex")
  public String a(){
    RuntimeException a = new RuntimeException("aMessage");
    RuntimeException b = new RuntimeException("bMessage", a);
    RuntimeException c = new RuntimeException("cMessage", b);
    throw c;
  }


}

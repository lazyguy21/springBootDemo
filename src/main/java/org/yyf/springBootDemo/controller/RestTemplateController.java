package org.yyf.springBootDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.yyf.springBootDemo.domain.DiscountRequestBO;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tobi on 2017/3/21.
 */
@RequestMapping("/rt")
@RestController
public class RestTemplateController {

  @Autowired
  RestTemplate restTemplate;

  @RequestMapping("/c")
  public void c() {
    ResponseEntity<String> result =
        restTemplate.getForEntity("http://localhost:8080/api/alone_funder/hello?src=111阿道夫1", String.class);
    String body = result.getBody();
    System.out.println(body);
  }

  @RequestMapping("/c2")
  public void c2() {
    Map param = new HashMap();
    param.put("src", 1111);
    param.put("src", 2222);
    param.put("name", "阿道夫asdfasd");
    param.put("age", 10);
    ResponseEntity<String> result =
        restTemplate.getForEntity("http://localhost:8080/api/alone_funder/p12?name={name}&age={age}", String.class,param );
    String body = result.getBody();
    System.out.println(body);
  }

  @RequestMapping("/p")
  public void p() {
    ResponseEntity<String> result =
        restTemplate.postForEntity("http://localhost:8080/api/alone_funder/p", new Date().toString(), String.class);
    String body = result.getBody();
    System.out.println(body);
  }

  @RequestMapping("/p2")
  public void p2() {
    DiscountRequestBO object = new DiscountRequestBO();
    object.setApplyAmount(new BigDecimal("1111111"));
    object.setOperateUserId(11111L);
    object.setTradePassword("12312asf打发斯蒂芬");
    String s = JSON.toJSONString(object);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    HttpEntity<String> entity = new HttpEntity<>(s, headers);
    ResponseEntity<String> result =
        restTemplate.postForEntity("http://localhost:8080/api/alone_funder/p", entity, String.class);
    String body = result.getBody();
    System.out.println(body);
  }
}

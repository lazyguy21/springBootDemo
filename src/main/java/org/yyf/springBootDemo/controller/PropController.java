package org.yyf.springBootDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yyf.springBootDemo.propDemo.TestProperties;

/**
 * Created by tobi on 2017/4/6.
 */
@RestController
@RequestMapping("/prop")
public class PropController {
    @Autowired
    Environment env;
    @Value("${a}")
    String a;
    @Autowired
    TestProperties testProperties;
    @RequestMapping("/list")
    public void test(){
        String aFromEnv = env.getRequiredProperty("a");
        System.out.println("aFromEnv: " + aFromEnv);
        System.out.println("aFromValue: " + a);
        System.out.println(testProperties);
        System.out.println(testProperties.getInner());
    }
}

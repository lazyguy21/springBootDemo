package org.yyf.springBootDemo.controller;

import com.google.common.collect.Maps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yyf.springBootDemo.domain.ColorEnum;
import org.yyf.springBootDemo.domain.User;
import org.yyf.springBootDemo.service.MockRMIService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by lazyguy on 2016-5-20.
 */
@RestController
@RequestMapping("test")
public class TestController {
    private static final Logger logger = LogManager.getLogger();
    //    @Autowired
//    private TestService testService;
    @Autowired
    private MockRMIService mockRMIService;

    @Value("${myname}")
    private String myname;

    @Value("${myAge}")
    private Integer  myAge;

    @RequestMapping("value")
    public Map value(){
        HashMap<Object, Object> result = Maps.newHashMap();
        result.put("myname", myname);
        result.put("myage", myAge);
        return result;
    }
    @RequestMapping("ex")
    public void ex() {

        RuntimeException runtimeException = new RuntimeException("some exception occurs!");
        logger.error("ahaha", runtimeException);
        logger.info("asdfasdf", runtimeException);
        throw runtimeException;

    }

    @RequestMapping("test1")
    public String test(HttpServletRequest httpServletRequest) {
//        testService.test();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }

    @RequestMapping("log")
    public void log() {
        logger.trace("trace info");
        logger.debug("debug info");
        logger.info("info  info");
        logger.warn("warn info");
        logger.error("error info");
        logger.fatal("fatal info");
    }

    @RequestMapping("user")
    public User user(HttpServletRequest httpServletRequest) {
        User user = new User(111L, "yyf", new Date(), true);
        user.setColorEnum(ColorEnum.blue);
        return user;
    }

    @RequestMapping("date")
    public void date(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {
        System.out.println(date);
    }

    @RequestMapping("au")
    public User user(User user, String name2) {
        System.out.println(user);
        System.out.println(name2);
        return user;
    }

    @RequestMapping("rmi")
    public void rmi() {
        mockRMIService.sleep2();
//        mockRMIService.sleepDouble();
        System.out.println("haha");
        System.out.println("zaihaha");
        mockRMIService.sleep1();
        mockRMIService.sleep2();
    }
}

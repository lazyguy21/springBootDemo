package org.yyf.springBootDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yyf.springBootDemo.springevent.MyEvent;

/**
 * Created by tobi on 2017/4/20.
 */
@RestController
public class EventController {
    @Autowired
    ApplicationContext applicationContext;
    @GetMapping("/event")
    public void send(){
        MyEvent myEvent = new MyEvent(applicationContext);
        myEvent.setData("some info,haha");
        applicationContext.publishEvent(myEvent);
    }
}

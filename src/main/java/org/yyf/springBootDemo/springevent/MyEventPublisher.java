package org.yyf.springBootDemo.springevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import javax.annotation.PostConstruct;

/**
 * Created by tobi on 2017/4/20.
 */

public class MyEventPublisher implements ApplicationEventPublisherAware {
    ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    ApplicationContext applicationContext;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
    @PostConstruct
    public void init(){
        MyEvent myEvent = new MyEvent(applicationContext);
        myEvent.setData("some useful info");
        applicationEventPublisher.publishEvent(myEvent);

    }
}

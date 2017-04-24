package org.yyf.springBootDemo.springevent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;

/**
 * Created by tobi on 2017/4/20.
 */
@Slf4j
public class MyEventListener implements ApplicationListener<MyEvent>{
    @Override
    public void onApplicationEvent(MyEvent event) {
        log.info("from MyEventListener :" + event);
        log.info("event type: "+event.getClass());
    }
    @EventListener
    public void someMethod(ApplicationEvent event) {
        log.info("event :"+event);
    }
}

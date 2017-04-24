package org.yyf.springBootDemo.springevent;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by tobi on 2017/4/20.
 */

public class AllEventListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("received a event: "+event.getClass());
    }
}

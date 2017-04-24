package org.yyf.springBootDemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yyf.springBootDemo.springevent.MyEventListener;
import org.yyf.springBootDemo.springevent.MyEventPublisher;

/**
 * Created by tobi on 2017/4/20.
 */
@Configuration
public class SpringEventConfig {
    @Bean
    public MyEventPublisher eventPublisher(){
        return new MyEventPublisher();
    }
    @Bean
    public MyEventListener myEventListener(){
       return new MyEventListener();
    }
}

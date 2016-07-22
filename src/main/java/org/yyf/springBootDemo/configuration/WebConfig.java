package org.yyf.springBootDemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.yyf.springBootDemo.aop.TestInterceptor;

/**
 * Created by tobi on 16-7-22.
 */
@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
    @Bean
    public TestInterceptor getTestInterceptor(){
        return new TestInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getTestInterceptor());
    }
}

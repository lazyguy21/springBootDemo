package org.yyf.springBootDemo.configuration;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.yyf.springBootDemo.aop.ExceptionFilter;
import org.yyf.springBootDemo.aop.TestInterceptor;
import org.yyf.springBootDemo.aop.TraceLogFilter;
import org.yyf.springBootDemo.web.component.RequestLogFilter;

import java.util.List;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by tobi on 16-7-22.
 */
@EnableWebMvc
@Configuration
@EnableSwagger2
public class WebConfig extends WebMvcConfigurerAdapter{
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
//            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
            "My Project's REST API",
            "This is a description of your API.",
            "API TOS",
            "url",
            "me@wherever.com",
            "API License",
            "API License URL");
        return apiInfo;
    }

    @Bean
    public TestInterceptor getTestInterceptor(){
        return new TestInterceptor();
    }



//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();//2
//
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(
////                SerializerFeature.PrettyFormat,
////                SerializerFeature.WriteClassName,
//                SerializerFeature.WriteEnumUsingToString,
//                SerializerFeature.WriteNullListAsEmpty,
//                SerializerFeature.WriteNullStringAsEmpty
//        );
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//
//        HttpMessageConverter<?> converter = fastConverter;
//        converters.add(converter);
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getTestInterceptor());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/txt/**").addResourceLocations("classpath:/static/");

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    @Bean
    public FilterRegistrationBean someFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(exceptionFilter());
        registration.addUrlPatterns("/filterEx");
//        registration.addInitParameter("paramName", "paramValue");
        registration.setName("exceptionFilter");
        registration.setOrder(1);
        return registration;
    }
    @Bean
    public FilterRegistrationBean traceLogFilter() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new TraceLogFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(0);
        return registration;
    }
    @Bean
    public FilterRegistrationBean requestLogFilter() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RequestLogFilter());
        registration.addUrlPatterns("/*");
//        registration.addInitParameter("paramName", "paramValue");
        registration.setOrder(1);
        return registration;
    }



    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setIncludeHeaders(true);
        return loggingFilter;
    }



    @Bean
    public ExceptionFilter exceptionFilter(){
        ExceptionFilter exceptionFilter = new ExceptionFilter();
        return exceptionFilter;
    }
}

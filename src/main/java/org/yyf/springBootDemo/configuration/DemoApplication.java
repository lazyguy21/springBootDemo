package org.yyf.springBootDemo.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.yyf.springBootDemo.propDemo.TestProperties;

import com.google.common.collect.Lists;

import java.util.List;

@SpringBootApplication(scanBasePackages = "org.yyf.springBootDemo")
@EnableTransactionManagement
@MapperScan("org.yyf.springBootDemo.dao")
@PropertySource("classpath:test.properties")
@EnableConfigurationProperties(TestProperties.class)
public class DemoApplication {
	@Autowired
	List<ClientHttpRequestInterceptor> interceptorList;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		RestTemplate restTemplate = restTemplateBuilder.build();
		restTemplate.setInterceptors(interceptorList);
		return restTemplate;
	}
}

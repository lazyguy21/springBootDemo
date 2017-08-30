package org.yyf.springBootDemo.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.yyf.springBootDemo.propDemo.TestProperties;

@SpringBootApplication(scanBasePackages = "org.yyf.springBootDemo")
@EnableTransactionManagement
@MapperScan("org.yyf.springBootDemo.dao")
@PropertySource("classpath:test.properties")
@EnableConfigurationProperties(TestProperties.class)
public class DemoApplication {
  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
}

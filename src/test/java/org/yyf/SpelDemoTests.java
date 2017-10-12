package org.yyf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.yyf.springBootDemo.configuration.DemoApplication;
import org.yyf.springBootDemo.domain.User;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@WebAppConfiguration
public class SpelDemoTests {

  @Value("#{sPELDomain.id}")
  Long id;

  private String name;
  private Map<String, Integer> classGradeMap;
  private List<Integer> intList;
  private int[] intArray;
  private User user;

  @Autowired
  ApplicationContext ctx;
  ExpressionParser parser = new SpelExpressionParser();


  @Test
  public void test(){
    System.out.println(id);
  }

  @Test
  public void getBean() throws Exception {
    String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
    Object bean = parser.parseExpression("@sPELDomain").getValue(ctx);
    System.out.println(bean);
  }
}

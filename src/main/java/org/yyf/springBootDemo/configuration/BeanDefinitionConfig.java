package org.yyf.springBootDemo.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.yyf.springBootDemo.domain.User;

@Configuration
public  class BeanDefinitionConfig implements BeanDefinitionRegistryPostProcessor {
  @Autowired
  ApplicationContext ctx;

  @Override
  public void postProcessBeanFactory(final ConfigurableListableBeanFactory beanFactory) throws BeansException {
    GenericBeanDefinition beanDefinition = getGenericBeanDefinition();

    beanDefinition.getPropertyValues().add("friend", beanFactory.getBean("dynamicUser2"));
    ((DefaultListableBeanFactory) beanFactory)
        .registerBeanDefinition("dynamicUser", beanDefinition);
  }

  private GenericBeanDefinition getGenericBeanDefinition() {
    GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
    beanDefinition.setBeanClass(User.class);
    beanDefinition.getPropertyValues().add("name", "张三");
    return beanDefinition;
  }



  @Override
  public void postProcessBeanDefinitionRegistry(final BeanDefinitionRegistry registry) throws BeansException {
    GenericBeanDefinition beanDefinition = getGenericBeanDefinition();
    registry.registerBeanDefinition("dynamicUser2",beanDefinition);
  }



}

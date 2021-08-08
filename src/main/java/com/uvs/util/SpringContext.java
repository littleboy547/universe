package com.uvs.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContext implements ApplicationContextAware {

  private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) throws BeansException {
    SpringContext.applicationContext = applicationContext;
  }

  public static Object getBean(String name) throws BeansException {
    return applicationContext.getBean(name);
  }
}

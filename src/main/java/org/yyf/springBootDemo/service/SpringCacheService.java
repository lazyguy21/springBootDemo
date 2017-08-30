package org.yyf.springBootDemo.service;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.yyf.springBootDemo.domain.User;

import java.util.Date;

@Service
public class SpringCacheService {

//  @Cacheable(value = {"userPrefix","user"},sync = true)
  @Cacheable(value = {"userPrefix"},sync = true)
  public User get(Long userId) {
    System.out.println("mock from DB");
    User user = new User(userId, "xiaoli", new Date(), true);
    return user;
  }
  @CachePut(value = "userPrefix",key = "#user.id")
  public void put(User user) {
    System.out.println("mock put user in db");
  }
}

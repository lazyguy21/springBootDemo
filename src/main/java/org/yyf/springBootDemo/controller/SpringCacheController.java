package org.yyf.springBootDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yyf.springBootDemo.domain.User;
import org.yyf.springBootDemo.service.SpringCacheService;

import java.util.Date;

@RestController
public class SpringCacheController {
  @Autowired
  RedisTemplate redisTemplate;
  @Autowired
  SpringCacheService springCacheService;

  @RequestMapping("baseDemo/{id}")
  public void t1(Long id){
    User user = new User(id, "xiaoli",new Date(), true);
    redisTemplate.opsForValue().set("user:"+user.getId(),user);

  }

  @RequestMapping("redisGet")
  public void redisGet(){
    User testKey = (User) redisTemplate.opsForValue().get("testKey");
    System.out.println(testKey);
  }


  @RequestMapping("cacheGet")
  public User cacheGet(Long uid){
    User user = springCacheService.get(uid);
    return user;
  }

  @CachePut(value = {"userPrefix","user"},key = "#user.id")
  @PostMapping("userPut")
  public void put(@RequestBody User user) {
    System.out.println("mock put user in db");
  }


  @PostMapping("userPut2")
  public void put2(@RequestBody User user) {
    springCacheService.put(user);
  }

  @PostMapping("userEvictById")
  @CacheEvict(value="userPrefix",key="#userId")
  public void userEvictById(Long userId) {
  }

  @PostMapping("userEvictAll")
  @CacheEvict(value="userPrefix",allEntries = true)
  public void userEvictAll() {

  }
}

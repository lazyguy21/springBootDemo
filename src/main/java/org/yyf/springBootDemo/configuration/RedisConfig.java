package org.yyf.springBootDemo.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.google.common.collect.Maps;

import java.util.HashMap;

@Configuration
@EnableCaching
public class RedisConfig {
  @Bean
  public JedisConnectionFactory redisConnectionFactory() {
    JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
    // Defaults
    redisConnectionFactory.setHostName("localhost");
    redisConnectionFactory.setPort(6379);
    return redisConnectionFactory;
  }

  @Bean
  public RedisSerializer redisSerializer() {
    return new GenericJackson2JsonRedisSerializer();
//    return new GenericJackson2JsonRedisSerializer();
  }

  @Bean
  public RedisTemplate redisTemplate(RedisConnectionFactory cf,RedisSerializer redisSerializer) {
    RedisTemplate redisTemplate = new RedisTemplate();
    redisTemplate.setConnectionFactory(cf);
    redisTemplate.setDefaultSerializer(redisSerializer);
//    redisTemplate.setValueSerializer(redisSerializer);
//    redisTemplate.setHashValueSerializer(redisSerializer);
    return redisTemplate;
  }

  @Bean
  public CacheManager cacheManager(RedisTemplate redisTemplate){
    RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
    redisCacheManager.setUsePrefix(true);
    HashMap<String, Long> expireMap = Maps.newHashMap();
    expireMap.put("user", 100L);
    expireMap.put("userPrefix", 1000L);
    redisCacheManager.setExpires(expireMap);
    return redisCacheManager;
  }
}

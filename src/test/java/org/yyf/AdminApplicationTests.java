package org.yyf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.yyf.springBootDemo.configuration.DemoApplication;
import org.yyf.springBootDemo.domain.User;
import org.yyf.springBootDemo.service.SpringCacheService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class AdminApplicationTests {
  protected final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  SpringCacheService springCacheService;

  /**
   * 多线程并发测试
   */
  @Test
  public void multiThreads() throws Exception {
    int number = 3; //线程数
    ExecutorService executorService = Executors.newFixedThreadPool(number);

    List<Future<User>> results = new ArrayList<>();
    Long userId = 2635812L;

    for (int i = 0; i < number; i++) { //非阻塞地启动number个线程，由Future接收结果
      Future<User> future = executorService.submit(new MyThread(userId));
      //Thread.sleep(300);
      results.add(future);
    }

    for (Future<User> f : results) { //从Future中获取结果，打印出来
      User user = f.get();
      logger.info(user.toString());
    }
  }

  class MyThread implements Callable<User> {
    Long userId;

    public MyThread(Long userId) {
      this.userId = userId;
    }

    @Override
    public User call() throws Exception {
      return springCacheService.get(userId);
    }
  }
}
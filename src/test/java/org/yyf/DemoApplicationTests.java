package org.yyf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import org.yyf.springBootDemo.configuration.DemoApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@WebAppConfiguration
public class DemoApplicationTests {
	@Autowired
	RestTemplate restTemplate;
	@Test
	public void contextLoads() {
	}
	@Test
	public void testRestTemplate(){
		String url = "http://localhost:8080/book";
		String result = restTemplate.getForObject(url, String.class);
		System.out.println(result);
	}

	@Test
	public void testPost() throws Exception {
	}
}

package org.yyf.springBootDemo.configuration.component;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;

import java.io.IOException;
import java.net.URI;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by @author yyf on 2018/8/10.
 */
@Component
@Slf4j
public class SignatureInterceptor implements ClientHttpRequestInterceptor {
  @Override
  public ClientHttpResponse intercept(final HttpRequest request, final byte[] body,
      final ClientHttpRequestExecution execution)
      throws IOException {
    String url = request.getURI().toString();
    log.info("url:{}",url);
    String signature = SignatureUtil.sign(url.getBytes(Charsets.UTF_8));
    request.getHeaders().add("signature",signature);
    ClientHttpResponse response = execution.execute(request, body);
    return response;
  }
}

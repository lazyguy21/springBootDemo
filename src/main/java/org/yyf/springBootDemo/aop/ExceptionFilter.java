package org.yyf.springBootDemo.aop;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Objects;

public class ExceptionFilter implements Filter {
  @Override
  public void init(final FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
      throws IOException, ServletException {
    String servletPath = ((HttpServletRequest) request).getServletPath();
    if (Objects.equals(servletPath, "/filterEx")) {
      throw new RuntimeException("this is a exception from filter");
    }
    chain.doFilter(request,response);

  }

  @Override
  public void destroy() {

  }
}

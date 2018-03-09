package org.yyf.springBootDemo.aop;

import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

public class TraceLogFilter extends OncePerRequestFilter {
  private static String TRACE_LOG_ID = "trace-log-id";

  @Override
  protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
      final FilterChain filterChain) throws ServletException, IOException {
    String traceLogId = getTraceLogId();
    MDC.put(TRACE_LOG_ID, traceLogId);
    request.setAttribute(TRACE_LOG_ID, traceLogId);
    response.setHeader(TRACE_LOG_ID, traceLogId);
    try {
      filterChain.doFilter(request, response);
    } finally {
      MDC.remove(TRACE_LOG_ID);
    }
  }

  private String getTraceLogId() {
    return UUID.randomUUID().toString();
  }
}

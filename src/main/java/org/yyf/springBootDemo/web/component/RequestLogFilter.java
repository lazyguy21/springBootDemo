package org.yyf.springBootDemo.web.component;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.base.Stopwatch;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestLogFilter extends OncePerRequestFilter {
  private Boolean includeQueryString = true;
  private Boolean includeClientInfo = true;
  private Boolean includeHeaders = true;
  private Boolean includeRequestBody = true;

  public void setIncludeQueryString(final Boolean includeQueryString) {
    this.includeQueryString = includeQueryString;
  }

  public void setIncludeClientInfo(final Boolean includeClientInfo) {
    this.includeClientInfo = includeClientInfo;
  }

  public void setIncludeHeaders(final Boolean includeHeaders) {
    this.includeHeaders = includeHeaders;
  }

  @Override
  protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
      final FilterChain filterChain) throws ServletException, IOException {
    Stopwatch stopwatch = Stopwatch.createStarted();
    HttpServletRequest requestToUse = request;
    if (logRequestBody(requestToUse)) {
      requestToUse = new InputStreamRequestWrapper(request);
    }
    String requestMessageInfo = createMessage(requestToUse);
    try {
      filterChain.doFilter(requestToUse, response);
    } finally {
      long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
      log.info("Request Info=== {}  execution elapsed=== {}ms", requestMessageInfo, elapsed);
    }
  }



  public final boolean isContentTypeOfLog(final HttpServletRequest request) {
    return isJson(request) || isForm(request);

  }

  public Boolean  isJson(final HttpServletRequest request) {
    String contentType = request.getContentType();
    if (contentType != null) {
      contentType = contentType.toLowerCase();
      return contentType.startsWith(MediaType.APPLICATION_JSON_VALUE);
    } else {
      return false;
    }
  }

  public Boolean  isForm(final HttpServletRequest request) {
    String contentType = request.getContentType();
    if (contentType != null) {
      contentType = contentType.toLowerCase();
      return contentType.startsWith(MediaType.APPLICATION_FORM_URLENCODED_VALUE);
    } else {
      return false;
    }
  }

  public Boolean logRequestBody(final HttpServletRequest request) {
    String method = request.getMethod().toUpperCase();
    Boolean shouldLogMethod = HttpMethod.POST.matches(method) || HttpMethod.PUT.matches(method);
    return isContentTypeOfLog(request) && includeRequestBody && shouldLogMethod;
  }

  protected String createMessage(HttpServletRequest request) {
    StringBuilder msg = new StringBuilder();
    msg.append("[ ");
    msg.append("method=").append(request.getMethod());
    msg.append(";uri=").append(request.getRequestURI());

    if (includeQueryString) {
      String queryString = request.getQueryString();
      if (queryString != null) {
        msg.append('?').append(queryString);
      }
    }

    if (includeClientInfo) {
      String client = request.getRemoteAddr();
      if (StringUtils.hasLength(client)) {
        msg.append(";client=").append(client);
      }
      HttpSession session = request.getSession(false);
      if (session != null) {
        msg.append(";session=").append(session.getId());
      }
      String user = request.getRemoteUser();
      if (user != null) {
        msg.append(";user=").append(user);
      }
    }

    if (includeHeaders) {
      msg.append(";headers=").append(new ServletServerHttpRequest(request).getHeaders());
    }

    if (logRequestBody(request)) {
      if (isJson(request)) {
        msg.append(";requestBody=").append(((InputStreamRequestWrapper) request).getRequestBodyString());
      } else if (isForm(request)) {
        msg.append(":request parameterMap:{");

        Map<String, String[]> parameterMap = request.getParameterMap();
        parameterMap.entrySet().forEach(entrySet->{
          msg.append(entrySet.getKey()).append("=").append(Arrays.toString(entrySet.getValue())).append(",");
        });

      }
    }
    msg.append(" ]");
    return msg.toString();
  }
}

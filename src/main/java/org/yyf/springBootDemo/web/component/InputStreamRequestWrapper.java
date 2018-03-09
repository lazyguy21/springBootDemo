package org.yyf.springBootDemo.web.component;

import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class InputStreamRequestWrapper extends HttpServletRequestWrapper {
  private byte[] requestBody;
  private ServletInputStream inputStream;
  private BufferedReader reader;

  public InputStreamRequestWrapper(final HttpServletRequest request) throws IOException {
    super(request);
    requestBody = ByteStreams.toByteArray(request.getInputStream());
//    inputStream = new InputStreamExtractInputStream();
    inputStream = new ProxyServletInputStream(new ByteArrayInputStream(requestBody));
  }

  public byte[] getRequestBody() {
    return requestBody;
  }

  public String getRequestBodyString() {
    return new String(getRequestBody(), Charset.forName(getCharacterEncoding()));
  }

  @Override
  public ServletInputStream getInputStream() throws IOException {
    if (inputStream != null) {
      return inputStream;
    }
    return super.getInputStream();
  }

  @Override
  public BufferedReader getReader() throws IOException {
    if (reader == null) {
      reader = new BufferedReader(new InputStreamReader(inputStream, getCharacterEncoding()));
    }
    return reader;
  }

  @Override
  public String getCharacterEncoding() {
    String enc = super.getCharacterEncoding();
    return (enc != null ? enc : Charsets.UTF_8.toString());
  }

  private class InputStreamExtractInputStream extends ServletInputStream {

    private int lastIndexRetrieved = -1;
    private ReadListener readListener = null;

    @Override
    public boolean isFinished() {
      return (lastIndexRetrieved == requestBody.length - 1);
    }

    @Override
    public boolean isReady() {
      // This implementation will never block
      // We also never need to call the readListener from this method, as this method will never return false
      return isFinished();
    }

    @Override
    public void setReadListener(ReadListener readListener) {
      this.readListener = readListener;
      if (!isFinished()) {
        try {
          readListener.onDataAvailable();
        } catch (IOException e) {
          readListener.onError(e);
        }
      } else {
        try {
          readListener.onAllDataRead();
        } catch (IOException e) {
          readListener.onError(e);
        }
      }
    }

    @Override
    public int read() throws IOException {
      int i;
      if (!isFinished()) {
        i = requestBody[lastIndexRetrieved + 1];
        lastIndexRetrieved++;
        if (isFinished() && (readListener != null)) {
          try {
            readListener.onAllDataRead();
          } catch (IOException ex) {
            readListener.onError(ex);
            throw ex;
          }
        }
        return i;
      } else {
        return -1;
      }
    }
  }
}

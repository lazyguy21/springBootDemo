package org.yyf.springBootDemo.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ConfigurationProperties("myCustom.structureProperty")
public class DemoProperties {

  private boolean isEnabled;

  private InetAddress remoteAddress;

  private final Security security = new Security();

  public static class Security {

    private String username;

    private String password;

    private List<String> roles = new ArrayList<>(Collections.singleton("USER"));

  }
}

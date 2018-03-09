package org.yyf.springBootDemo.domain;

public interface EnglishChineseLabel {
  String zhLabel = "";
  String enLabel = "";

  default String getZhLabel() {

    return "";
  }

  String getEnLabel();
}

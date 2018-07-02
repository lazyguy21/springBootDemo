package org.yyf.springBootDemo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.List;

public enum ComputerState implements CodeInsideEnum {
  OPEN(10),
  //开启
  CLOSE(11),
  //关闭
  OFF_LINE(12),
  //离线
  FAULT(200),
  //故障
  UNKNOWN(255);     //未知

  private int code;

  ComputerState(int code) {
    this.code = code;
  }

  @JsonCreator
  public static ComputerState valueOf(int code) {
    ComputerState computerState = CodeEnumUtil.codeOf(ComputerState.class, code);
    return computerState;
  }

  public static void main(String[] args) throws IllegalAccessException, InstantiationException {
    boolean assignableFrom1 = CodeInsideEnum.class.isAssignableFrom(ComputerState.class);
    boolean assignableFrom2 = ArrayList.class.isAssignableFrom(List.class);
    System.out.println(assignableFrom);
    System.out.println(assignableFrom1);
    System.out.println(assignableFrom2);
//    System.out.println(ComputerState.class.newInstance() instanceof (CodeInsideEnum.class));
  }

  @JsonValue
  @Override
  public int getCode() {
    return this.code;
  }
}
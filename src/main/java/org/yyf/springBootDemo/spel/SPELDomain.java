package org.yyf.springBootDemo.spel;

import org.springframework.stereotype.Component;
import org.yyf.springBootDemo.domain.User;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Component(value = "sPELDomain")
@Data
public class SPELDomain {
  private Long id;
  private String name;
  private Map<String, Integer> classGradeMap;
  private List<Integer> intList;
  private int[] intArray;
  private User user;

  public SPELDomain() {
    this.id=123L;
    name = "adf";
    classGradeMap = Maps.newHashMap();
    classGradeMap.put("classA", 1);
  }

  public static void hello() {
    System.out.println("hello!");
  }
}

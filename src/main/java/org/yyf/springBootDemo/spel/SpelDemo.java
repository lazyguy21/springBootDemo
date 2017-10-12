package org.yyf.springBootDemo.spel;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpelDemo {
  public static void main(String[] args) {
    ExpressionParser parser = new SpelExpressionParser();
//    int year = (Integer) parser.parseExpression("Birthdate.Year + 1900").getValue(context);

  }
}

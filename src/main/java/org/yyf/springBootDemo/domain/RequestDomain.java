package org.yyf.springBootDemo.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by tobi on 2017/7/18.
 */
public class RequestDomain {
  @NotNull(message = "id 不能为空")
  private Long id;
  @NotEmpty(message = "name 不能为空")
  private String name;

  @Min(value = 18,message = "年纪必须大于18")
  private Integer age;

}

package org.yyf.springBootDemo.domain;


import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by @author yyf on 2018/4/24.
 * 贴现
 */
@Data
@ApiModel(description = "申请贴现所需参数")
public class DiscountRequestBO {
  @ApiModelProperty(value = "tap主键")
  @NotNull
  private Long tapId;
  @ApiModelProperty(value = "tapNo")
  @NotNull
  private String tapNo;
  @ApiModelProperty(value = "供应商申请贴现的金额")
  private BigDecimal applyAmount;

  @ApiModelProperty(value = "交易密码,只在贴现审批被资金方拒绝时需要")
  private String tradePassword;

  @ApiModelProperty(value = "验证码,只在申请贴现，审批成功时需要")
  private String verifyCode;

  private Long operateUserId;

  @ApiModelProperty(hidden = true, value = "安心签签章需要使用的参数，调用验证码时的返回值")
  private String projectCode;

  public Map toMap() {
    Map map = new HashMap();
    map.put("tapId", tapId);
    map.put("tapNo", tapNo);
    map.put("applyAmount", applyAmount);
    map.put("tradePassword", tradePassword);
    map.put("verifyCode", verifyCode);
    map.put("operateUserId", operateUserId);
    map.put("projectCode", projectCode);
    return map;
  }
}

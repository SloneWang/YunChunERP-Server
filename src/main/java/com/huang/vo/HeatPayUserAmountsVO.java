package com.huang.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HeatPayUserAmountsVO {
    //正在执行操作的操作员
    private String username;
    //缴费用户的id
    private int id;
    //缴费金额
    private BigDecimal payAmount;
}

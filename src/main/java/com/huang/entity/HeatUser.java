package com.huang.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@TableName(value="heat_user")
public class HeatUser {
    @TableId(type = IdType.AUTO)
    private int id;
    private String name;
    private String phone;
    private String district;
    private String account;
    private BigDecimal heatArea;
    private Date date;
    private String payStatus;
    private int failPay;
    private BigDecimal userBalance;
    private BigDecimal payAmount;

}

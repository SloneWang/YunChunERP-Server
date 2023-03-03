package com.huang.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class HeatUserDTO {
    //数据库id
    private int id;
    //用户姓名
    private String name;
    //用户电话
    private String phone;
    //用户小区编号，如“130827兆丰润景小区”
    private String district;
    //户号，如:602
    private String account;
    //供热面积
    private BigDecimal heatArea;
    //需缴费金额
    BigDecimal payAmount;
    //缴费截止时间
    Date payDate;
    //缴费状态
    private String payStatus;
    //用户信用状态标识
    private String userStatus;
    //用户余额
    private BigDecimal userBalance;
}

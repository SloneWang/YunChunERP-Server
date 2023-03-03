package com.huang.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class UpdatePayPlanVO {
    //主键
    private Integer id;
    //修改员工编号，即当前登录的员工编号
    private String modifyBy;
    //设置付款金额(本次付款截止日期后生效)
    private BigDecimal amountPlan;
    //未支付金额
    private BigDecimal amountNotPaid;
    //负责员工编号(可设置此词条将该回款计划交给其他员工负责)
    private String employeeNo;
    //付款周期
    private Integer payCycle;
}

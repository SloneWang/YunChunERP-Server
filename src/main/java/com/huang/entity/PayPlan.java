package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@TableName(value = "pay_plan")
public class PayPlan {

    @TableId(type = IdType.AUTO)
    //主键
    private Integer id;
    //与该回款计划关联的合同编号
    private String contractNo;
    //回款状态
    private String payStatus;
    //本次付款截止日期
    private Date payDate;
    //已支付的回款
    private BigDecimal amountPaid;
    //账户余额或欠款(欠款用负数表示)
    private BigDecimal balance;
    //本次付款应付金额
    private BigDecimal amountOnce;
    //逾期次数
    private Integer lateTimes;
    //未支付的回款
    private BigDecimal amountNotPaid;
    //负责员工编号
    private String employeeNo;
    //创建日期
    private Date payCreateDate;
    //付款周期(月)
    private Integer payCycle;
    //设置付款金额(本次付款截止日期后生效)
    private BigDecimal amountPlan;
}

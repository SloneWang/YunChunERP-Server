package com.huang.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class SaveContractVO {

    private String contractNumber;
    private String invoiceType;
    private String employeeNo;
//    private String  reviewerNo;
//    private String  reviewerName;
//    private String  reviewerComment;
    private String  customerCompany;
    private String  customerName;
    private String  customerPhonenum;
    private String  secCustomerName;
    private String  secCustomerPhonenum;
    private String installAddress;
    private String deliveryMethod;
    private BigDecimal upAmount;
    //客户所属行业
    private String industry;
    //客户职务
    private String customerJob;
    //渠道来源
    private String channel;
    private Integer productNumber;
    private Integer payCycle;
    private Date payDate;
    private BigDecimal amountPlan;
    private String productNo;
}

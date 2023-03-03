package com.huang.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class UpdateContractVO {
    private Integer id;
    private String modifyBy;
    private String invoiceType;
    //    private String  reviewerNo;
//    private String  reviewerName;
//    private String  reviewerComment;
    private String  customerCompany;
    private String  customerName;
    private String  customerPhonenum;
    private String  secCustomerName;
    private String  secCustomerPhonenum;
    private String installAddress;
    //    private String contractDocument;
    private String deliveryMethod;
    private String industry;
    private String customerJob;
    private String channel;
    private BigDecimal upAmount;
    private Integer productNumber;
    private String productNo;

    private Date estimatedInstallDate;
    private Date installDate;
    private BigDecimal deliveryFee;
    private BigDecimal installFee;
}

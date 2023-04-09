package com.huang.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class UpdateContractVO {
    //主键
    private Integer id;
    //修改人
    private String modifyBy;
    //发票类型
    private String invoiceType;
    //安装地址
    private String installAddress;
    //送货方式
    private String deliveryMethod;
    //负责员工
    private String employeeNo;
    //订金
    private BigDecimal signFee;
    //提货收款
    private BigDecimal pickFee;
    //安装收款
    private BigDecimal installFee;
    //质保收款
    private BigDecimal warrantyFee;
    //质保时间（月）
    private Integer warrantyPeriod;
    //修改原因
    private String updateReason;
}

package com.huang.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class UpdateContractVO {
    private Integer id;
    private String modifyBy;
    private String invoiceType;
    private String installAddress;
    private String deliveryMethod;
    private Date estimatedInstallDate;
    private Date installDate;
}

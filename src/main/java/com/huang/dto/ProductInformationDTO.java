package com.huang.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInformationDTO {
    //主键
    private Integer id;
    //产品名称
    private String productName;
    //产品编号
    private String productNo;
    //价格
    private BigDecimal productPrice;
    //最低加价率
    private BigDecimal lowestAddRate;

}

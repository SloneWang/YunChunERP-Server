package com.huang.vo;

import com.huang.entity.MaterialList;
import com.huang.entity.ProductList;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Data
public class SaveContractVO {
    //会员编号
    private String memberNo;
    //待审核信息(不超过500字)
    private String requestComment;
    //发票类型
    private String invoiceType;
    //当前员工编号
    private String employeeNo;
    //安装地址
    private String installAddress;
    //送货方式“自提”或者“送货”
    private String deliveryMethod;
    //订金
    private BigDecimal signFee;
    //提货收款
    private BigDecimal pickFee;
    //安装收款
    private BigDecimal installFee;
    //质保时间（月）
    private Integer warrantyPeriod;
    //质保收款
    private BigDecimal warrantyFee;
    //合同税率
    private BigDecimal contractTax;
    //订购材料列表
    private List<MaterialList> contractMaterial;
    //订购产品列表
    private List<ProductList> contractProduct;
    //总基价
    private BigDecimal totalBasePrice;
    //总售价
    private BigDecimal totalSalePrice;
    //安装费加价率
    private BigDecimal installAddRate;
}

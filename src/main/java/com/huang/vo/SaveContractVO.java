package com.huang.vo;

import com.huang.entity.ContractProduct;
import com.huang.entity.ProductInformation;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Data
public class SaveContractVO {

    //合同编号
    private String contractNumber;
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
    //加价金额
    private BigDecimal upAmount;
    //还款周期
    private Integer payCycle;
    //第一次还款日
    private Date payDate;
    //每次还款金额
    private BigDecimal amountPlan;
    //产品列表
    private List<ContractProduct> contractProducts;
}

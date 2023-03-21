package com.huang.dto;

import com.huang.entity.ContractProduct;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Data
public class AllContractDTO {
    private Integer id;
    //合同编号
    private String contractNumber;
    //签约日期
    private Date signDate;
    //发票类型
    private String invoiceType;
    //创建员工编号
    private String employeeNo ;
    //创建员工姓名
    private String  employeeName;
    //审核人编号
    private String  reviewerNo;
    //审核人姓名
    private String  reviewerName;
    //客户公司
    private String  customerCompany;
    //客户姓名
    private String  customerName;
    //客户电话
    private String  customerPhonenum;
    //第二联系人姓名
    private String  secCustomerName;
    //第二联系人电话
    private String  secCustomerPhonenum;
    //合同生命周期
    private String  contractLifecycle;
    //预计安装日期
    private Date  estimatedInstallDate;
    //实际安装日期
    private Date installDate;
    //安装地址
    private String installAddress;
    //合同利润率
    private String profitRate;
    //送货方式
    private String deliveryMethod;
    //产品列表
    private List<ContractProduct> contractProducts;
}
package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@TableName(value = "salesman_contract")
public class Contract {
    @TableId(type = IdType.AUTO)
    //主键
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
    //审核意见
    private String  reviewerComment;
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
    //合同附件链接
    private String contractDocument;
    //合同利润
    private String subitemProfit;
    //合同利润率
    private String subitemProfitRate;
    //运费
    private BigDecimal deliveryFee;
    //加价金额
    private BigDecimal upAmount;
    //送货方式
    private String deliveryMethod;
    //产品数量
    private Integer productNumber;
    //安装费用
    private BigDecimal installFee;
    //产品编号
    private String productNo;
}

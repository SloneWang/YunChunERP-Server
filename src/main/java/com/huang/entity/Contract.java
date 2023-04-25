package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@TableName(value = "contract_information")
public class Contract {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(name="主键")
    private Integer id;
    @ApiModelProperty(name="会员编号")
    private String memberNo;
    @ApiModelProperty(name="合同编号")
    private String contractNo;
    @ApiModelProperty(name="发票类型")
    private String invoiceType;
    //创建员工编号
    private String CreateEmployeeNo ;
    //审核人编号
    private String  reviewerNo;
    //合同生命周期
    private String  contractLifecycle;
    //安装地址
    private String installAddress;
    //合同附件链接
    private String contractDocument;
    //送货方式
    private String deliveryMethod;
    //审核标志
    private Integer tag;
    //负责员工编号
    private String employeeNo;
    //余额或欠款
    private BigDecimal balance;
    //签约日期
    private Date signDate;
    //订金
    private BigDecimal signFee;
    //提货日期
    private Date pickDate;
    //提货收款
    private BigDecimal pickFee;
    //实际安装日期
    private Date installDate;
    //安装成本
    private BigDecimal installCost;
    //安装收款
    private BigDecimal installFee;
    //质保时间（月）
    private Integer warrantyPeriod;
    //质保收款
    private BigDecimal warrantyFee;
    //合同税率
    private BigDecimal contractTax;
    //合同总净利润
    private BigDecimal totalNetProfit;
    //合同总毛利润
    private BigDecimal totalGrossProfit;
    //安装费加价率
    private BigDecimal installAddRate;
}

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
    //会员编号
    private String memberNo;
    //合同编号
    private String contractNo;
    //签约日期
    private Date signDate;
    //发票类型
    private String invoiceType;
    //创建员工编号
    private String employeeNo ;
    //审核人编号
    private String  reviewerNo;
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
    //送货方式
    private String deliveryMethod;
}

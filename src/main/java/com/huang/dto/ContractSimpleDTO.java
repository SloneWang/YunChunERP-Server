package com.huang.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ContractSimpleDTO {
    //合同主键
    private int id;
    //合同编号
    private String contractNumber;
    //客户姓名
    private String customerName;
    //客户电话
    private String customerPhonenum;
    //客户所属公司
    private String customerCompany;
    //签约日期
    private Date signDate;
    //创建合同员工姓名
    private String  employeeName;
    //合同生命周期
    private String  contractLifecycle;
}

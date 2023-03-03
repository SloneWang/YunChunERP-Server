package com.huang.vo;

import lombok.Data;

@Data
public class PayPlanPickVO {
    //查询范围 可选项：“全部”“与我相关”
    private String Scale;
    //查询状态 可选项“全部”“即将逾期”“已逾期”
    private String Status;
    //查询员工编号，即当前登录员工的编号
    private String employeeNo;
}







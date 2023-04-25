package com.huang.vo;

import com.huang.entity.ApplyProduct;
import lombok.Data;

import java.util.List;

@Data
public class ApplyProductVO {
    //合同id
    private Integer contractId;
    //申请员工编号
    private String employeeNo;
    //产品列表
    private List<ApplyProduct> applyProducts;
}

package com.huang.vo;

import com.huang.entity.ApplyProduct;
import com.huang.entity.ReturnProduct;
import lombok.Data;

import java.util.List;

@Data
public class ReturnProductVO {
    //合同id
    private Integer contractId;
    //申请员工编号
    private String employeeNo;
    //产品列表
    private List<ReturnProduct> returnProducts;
}

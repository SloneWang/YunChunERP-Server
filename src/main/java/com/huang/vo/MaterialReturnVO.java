package com.huang.vo;

import com.huang.entity.MaterialReturn;
import lombok.Data;

import java.util.List;

@Data
public class MaterialReturnVO {
    //列表id
    private Integer listId;
    //操作员编号
    private String employeeNo;
    //产品列表
    private List<MaterialReturn> materialReturns;
}

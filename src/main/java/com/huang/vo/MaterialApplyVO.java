package com.huang.vo;

import com.huang.entity.MaterialApply;
import lombok.Data;

import java.util.List;

@Data
public class MaterialApplyVO {
    //列表编号
    private Integer listId;
    //当前操作员编号
    private String employeeNo;
    //材料列表
    private List<MaterialApply> materialApplies;
}

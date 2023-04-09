package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;


@Data
@TableName(value = "material_list")
public class MaterialList {
    @TableId(type = IdType.AUTO)
    //主键
    private Integer id;
    //材料id
    private Integer materialId;
    //材料数量
    private BigDecimal materialNumber;
    //合同id
    private Integer contractId;
    //是否生效
    private Integer tag;
    //材料销售单价
    private BigDecimal materialPrice;
}

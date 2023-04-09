package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName(value = "material_requirement")
public class MaterialRequirement {
    @TableId(type = IdType.AUTO)
    //主键
    private Integer id;
    //材料id
    private Integer materialId;
    //需求数量
    private BigDecimal materialNumber;
    //产品id
    private Integer productId;
}

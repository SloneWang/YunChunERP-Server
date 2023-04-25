package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName(value = "material_cost_record")
public class MaterialCostRecord {
    @TableId(type = IdType.AUTO)
    //主键
    private Integer id;
    //材料id
    private Integer materialId;
    //材料成本
    private BigDecimal materialCost;
    //修改时间
    private Date updateTime;
}

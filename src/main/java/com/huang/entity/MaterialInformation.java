package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName(value = "material_information")
public class MaterialInformation {
    @TableId(type = IdType.AUTO)
    //主键
    private Integer id;
    //材料类型
    private String materialType;
    //材料名称
    private String materialName;
    //材料编号
    private String materialNo;
    //供应商
    private String supplier;
    //单位
    private String unit;
    //单价
    private BigDecimal unitPrice;
    //最低加价率
    private BigDecimal lowestAddRate;
    //是否生效
    private Integer tag;
}
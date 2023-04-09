package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName(value = "product_information")
public class ProductInformation {
    @TableId(type = IdType.AUTO)
    //主键
    private Integer id;
    //产品名称
    private String productName;
    //产品编号
    private String productNo;
    //最低加价率
    private BigDecimal lowestAddRate;
    //共耗材料
    private BigDecimal coConsumableMaterial;
    //合体材料
    private BigDecimal compositeMaterial;
    //包砌人工
    private BigDecimal masonryLabor;
    //本体人工
    private BigDecimal ontologyLabor;
    //是否生效
    private Integer tag;
    //随机文件
    private BigDecimal randomFiles;
}

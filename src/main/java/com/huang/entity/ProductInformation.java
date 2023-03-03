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
    //产品编号
    private String productNo;
    //产品类型
    private String productType;
    //是否有货
    private Boolean productAble;
    //单价
    private BigDecimal price;
    //备注信息
    private String Notes;
}

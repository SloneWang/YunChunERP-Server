package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@TableName(value = "product_list")
public class ProductList {
    @TableId(type = IdType.AUTO)
    //主键
    private Integer id;
    //产品id
    private Integer productId;
    //产品数量
    private BigDecimal productNumber;
    //合同id
    private Integer contractId;
    //是否生效
    private Integer tag;
    //产品销售单价
    private BigDecimal productPrice;
    //项目状态
    private String projectStatus;
    //项目开始日期
    private Date projectStart;
    //项目结束日期
    private Date projectEnd;
}
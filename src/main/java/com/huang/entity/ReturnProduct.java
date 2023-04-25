package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName(value = "return_product")
public class ReturnProduct {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer contractId;
    private Integer materialId;
    private BigDecimal materialNumber;
    private String modifyBy;
    private Date returnTime;
    private BigDecimal materialPrice;
    private String reviewerNo;
    private String returnResult;
}

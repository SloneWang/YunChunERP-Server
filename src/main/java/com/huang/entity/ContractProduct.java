package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName(value = "contract_product")
public class ContractProduct  {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String productNo;
    private BigDecimal productNumber;
    private String contractNo;
    private Integer tag;
}


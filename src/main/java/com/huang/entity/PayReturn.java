package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.poi.hpsf.Decimal;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName(value = "pay_return")
public class PayReturn {
    @TableId(type = IdType.AUTO)
    //主键
    private Integer id;
    //创建时间
    private Date updateTime;
    //金额
    private BigDecimal amount;
    //回款类型
    private String type;
    //合同编号
    private Integer contractId;
    //回款状态
    private String status;
}

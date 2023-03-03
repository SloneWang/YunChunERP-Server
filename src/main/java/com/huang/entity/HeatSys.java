package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName(value="heat_sys")
public class HeatSys {
    @TableId(type = IdType.AUTO)
    private int id;
    private int payCycle;
    private BigDecimal interestRate;
    private int lateTimes;
    private BigDecimal unitPrice;
}

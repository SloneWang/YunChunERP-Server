package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName(value="heat_record")
public class HeatRecord {
    @TableId(type = IdType.AUTO)
    //主键
    private int id;
    //缴费日期
    private Date date;
    //缴费金额
    private BigDecimal amount;
    //收费员工用户名
    private String collector;
    //所属小区
    private String district;
    //户号
    private String account;
}

package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
@TableName(value = "sys_settings")
public class SysSettings {
    @TableId(type = IdType.AUTO)
    //主键
    private Integer id;
    //税率
    private BigDecimal taxRate;

}
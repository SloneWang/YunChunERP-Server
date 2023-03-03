package com.huang.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

import java.util.List;

@Data
@TableName(value = "depart")
public class Depart {
    @TableId(type = IdType.AUTO)
    private Integer id;
    //部门名称
    private String departName;
    //工作人员
    private String worker;
    //是否在职上班（状态）
    private int state;
    //管理人员ID（经理id）
    private int adminId;

    @TableField(exist = false)
    //经理
    private String manager;




}


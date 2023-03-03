package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
//供热收费功能中，用户权限管理模块
@Data
@TableName(value="heat_authority")
public class HeatAuth {
    @TableId(type = IdType.AUTO)
    //数据库自动id,前端返回值时此处不要赋值
    private int id;
    //员工用于登录整个系统的用户名
    private String username;
    //管理权限(弃用之前逗号隔开的表示方式,现在一个HeatAuth对象只存储一项adminAuthority或者customAuthority权限)
    private String adminAuthority;
    //收费权限
    private String customAuthority;
}

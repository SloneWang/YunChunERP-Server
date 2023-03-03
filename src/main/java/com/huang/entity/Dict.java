package com.huang.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

//图标实体类
@TableName(value = "sys_dict")
@Data
public class Dict {
    //名称
    private String name;
    //内容
    private String value;
    //类型
    private String type;

}

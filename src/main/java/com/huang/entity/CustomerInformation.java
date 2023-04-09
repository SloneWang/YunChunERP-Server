package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;



@Data
@TableName(value = "customer_information")
public class CustomerInformation {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String memberNo;
    private String customerName;
    private String customerName2;
    private String customerPhone;
    private String customerPhone2;
    private String company;
    private String industry;
    private String customerJob;
    private String channel;
    private Integer tag;
}

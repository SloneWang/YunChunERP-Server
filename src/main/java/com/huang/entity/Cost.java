package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "heat_cost")
public class Cost {
    @TableId(type = IdType.AUTO)
    private Integer id;
    //姓名
    private String name;
    //电话联系方式
    private String phonenumber;
    //位置
    private String location;
    //供热面积(㎡)
    private int heatedArea;
    //全部面积(㎡)
    private int totalArea;
    //非供热面积(㎡)
    private int unheatedArea;
    //单价(元/㎡)
    private int unitPrice;
    //总金额(元)
    private int totalPrice;
    //是否缴费
    private String pay;
    //全年/半年
    private String throughoutHalfYear;
    //供热状态
    private String heatState;
    //启阀费(元/次)
    private int openValveCost;




}

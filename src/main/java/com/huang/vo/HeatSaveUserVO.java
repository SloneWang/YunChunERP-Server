package com.huang.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class HeatSaveUserVO {
    //当前正在操作的业务员用户名
    private String username;
    //用户姓名
    private String name;
    //用户手机号码
    private String phone;
    //所在小区，“130827兆丰润景小区”
    private String district;
    //户号
    private String account;
    //供热面积
    private BigDecimal heatArea;
}

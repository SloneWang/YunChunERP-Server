package com.huang.vo;

import com.huang.entity.HeatAuth;
import lombok.Data;

import java.util.List;

@Data
public class HeatSaveAuthVO {
    //操作员工的用户名
    private String username;
    //用户权限
    private List<HeatAuth> heatAuth;
}

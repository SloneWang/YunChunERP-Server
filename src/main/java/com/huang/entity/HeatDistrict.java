package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value="heat_district")
public class HeatDistrict {
    @TableId(type = IdType.AUTO)
    private int id;
    private String province;
    private String city;
    private String county;
    private String residentialQuarter;
}

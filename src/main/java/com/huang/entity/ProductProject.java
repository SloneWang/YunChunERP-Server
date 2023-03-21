package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
@TableName(value = "product_project")
public class ProductProject {
    @TableId(type = IdType.AUTO)
    //主键
    private Integer id;
    private String projectNumber;
    //产品编号
    private String contractNumber;
    //产品类型
    private String productType;
    //是否有货
    private Date startTime;
    //单价
    private Date endTimeEs;
    //备注信息
    private Date endTime;
    private String chargePerson;
    private String projectStatus;
}

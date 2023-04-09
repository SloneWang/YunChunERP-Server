package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName(value = "review_request")
public class ReviewRequest {
    @TableId(type = IdType.AUTO)
    //主键
    private Integer id;
    //审核类型
    private String reviewType;
    //申请员工编号
    private String employeeNo;
    //申请信息
    private String remark;
    //审核日期
    private Date reviewDate;
    //审核员工编号
    private String reviewerNo;
    //审核结果
    private String reviewResult;
    //申请日期
    private Date requestDate;
    //信息索引
    private Integer indexNo;
    //附加信息
    private String additionalInformation;
}
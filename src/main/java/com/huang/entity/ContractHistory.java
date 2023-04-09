package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "contract_history")
public class ContractHistory {
    @TableId(type = IdType.AUTO)
    //主键
    private Integer id;
    //合同编号
    private String contractNo;
    //修改时间
    private Date modifyTime;
    //修改员工编号
    private String modifyBy;
    //审核人编号
    private String reviewBy;
    //备注信息
    private String remark;
}
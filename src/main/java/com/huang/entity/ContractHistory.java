package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "contract_history")
public class ContractHistory {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(name="主键")
    private Integer id;
    @ApiModelProperty(name="合同编号")
    private String contractNo;
    @ApiModelProperty(name="修改时间")
    private Date modifyTime;
    @ApiModelProperty(name="修改员工编号")
    private String modifyBy;
    @ApiModelProperty(name="审核人编号")
    private String reviewBy;
    @ApiModelProperty(name="备注信息")
    private String remark;
}
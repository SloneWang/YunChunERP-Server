package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName(value = "material_apply")
public class MaterialApply {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(name="主键")
    private Integer id;
    @ApiModelProperty(name="合同编号")
    private Integer listId;
    @ApiModelProperty(name="材料编号")
    private Integer materialId;
    @ApiModelProperty(name="申领数量")
    private BigDecimal materialNumber;
    @ApiModelProperty(name="申领人编号")
    private String chargePerson;
    @ApiModelProperty(name="申领时间")
    private Date applyTime;
    @ApiModelProperty(name="审核人编号")
    private String reviewerNo;
    @ApiModelProperty(name="审核结果")
    private String applyResult;
}

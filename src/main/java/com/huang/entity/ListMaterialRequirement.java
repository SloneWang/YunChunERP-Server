package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName(value = "list_material_requirement")
public class ListMaterialRequirement {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(name="主键")
    private Integer id;
    @ApiModelProperty(name="产品id")
    private Integer listId;
    @ApiModelProperty(name="材料id")
    private Integer materialId;
    @ApiModelProperty(name="材料数量")
    private BigDecimal materialNumber;
    @ApiModelProperty(name="审核标志")
    private Integer tag;
}
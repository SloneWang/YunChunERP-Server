package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName(value = "material_return")
public class MaterialReturn {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(name="主键")
    private Integer id;
    @ApiModelProperty(name="合同id")
    private Integer listId;
    @ApiModelProperty(name="材料id")
    private Integer materialId;
    @ApiModelProperty(name="退还数量")
    private BigDecimal materialNumber;
    @ApiModelProperty(name="退还人编号")
    private String chargePerson;
    @ApiModelProperty(name="退还时间")
    private Date returnTime;
}
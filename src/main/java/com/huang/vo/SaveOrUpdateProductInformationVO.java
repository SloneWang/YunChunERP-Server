package com.huang.vo;

import com.huang.entity.MaterialRequirement;
import com.huang.entity.ProductInformation;
import lombok.Data;

import java.util.List;

@Data
public class SaveOrUpdateProductInformationVO {
    private ProductInformation productInformation;
    private List<MaterialRequirement> materialRequirements;
}

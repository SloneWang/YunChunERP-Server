package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.huang.dto.ProductInformationDTO;
import com.huang.entity.MaterialInformation;
import com.huang.entity.MaterialRequirement;
import com.huang.entity.ProductInformation;

import java.util.List;


public interface ProductInformationService extends IService<ProductInformation> {
    Object saveOrUpdateProductInformation(ProductInformation productInformation, List<MaterialRequirement> materialRequirements);
    Object deleteProductInformationByid(Integer id);
    Object selectProductInformation();
    ProductInformationDTO selectProductInformationById(Integer id);
}

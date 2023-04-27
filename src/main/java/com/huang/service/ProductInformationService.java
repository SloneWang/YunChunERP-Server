package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.huang.dto.ProductInformationDTO;
import com.huang.entity.MaterialInformation;
import com.huang.entity.MaterialRequirement;
import com.huang.entity.ProductInformation;
import com.huang.vo.SaveOrUpdateProductInformationVO;

import java.util.List;


public interface ProductInformationService extends IService<ProductInformation> {
    boolean saveOrUpdateProductInformation(ProductInformation productInformation, List<MaterialRequirement> materialRequirements);
    boolean deleteProductInformationByid(Integer id);
    SaveOrUpdateProductInformationVO getProductDetail(Integer id);
    List<ProductInformationDTO> selectProductInformation();
    ProductInformationDTO selectProductInformationByListId(Integer productId,Integer listId);
}

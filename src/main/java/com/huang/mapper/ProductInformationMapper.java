package com.huang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huang.entity.MaterialList;
import com.huang.entity.MaterialRequirement;
import com.huang.entity.ProductInformation;
import com.huang.entity.ProductList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface ProductInformationMapper extends BaseMapper<ProductInformation> {
    List<ProductInformation> selectProductInformationByNameAndNo(String productName,String productNo);
    boolean insertMaterialRequirement(MaterialRequirement materialRequirement);
    List<MaterialRequirement> selectMaterialRequirementByProductId(Integer productId);
    boolean deleteMaterialRequirementByProductId(Integer productId);
}

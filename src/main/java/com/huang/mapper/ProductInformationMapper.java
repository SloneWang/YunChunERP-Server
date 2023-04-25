package com.huang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huang.entity.*;
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
    List<ListMaterialRequirement> selectListMaterialRequirementByListId(Integer listId);
}

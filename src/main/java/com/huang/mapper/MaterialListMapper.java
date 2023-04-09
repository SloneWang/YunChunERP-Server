package com.huang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huang.entity.MaterialInformation;
import com.huang.entity.MaterialList;
import com.huang.entity.MaterialRequirement;
import com.huang.entity.ProductList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface MaterialListMapper extends BaseMapper<MaterialList> {
    List<MaterialList> selectMaterialListByContractId(Integer contractId);
    boolean deleteMaterialListByContractId(Integer contractId);
    List<MaterialList> selectMaterialListByMaterialId(String materialId);
}
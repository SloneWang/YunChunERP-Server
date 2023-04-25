package com.huang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huang.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface MaterialListMapper extends BaseMapper<MaterialList> {
    List<MaterialList> selectMaterialListByContractId(Integer contractId);
    boolean deleteMaterialListByContractId(Integer contractId);
    List<MaterialList> selectMaterialListByMaterialId(String materialId);
    boolean insertReturnProduct(ReturnProduct returnProduct);
    boolean insertApplyProduct(ApplyProduct applyProduct);
    List<ReturnProduct> selectReturnProduct(Integer contractId);
    List<ApplyProduct> selectApplyProduct(Integer contractId);
    boolean updateApplyProduct(ApplyProduct applyProduct);
    boolean updateReturnProduct(ReturnProduct returnProduct);

}
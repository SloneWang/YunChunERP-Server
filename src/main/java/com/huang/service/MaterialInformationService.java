package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.entity.MaterialCostRecord;
import com.huang.entity.MaterialInformation;

import java.math.BigDecimal;
import java.util.List;


public interface MaterialInformationService extends IService<MaterialInformation> {
     boolean saveOrUpdateMaterialInformation(MaterialInformation materialInformation);
     boolean deleteMaterialInformationByid(Integer id);
     List<MaterialInformation> selectMaterialInformation();
     List<MaterialCostRecord> selectMaterialCostRecord(Integer materialId);
     MaterialInformation selectMaterialInformationById(Integer id);
}

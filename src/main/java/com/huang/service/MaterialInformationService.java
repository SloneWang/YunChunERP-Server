package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.entity.MaterialInformation;


public interface MaterialInformationService extends IService<MaterialInformation> {
     Object saveOrUpdateMaterialInformation(MaterialInformation materialInformation);
     Object deleteMaterialInformationByid(Integer id);
     Object selectMaterialInformation();
     MaterialInformation selectMaterialInformationById(Integer id);
}

package com.huang.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.entity.MaterialInformation;


public interface MaterialInformationService extends IService<MaterialInformation> {
     boolean saveOrUpdateMaterialInformation(MaterialInformation materialInformation) throws Exception;
     boolean deleteMaterialInformationByid(Integer id) throws Exception;
     List<MaterialInformation> selectMaterialInformation();
     MaterialInformation selectMaterialInformationById(Integer id);
}

package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.entity.MaterialInformation;
import com.huang.entity.MaterialList;
import com.huang.entity.MaterialRequirement;
import com.huang.entity.ProductList;

import java.util.List;


public interface MaterialListService extends IService<MaterialList> {
    Object selectMaterialList();

}
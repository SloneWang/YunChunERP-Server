package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.entity.CustomerInformation;
import com.huang.entity.ListMaterialRequirement;

import java.util.List;

public interface ListMaterialRequirementService extends IService<ListMaterialRequirement> {
    List<ListMaterialRequirement> selectMaterialRequirement(Integer listId);
}

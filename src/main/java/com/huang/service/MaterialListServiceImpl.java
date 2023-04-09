package com.huang.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.entity.MaterialList;
import com.huang.mapper.MaterialListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MaterialListServiceImpl extends ServiceImpl<MaterialListMapper, MaterialList> implements MaterialListService{
    @Autowired
    MaterialListMapper materialListMapper;

    @Override
    public Object selectMaterialList() {
        return list();
    }
}

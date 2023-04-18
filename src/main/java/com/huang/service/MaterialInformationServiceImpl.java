package com.huang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.entity.MaterialInformation;
import com.huang.mapper.MaterialInformationMapper;
import com.huang.mapper.MaterialListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class MaterialInformationServiceImpl extends ServiceImpl<MaterialInformationMapper, MaterialInformation> implements MaterialInformationService{
    @Autowired
    MaterialInformationMapper materialInformationMapper;
    @Autowired
    MaterialListMapper materialListMapper;

    @Override
    public boolean saveOrUpdateMaterialInformation(MaterialInformation materialInformation) throws Exception {
        QueryWrapper<MaterialInformation> queryWrapper = new QueryWrapper<MaterialInformation>()
                .eq("material_name",materialInformation.getMaterialName())
                .eq("material_no",materialInformation.getMaterialNo())
                .eq("tag",0);
        List<MaterialInformation> tempMaterialInformation=list(queryWrapper);
        if(tempMaterialInformation.size()!=0){
            if(!removeById(tempMaterialInformation.get(0).getId())){
                throw new Exception("删除原数据失败");
            }
        }
        if(!saveOrUpdate(materialInformation)){
            throw new Exception("插入材料信息失败");
        }
        return true;
    }

    @Override
    @Transactional
    public boolean deleteMaterialInformationByid(Integer id) throws Exception {
        MaterialInformation materialInformation=new MaterialInformation();
        materialInformation.setId(id);
        materialInformation.setTag(0);
        if(!saveOrUpdate(materialInformation)){
            throw new Exception("删除原料失败");
        }
        return true;
    }

    @Override
    public List<MaterialInformation> selectMaterialInformation() {
        QueryWrapper<MaterialInformation> queryWrapper=new QueryWrapper<MaterialInformation>()
                .eq("tag",1);
        return list(queryWrapper);
    }

    @Override
    public MaterialInformation selectMaterialInformationById(Integer id) {
        QueryWrapper<MaterialInformation> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",id);
        return getOne(queryWrapper);
    }
}

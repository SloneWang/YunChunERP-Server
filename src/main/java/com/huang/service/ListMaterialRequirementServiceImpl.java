package com.huang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.entity.CustomerInformation;
import com.huang.entity.ListMaterialRequirement;
import com.huang.mapper.CustomerInformationMapper;
import com.huang.mapper.ListMaterialRequirementMapper;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;


@Service
public class ListMaterialRequirementServiceImpl extends ServiceImpl<ListMaterialRequirementMapper, ListMaterialRequirement> implements ListMaterialRequirementService {
    @Override
    public List<ListMaterialRequirement> selectMaterialRequirement(Integer listId) {
        QueryWrapper<ListMaterialRequirement> queryWrapper=new QueryWrapper<ListMaterialRequirement>()
                .eq("list_id",listId);
        return list(queryWrapper);
    }
}
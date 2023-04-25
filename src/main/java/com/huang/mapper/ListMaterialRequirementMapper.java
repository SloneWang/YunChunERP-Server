package com.huang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huang.entity.ListMaterialRequirement;
import com.huang.entity.MaterialList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ListMaterialRequirementMapper extends BaseMapper<ListMaterialRequirement> {

}
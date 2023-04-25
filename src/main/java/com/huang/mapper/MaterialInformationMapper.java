package com.huang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huang.entity.MaterialCostRecord;
import com.huang.entity.MaterialInformation;
import com.huang.entity.ProductInformation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface MaterialInformationMapper extends BaseMapper<MaterialInformation> {
    boolean insertMaterialCostRecord(MaterialCostRecord materialCostRecord);
    List<MaterialCostRecord> selectMaterialCostRecord(Integer materialId);
}
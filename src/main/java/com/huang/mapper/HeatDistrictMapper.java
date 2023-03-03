package com.huang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huang.entity.HeatDistrict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface HeatDistrictMapper extends BaseMapper<HeatDistrict> {
    List<HeatDistrict> selectQua(String province,String city,String county);
    List<HeatDistrict> selectDisByPr(String province);
    List<HeatDistrict> selectDisByCi(String province,String city);
}

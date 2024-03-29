package com.huang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huang.entity.CustomerInformation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CustomerInformationMapper extends BaseMapper<CustomerInformation> {
    List<CustomerInformation> selectOneCustomer(String memberNo);
}

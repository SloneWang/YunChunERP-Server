package com.huang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.entity.Depart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface  DepartMapper extends BaseMapper<Depart> {
    Page<Depart> findPage(Page<Depart> page, @Param("name") String name);
}

package com.huang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huang.entity.MaterialList;
import com.huang.entity.ProductList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface ProductListMapper extends BaseMapper<ProductList> {
    List<ProductList> selectProductListByContractId(Integer contractId);
    boolean deleteProductListByContractId(Integer contractId);
    List<ProductList> selectProductListByProductId(String productId);
}
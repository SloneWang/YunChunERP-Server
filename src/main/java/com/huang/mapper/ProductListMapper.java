package com.huang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huang.entity.MaterialApply;
import com.huang.entity.MaterialList;
import com.huang.entity.MaterialReturn;
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
    boolean insertMaterialReturn(MaterialReturn materialReturn);
    boolean insertMaterialApply(MaterialApply materialApply);
    List<MaterialReturn> selectMaterialReturn(Integer listId);
    List<MaterialApply> selectMaterialApply(Integer listId);
    boolean updateMaterialReturn(MaterialReturn materialReturn);
    boolean updateMaterialApply(MaterialApply materialApply);
}
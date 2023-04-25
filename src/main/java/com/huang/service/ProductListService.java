package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.entity.MaterialApply;
import com.huang.entity.MaterialList;
import com.huang.entity.MaterialReturn;
import com.huang.entity.ProductList;
import com.huang.vo.MaterialApplyVO;
import com.huang.vo.MaterialReturnVO;

import java.util.List;

public interface ProductListService extends IService<ProductList> {
    List<ProductList> selectProductList();
    boolean productComplete(Integer id);
    boolean productWrap(Integer id);
    boolean materialReturn(MaterialReturnVO materialReturnVO);
    boolean materialApply(MaterialApplyVO materialApplyVO);
    List<MaterialReturn> selectMaterialReturn(Integer listId);
    List<MaterialApply> selectMaterialApply(Integer listId);
}
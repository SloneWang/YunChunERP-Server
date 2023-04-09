package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.entity.MaterialList;
import com.huang.entity.ProductList;

public interface ProductListService extends IService<ProductList> {
    Object selectProductList();
    Object productComplete(Integer id);
    Object productWrap(Integer id);
}
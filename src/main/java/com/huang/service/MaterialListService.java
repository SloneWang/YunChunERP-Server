package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.entity.*;
import com.huang.vo.ApplyProductVO;
import com.huang.vo.ReturnProductVO;

import java.util.List;


public interface MaterialListService extends IService<MaterialList> {
    List<MaterialList> selectMaterialList();
    boolean returnProduct(ReturnProductVO returnProductVO);
    boolean applyProduct(ApplyProductVO applyProductVO);
    List<ReturnProduct> selectReturnProduct(Integer contractId);
    List<ApplyProduct> selectApplyProduct(Integer contractId);
}
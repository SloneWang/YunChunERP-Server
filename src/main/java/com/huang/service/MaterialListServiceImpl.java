package com.huang.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.entity.*;
import com.huang.mapper.MaterialListMapper;
import com.huang.mapper.ProductListMapper;
import com.huang.vo.ApplyProductVO;
import com.huang.vo.ReturnProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class MaterialListServiceImpl extends ServiceImpl<MaterialListMapper, MaterialList> implements MaterialListService{
    @Autowired
    MaterialListMapper materialListMapper;
    @Autowired
    ProductListMapper productListMapper;
    @Autowired
    ReviewRequestServiceImpl reviewRequestService;

    @Override
    public List<MaterialList> selectMaterialList() {
        return list();
    }

    @Override
    @Transactional
    public boolean applyProduct(ApplyProductVO applyProductVO) {
        try {
            StringBuilder str=new StringBuilder();
            for(ApplyProduct ma:applyProductVO.getApplyProducts()){
                ma.setContractId(applyProductVO.getContractId());
                ma.setModifyBy(applyProductVO.getEmployeeNo());
                ma.setApplyTime(new java.util.Date(System.currentTimeMillis()));
                ma.setApplyResult("正在审核");
                if(!materialListMapper.insertApplyProduct(ma)){
                    throw new Exception("添加产品申请失败");
                }
                str.append(",");
                str.append(ma.getId().toString());
            }
            str.deleteCharAt(0);

            //插入待审批信息
            ReviewRequest reviewRequest =new ReviewRequest();
            reviewRequest.setReviewType("添加产品申请");
            reviewRequest.setEmployeeNo(applyProductVO.getEmployeeNo());
            reviewRequest.setRequestDate(new java.util.Date(System.currentTimeMillis()));
            reviewRequest.setAdditionalInformation(applyProductVO.getApplyProducts().toString());
            reviewRequest.setAdditionalInformation(str.toString());
            reviewRequest.setIndexNo(applyProductVO.getContractId());
            if (!reviewRequestService.saveOrUpdate(reviewRequest)){
                throw new Exception("保存审批信息失败");
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public boolean returnProduct(ReturnProductVO returnProductVO) {
        try {
            StringBuilder str=new StringBuilder();
            for(ReturnProduct ma:returnProductVO.getReturnProducts()){
                ma.setContractId(returnProductVO.getContractId());
                ma.setModifyBy(returnProductVO.getEmployeeNo());
                ma.setReturnTime(new java.util.Date(System.currentTimeMillis()));
                ma.setReturnResult("正在审核");
                if(!materialListMapper.insertReturnProduct(ma)){
                    throw new Exception("添加退货申请失败");
                }
                str.append(",");
                str.append(ma.getId().toString());
            }
            str.deleteCharAt(0);

            //插入待审批信息
            ReviewRequest reviewRequest =new ReviewRequest();
            reviewRequest.setReviewType("退货申请");
            reviewRequest.setEmployeeNo(returnProductVO.getEmployeeNo());
            reviewRequest.setRequestDate(new java.util.Date(System.currentTimeMillis()));
            reviewRequest.setAdditionalInformation(returnProductVO.getReturnProducts().toString());
            reviewRequest.setAdditionalInformation(str.toString());
            reviewRequest.setIndexNo(returnProductVO.getContractId());
            if (!reviewRequestService.saveOrUpdate(reviewRequest)){
                throw new Exception("保存审批信息失败");
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ReturnProduct> selectReturnProduct(Integer contractId) {
        return materialListMapper.selectReturnProduct(contractId);
    }

    @Override
    public List<ApplyProduct> selectApplyProduct(Integer contractId) {
        return materialListMapper.selectApplyProduct(contractId);
    }
}

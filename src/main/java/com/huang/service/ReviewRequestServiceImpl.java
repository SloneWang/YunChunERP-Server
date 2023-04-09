package com.huang.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.entity.*;
import com.huang.mapper.MaterialListMapper;
import com.huang.mapper.ProductListMapper;
import com.huang.mapper.ReviewRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class ReviewRequestServiceImpl extends ServiceImpl<ReviewRequestMapper, ReviewRequest> implements ReviewRequestService {
    @Autowired
    ReviewRequestMapper reviewRequestMapper;
    @Autowired
    SalesmanServiceImpl salesmanService;
    @Autowired
    ProductListServiceImpl productListService;
    @Autowired
    MaterialListServiceImpl materialListService;
    @Autowired
    ProductListMapper productListMapper;
    @Autowired
    MaterialListMapper materialListMapper;

    @Override
    public Object reviewInformation(String reviewNo) {
        return reviewRequestMapper.selectReviewInformation();
    }

    @Override
    @Transactional
    public Object reviewResult(String reviewNo, Integer id, boolean flag,String contractNo) {
        try {

            ReviewRequest tempReviewRequest = reviewRequestMapper.selectOne(id).get(0);
            if(tempReviewRequest.getReviewType().equals("创建新合同")){
                tempReviewRequest.setReviewerNo(reviewNo);
                tempReviewRequest.setReviewDate(new Date(System.currentTimeMillis()));
                if(flag){
                    //插入审核人和审核时间到合同信息表
                    //将合同编号保存到合同信息表中
                    tempReviewRequest.setReviewResult("审核通过");
                    Contract tempContract=new Contract();
                    tempContract.setId(tempReviewRequest.getIndexNo());
                    tempContract.setReviewerNo(reviewNo);
                    tempContract.setTag(1);
                    tempContract.setContractNo(contractNo);
                    if(!salesmanService.saveOrUpdate(tempContract)){
                        throw new Exception("合同信息修改失败");
                    }
                    //将合同信息表材料列表产品列表tag更新为1
                    for(MaterialList ma:materialListMapper.selectMaterialListByContractId(tempReviewRequest.getIndexNo())){
                        MaterialList tempMaterialList=new MaterialList();
                        tempMaterialList.setId(ma.getId());
                        tempMaterialList.setTag(1);
                        materialListService.saveOrUpdate(tempMaterialList);
                    }
                    for(ProductList pro:productListMapper.selectProductListByContractId(tempReviewRequest.getIndexNo())){
                        ProductList tempProductList=new ProductList();
                        tempProductList.setId(pro.getId());
                        tempProductList.setTag(1);
                        productListService.saveOrUpdate(tempProductList);
                    }
                }
                else{
                    tempReviewRequest.setReviewResult("审核未通过");
                    if(!materialListMapper.deleteMaterialListByContractId(tempReviewRequest.getIndexNo())){
                        throw new Exception("删除材料列表失败");
                    }
                    if(!productListMapper.deleteProductListByContractId(tempReviewRequest.getIndexNo())){
                        throw new Exception("删除产品列表失败");
                    }
                    if(!salesmanService.removeById(tempReviewRequest.getIndexNo())){
                        throw new Exception("删除合同信息失败");
                    }
                }
                if(!saveOrUpdate(tempReviewRequest)){
                 throw new Exception("审核信息保存失败");
                }
                return true;
            }
            else if(tempReviewRequest.getReviewType().equals("修改合同信息")){
                tempReviewRequest.setReviewerNo(reviewNo);
                tempReviewRequest.setReviewDate(new Date(System.currentTimeMillis()));
                Contract saveData=salesmanService.selectUpdateContractInformation(tempReviewRequest.getIndexNo());
                if(flag){
                    if(!reviewRequestMapper.upDateContractHistory(reviewNo,Integer.parseInt(saveData.getCreateEmployeeNo()))){
                        throw new Exception("历史记录插入失败");
                    }
                    tempReviewRequest.setReviewResult("审核通过");
                    Contract tempContract=new Contract();
                    tempContract.setId(tempReviewRequest.getIndexNo());
                    tempContract.setReviewerNo(reviewNo);
                    tempContract.setInvoiceType(saveData.getInvoiceType());
                    tempContract.setInstallAddress(saveData.getInstallAddress());
                    tempContract.setDeliveryMethod(saveData.getDeliveryMethod());
                    tempContract.setEmployeeNo(saveData.getEmployeeNo());
                    tempContract.setWarrantyPeriod(saveData.getWarrantyPeriod());
                    if(!salesmanService.saveOrUpdate(tempContract)){
                        throw new Exception("合同信息修改失败");
                    }
                    if(!salesmanService.removeById(saveData.getId())){
                        throw new Exception("删除临时合同信息失败");
                    }
                }
                else{
                    tempReviewRequest.setReviewResult("审核未通过");
                    if(!salesmanService.removeById(saveData.getId())){
                        throw new Exception("删除临时合同信息失败");
                    }
                }
                if(!saveOrUpdate(tempReviewRequest)){
                    throw new Exception("审核信息保存失败");
                }
                return true;

            }
            else  throw new Exception("审核类型不存在");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

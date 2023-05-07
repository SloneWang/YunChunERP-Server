package com.huang.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.entity.*;
import com.huang.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
    @Autowired
    ProductInformationMapper productInformationMapper;
    @Autowired
    ListMaterialRequirementServiceImpl listMaterialRequirementService;
    @Autowired
    MaterialInformationServiceImpl materialInformationService;
    @Autowired
    SalesmanMapper salesmanMapper;


    @Override
    public List<ReviewRequest> reviewInformation(String reviewNo) {
        return reviewRequestMapper.selectReviewInformation();
    }

    @Override
    @Transactional
    public boolean reviewResult(String reviewNo, Integer id, boolean flag,String contractNo) {
        try {

            ReviewRequest tempReviewRequest = reviewRequestMapper.selectOne(id).get(0);
            switch (tempReviewRequest.getReviewType()) {
                case "创建新合同":
                    tempReviewRequest.setReviewerNo(reviewNo);
                    tempReviewRequest.setReviewDate(new Date(System.currentTimeMillis()));
                    if (flag) {
                        //插入审核人和审核时间到合同信息表
                        //将合同编号保存到合同信息表中
                        tempReviewRequest.setReviewResult("审核通过");
                        Contract tempContract = new Contract();
                        tempContract.setId(tempReviewRequest.getIndexNo());
                        tempContract.setReviewerNo(reviewNo);
                        tempContract.setTag(1);
                        tempContract.setContractNo(contractNo);
                        if (!salesmanService.saveOrUpdate(tempContract)) {
                            throw new Exception("合同信息修改失败");
                        }
                        //将合同信息表材料列表产品列表tag更新为1
                        for (MaterialList ma : materialListMapper.selectMaterialListByContractId(tempReviewRequest.getIndexNo())) {
                            MaterialList tempMaterialList = new MaterialList();
                            tempMaterialList.setId(ma.getId());
                            tempMaterialList.setTag(1);
                            materialListService.saveOrUpdate(tempMaterialList);
                        }
                        for (ProductList pro : productListMapper.selectProductListByContractId(tempReviewRequest.getIndexNo())) {
                            ProductList tempProductList = new ProductList();
                            tempProductList.setId(pro.getId());
                            tempProductList.setTag(1);
                            productListService.saveOrUpdate(tempProductList);

                            //将用料列表tag更新为1
                            for(ListMaterialRequirement rere:productInformationMapper.selectListMaterialRequirementByListId(pro.getId())){
                                ListMaterialRequirement newRe=new ListMaterialRequirement();
                                newRe.setId(rere.getId());
                                newRe.setTag(1);
                                if(!listMaterialRequirementService.saveOrUpdate(newRe)){
                                    throw new Exception("保存用料表失败");
                                }
                            }
                        }

                        //插入回款计划
                        Contract tempContractInformation=salesmanService.getById(tempReviewRequest.getIndexNo());
                        PayReturn payReturn=new PayReturn();
                        payReturn.setContractId(tempReviewRequest.getIndexNo());
                        payReturn.setAmount(tempContractInformation.getSignFee());
                        payReturn.setStatus("待收取");
                        payReturn.setType("合同订金");
                        payReturn.setUpdateTime(new java.util.Date(System.currentTimeMillis()));
                        if(!salesmanMapper.insertPayReturn(payReturn)){
                            throw new Exception("回款计划保存失败");
                        }

                    } else {
                        tempReviewRequest.setReviewResult("审核未通过");
                        if (!materialListMapper.deleteMaterialListByContractId(tempReviewRequest.getIndexNo())) {
                            throw new Exception("删除材料列表失败");
                        }
                        if (!productListMapper.deleteProductListByContractId(tempReviewRequest.getIndexNo())) {
                            throw new Exception("删除产品列表失败");
                        }
                        if (!salesmanService.removeById(tempReviewRequest.getIndexNo())) {
                            throw new Exception("删除合同信息失败");
                        }
                    }
                    if (!saveOrUpdate(tempReviewRequest)) {
                        throw new Exception("审核信息保存失败");
                    }
                    return true;
                case "修改合同信息":
                    tempReviewRequest.setReviewerNo(reviewNo);
                    tempReviewRequest.setReviewDate(new Date(System.currentTimeMillis()));
                    Contract saveData = salesmanService.selectUpdateContractInformation(tempReviewRequest.getIndexNo());
                    if (flag) {
                        if (!reviewRequestMapper.upDateContractHistory(reviewNo, Integer.parseInt(saveData.getCreateEmployeeNo()))) {
                            throw new Exception("历史记录插入失败");
                        }
                        tempReviewRequest.setReviewResult("审核通过");
                        Contract tempContract = new Contract();
                        tempContract.setId(tempReviewRequest.getIndexNo());
                        tempContract.setReviewerNo(reviewNo);
                        tempContract.setInvoiceType(saveData.getInvoiceType());
                        tempContract.setInstallAddress(saveData.getInstallAddress());
                        tempContract.setDeliveryMethod(saveData.getDeliveryMethod());
                        tempContract.setEmployeeNo(saveData.getEmployeeNo());
                        tempContract.setWarrantyPeriod(saveData.getWarrantyPeriod());
                        if (!salesmanService.saveOrUpdate(tempContract)) {
                            throw new Exception("合同信息修改失败");
                        }
                    } else {
                        tempReviewRequest.setReviewResult("审核未通过");
                    }
                    if (!salesmanService.removeById(saveData.getId())) {
                        throw new Exception("删除临时合同信息失败");
                    }
                    if (!saveOrUpdate(tempReviewRequest)) {
                        throw new Exception("审核信息保存失败");
                    }
                    return true;

                case "删除合同":
                    tempReviewRequest.setReviewerNo(reviewNo);
                    tempReviewRequest.setReviewDate(new Date(System.currentTimeMillis()));
                    if (flag) {
                        tempReviewRequest.setReviewResult("审核通过");
                        salesmanService.deleteContract(tempReviewRequest.getIndexNo(), tempReviewRequest.getEmployeeNo(), reviewNo);
                    } else {
                        tempReviewRequest.setReviewResult("审核未通过");
                    }
                    if (!saveOrUpdate(tempReviewRequest)) {
                        throw new Exception("审核信息保存失败");
                    }
                    return true;
                case "领料申请":
                    tempReviewRequest.setReviewerNo(reviewNo);
                    tempReviewRequest.setReviewDate(new Date(System.currentTimeMillis()));
                    if (flag) {
                        boolean tempFlag;
                        tempReviewRequest.setReviewResult("审核通过");
                        String[] strs=tempReviewRequest.getAdditionalInformation().split(",");
                        List<ListMaterialRequirement> tempListMaterialRequirements=productInformationMapper.selectListMaterialRequirementByListId(reviewRequestMapper.selectMaterialApply(Integer.parseInt(strs[0])).get(0).getListId());
                        for(String stt:strs){
                            Integer tempInt=Integer.parseInt(stt);
                            MaterialApply tempMaterialApply=reviewRequestMapper.selectMaterialApply(tempInt).get(0);
                            tempMaterialApply.setApplyResult("审核通过");
                            tempMaterialApply.setReviewerNo(reviewNo);
                            if(!productListMapper.updateMaterialApply(tempMaterialApply)){
                                throw new Exception("修改领料信息失败");
                            }
                            tempFlag=false;
                            for(ListMaterialRequirement lisMa:tempListMaterialRequirements){
                                if(tempMaterialApply.getMaterialId().equals(lisMa.getMaterialId())){
                                    tempFlag=true;
                                    lisMa.setMaterialNumber(lisMa.getMaterialNumber().add(tempMaterialApply.getMaterialNumber()));
                                }
                            }
                            if(!tempFlag){
                                ListMaterialRequirement listMaterialRequire=new ListMaterialRequirement();
                                listMaterialRequire.setMaterialId(tempMaterialApply.getMaterialId());
                                listMaterialRequire.setMaterialNumber(tempMaterialApply.getMaterialNumber());
                                listMaterialRequire.setListId(tempMaterialApply.getListId());
                                if(!listMaterialRequirementService.saveOrUpdate(listMaterialRequire)){
                                    throw new Exception("保存用料表失败");
                                }

                            }
                        }
                        for(ListMaterialRequirement li:tempListMaterialRequirements){
                            if(!listMaterialRequirementService.saveOrUpdate(li)){
                                throw new Exception("更新用料表失败");
                            }
                        }
                    } else {
                        tempReviewRequest.setReviewResult("审核未通过");
                        String[] strs=tempReviewRequest.getAdditionalInformation().split(",");
                        for(String stt:strs){
                            Integer tempInt=Integer.parseInt(stt);
                            MaterialApply tempMaterialApply=reviewRequestMapper.selectMaterialApply(tempInt).get(0);
                            tempMaterialApply.setApplyResult("审核未通过");
                            tempMaterialApply.setReviewerNo(reviewNo);
                            if(!productListMapper.updateMaterialApply(tempMaterialApply)){
                                throw new Exception("插入领料信息失败");
                            }
                        }
                    }
                    if (!saveOrUpdate(tempReviewRequest)) {
                        throw new Exception("审核信息保存失败");
                    }
                    return true;
                case "添加产品申请":
                    tempReviewRequest.setReviewerNo(reviewNo);
                    tempReviewRequest.setReviewDate(new Date(System.currentTimeMillis()));
                    if (flag) {
                        boolean tempFlag;
                        tempReviewRequest.setReviewResult("审核通过");
                        String[] strs=tempReviewRequest.getAdditionalInformation().split(",");
                        Integer tempContractId=reviewRequestMapper.selectApplyProduct(Integer.parseInt(strs[0])).get(0).getContractId();

                        //将价格差补到合同安装费用中
                        QueryWrapper<Contract> queryWrapper1=new QueryWrapper<Contract>()
                                .eq("id",tempContractId);
                        Contract tempSalesmanContract=salesmanService.getOne(queryWrapper1);

                        List<MaterialList> tempListMaterialRequirements=materialListMapper.selectMaterialListByContractId(tempContractId);
                        for(String stt:strs){
                            Integer tempInt=Integer.parseInt(stt);
                            ApplyProduct tempMaterialApply=reviewRequestMapper.selectApplyProduct(tempInt).get(0);
                            tempSalesmanContract.setInstallFee(tempSalesmanContract.getInstallFee().add(tempMaterialApply.getMaterialPrice().multiply(tempMaterialApply.getMaterialNumber())));
                            tempMaterialApply.setApplyResult("审核通过");
                            tempMaterialApply.setReviewerNo(reviewNo);
                            if(!materialListMapper.updateApplyProduct(tempMaterialApply)){
                                throw new Exception("插入添加产品记录失败");
                            }
                            tempFlag=false;
                            for(MaterialList lisMa:tempListMaterialRequirements){
                                if(tempMaterialApply.getMaterialId().equals(lisMa.getMaterialId())&&tempMaterialApply.getMaterialPrice().compareTo(lisMa.getMaterialPrice())==0){
                                    tempFlag=true;
                                    lisMa.setMaterialNumber(lisMa.getMaterialNumber().add(tempMaterialApply.getMaterialNumber()));
                                }
                            }
                            if(!tempFlag){
                                MaterialList listMaterialRequire=new MaterialList();
                                listMaterialRequire.setContractId(tempMaterialApply.getContractId());
                                listMaterialRequire.setMaterialId(tempMaterialApply.getMaterialId());
                                listMaterialRequire.setMaterialNumber(tempMaterialApply.getMaterialNumber());
                                listMaterialRequire.setTag(1);
                                listMaterialRequire.setMaterialPrice(tempMaterialApply.getMaterialPrice());
                                if(!materialListService.saveOrUpdate(listMaterialRequire)){
                                    throw new Exception("保存产品列表失败");
                                }

                            }
                        }
                        for(MaterialList li:tempListMaterialRequirements){
                            if(!materialListService.saveOrUpdate(li)){
                                throw new Exception("更新用料表失败");
                            }
                        }
                        if(!salesmanService.saveOrUpdate(tempSalesmanContract)){
                            throw new Exception("更新合同信息失败");
                        }

                    } else {
                        tempReviewRequest.setReviewResult("审核未通过");
                        String[] strs=tempReviewRequest.getAdditionalInformation().split(",");
                        for(String stt:strs){
                            Integer tempInt=Integer.parseInt(stt);
                            ApplyProduct tempMaterialApply=reviewRequestMapper.selectApplyProduct(tempInt).get(0);
                            tempMaterialApply.setApplyResult("审核未通过");
                            tempMaterialApply.setReviewerNo(reviewNo);
                            if(!materialListMapper.updateApplyProduct(tempMaterialApply)){
                                throw new Exception("插入添加产品信息失败");
                            }
                        }
                    }
                    if (!saveOrUpdate(tempReviewRequest)) {
                        throw new Exception("审核信息保存失败");
                    }

                    return true;
                case "退货申请":
                    tempReviewRequest.setReviewerNo(reviewNo);
                    tempReviewRequest.setReviewDate(new Date(System.currentTimeMillis()));
                    if (flag) {
                        boolean tempFlag;
                        tempReviewRequest.setReviewResult("审核通过");
                        String[] strs=tempReviewRequest.getAdditionalInformation().split(",");
                        Integer tempContractId=reviewRequestMapper.selectReturnProduct(Integer.parseInt(strs[0])).get(0).getContractId();
                        QueryWrapper<Contract> queryWrapper1=new QueryWrapper<Contract>()
                                .eq("id",tempContractId);
                        Contract tempSalesmanContract=salesmanService.getOne(queryWrapper1);
                        List<MaterialList> tempListMaterialRequirements=materialListMapper.selectMaterialListByContractId(tempContractId);
                        for(String stt:strs){
                            Integer tempInt=Integer.parseInt(stt);
                            ReturnProduct tempMaterialApply=reviewRequestMapper.selectReturnProduct(tempInt).get(0);
                            tempSalesmanContract.setInstallFee(tempSalesmanContract.getInstallFee().subtract(tempMaterialApply.getMaterialPrice().multiply(tempMaterialApply.getMaterialNumber())));
                            tempMaterialApply.setReturnResult("审核通过");
                            tempMaterialApply.setReviewerNo(reviewNo);
                            if(!materialListMapper.updateReturnProduct(tempMaterialApply)){
                                throw new Exception("插入添加产品记录失败");
                            }
                            tempFlag=false;
                            for(MaterialList lis:tempListMaterialRequirements){
                                if(tempMaterialApply.getMaterialId().equals(lis.getMaterialId())&&tempMaterialApply.getMaterialPrice().compareTo(lis.getMaterialPrice())==0){
                                    tempFlag=true;
                                    if(tempMaterialApply.getMaterialNumber().compareTo(lis.getMaterialNumber())<0){
                                        lis.setMaterialNumber(lis.getMaterialNumber().subtract(tempMaterialApply.getMaterialNumber()));
                                        break;
                                    } else if (tempMaterialApply.getMaterialNumber().compareTo(lis.getMaterialNumber())==0) {
                                        lis.setMaterialNumber(lis.getMaterialNumber().subtract(tempMaterialApply.getMaterialNumber()));
                                        if(!materialListService.removeById(lis.getId())){
                                            throw new Exception("修改用料表信息失败");
                                        }
                                        break;
                                    } else {
                                        throw new Exception("退货数量大于用料数量");
                                    }
                                }
                            }
                            if(!tempFlag){
                                throw new Exception("退货种类不存在于用料表上");
                            }
                        }
                        tempListMaterialRequirements.removeIf(materialList -> materialList.getMaterialNumber().compareTo(BigDecimal.valueOf(0))==0);
                        for(MaterialList liss:tempListMaterialRequirements){
                            if(!materialListService.saveOrUpdate(liss)){
                                throw new Exception("退货信息插入失败");
                            }
                        }
                        if(!salesmanService.saveOrUpdate(tempSalesmanContract)){
                            throw new Exception("更新合同信息失败");
                        }
                    } else {
                        tempReviewRequest.setReviewResult("审核未通过");
                        String[] strs=tempReviewRequest.getAdditionalInformation().split(",");
                        for(String stt:strs){
                            Integer tempInt=Integer.parseInt(stt);
                            ReturnProduct tempMaterialApply=reviewRequestMapper.selectReturnProduct(tempInt).get(0);
                            tempMaterialApply.setReturnResult("审核未通过");
                            tempMaterialApply.setReviewerNo(reviewNo);
                            if(!materialListMapper.updateReturnProduct(tempMaterialApply)){
                                throw new Exception("插入添加产品信息失败");
                            }
                        }
                    }
                    if (!saveOrUpdate(tempReviewRequest)) {
                        throw new Exception("审核信息保存失败");
                    }
                    return true;
                case "作废合同":
                    tempReviewRequest.setReviewerNo(reviewNo);
                    tempReviewRequest.setReviewDate(new Date(System.currentTimeMillis()));
                    if (flag) {
                        tempReviewRequest.setReviewResult("审核通过");
                        salesmanService.cancelContract(tempReviewRequest.getIndexNo(), tempReviewRequest.getEmployeeNo(), reviewNo);
                    } else {
                        tempReviewRequest.setReviewResult("审核未通过");
                    }
                    if (!saveOrUpdate(tempReviewRequest)) {
                        throw new Exception("审核信息保存失败");
                    }
                    return true;
                default:
                    throw new Exception("审核类型不存在");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

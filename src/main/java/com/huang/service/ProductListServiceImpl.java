package com.huang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.dto.ProductInformationDTO;
import com.huang.entity.*;
import com.huang.mapper.MaterialListMapper;
import com.huang.mapper.ProductInformationMapper;
import com.huang.mapper.ProductListMapper;
import com.huang.vo.MaterialApplyVO;
import com.huang.vo.MaterialReturnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductListServiceImpl extends ServiceImpl<ProductListMapper, ProductList> implements ProductListService{
    @Autowired
    ProductListMapper productListMapper;
    @Autowired
    SalesmanServiceImpl salesmanService;
    @Autowired
    MaterialListMapper materialListMapper;
    @Autowired
    MaterialInformationServiceImpl materialInformationService;
    @Autowired
    ProductInformationServiceImpl productInformationService;
    @Autowired
    ProductInformationMapper productInformationMapper;
    @Autowired
    ListMaterialRequirementServiceImpl listMaterialRequirementService;
    @Autowired
    ReviewRequestServiceImpl reviewRequestService;
    @Autowired
    ProductListServiceImpl productListService;

    @Override
    public List<ProductList> selectProductList() {
        return list();
    }


    @Override
    @Transactional
    public boolean productComplete(Integer id) {
        try {
            ProductList tempProductList = new ProductList();
            QueryWrapper<ProductList> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",id);
            ProductList productListSelect= getOne(queryWrapper);
            if(productListSelect.getTag()==0){
                throw new Exception("审核中，无法执行修改操作");
            }
            if(productListSelect.getProjectStatus().equals("正在包砌")){
                tempProductList.setId(id);
                tempProductList.setProjectStatus("已完成");
                tempProductList.setProjectEnd(new Date(System.currentTimeMillis()));
                if(!saveOrUpdate(tempProductList)){
                    throw new Exception("生产列表更新失败");
                }
            }
            else throw new Exception("无法跳过包砌环节，请先完成包砌");
            boolean flag=true;
            for(ProductList temPro: productListMapper.selectProductListByContractId(productListSelect.getContractId())){
                if(!temPro.getProjectStatus().equals("已完成")){
                    flag=false;
                    break;
                }
            }
            if(flag){
                QueryWrapper<Contract> queryWrapper1=new QueryWrapper<Contract>()
                        .eq("id",productListSelect.getContractId());
                BigDecimal tempTaxRate=salesmanService.getOne(queryWrapper1).getContractTax();
                Contract tempContract=new Contract();
                tempContract.setId(productListSelect.getContractId());
                tempContract.setContractLifecycle("生产完毕");
//                BigDecimal tempTotalGrossProfit=new BigDecimal("0");
//                BigDecimal tempTotalNetProfit=new BigDecimal("0");
//                for(MaterialList ma:materialListMapper.selectMaterialListByContractId(productListSelect.getContractId())){
//                    MaterialInformation tempMaterialInformation=materialInformationService.selectMaterialInformationById(ma.getMaterialId());
//                    tempTotalGrossProfit=tempTotalGrossProfit.add(ma.getMaterialNumber().multiply(ma.getMaterialPrice().divide(BigDecimal.valueOf(1).add(tempTaxRate),2, RoundingMode.HALF_UP).subtract(tempMaterialInformation.getUnitPrice())));
//                    tempTotalNetProfit=tempTotalNetProfit.add(ma.getMaterialNumber().multiply(ma.getMaterialPrice().divide(BigDecimal.valueOf(1).add(tempTaxRate),2, RoundingMode.HALF_UP).subtract(tempMaterialInformation.getUnitPrice().add(tempMaterialInformation.getUnitPrice().multiply(tempMaterialInformation.getLowestAddRate())))));
//                }
//                for(ProductList pro:productListMapper.selectProductListByContractId(productListSelect.getContractId())){
//                    ProductInformationDTO productInformationDTO=productInformationService.selectProductInformationByListId(pro.getProductId(),pro.getId());
//                    tempTotalGrossProfit=tempTotalGrossProfit.add(pro.getProductNumber().multiply(pro.getProductPrice().divide(BigDecimal.valueOf(1).add(tempTaxRate),2, RoundingMode.HALF_UP).subtract(productInformationDTO.getProductPrice())));
//                    tempTotalNetProfit=tempTotalNetProfit.add(pro.getProductNumber().multiply(pro.getProductPrice().divide(BigDecimal.valueOf(1).add(tempTaxRate),2, RoundingMode.HALF_UP).subtract(productInformationDTO.getProductPrice().add(productInformationDTO.getProductPrice().multiply(productInformationDTO.getLowestAddRate())))));
//                }
//                tempContract.setTotalGrossProfit(tempTotalGrossProfit);
//                tempContract.setTotalNetProfit(tempTotalNetProfit);
                if(!salesmanService.saveOrUpdate(tempContract)){
                    throw new Exception("合同状态更新失败");
                }
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public boolean productWrap(Integer id) {
        try {
            ProductList tempProductList = new ProductList();
            QueryWrapper<ProductList> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",id);
            ProductList productListSelect= getOne(queryWrapper);
            if(productListSelect.getTag()==0){
                throw  new Exception("审核中，无法执行修改操作");
            }
            if(productListSelect.getProjectStatus().equals("正在生产本体")){
                tempProductList.setId(id);
                tempProductList.setProjectStatus("正在包砌");
                if(!saveOrUpdate(tempProductList)){
                    throw new Exception("生产列表更新失败");
                }
                return true;
            }
            else throw new Exception("生产列表状态数据损坏");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public boolean materialReturn(MaterialReturnVO materialReturnVO) {
        try {
            boolean flag;
            List<ListMaterialRequirement> listMaterialRequirements= productInformationMapper.selectListMaterialRequirementByListId(materialReturnVO.getListId());
            for(MaterialReturn mn:materialReturnVO.getMaterialReturns()){
                mn.setReturnTime(new java.util.Date(System.currentTimeMillis()));
                mn.setListId(materialReturnVO.getListId());
                mn.setChargePerson(materialReturnVO.getEmployeeNo());
                if(!productListMapper.insertMaterialReturn(mn)){
                    throw new Exception("插入退料信息失败");
                }
                flag=false;

                for(ListMaterialRequirement lis:listMaterialRequirements){
                    if(mn.getMaterialId().equals(lis.getMaterialId())){
                        flag=true;
                        if(mn.getMaterialNumber().compareTo(lis.getMaterialNumber())<0){
                            lis.setMaterialNumber(lis.getMaterialNumber().subtract(mn.getMaterialNumber()));
                            break;
                        }
                        else if(mn.getMaterialNumber().compareTo(lis.getMaterialNumber())==0){
                            lis.setMaterialNumber(lis.getMaterialNumber().subtract(mn.getMaterialNumber()));
                            if(!listMaterialRequirementService.removeById(lis.getId())){
                                throw new Exception("删除用料列表失败");
                            }
                            break;
                        }
                        else {
                            throw new Exception("退料数量大于用料数量");
                        }
                    }
                }
                if(!flag){
                    throw new Exception("退料种类不存在于用料表上");
                }
            }
            listMaterialRequirements.removeIf(listMaterialRequirement -> listMaterialRequirement.getMaterialNumber().compareTo(BigDecimal.valueOf(0)) == 0);
            for(ListMaterialRequirement liss:listMaterialRequirements){
                if(!listMaterialRequirementService.saveOrUpdate(liss)){
                    throw new Exception("用料表插入失败");
                }
            }




            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public boolean materialApply(MaterialApplyVO materialApplyVO) {
        try {
            StringBuilder str=new StringBuilder();
            for(MaterialApply ma:materialApplyVO.getMaterialApplies()){
                ma.setListId(materialApplyVO.getListId());
                ma.setChargePerson(materialApplyVO.getEmployeeNo());
                ma.setApplyTime(new java.util.Date(System.currentTimeMillis()));
                ma.setApplyResult("正在审核");
                if(!productListMapper.insertMaterialApply(ma)){
                    throw new Exception("插入用料申请失败");
                }
                str.append(",");
                str.append(ma.getId().toString());
            }
            str.deleteCharAt(0);

            //插入待审批信息
            ReviewRequest reviewRequest =new ReviewRequest();
            reviewRequest.setReviewType("领料申请");
            reviewRequest.setEmployeeNo(materialApplyVO.getEmployeeNo());
            reviewRequest.setRequestDate(new java.util.Date(System.currentTimeMillis()));
            reviewRequest.setAdditionalInformation(materialApplyVO.getMaterialApplies().toString());
            reviewRequest.setAdditionalInformation(str.toString());
            QueryWrapper<ProductList> queryWrapper6=new QueryWrapper<ProductList>()
                    .eq("id",materialApplyVO.getListId());
            reviewRequest.setIndexNo(productListService.getOne(queryWrapper6).getContractId());
            if (!reviewRequestService.saveOrUpdate(reviewRequest)){
                throw new Exception("保存审批信息失败");
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MaterialReturn> selectMaterialReturn(Integer listId) {
        return productListMapper.selectMaterialReturn(listId);
    }

    @Override
    public List<MaterialApply> selectMaterialApply(Integer listId) {
        return productListMapper.selectMaterialApply(listId);
    }
}
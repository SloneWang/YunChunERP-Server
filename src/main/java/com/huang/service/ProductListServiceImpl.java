package com.huang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.dto.ProductInformationDTO;
import com.huang.entity.Contract;
import com.huang.entity.MaterialInformation;
import com.huang.entity.MaterialList;
import com.huang.entity.ProductList;
import com.huang.mapper.MaterialListMapper;
import com.huang.mapper.ProductListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;

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

    @Override
    public Object selectProductList() {
        return list();
    }


    @Override
    public Object productComplete(Integer id) {
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
                Contract tempContract=new Contract();
                tempContract.setId(productListSelect.getContractId());
                tempContract.setContractLifecycle("生产完毕");

                BigDecimal tempTotalGrossProfit=new BigDecimal("0");
                BigDecimal tempTotalNetProfit=new BigDecimal("0");
                for(MaterialList ma:materialListMapper.selectMaterialListByContractId(productListSelect.getContractId())){
                    MaterialInformation tempMaterialInformation=materialInformationService.selectMaterialInformationById(ma.getMaterialId());
                    tempTotalGrossProfit=tempTotalGrossProfit.add(ma.getMaterialNumber().multiply(ma.getMaterialPrice().subtract(tempMaterialInformation.getUnitPrice())));
                    tempTotalNetProfit=tempTotalNetProfit.add(ma.getMaterialNumber().multiply(ma.getMaterialPrice().subtract(tempMaterialInformation.getUnitPrice().add(tempMaterialInformation.getUnitPrice().multiply(tempMaterialInformation.getLowestAddRate())))));
                }
                for(ProductList pro:productListMapper.selectProductListByContractId(productListSelect.getContractId())){
                    ProductInformationDTO productInformationDTO=productInformationService.selectProductInformationById(pro.getProductId());
                    tempTotalGrossProfit=tempTotalGrossProfit.add(pro.getProductNumber().multiply(pro.getProductPrice().subtract(productInformationDTO.getProductPrice())));
                    tempTotalNetProfit=tempTotalNetProfit.add(pro.getProductNumber().multiply(pro.getProductPrice().subtract(productInformationDTO.getProductPrice().add(productInformationDTO.getProductPrice().multiply(productInformationDTO.getLowestAddRate())))));
                }
                tempContract.setTotalGrossProfit(tempTotalGrossProfit);
                tempContract.setTotalNetProfit(tempTotalNetProfit);
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
    public Object productWrap(Integer id) {
        try {
            ProductList tempProductList = new ProductList();
            QueryWrapper<ProductList> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",id);
            ProductList productListSelect= getOne(queryWrapper);
            if(productListSelect.getTag()==0){
                return new Exception("审核中，无法执行修改操作");
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
}
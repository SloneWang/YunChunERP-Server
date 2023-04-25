package com.huang.controller;


import com.huang.entity.*;
import com.huang.service.MaterialInformationServiceImpl;
import com.huang.service.ProductInformationServiceImpl;
import com.huang.vo.SaveOrUpdateProductInformationVO;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/productInformation")
public class ProductInformationController {
    @Resource
    ProductInformationServiceImpl productInformationService;
    @Resource
    MaterialInformationServiceImpl materialInformationService;

    @GetMapping("/selectProductInformation")
    public Object selectProductInformation(){
        try {
            return productInformationService.selectProductInformation();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @PostMapping("/saveOrUpdateProductInformation")
//    public Object saveOrUpdateProductInformation(@RequestParam("productInformation") String productInformation, @RequestParam("material") String materialRequirements){
//        try {
//              List<MaterialList> ma=new ArrayList<>();
//            return productInformationService.saveOrUpdateProductInformation(JSON.parseObject(productInformation, ProductInformation.class),
//                    JSON.parseObject(materialRequirements, ma.getClass()));
//        } catch (Exception e) {
//            return e.toString();
//        }
//    }

    @PostMapping("/saveOrUpdateProductInformation")
    public boolean saveOrUpdateProductInformation(@RequestBody SaveOrUpdateProductInformationVO saveOrUpdateProductInformationVO){
        try {
            return productInformationService.saveOrUpdateProductInformation(saveOrUpdateProductInformationVO.getProductInformation(),saveOrUpdateProductInformationVO.getMaterialRequirements());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/deleteProductInformation/{id}")
    public boolean deleteProductInformation(@PathVariable Integer id){
        try {
            return productInformationService.deleteProductInformationByid(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/selectMaterialInformation")
    public List<MaterialInformation> selectMaterialInformation(){
        try {
            return materialInformationService.selectMaterialInformation();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/saveOrUpdateMaterialInformation")
    public boolean saveOrUpdateMaterialInfo(@RequestBody MaterialInformation materialInformation){
        try {
            return materialInformationService.saveOrUpdateMaterialInformation(materialInformation);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/deleteMaterialInformation/{id}")
    public boolean deleteMaterialInformation(@PathVariable Integer id){
        try {
            return materialInformationService.deleteMaterialInformationByid(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //查询对应材料的历史价格变动记录
    @GetMapping("/selectMaterialCostRecord/{materialId}")
    public List<MaterialCostRecord> selectMaterialCostRecord(Integer materialId){
        try {
            return materialInformationService.selectMaterialCostRecord(materialId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
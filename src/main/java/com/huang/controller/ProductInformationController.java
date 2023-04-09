package com.huang.controller;

import com.huang.entity.MaterialInformation;
import com.huang.entity.MaterialRequirement;
import com.huang.entity.ProductInformation;
import com.huang.service.MaterialInformationServiceImpl;
import com.huang.service.MaterialListServiceImpl;
import com.huang.service.ProductInformationServiceImpl;
import com.huang.service.ProductListServiceImpl;
import com.huang.vo.SaveOrUpdateProductInformationVO;
import com.huang.vo.UpdateContractVO;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
            return e.toString();
        }
    }

    @PostMapping("/saveOrUpdateProductInformation")
    public Object saveOrUpdateProductInformation(@RequestBody SaveOrUpdateProductInformationVO saveOrUpdateProductInformationVO){
        try {
            return productInformationService.saveOrUpdateProductInformation(saveOrUpdateProductInformationVO.getProductInformation(),saveOrUpdateProductInformationVO.getMaterialRequirements());
        } catch (Exception e) {
            return e.toString();
        }
    }

    @DeleteMapping("/deleteProductInformation/{id}")
    public Object deleteProductInformation(@PathVariable Integer id){
        try {
            return productInformationService.deleteProductInformationByid(id);
        } catch (Exception e) {
            return e.toString();
        }
    }

    @GetMapping("/selectMaterialInformation")
    public Object selectMaterialInformation(){
        try {
            return materialInformationService.selectMaterialInformation();
        } catch (Exception e) {
            return e.toString();
        }
    }

    @PostMapping("/saveOrUpdateMaterialInformation")
    public Object saveOrUpdateMaterialInformation(@RequestBody MaterialInformation materialInformation){
        try {
            return materialInformationService.saveOrUpdateMaterialInformation(materialInformation);
        } catch (Exception e) {
            return e.toString();
        }
    }
    @DeleteMapping("/deleteMaterialInformation/{id}")
    public Object deleteMaterialInformation(@PathVariable Integer id){
        try {
            return materialInformationService.deleteMaterialInformationByid(id);
        } catch (Exception e) {
            return e.toString();
        }
    }
}
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.reader.StreamReader;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.util.List;

@RestController
@RequestMapping("/productInformation")
public class ProductInformationController {
    @Autowired
    ProductInformationServiceImpl productInformationService;
    @Autowired
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
    public List<MaterialInformation> selectMaterialInformation(){
        return materialInformationService.selectMaterialInformation();
    }

    @PostMapping("/saveOrUpdateMaterialInformation")
    public boolean saveOrUpdateMaterialInformation(@RequestBody MaterialInformation materialInformation) throws Exception{

        System.out.println(materialInformation.toString());
        return materialInformationService.saveOrUpdateMaterialInformation(materialInformation);
    }

    @DeleteMapping("/deleteMaterialInformation/{id}")
    public boolean deleteMaterialInformation(@PathVariable Integer id) throws Exception{
        return materialInformationService.deleteMaterialInformationByid(id);
    }
}
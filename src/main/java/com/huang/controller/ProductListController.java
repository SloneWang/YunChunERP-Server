package com.huang.controller;

import com.huang.service.MaterialListServiceImpl;
import com.huang.service.ProductListServiceImpl;
import com.huang.service.ReviewRequestServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/productList")
public class ProductListController {
    @Resource
    ProductListServiceImpl productListService;
    @Resource
    MaterialListServiceImpl materialListService;

    @GetMapping("/selectMaterialList")
    public Object selectMaterialList(){
        try {
            return materialListService.selectMaterialList();
        } catch (Exception e) {
            return e.toString();
        }
    }
    @GetMapping("/selectProductList")
    public Object selectProductList(){
        try {
            return productListService.selectProductList();
        } catch (Exception e) {
            return e.toString();
        }
    }
    @GetMapping("/productComplete/{id}")
    public Object productComplete(@PathVariable Integer id){
        try {
            return productListService.productComplete(id);
        } catch (Exception e) {
            return e.toString();
        }
    }
    @GetMapping("/productWrap/{id}")
    public Object productWrap(@PathVariable Integer id){
        try {
            return productListService.productWrap(id);
        } catch (Exception e) {
            return e.toString();
        }
    }

}

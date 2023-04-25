package com.huang.controller;

import com.huang.entity.*;
import com.huang.service.ListMaterialRequirementServiceImpl;
import com.huang.service.MaterialListServiceImpl;
import com.huang.service.ProductListServiceImpl;
import com.huang.vo.ApplyProductVO;
import com.huang.vo.MaterialApplyVO;
import com.huang.vo.MaterialReturnVO;
import com.huang.vo.ReturnProductVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/productList")
public class ProductListController {
    @Resource
    ProductListServiceImpl productListService;
    @Resource
    MaterialListServiceImpl materialListService;
    @Resource
    ListMaterialRequirementServiceImpl listMaterialRequirementService;

    @GetMapping("/selectMaterialList")
    public List<MaterialList> selectMaterialList(){
        try {
            return materialListService.selectMaterialList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/selectProductList")
    public List<ProductList> selectProductList(){
        try {
            return productListService.selectProductList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/productComplete/{id}")
    public boolean productComplete(@PathVariable Integer id){
        try {
            return productListService.productComplete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/productWrap/{id}")
    public boolean productWrap(@PathVariable Integer id){
        try {
            return productListService.productWrap(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //查询对应生产项目的用料列表
    @GetMapping("/selectMaterialRequirement/{listId}")
    public List<ListMaterialRequirement> selectMaterialRequirement(@PathVariable Integer listId){
        try {
            return listMaterialRequirementService.selectMaterialRequirement(listId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //退货
    @PostMapping("/returnProduct")
    public boolean returnProduct(@RequestBody ReturnProductVO returnProductVO){
        try {
            return materialListService.returnProduct(returnProductVO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //补货
    @PostMapping("/applyProduct")
    public boolean applyProduct(@RequestBody ApplyProductVO applyProductVO){
        try {
            return materialListService.applyProduct(applyProductVO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //查询退货申请记录及结果
    @GetMapping("/selectReturnProduct/{contractId}")
    public List<ReturnProduct> selectReturnProduct(@PathVariable Integer contractId){
        try {
            return materialListService.selectReturnProduct(contractId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //查询补货申请记录及结果
    @GetMapping("/selectApplyProduct/{contractId}")
    public List<ApplyProduct> selectApplyProduct(@PathVariable Integer contractId){
        try {
            return materialListService.selectApplyProduct(contractId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //通过对应生产项目id退料
    @PostMapping("/materialReturn")
    public boolean materialReturn(@RequestBody MaterialReturnVO materialReturnVO){
        try {
            return productListService.materialReturn(materialReturnVO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //通过对应生产项目id发送补料申请
    @PostMapping("/materialApply")
    public boolean materialApply(@RequestBody MaterialApplyVO materialApplyVO){
        try {
            return productListService.materialApply(materialApplyVO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //查询退料记录
    @GetMapping("/selectMaterialReturn/{listId}")
    public List<MaterialReturn> selectMaterialReturn(@PathVariable Integer listId){
        try {
            return productListService.selectMaterialReturn(listId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //查询补料申请记录及结果
    @GetMapping("/selectMaterialApply/{listId}")
    public List<MaterialApply> selectMaterialApply(@PathVariable Integer listId){
        try {
            return productListService.selectMaterialApply(listId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

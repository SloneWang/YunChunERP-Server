package com.huang.controller;

import com.huang.common.Result;
import com.huang.service.SalesmanServiceImpl;
import com.huang.vo.SaveContractVO;
import com.huang.vo.UpdateContractVO;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/salesman")
public class SalesmanController {
    @Resource
    SalesmanServiceImpl salesmanService;

    @GetMapping("/simpleContractSelect")
    //List<ContractSimpleDTO>
    public Result simpleContractSelect (){
        return salesmanService.contractSimp();
    }

    @GetMapping("/selectContract/{id}")
    //Contract
    public Result selectContractById(HttpServletResponse response,@PathVariable Integer id){
        return salesmanService.contract(response,id);
    }

    @GetMapping("/productInformation")
    //List<ProductInformation>
    public Result selectProductInformation(){
        return salesmanService.productInformation();
    }

    @PostMapping("/saveContract")
    public Result saveContract(@RequestParam("file") CommonsMultipartFile file, @RequestBody SaveContractVO saveContractVO){
        return salesmanService.saveContract(file,saveContractVO);
    }

    @PostMapping("/updateContract")
    public Result updateContract(@RequestParam("file") CommonsMultipartFile file, @RequestBody UpdateContractVO updateContractVO){
        return salesmanService.updateContract(file,updateContractVO);
    }

    @GetMapping("/contractHistory")
    //List<ContractHistory>
    public Result contractHistory(){
        return salesmanService.contractHistory();
    }

    @DeleteMapping ("/deleteContract/{id}/{employeeNo}")
    public Result deleteContract(@PathVariable Integer id,@PathVariable String employeeNo){
        return salesmanService.deleteContract(id,employeeNo);
    }
}

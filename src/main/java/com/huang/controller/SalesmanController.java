package com.huang.controller;

import com.huang.common.Result;
import com.huang.entity.CustomerInformation;
import com.huang.service.CustomerInformationServiceImpl;
import com.huang.service.SalesmanServiceImpl;
import com.huang.vo.SaveContractVO;
import com.huang.vo.UpdateContractVO;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import cn.hutool.json.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/salesman")
public class SalesmanController {
    @Resource
    SalesmanServiceImpl salesmanService;
    @Resource
    CustomerInformationServiceImpl customerInformationService;

    @GetMapping("/simpleContractSelect")
    //List<ContractSimpleDTO>
    public Object simpleContractSelect (){
        return salesmanService.contractSimp();
    }

    @GetMapping("/selectContract/{id}")
    //ContractDTO
    public Object selectContractById(HttpServletResponse response,@PathVariable Integer id){
        return salesmanService.contract(response,id);
    }

    @GetMapping("/productInformation")
    //List<ProductInformation>
    public Object selectProductInformation(){
        return salesmanService.productInformation();
    }

    @PostMapping("/saveContract")
    public Object saveContract(@RequestParam("file") MultipartFile file, @RequestParam("contract") String saveContractVO){
        return salesmanService.saveContract(file,JSONUtil.toBean(saveContractVO, SaveContractVO.class));
    }

    @PostMapping("/updateContract")
    public Object updateContract(@RequestParam("file") MultipartFile file, @RequestParam("contract") String updateContractVO){
        return salesmanService.updateContract(file, JSONUtil.toBean(updateContractVO, UpdateContractVO.class));
    }

    @GetMapping("/contractHistory")
    //List<ContractHistory>
    public Object contractHistory(){
        return salesmanService.contractHistory();
    }

    @DeleteMapping ("/deleteContract/{id}/{employeeNo}")
    public Object deleteContract(@PathVariable Integer id,@PathVariable String employeeNo){
        return salesmanService.deleteContract(id,employeeNo);
    }
    @GetMapping("/selectCustomer")
    public Object selectCustomer(){
        return customerInformationService.selectAllCustomer();
    }
    @PostMapping("/saveCustomerInformation")
    public Object saveCustomerInformation(@RequestBody CustomerInformation customerInformation){
        return customerInformationService.saveOrUpdateCustomer(customerInformation);
    }
}

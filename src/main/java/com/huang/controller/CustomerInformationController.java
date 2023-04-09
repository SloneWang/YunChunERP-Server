package com.huang.controller;

import com.huang.entity.CustomerInformation;
import com.huang.service.CustomerInformationServiceImpl;
import com.huang.service.MaterialInformationServiceImpl;
import com.huang.service.ProductInformationServiceImpl;
import com.huang.vo.UpdateContractVO;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/customerInformation")
public class CustomerInformationController {
    @Resource
    CustomerInformationServiceImpl customerInformationService;

    @GetMapping("/selectAllCustomer")
    public Object selectAllCustomer(){
        try {
            return customerInformationService.selectAllCustomer();
        } catch (Exception e) {
            return e.toString();
        }
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public Object deleteCustomer(@PathVariable Integer id){
        try {
            return customerInformationService.deleteCustomerByid(id);
        } catch (Exception e) {
            return e.toString();
        }
    }

    @PostMapping("/saveOrUpdateCustomer")
    public Object saveOrUpdateCustomer(@RequestBody CustomerInformation customerInformation){
        try {
            return customerInformationService.saveOrUpdateCustomer(customerInformation);
        } catch (Exception e) {
            return e.toString();
        }
    }
}
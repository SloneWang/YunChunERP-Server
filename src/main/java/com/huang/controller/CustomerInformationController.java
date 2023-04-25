package com.huang.controller;

import com.huang.entity.CustomerInformation;
import com.huang.service.CustomerInformationServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/customerInformation")
public class CustomerInformationController {
    @Resource
    CustomerInformationServiceImpl customerInformationService;

    @GetMapping("/selectAllCustomer")
    public List<CustomerInformation> selectAllCustomer(){
        try {
            return customerInformationService.selectAllCustomer();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public boolean deleteCustomer(@PathVariable Integer id){
        try {
            return customerInformationService.deleteCustomerByid(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/saveOrUpdateCustomer")
    public boolean saveOrUpdateCustomer(@RequestBody CustomerInformation customerInformation){
        try {
            return customerInformationService.saveOrUpdateCustomer(customerInformation);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
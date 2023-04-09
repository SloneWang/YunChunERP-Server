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
import java.io.IOException;
import java.math.BigDecimal;

@RestController
@RequestMapping("/salesman")
public class SalesmanController {
    @Resource
    SalesmanServiceImpl salesmanService;

    @PostMapping("/saveContract")
    public Object saveContract(@RequestBody SaveContractVO saveContractVO){
        try {
            return salesmanService.saveContract(saveContractVO);
        } catch (Exception e) {
            return e.toString();
        }
    }
    @PostMapping("/updateContract")
    public Object updateContract(@RequestBody UpdateContractVO updateContractVO){
        try {
            return salesmanService.updateContract(updateContractVO);
        } catch (Exception e) {
            return e.toString();
        }
    }
    @GetMapping("/simpleContractSelect/{employeeNo}")
    public Object simpleContractSelect (@PathVariable String employeeNo){
        try {
            return salesmanService.contractSimp(employeeNo);
        } catch (Exception e) {
            return e.toString();
        }
    }
    @GetMapping("/selectContract/{id}")
    public Object selectContractById(@PathVariable Integer id){
        try {
            return salesmanService.contract(id);
        } catch (Exception e) {
            return e.toString();
        }
    }
    @GetMapping("/contractHistory")
    public Object contractHistory(){
        try {
            return salesmanService.contractHistory();
        } catch (Exception e) {
            return e.toString();
        }
    }
    @DeleteMapping ("/deleteContract/{id}/{employeeNo}")
    public Object deleteContract(@PathVariable Integer id,@PathVariable String employeeNo){
        try {
            return salesmanService.deleteContract(id,employeeNo);
        } catch (Exception e) {
            return e.toString();
        }
    }
    @GetMapping("/pickComplete/{id}")
    public Object pickComplete(@PathVariable Integer id){
        try {
            return salesmanService.pickComplete(id);
        } catch (Exception e) {
            return e.toString();
        }
    }
    @GetMapping("/installComplete/{id}/{installCost}")
    public Object installComplete(@PathVariable Integer id, @PathVariable BigDecimal installCost){
        try {
            return salesmanService.installComplete(id,installCost);
        } catch (Exception e) {
            return e.toString();
        }
    }
    @GetMapping("/warrantyComplete/{id}")
    public Object warrantyComplete(@PathVariable Integer id){
        try {
            return salesmanService.warrantyComplete(id);
        } catch (Exception e) {
            return e.toString();
        }
    }
    @PostMapping("/saveOrUpdateContractFile")
    public Object saveOrUpdateContractFile(@RequestParam("file") MultipartFile file, @RequestParam("contract") String id){
        try {
            return salesmanService.saveOrUpdateContractFile(file,Integer.parseInt(id));
        } catch (IOException e) {
            return e.toString();
        }
    }
    @GetMapping("/selectContractFile/{id}")
    public Object selectContractFile(HttpServletResponse response,@PathVariable Integer id){
        try {
            return salesmanService.selectContractFile(response,id);
        } catch (Exception e) {
            return e.toString();
        }
    }

}

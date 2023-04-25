package com.huang.controller;


import com.huang.dto.AllContractDTO;
import com.huang.dto.ContractSimpleDTO;
import com.huang.entity.ContractHistory;
import com.huang.service.SalesmanServiceImpl;
import com.huang.vo.SaveContractVO;
import com.huang.vo.UpdateContractVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/salesman")
public class SalesmanController {
    @Resource
    SalesmanServiceImpl salesmanService;

    @PostMapping("/saveContract")
    public boolean saveContract(@RequestBody SaveContractVO saveContractVO){
        try {
            return salesmanService.saveContract(saveContractVO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/updateContract")
    public boolean updateContract(@RequestBody UpdateContractVO updateContractVO){
        try {
            return salesmanService.updateContract(updateContractVO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/simpleContractSelect/{employeeNo}")
    public List<ContractSimpleDTO> simpleContractSelect (@PathVariable String employeeNo){
        try {
            return salesmanService.contractSimp(employeeNo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/selectContract/{id}")
    public AllContractDTO selectContractById(@PathVariable Integer id){
        try {
            return salesmanService.contract(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/contractHistory")
    public List<ContractHistory> contractHistory(){
        try {
            return salesmanService.contractHistory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping ("/deleteContract/{id}/{employeeNo}")
    public boolean deleteContract(@PathVariable Integer id,@PathVariable String employeeNo){
        try {
            return salesmanService.deleteContract(id,employeeNo,employeeNo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/pickComplete/{id}")
    public boolean pickComplete(@PathVariable Integer id){
        try {
            return salesmanService.pickComplete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/installComplete/{id}/{installCost}")
    public boolean installComplete(@PathVariable Integer id, @PathVariable BigDecimal installCost){
        try {
            return salesmanService.installComplete(id,installCost);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/warrantyComplete/{id}")
    public boolean warrantyComplete(@PathVariable Integer id){
        try {
            return salesmanService.warrantyComplete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/saveOrUpdateContractFile")
    public boolean saveOrUpdateContractFile(@RequestParam("file") MultipartFile file, @RequestParam("contract") String id){
        try {
            return salesmanService.saveOrUpdateContractFile(file,Integer.parseInt(id));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/selectContractFile/{id}")
    public boolean selectContractFile(HttpServletResponse response,@PathVariable Integer id){
        try {
            return salesmanService.selectContractFile(response,id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/deleteContractLimit/{id}/{employeeNo}/{requestComment}")
    public boolean deleteContractLimit(@PathVariable Integer id,@PathVariable String employeeNo,@PathVariable String requestComment){
        try {
            return salesmanService.deleteContractLimit(id,employeeNo,requestComment);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/cancelContract/{id}/{employeeNo}")
    public boolean deleteContractLimit(@PathVariable Integer id,@PathVariable String employeeNo){
        try {
            return salesmanService.cancelContract(id,employeeNo,employeeNo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/cancelContractLimit/{id}/{employeeNo}/{requestComment}")
    public boolean cancelContractLimit(@PathVariable Integer id,@PathVariable String employeeNo,@PathVariable String requestComment){
        try {
            return salesmanService.cancelContractLimit(id,employeeNo,requestComment);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.huang.entity.Contract;

import com.huang.vo.SaveContractVO;
import com.huang.vo.UpdateContractVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public interface SalesmanService extends IService<Contract> {

    Object saveContract(SaveContractVO saveData);
    Object updateContract(UpdateContractVO updateData);
    Object contractSimp(String employeeNo);
    Object contract(Integer id);
    Object contractHistory();
    Object deleteContract(Integer id,String employeeNo);
    Object pickComplete(Integer id);
    Object installComplete(Integer id, BigDecimal installCost);
    Object warrantyComplete(Integer id);
    Object saveOrUpdateContractFile(MultipartFile file,Integer id) throws IOException;
    Object selectContractFile(HttpServletResponse response,Integer id);
    Contract selectUpdateContractInformation(Integer id);

}

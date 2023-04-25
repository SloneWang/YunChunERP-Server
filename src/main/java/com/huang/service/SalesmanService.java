package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.huang.dto.AllContractDTO;
import com.huang.dto.ContractSimpleDTO;
import com.huang.entity.Contract;

import com.huang.entity.ContractHistory;
import com.huang.vo.SaveContractVO;
import com.huang.vo.UpdateContractVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface SalesmanService extends IService<Contract> {

    boolean saveContract(SaveContractVO saveData);
    boolean updateContract(UpdateContractVO updateData);
    List<ContractSimpleDTO> contractSimp(String employeeNo);
    AllContractDTO contract(Integer id);
    List<ContractHistory> contractHistory();
    boolean deleteContract(Integer id,String employeeNo,String reviewNo);
    boolean pickComplete(Integer id);
    boolean installComplete(Integer id, BigDecimal installCost);
    boolean warrantyComplete(Integer id);
    boolean saveOrUpdateContractFile(MultipartFile file,Integer id) throws IOException;
    boolean selectContractFile(HttpServletResponse response,Integer id);
    Contract selectUpdateContractInformation(Integer id);
    boolean deleteContractLimit(Integer id,String employeeNo,String requestComment);
    boolean cancelContract(Integer id,String employeeNo,String reviewNo);
    boolean cancelContractLimit(Integer id,String employeeNo,String requestComment);
    void autoUpdate();
}

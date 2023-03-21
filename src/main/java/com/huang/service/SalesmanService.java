package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.common.Result;
import com.huang.dto.ContractSimpleDTO;
import com.huang.entity.Contract;
import com.huang.entity.ContractHistory;
import com.huang.entity.ProductInformation;
import com.huang.vo.SaveContractVO;
import com.huang.vo.UpdateContractVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface SalesmanService extends IService<Contract> {
    //List<ContractSimpleDTO>
    Object contractSimp();
    //Contract
    Object contract(HttpServletResponse response,Integer id);
    //List<ProductInformation>
    Object productInformation();
    Object saveContract(MultipartFile file,SaveContractVO saveData) throws IOException;
    Object updateContract(MultipartFile file,UpdateContractVO updateData);
    //List<ContractHistory>
    Object contractHistory();
    Object deleteContract(Integer id,String employeeNo);
}

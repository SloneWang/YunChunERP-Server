package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.common.Result;
import com.huang.dto.ContractSimpleDTO;
import com.huang.entity.Contract;
import com.huang.entity.ContractHistory;
import com.huang.entity.ProductInformation;
import com.huang.vo.SaveContractVO;
import com.huang.vo.UpdateContractVO;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface SalesmanService extends IService<Contract> {
    //List<ContractSimpleDTO>
    Result contractSimp();
    //Contract
    Result contract(HttpServletResponse response,Integer id);
    //List<ProductInformation>
    Result productInformation();
    Result saveContract(CommonsMultipartFile file,SaveContractVO saveData) throws IOException;
    Result updateContract(CommonsMultipartFile file,UpdateContractVO updateData);
    //List<ContractHistory>
    Result contractHistory();
    Result deleteContract(Integer id,String employeeNo);
}

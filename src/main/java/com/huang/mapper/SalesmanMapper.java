package com.huang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huang.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SalesmanMapper extends BaseMapper<Contract> {
     List<ProductInformation> selectProduct();

     List<User> selectEmployeeInformation(String employeeNo);

     List<ProductInformation> selectOneProduct(String productNo);

     List<Contract> selectContractById(Integer id);
     List<ContractHistory> selectContractHistory();
     Boolean deleteByContractNumber(String contractNumber);
     boolean insertContractHistory(ContractHistory contractHistory);
     List<Contract> selectAllContract();
     List<Contract> selectContractInformationByEmployeeNo(String employeeNo);
     List<SysSettings> selectTaxRate();
     boolean insertPayReturn(PayReturn payReturn);
     boolean updatePayReturn(String status,Integer id);
     List<PayReturn> selectPayReturn();
     List<PayReturn> selectPayReturnById(Integer id);
}
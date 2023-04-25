package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.common.Result;
import com.huang.entity.CustomerInformation;

import java.util.List;

public interface CustomerInformationService extends IService<CustomerInformation> {
    boolean saveOrUpdateCustomer(CustomerInformation customerInformation);
    List<CustomerInformation> selectAllCustomer();
    boolean deleteCustomerByid(Integer id);
}

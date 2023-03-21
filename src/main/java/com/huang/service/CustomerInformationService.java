package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.common.Result;
import com.huang.entity.CustomerInformation;

public interface CustomerInformationService extends IService<CustomerInformation> {
    Object saveOrUpdateCustomer(CustomerInformation customerInformation);
    Object selectAllCustomer();
}

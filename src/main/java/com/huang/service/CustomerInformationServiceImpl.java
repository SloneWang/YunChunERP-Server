package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.common.Result;
import com.huang.entity.CustomerInformation;
import com.huang.mapper.CustomerInformationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerInformationServiceImpl extends ServiceImpl<CustomerInformationMapper, CustomerInformation> implements CustomerInformationService {
    @Autowired
    CustomerInformationMapper customerInformationMapper;

    @Override
    @Transactional
    public Object saveOrUpdateCustomer(CustomerInformation customerInformation) {
        List<CustomerInformation> customerTemp=customerInformationMapper.selectOneCustomer(customerInformation.getMemberNo());
        if(customerTemp.size()!=0){
            if(!customerTemp.get(0).getMemberNo().equals(customerInformation.getMemberNo())){
                return "会员号码禁止修改";
            }
            else {
                if(!saveOrUpdate(customerInformation)){
                    return "未能成功保存客户信息";
                }
                else return true;
            }
        }
        if(!saveOrUpdate(customerInformation)){
            return "未能成功保存客户信息";
        }
        else return true;
    }

    @Override
    public Object selectAllCustomer() {
        return list();
    }
}

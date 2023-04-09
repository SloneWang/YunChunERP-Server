package com.huang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
        try {
            QueryWrapper<CustomerInformation> queryWrapper = new QueryWrapper<CustomerInformation>()
                    .eq("member_no",customerInformation.getMemberNo())
                    .eq("tag",0);
            List<CustomerInformation> tempCustomerInformation=list(queryWrapper);
            if(tempCustomerInformation.size()!=0){
                removeById(tempCustomerInformation.get(0).getId());
            }
            QueryWrapper<CustomerInformation> queryWrapper1=new QueryWrapper<CustomerInformation>()
                    .eq("id",customerInformation.getId());
            List<CustomerInformation> customerTemp=list(queryWrapper1);
            if(customerTemp.size()!=0){
                if(!customerTemp.get(0).getMemberNo().equals(customerInformation.getMemberNo())){
                    throw new Exception("会员号码禁止修改");
                }
            }
            if(!saveOrUpdate(customerInformation)){
                return "未能成功保存客户信息";
            }
            else return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object selectAllCustomer() {
        QueryWrapper<CustomerInformation> queryWrapper=new QueryWrapper<CustomerInformation>()
                .eq("tag",1);
        return list(queryWrapper);
    }

    @Override
    public Object deleteCustomerByid(Integer id) {
        try {
            CustomerInformation customerInformation=new CustomerInformation();
            customerInformation.setId(id);
            customerInformation.setTag(0);
            if(!saveOrUpdate(customerInformation)){
                throw new Exception("删除会员信息失败");
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

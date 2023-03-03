package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.entity.CustomerInformation;
import com.huang.mapper.CustomerInformationMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerInformationServiceImpl extends ServiceImpl<CustomerInformationMapper, CustomerInformation> implements CustomerInformationService {
}

package com.huang.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.entity.Cost;
import com.huang.mapper.CostMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostServiceImpl extends ServiceImpl<CostMapper, Cost> implements CostService {


    @Override
    public List<Cost> find() {
        return list();
    }

    @Override
    public boolean saveUser(Cost cost) {
        return saveOrUpdate(cost);
    }

    @Override
    public boolean deleteById(int id) {
        return removeById(id);
    }

    @Override
    public boolean deleteIds(List<Integer> ids) {
        return removeBatchByIds(ids);
    }
}

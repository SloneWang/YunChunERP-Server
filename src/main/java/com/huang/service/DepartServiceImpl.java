package com.huang.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.entity.Depart;
import com.huang.mapper.DepartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartServiceImpl extends ServiceImpl<DepartMapper, Depart> implements DepartService {
    @Autowired
    private DepartMapper departMapper;

    @Override
    public List<Depart> findAll() {
        return list();
    }

    @Override
    public boolean saveUser(Depart depart) {
        return saveOrUpdate(depart);
    }

    @Override
    public boolean deleteById(int id) {
        return removeById(id);
    }

    @Override
    public boolean deleteIds(List<Integer> ids) {
        return removeBatchByIds(ids);
    }

    @Override
    public Page<Depart> finPage(Page<Depart> page,String name) {
        return departMapper.findPage(page,name);
    }
}

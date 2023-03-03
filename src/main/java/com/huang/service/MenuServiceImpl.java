package com.huang.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.entity.Menu;
import com.huang.mapper.MenuMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService  {
    @Override
    public List<Menu> find() {
        return list();
    }

    @Override
    public boolean saveUser(Menu menu) {
        return saveOrUpdate(menu);
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
    public List<Menu> findMenus(String name) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        //判断下名字不为空的时候再拼接
        if(StrUtil.isNotBlank(name)){
            queryWrapper.like("name",name);
        }
        //查询所有的数据
        List<Menu> list = list(queryWrapper);
        //找出pid为null所有的一级菜单
        List<Menu> parentNodes = list.stream().filter(menu -> menu.getPid() == null).collect(Collectors.toList());
        //找出一级菜单的所有的子菜单
        for (Menu menu:parentNodes) {
            //这里因为pid有可能为空，所有放在等价后面
            menu.setChildren(list.stream().filter(m -> menu.getId().equals(m.getPid()) ).collect(Collectors.toList()));
        }
        return parentNodes;
    }
}

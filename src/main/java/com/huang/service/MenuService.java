package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.entity.Menu;
import com.huang.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface MenuService extends IService<Menu> {
    //查找全部数据
    public List<Menu> find();


    //保存对象，包含更新和新增
    public boolean saveUser(Menu menu);

    //删除
    public boolean deleteById(@Param("id") int id);

    //批量删除
    public boolean deleteIds(@RequestBody List<Integer> ids);

    public List<Menu> findMenus(String name);
}

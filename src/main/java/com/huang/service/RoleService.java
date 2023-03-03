package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.huang.entity.Role;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface RoleService extends IService<Role> {
    //查找全部数据
    public List<Role> find();


    //保存对象，包含更新和新增
    public boolean saveUser(Role role);

    //删除
    public boolean deleteById(@Param("id") int id);

    //批量删除
    public boolean deleteIds(@RequestBody List<Integer> ids);


    public void setRoleMenu(Integer roleId,List<Integer> menuIds);

    //返回菜单数组
    public List<Integer> getRoleMenu(Integer roleId);
}

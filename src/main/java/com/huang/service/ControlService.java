package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.entity.Control;
import com.huang.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;
@Service
public interface ControlService extends IService<Control> {

    //查找全部数据
    public List<Control> find();


    //保存对象，包含更新和新增
    public boolean saveUser(Control control);

    //删除
    public boolean deleteById(@Param("id") int id);

    //批量删除
    public boolean deleteIds(@RequestBody List<Integer> ids);

}

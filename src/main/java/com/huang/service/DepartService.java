package com.huang.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.entity.Depart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface DepartService extends IService<Depart> {
    //    public List<User> findAll();
    public List<Depart> findAll();

    //保存对象的方法包含新增以及更新的操作，根据ID看情况
//    public int save(User user);
    public boolean saveUser(Depart depart);
    //删除
    public boolean deleteById(@Param("id") int id);

//    //分页查找
//    public List<User> selectPage(int pageNum,int PageSize,String username,String email,String address);
//
//    //查找分页总条数
//    public int selectTotal(String username,String email, String address);


    //批量删除
    public boolean deleteIds(@RequestBody List<Integer> ids );

    Page<Depart> finPage(Page<Depart> objectPage,String name);
}

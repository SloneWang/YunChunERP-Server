package com.huang.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.common.Result;
import com.huang.entity.Feeding;
import com.huang.entity.Role;
import com.huang.service.RoleServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleServiceImpl roleService;

    //新增或更新
    @PostMapping
    public boolean save(@RequestBody Role role){

        return roleService.saveUser(role);
    }
    //删除
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){
        return roleService.deleteById(id);
    }

    //批量删除
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
            return roleService.deleteIds(ids);
    }

    @GetMapping("/")
    //查找所有用户
    public List<Role> findAll(){
        return roleService.find();
    }

    @GetMapping("/page") //接口路径：/role/page
    public IPage<Role> findPage(@RequestParam int pageNum,
                                   @RequestParam int pageSize,
                                   @RequestParam(defaultValue = "") String name) {

        //前端首先拿到一个我们需要进入的页面，比如要进第三页，但实际上数据库拿到是需要对接受的页码参数进行处理，需要减-1乘上每页显示的行数才是我们真正的pageNum
//        这个应该放在servce层进行处理，controller直接调用
//        pageNum=(pageNum-1)*pageSize;
        //需要显示一共查找了多少数据
        IPage<Role> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if(!"".equals(name)){
            queryWrapper.like("name",name);
        }


        IPage<Role> roleIPage  = roleService.page(page, queryWrapper);
        return roleIPage;
    }
    /**
     * 绑定角色和菜单的关系
     * @param roleId 角色id
     * @param menuIds 菜单id数组
     * @return

     */
    //当传递的是数组对象的时候要用requestBody
    @PostMapping("/roleMenu/{roleId}")
    public Result roleMenu(@PathVariable Integer roleId, @RequestBody List<Integer> ids){
        roleService.setRoleMenu(roleId,ids);
        return Result.success();

    }
    @GetMapping("/roleMenu/{roleId}")
    public Result getRoleMenu(@PathVariable Integer roleId){
        List<Integer> roleMenu = roleService.getRoleMenu(roleId);
        return Result.success(roleMenu);
    }

}

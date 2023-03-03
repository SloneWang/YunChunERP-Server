package com.huang.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.common.Constants;
import com.huang.common.Result;
import com.huang.entity.Dict;
import com.huang.entity.Menu;
import com.huang.entity.Role;
import com.huang.mapper.DictMapper;
import com.huang.service.MenuServiceImpl;
import io.lettuce.core.resource.KqueueProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuServiceImpl menuService;

    @Autowired
    private DictMapper dictMapper;

    //新增或更新
    @PostMapping
    public boolean save(@RequestBody Menu menu){

        return menuService.saveUser(menu);
    }
    //删除
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){
        return menuService.deleteById(id);
    }

    //批量删除
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return menuService.deleteIds(ids);
    }

    @GetMapping("/")
    public Result findAll(@RequestParam(defaultValue = "") String name){

        return Result.success(menuService.findMenus(name));
    }

    @GetMapping("/page") //接口路径：/role/page
    public IPage<Menu> findPage(@RequestParam int pageNum,
                                @RequestParam int pageSize,
                                @RequestParam(defaultValue = "") String name) {

        //前端首先拿到一个我们需要进入的页面，比如要进第三页，但实际上数据库拿到是需要对接受的页码参数进行处理，需要减-1乘上每页显示的行数才是我们真正的pageNum
//        这个应该放在servce层进行处理，controller直接调用
//        pageNum=(pageNum-1)*pageSize;
        //需要显示一共查找了多少数据
        IPage<Menu> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        if(!"".equals(name)){
            queryWrapper.like("name",name);
        }


        IPage<Menu> menuIPage  = menuService.page(page, queryWrapper);
        return menuIPage;
    }
    @GetMapping("/icons")
    public Result getIcons(){
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", Constants.DICT_TYPE_ICON);
        return Result.success(dictMapper.selectList(queryWrapper));
    }


}

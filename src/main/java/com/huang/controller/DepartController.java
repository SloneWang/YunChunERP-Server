package com.huang.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.entity.Depart;
import com.huang.service.DepartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Depart")
public class DepartController {
    @Autowired
    DepartServiceImpl departService;

    @GetMapping("/")
    //查找所有用户
    public List<Depart> findAll(){
        return departService.findAll();
    }
    //RequestBody是可以将前端的json转为后端java对象的
    @PostMapping
    // 这个方法现在就包含了更新和新增的操作
    public boolean save(@RequestBody Depart Depart){
//        return userService.save(user);
        return departService.saveUser(Depart);
    }

    @DeleteMapping("/{id}")
    //pathvariable后面指定的id跟mapping请求里的id需要一致,他是获取路径里的参数的，也就是指/这种下面的参数
    public boolean  delete(@PathVariable int id){
        return departService.deleteById(id);
    }



    //批量删除
    @PostMapping("/del/batch")
    //因为我们要批量传入ID了，需要用到requestBody
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return departService.deleteIds(ids);
    }



    @GetMapping("/page") //接口路径：/user/page
    public Page<Depart> findPage(@RequestParam int pageNum,
                                @RequestParam int pageSize,
                                @RequestParam(defaultValue = "") String name,
                                @RequestParam(defaultValue = "" ) String phoneNumber,
                                @RequestParam(defaultValue = "") String pay,
                                @RequestParam(defaultValue = "") String location) {

//        //前端首先拿到一个我们需要进入的页面，比如要进第三页，但实际上数据库拿到是需要对接受的页码参数进行处理，需要减-1乘上每页显示的行数才是我们真正的pageNum
////        这个应该放在servce层进行处理，controller直接调用
////        pageNum=(pageNum-1)*pageSize;
//        //需要显示一共查找了多少数据
//        IPage<Depart> page = new Page<>(pageNum, pageSize);
//        QueryWrapper<Depart> queryWrapper = new QueryWrapper<>();
//        //引号里的是数据库字段名
//        if(!"".equals(name)){
//            queryWrapper.like("name",name);
//        }
//        if(!"".equals(phoneNumber)){
//            queryWrapper.like("phoneNumber", phoneNumber);
//        }
//        if(!"".equals(pay)){
//            queryWrapper.like("pay", pay);
//        }
//        if(!"".equals(location)){
//            queryWrapper.like("location", location);
//        }
//
//        IPage<Depart> DepartIPage = departService.page(page, queryWrapper);

        Page<Depart> page = departService.finPage(new Page<>(pageNum,pageSize),name);
//
//        return DepartIPage;
        return page;
    }
    
}

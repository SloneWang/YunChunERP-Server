package com.huang.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.entity.Control;


import com.huang.service.ControlServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/control")
public class ControlController {
    @Autowired
    ControlServiceImpl controlService;

    @GetMapping("/")
    //查找所有用户
    public List<Control> findAll(){
        return controlService.find();
    }
    //RequestBody是可以将前端的json转为后端java对象的
    @PostMapping
    // 这个方法现在就包含了更新和新增的操作
    public boolean save(@RequestBody Control control){
//        return userService.save(user);
        return controlService.saveUser(control);
    }

    @DeleteMapping("/{id}")
    //pathvariable后面指定的id跟mapping请求里的id需要一致,他是获取路径里的参数的，也就是指/这种下面的参数
    public boolean  delete(@PathVariable int id){
        return controlService.deleteById(id);
    }



    //批量删除
    @PostMapping("/del/batch")
    //因为我们要批量传入ID了，需要用到requestBody
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return controlService.deleteIds(ids);
    }



    @GetMapping("/page") //接口路径：/user/page
    public IPage<Control> findPage(@RequestParam int pageNum,
                                @RequestParam int pageSize) {

        //前端首先拿到一个我们需要进入的页面，比如要进第三页，但实际上数据库拿到是需要对接受的页码参数进行处理，需要减-1乘上每页显示的行数才是我们真正的pageNum
//        这个应该放在servce层进行处理，controller直接调用
//        pageNum=(pageNum-1)*pageSize;
        //需要显示一共查找了多少数据
        IPage<Control> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Control> queryWrapper = new QueryWrapper<>();



        IPage<Control> controlIPage = controlService.page(page, queryWrapper);
        return controlIPage;
    }


}

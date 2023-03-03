package com.huang.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.common.Result;
import com.huang.dto.UserPasswordDTO;
import com.huang.entity.User;
import com.huang.mapper.UserMapper;
import com.huang.service.UserServiceImpl;
import com.huang.utils.TokenUtils;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/")
    //查找所有用户
    public List<User> findAll(){
        return userService.findAll();
    }
    //RequestBody是可以将前端的json转为后端java对象的

    @PostMapping
   // 这个方法现在就包含了更新和新增的操作
    public boolean save(@RequestBody User user){
//        return userService.save(user);
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    //pathvariable后面指定的id跟mapping请求里的id需要一致,他是获取路径里的参数的，也就是指/这种下面的参数
    public boolean  delete(@PathVariable int id){
       return userService.deleteById(id);
    }



    @GetMapping("/role/{role}")
    public Result findUsersByRole(@PathVariable String role){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role",role);
        List<User> list = userService.list(queryWrapper);
        return Result.success(list);
    }


    //批量删除
    @PostMapping("/del/batch")
    //因为我们要批量传入ID了，需要用到requestBody
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return userService.deleteIds(ids);
    }

    //分页查询接口
    //接口路径：/user/page
    //@RequestParam接受 ?pageNum=1&pageSize=10映射到下方来
    //结论：limit 第一个的参数=（pageNum-1)*pageSize
    //第二个参数就是pagesize
//    @GetMapping("/page") //接口路径：/user/page
//    public Map<String,Object> findPage(@RequestParam int pageNum,
//                                       @RequestParam int pageSize,
//                                       @RequestParam String username,
//                                       @RequestParam String email,
//                                       @RequestParam String address) {
//
//        //前端首先拿到一个我们需要进入的页面，比如要进第三页，但实际上数据库拿到是需要对接受的页码参数进行处理，需要减-1乘上每页显示的行数才是我们真正的pageNum
////        这个应该放在servce层进行处理，controller直接调用
////        pageNum=(pageNum-1)*pageSize;
//        //需要显示一共查找了多少数据
//        int total = userService.selectTotal(username,email,address);
//        List<User> users = userService.selectPage(pageNum, pageSize,username,email,address);
//        //我们拿一个数组同时存放数据条数以及我们查找的人员数据的信息
//        Map<String, Object> res = new HashMap<>();
//        res.put("data", users);
//        res.put("total", total);
//        return res;
//
//
//    }
    @GetMapping("/page") //接口路径：/user/page
    public IPage<User> findPage(@RequestParam int pageNum,
                                @RequestParam int pageSize,
                                @RequestParam(defaultValue ="")  String username,
                                @RequestParam(defaultValue ="") String email,
                                @RequestParam (defaultValue ="")String address) {

        //前端首先拿到一个我们需要进入的页面，比如要进第三页，但实际上数据库拿到是需要对接受的页码参数进行处理，需要减-1乘上每页显示的行数才是我们真正的pageNum
//        这个应该放在servce层进行处理，controller直接调用
//        pageNum=(pageNum-1)*pageSize;
        //需要显示一共查找了多少数据
//        IPage<User> page = new Page<>(pageNum, pageSize);
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        //引号里的是数据库字段名
//        if(!"".equals(username)){
//            queryWrapper.like("username", username);
//        }
//        if(!"".equals(email)){
//            queryWrapper.like("email", email);
//        }
//        if(!"".equals(address)){
//            queryWrapper.like("address", address);
//        }
        //获取用户当前信息
        User currentUser = TokenUtils.getCurrentUser();
        System.out.println("获取当前用户信息================================================"+currentUser.getNickname());


//        IPage<User> userIPage = userService.page(page, queryWrapper);
       IPage<User> userIPage = userService.findPage(new Page<User>(pageNum,pageSize),username,email,address);
        return userIPage;
    }

    //显示个人信息
    @GetMapping("/username/{username}")
    public Result findOne(@PathVariable String username){


        return Result.success(userService.findOne(username));



    }

    /*
     导出接口
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        //从数据库查询出所有用户数据
        List<User> list = userService.list();
        //这里的这些工具是因为我们之前引入了hutool的工具包，所以这里可以直接使用
        //通过工具类创建writer写到磁盘路径
        //ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath+"/用户信息.xlsx");
        //在内存操作，写出到浏览器中(这里通过下载到浏览器指定路径，不自己写磁盘路径)
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名，让excel表头可以有这些中文的别名
        writer.addHeaderAlias("id","id");
        writer.addHeaderAlias("username","用户名");
        writer.addHeaderAlias("password","密码");
        writer.addHeaderAlias("name","姓名");
        writer.addHeaderAlias("nickname","昵称");
        writer.addHeaderAlias("email","邮箱");
        writer.addHeaderAlias("phone","电话");
        writer.addHeaderAlias("address","地址");
        writer.addHeaderAlias("role","角色");
        writer.addHeaderAlias("createTime","创建时间");

        //一次性写出list内的对象到excel，使用默认的样式，强制输出标题
        writer.write(list,true);
        //浏览器下载的响应头设置
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName= URLEncoder.encode("用户信息1","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+ fileName +".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out,true);
        out.close();
        writer.close();
    }
    /*
    excel 导入
     */
    @PostMapping("/import")
    public Boolean imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        //读取的时候指定一个User类泛型，这就是最后输出的bean的这个泛型
        List<User> list = reader.readAll(User.class);

        System.out.println(list);
        //批量保存我们需要的数据
        userService.saveBatch(list);
        return true;
    }


    /**
     * 修改密码
     *
     * @param userPasswordDTO
     * @return
     */
    @PostMapping("/password")
    public Result password(@RequestBody UserPasswordDTO userPasswordDTO){
        userService.updatePassword(userPasswordDTO);
        return Result.success();
    }



}

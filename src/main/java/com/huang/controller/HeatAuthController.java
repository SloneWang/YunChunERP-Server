package com.huang.controller;

import com.huang.dto.HeatAuthDTO;
import com.huang.entity.HeatAuth;
import com.huang.entity.HeatSys;
import com.huang.service.HeatAuthServiceImpl;
import com.huang.vo.HeatSaveAuthVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/heat/Auth")
public class HeatAuthController {
    @Resource
    HeatAuthServiceImpl heatAuthService;

    //查看管理权限按钮中的保存功能
    @PostMapping("/saveAdmin")
    public boolean saveAdminAuth(@RequestBody HeatSaveAuthVO heatSaveAuthVO){
        return heatAuthService.saveUserAdmin(heatSaveAuthVO);
    }
    //刚进入页面时用来查询用户列表的接口
    @GetMapping ("/{username}")
    public List<HeatAuthDTO> selectHeatUser(@PathVariable String username){
        return heatAuthService.selectHeatUser(username);
    }

    //查看管理权限按钮和查看收费权限按钮中的删除功能
    @DeleteMapping ("/delete/{id}")
    public boolean deleteHeatAuth(@PathVariable int id){
           return heatAuthService.deleteHeatAuth(id);
    }
    //查看用户管理权限按钮
    @GetMapping ("/selectAdmin/{username}/{name}")
    public List<HeatAuth> selectHeatAdmin(@PathVariable String username,@PathVariable String name){
        return heatAuthService.selectHeatAdmin(username,name);
    }
    //查看用户收费权限按钮
    @GetMapping("/selectDis/{username}/{name}")
    public List<HeatAuth> selectHeatDis(@PathVariable String username,@PathVariable String name){
        return heatAuthService.selectHeatDis(username,name);
    }
    @GetMapping("/save")
    public  List<String> selectUsername(){
        return heatAuthService.selectUsername();
    }
    @GetMapping("/userAuth/{username}")
    public  List<HeatAuth> selectUserAuthByUs(@PathVariable String username){
        return heatAuthService.selectUserAuthByUs(username);
    }
    @GetMapping ("/selectMyAuth/{username}")
    public List<HeatAuth> selectMyAuth(@PathVariable String username){
        return heatAuthService.selectMyAuth(username);
    }
    @GetMapping ("/selectMyDis/{username}")
    public List<HeatAuth> selectMyDis(@PathVariable String username){
        return heatAuthService.selectMyDis(username);
    }

    @PostMapping ("/saveSys")
    public boolean saveSys(@RequestBody HeatSys heatSys) {
        return heatAuthService.saveSys(heatSys);
    }
    @GetMapping ("/selectSys/{username}")
    public HeatSys selectSys(@PathVariable String username){
        return heatAuthService.selectSys(username);
    }


}

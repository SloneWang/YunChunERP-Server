package com.huang.controller;

import com.huang.dto.HeatUserDTO;
import com.huang.entity.HeatDistrict;
import com.huang.entity.HeatRecord;
import com.huang.service.HeatDistrictServiceImpl;
import com.huang.service.HeatUserService;
import com.huang.service.HeatUserServiceImpl;
import com.huang.vo.HeatPayUserAmountsVO;
import com.huang.vo.HeatSaveUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/heat/user")
public class HeatUserController {
    @Resource
    HeatUserServiceImpl heatUserService;

    @PostMapping("/save")
    public Boolean saveUser(@RequestBody HeatSaveUserVO heatUser){
        return  heatUserService.saveUser(heatUser);
    }
    @GetMapping("/get/{username}")
    public List<HeatUserDTO> selectUsers(@PathVariable String username){
        //String use = username.substring(1, username.length()-1);
        return  heatUserService.selectUsers(username);
    }
    @PostMapping("/pay")
    public Boolean payUserAmounts(@RequestBody HeatPayUserAmountsVO heatPayUserAmountsVO){
        return  heatUserService.payUserAmounts(heatPayUserAmountsVO);
    }
    @DeleteMapping("/delete/{id}")
    public Boolean deleteUser(@PathVariable int id){
        return  heatUserService.deleteUser(id);
    }
    @GetMapping("/record/{id}")
    public List<HeatRecord> selectHeatRecords(@PathVariable int id){
        return  heatUserService.selectHeatRecords(id);
    }

}

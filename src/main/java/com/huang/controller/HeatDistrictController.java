package com.huang.controller;

import com.huang.entity.HeatDistrict;
import com.huang.service.HeatDistrictServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/heat/district")
public class HeatDistrictController {
    @Resource
    HeatDistrictServiceImpl heatDistrictService;
    @GetMapping ("/select/{districtCode}")
    public List<String> selectQuarter(@PathVariable String districtCode){
        return heatDistrictService.selectQua(districtCode);
    }
    @GetMapping ("/creat/{heatDistrict}")
    public boolean saveQuarter(@PathVariable String heatDistrict){
        return  heatDistrictService.saveUser(heatDistrict);
    }
    @GetMapping ("/selectDis/{heatDistrict}")
    public List<String> selectDisByStr(@PathVariable String heatDistrict){
        return  heatDistrictService.selectDisByStr(heatDistrict);
    }

}

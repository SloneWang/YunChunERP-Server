package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.entity.HeatDistrict;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface HeatDistrictService extends IService<HeatDistrict> {

     List<String> selectQua(String disCode);


    boolean saveUser(String heatDistrict);
    List<String> selectDisByStr(String disCode);

}

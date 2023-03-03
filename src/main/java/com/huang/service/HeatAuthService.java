package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.dto.HeatAuthDTO;
import com.huang.entity.HeatAuth;
import com.huang.entity.HeatSys;
import com.huang.vo.HeatSaveAuthVO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HeatAuthService extends IService<HeatAuth> {

    //保存或更改
    boolean saveUserAdmin(HeatSaveAuthVO heatSaveAuthVO);
    //通过操作员用户名查询下属id
    List<HeatAuthDTO> selectHeatUser(String username);
    //删除
    boolean deleteHeatAuth(int id);
    //通过操作员用户名和下属用户名查询该用户权限列表
    List<HeatAuth> selectHeatAdmin(String username,String name);
    List<HeatAuth> selectHeatDis(String username,String name);
    List<String> selectUsername();
    List<HeatAuth> selectUserAuthByUs(String username);
    List<HeatAuth> selectMyAuth(String username);
    List<HeatAuth> selectMyDis(String username);
    boolean saveSys(HeatSys heatSys);
    HeatSys selectSys(String username);
}


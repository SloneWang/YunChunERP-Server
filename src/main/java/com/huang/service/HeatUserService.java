package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.dto.HeatUserDTO;
import com.huang.entity.HeatRecord;
import com.huang.entity.HeatUser;
import com.huang.vo.HeatPayUserAmountsVO;
import com.huang.vo.HeatSaveUserVO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface HeatUserService extends IService<HeatUser> {
    Boolean saveUser(HeatSaveUserVO heatUser);
    List<HeatUserDTO> selectUsers (String username);
    Boolean payUserAmounts(HeatPayUserAmountsVO heatPayUserAmountsVO);
    Boolean deleteUser(int id);
    List<HeatRecord> selectHeatRecords(int id);
    void updateData();
}

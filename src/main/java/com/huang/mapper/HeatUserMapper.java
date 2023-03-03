package com.huang.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huang.entity.*;
import com.huang.vo.HeatSaveUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface HeatUserMapper extends BaseMapper<HeatUser> {
    List<HeatUser> ifUserExist(@Param("district") String district,@Param("account") String account);
    Boolean upDateUser(HeatUser heatUser);
    Boolean insertUser(HeatUser heatUser);
    List<HeatUser> selectUserByAuth(@Param("adminAuth") String adminAuth);
    List<HeatSys> selectSysPara();
    int deleteByUser(@Param("heatUser") HeatUser heatUser);
    int insertRecord(HeatRecord heatRecord);
    List<HeatRecord> selectRecordByUser(@Param("district") String district,@Param("account") String account);
    Boolean upDateUserLimited(HeatSaveUserVO heatSaveUserVO);

    List<HeatAuth> selectUserAuth(String username);
    List<HeatAuth> selectUserAdminAuth(String username);
    List<HeatUser> selectUserByAdminAuth(@Param("adminAuth") String adminAuth);
    }

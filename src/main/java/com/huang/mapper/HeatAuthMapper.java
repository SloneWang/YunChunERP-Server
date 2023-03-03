package com.huang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huang.entity.HeatAuth;
import com.huang.entity.HeatSys;
import com.huang.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HeatAuthMapper extends BaseMapper<HeatAuth> {
    List<HeatAuth> checkByAuth(@Param("username") String username,@Param("adminAuth") String adminAuth);
    List<HeatAuth> checkByDis(@Param("username") String username,@Param("disAuth") String disAuth);
    List<HeatAuth> selectUserAuth(String username);
    List<HeatAuth> selectAdminByAuth(String adminAuth);
    List<HeatAuth> selectDisByAuth(String adminAuth);
    boolean deleteByAdmin(@Param("username") String username,@Param("adminAuth") String adminAuth);
    boolean deleteByDis(@Param("username") String username,@Param("disAuth") String disAuth);
    List<User> selectSystemUser();
    List<HeatAuth> selectUserDis(String username);
    List<User> selectSysUser(String username);
    boolean saveSysPar(HeatSys heatSys);
}

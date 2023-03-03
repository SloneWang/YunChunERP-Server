package com.huang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huang.entity.RoleMenu;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    @Delete("delete from yunchun.sys_role_menu  where role_id = #{roleId}  ")
    public int deleteByRoleId(@Param("roleId") Integer roleId);

    @Select("select menu_id from yunchun.sys_role_menu  where role_id = #{roleId}  ")
    public List<Integer> selectByRoleId(@Param("roleId") Integer roleId);
}

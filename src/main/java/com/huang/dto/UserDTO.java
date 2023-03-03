package com.huang.dto;

import com.huang.entity.Menu;
import lombok.Data;

import java.util.List;

/*
  接受前端请求的参数
 */
@Data
public class UserDTO {
    private String username;
    private String password;
    private String nickname;
    private String avatarUrl;
    private String token;

    //用来查询当前用户的菜单
    private List<Menu> menus;

    //查询当前用户的角色
    private String role;


}

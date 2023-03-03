package com.huang.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.common.Constants;
import com.huang.dto.UserDTO;
import com.huang.entity.Menu;
import com.huang.entity.User;
import com.huang.exception.ServiceException;
import com.huang.mapper.RoleMapper;
import com.huang.mapper.RoleMenuMapper;
import com.huang.mapper.UserMapper;
import com.huang.utils.TokenUtils;
//import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginServiceImpl extends ServiceImpl<UserMapper, User> implements LoginService {
    private static final Log LOG = Log.get();

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private MenuServiceImpl menuService;

    @Override
    public UserDTO login(UserDTO userDTO) {
        //数据库查询的话，我们的对象必须要是对应的实体类
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("username",userDTO.getUsername());
//        queryWrapper.eq("password",userDTO.getPassword());
//        User one;
//
//
//        try {
//            one = getOne(queryWrapper); //从数据库查询用户信息
//
//       }catch (Exception e){
//           LOG.error(e);
//           throw new ServiceException(Constants.CODE_500,"系统错误");
//       }
        User one = getUserInfo(userDTO);
        //我们这里要判断这里是否有我要的数据
        if(one !=null){
            //把one里面的数据copy到userDTO里去，不copy的话只有账号密码的信息，copy后有头像昵称的信息
            //忽略大小写
            BeanUtil.copyProperties(one,userDTO,true);

            //设置Token
            String token = TokenUtils.genToken(one.getId().toString(),one.getPassword());
            userDTO.setToken(token);

            String role = one.getRole();//ROLE_ADMIN/管理员
            //因为我们的菜单ID是跟ROLE ID 关联的，所以我们需要先查我们的roleid
            Integer roleId = roleMapper.selectByFlag(role);

           //设置用户的菜单列表
            List<Menu> roleMenus = getRoleMenus(roleId);

            userDTO.setMenus(roleMenus);

            return userDTO;
        }else {
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }

    }

    @Override
    public User register(UserDTO userDTO) {
        //校验 注册 账户名不能相同
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userDTO.getUsername());

        User one;
        try {
            one = getOne(queryWrapper);// 从数据库查询用户信息
        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        //如果账号不存在，再进行save操作
        if(one ==null){
            //这里如果不new 就 copy会报空指针异常的错误
            one = new User();
            //这里是要把userDTO copy到user里 因为我们存储的是user对象，要把userDTO复制过来然后存储
            BeanUtil.copyProperties(userDTO,one,true);
            save(one); //把copy完的用户对象存储到数据库里
        }else {
            throw new ServiceException(Constants.CODE_600,"用户已存在");
        }
        return one;
    }
    //封装一个方法方便后面调用
    public User getUserInfo(UserDTO userDTO){
        //数据库查询的话，我们的对象必须要是对应的实体类
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userDTO.getUsername());
        queryWrapper.eq("password",userDTO.getPassword());
        User one;


        try {
            one = getOne(queryWrapper); //从数据库查询用户信息

        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        return one;
    }


    /**
     * 获取当前角色的菜单列表
     * @param roleId
     * @return
     */
    private List<Menu> getRoleMenus(Integer roleId){
        //当前角色的所有菜单ID的集合
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);
        //查出系统的所有菜单
        List<Menu> menus = menuService.findMenus("");
        //new一个最后筛选完成之后的list
        List<Menu> roleMenus = new ArrayList<>();
        //筛选出当前用户角色的菜单（也就是过滤掉其他的菜单）
        for(Menu menu:menus){
            //如果遍历出来的菜单ID在上面的meunIds里面，那么就筛选成功了
            if(menuIds.contains(menu.getId())){
                roleMenus.add(menu);
            }
            //二级菜单，如果判断出当前角色的菜单ID不包含二级菜单的ID，就删除当前的二级菜单
            List<Menu> children = menu.getChildren();
            //removeIf 移除children里面不在menuIds集合中的元素
            children.removeIf(child -> !menuIds.contains(child.getId()));
            menu.setChildren(children);
        }
        return roleMenus;
    }
}

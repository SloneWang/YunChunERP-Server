package com.huang.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.huang.entity.User;
import com.huang.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
@Component
public class TokenUtils {

    //首先定义一个静态对象和普通对象
    private static UserServiceImpl staticUserService;
    @Autowired
    private UserServiceImpl userService;

    @PostConstruct
    //PostConstruct在构造函数之后执行
    public void setStaticUserService(){
        //通过这种方式，当后台启动的时候，把我们注入到bean里面的这个service存入到静态对象里去
        staticUserService = userService;
    }


    /**
     * 生成token
     * @return
     */
    public static String genToken(String userId,String sign){
       return   JWT.create().withAudience(userId) // 将 user id 保存到 token 里面,作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(),2)) //从当前时间开始的2小时后过期
                .sign(Algorithm.HMAC256(sign)); // 以 password 作为 token 的密钥


    }

    /**
     * 获取当前登录的用户信息
     * @return user对象
     */

    public static User getCurrentUser(){
        try {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        if(StrUtil.isNotBlank(token)) {

            //因为获取当前这个请求的header里面的token，然后在解析userId
            String userId = JWT.decode(token).getAudience().get(0);
            return staticUserService.getById(Integer.valueOf(userId));
        }
        }catch (Exception e){
                return null;
        }
        return null;
    }

}

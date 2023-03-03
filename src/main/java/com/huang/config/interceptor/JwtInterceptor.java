package com.huang.config.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.huang.common.Constants;
import com.huang.entity.User;
import com.huang.exception.ServiceException;
import com.huang.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        //从请求头中获取token、认证token、解析token中的userId
        //前端发回的token
        String token = request.getHeader("token");
        //如果不是映射的方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        //执行认证
        if(StrUtil.isBlank(token)){
            throw new ServiceException(Constants.CODE_401,"无token,请重新登录");
        }
        //获取token中的user id
        String userId;
        try{
            userId = JWT.decode(token).getAudience().get(0);//获取之前设置的载荷
        }catch (JWTDecodeException j){
            throw new ServiceException(Constants.CODE_401,"token验证失败");
        }
        //根据token中的userId查询数据库
        User user = userService.getById(userId);
        if(user == null){
            throw new ServiceException(Constants.CODE_401,"用户不存在，请重新登录");
        }
        //用户密码加签去验证token  使用 用户除密码之外的任意字段作为签名
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try{
            jwtVerifier.verify(token);//验证token
        }catch (JWTVerificationException e){
            throw new ServiceException(Constants.CODE_401,"token验证失败，请重新登陆");
        }

        return true;
    }
}

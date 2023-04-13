package com.huang.exception;


import com.huang.common.Result;
import com.huang.component.WebSocketComponent;

import cn.hutool.http.server.HttpServerResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//全局的异常处理器
@ControllerAdvice
public class GlobleExceptionHandler {

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    /**
     * @ExceptionHandler相当于controller的@RequestMapping
     * 如果抛出的的是ServiceException，则调用该方法
     * @param se 业务异常
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result handle(ServiceException se){
        return Result.error(se.getCode(),se.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public void exceptionHandler(Exception ex) {
        WebSocketComponent.sendMessageToUserByToken(request.getHeader("Token"), ex.getMessage());
        try {
            response.getWriter().write("null");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

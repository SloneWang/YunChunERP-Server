package com.huang.exception;


import com.huang.common.Result;
import com.huang.component.WebSocketComponent;

import cn.hutool.core.io.IoUtil;
import cn.hutool.http.server.HttpServerResponse;
import springfox.documentation.spi.service.contexts.ResponseContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//全局的异常处理器
@ControllerAdvice
public class GlobleExceptionHandler {
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
    @ResponseBody
    public String exceptionHandler(Exception ex) {
        ServletRequestAttributes attributes = (ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes());
        HttpServletRequest request = attributes.getRequest();
        WebSocketComponent.sendMessageToUserByToken(request.getHeader("Token"), ex.getMessage());
        return "null";
    }
}

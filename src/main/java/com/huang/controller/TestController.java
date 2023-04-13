package com.huang.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUpgradeHandler;
import javax.xml.ws.spi.http.HttpContext;

import org.apache.commons.fileupload.RequestContext;
import org.springframework.messaging.rsocket.annotation.ConnectMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.WebSocketHttpRequestHandler;

import cn.hutool.http.HttpRequest;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("")
    public void Test(@RequestBody String str) {
        System.out.println("test");
        System.out.println(str);
    }

    @GetMapping("/test2")
    public void Test2() throws Exception {
        throw new Exception("是故意的。");
    }

    @GetMapping("/test3")
    public String Test3() {
        return "调用成功！";
    }
}

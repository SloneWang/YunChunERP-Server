package com.huang.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("")
    public void Test(@RequestBody String str) {
        System.out.println("test");
        System.out.println(str);
    }
}

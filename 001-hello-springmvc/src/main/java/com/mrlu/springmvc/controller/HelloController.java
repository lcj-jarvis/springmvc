package com.mrlu.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 简单de快乐
 * @date 2021-09-23 21:45
 */
@Controller
public class HelloController {

    @RequestMapping(value ="/")
    public String toIndex() {
        return "index";
    }

    @GetMapping("/hello")
    public String toHello() {
        return "hello";
    }

}

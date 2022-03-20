package com.mrlu.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-02-13 15:22
 */

@Controller
public class MyController {

    @RequestMapping(value = {"/some.do","other.do"})
    public ModelAndView doSome(String name,Integer age) {
        System.out.println("=====MyController的doSome=====");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name",name);
        modelAndView.addObject("age",age);
        modelAndView.setViewName("show");

        return modelAndView;
    }
}

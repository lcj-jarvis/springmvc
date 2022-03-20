package com.mrlu.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-02-13 15:22
 */


@Controller
public class MyController {

    @RequestMapping(value = "/user/some.do")
    public ModelAndView doSome(){ //doGet
        System.out.println("冲冲冲！！！");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg","欢迎使用springmvc做web开发");
        modelAndView.addObject("fun","执行的是doSome方法");

        modelAndView.setViewName("/index.jsp");

        return modelAndView;
    }
}

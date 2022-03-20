package com.mrlu.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-02-13 15:22
 */

@Controller
public class MyController {

    /**
     * 处理器方法返回ModelAndView，实现转发forward
     * 语法：setViewName("forward:完整路径")
     * forward特点：不和视图解析器一同使用，就当项目中没有视图解析器
     * @return
     */
    @RequestMapping(value = "/user/some.do",method = RequestMethod.POST)
    public ModelAndView doSome(String name,Integer age){
        System.out.println("请求转发！！！");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name",name);
        modelAndView.addObject("age",age);
        //显式转发
        //modelAndView.setViewName("forward:/WEB-INF/view/show.jsp");

        //不和视图解析器一同使用，就当项目中没有视图解析器
        modelAndView.setViewName("forward:/hello.jsp");
        return modelAndView;
    }

    /**
     *  处理器方法返回ModelAndView。实现重定向redirect
     *  语法：setViewName("redirect:视图完整路径")
     *  redirect特点：不和视图解析器一同使用，就当项目中没有视图解析器
     *
     *  框架对重定向的操作：
     *  1、框架会把Model中的简单类型的数据，转为String使用，作为hello1.jsp的get请求参数使用
     *    目的是user/redirect.do 和 hello1.jsp请求之间传递数据
     *
     *     这些写不能获取到数据，因为请求重定向是两次请求，不共享request域的数据
     *      <h3>name数据:${name}</h3>
     *      <h3>age数据:${age}</h3>
     *
     *      正确写法：
     *      <h3>name数据:${param.name}</h3>
     *      <h3>age数据:${param.age}</h3>
     *
     *
     */
    @RequestMapping(value = "/user/redirect.do")
    public ModelAndView doOther(String name,Integer age){
        System.out.println("请求重定向！！！");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name",name);
        modelAndView.addObject("age",age);

        //请求重定向不能转发到WEB-INF目录下的内容
        //modelAndView.setViewName("redirect:/WEB-INF/view/show.jsp");

        //不和视图解析器一同使用，就当项目中没有视图解析器
        //setViewName里的“/”相当于http://localhost:8080/工程路径/
        modelAndView.setViewName("redirect:/hello1.jsp");
        //http://localhost:8080/08_forward_redirect/user/redirect.do?name=jackson&age=23
        return modelAndView;
    }
}

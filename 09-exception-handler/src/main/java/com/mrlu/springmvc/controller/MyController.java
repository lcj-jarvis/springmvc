package com.mrlu.springmvc.controller;

import com.mrlu.springmvc.exception.AgeException;
import com.mrlu.springmvc.exception.MyUserException;
import com.mrlu.springmvc.exception.NameException;
import com.mrlu.springmvc.exception.PasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-02-13 15:22
 */

@Controller
public class MyController {


    @RequestMapping(value = "/some.do")
    public ModelAndView doSome(String name,Integer age) throws MyUserException {
        ModelAndView modelAndView = new ModelAndView();
        //根据请求参数抛出异常
        if(! "zs".equals(name)){
            throw new NameException("姓名不正确！！！");
        }

        if (age == null || age > 80){
            throw new AgeException("年龄过大");
        }

        modelAndView.addObject("name",name);
        modelAndView.addObject("age",age);
        modelAndView.setViewName("show");
        return modelAndView;
    }

    /**
     * 测试@ResponseStatus前先注释GlobalExceptionHandler的doOtherException和doRuntimeException方法
     * 在浏览器地址栏上输出以下地址测试
     * http://localhost:8080/09_exception_handler/testResponseStatusExceptionResolver?password=11
     *
     * 在这个方法是上面加了@ResponseStatus这个注解，即使密码正确，也会调跳转到错误的页面。
     */
    //@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "密码错误")
    @RequestMapping(value = "/testResponseStatusExceptionResolver")
    public String testResponseStatus(@RequestParam("password") Integer password) throws MyUserException {
        if (password == null || password != 10){
            throw new PasswordException("密码错误");
        }
        return "show";
    }

    /**
     * 验证在SpringMvc配置文件中配置的SimpleMappingExceptionResolver
     */
    @RequestMapping("/testSimpleMappingExceptionResolver")
    public String testSimpleMappingExceptionResolver(@RequestParam("i")Integer i){
        byte[] bytes = new byte[10];
        System.out.println(bytes[i]);
        //new ArrayIndexOutOfBoundsException()
        return "show";
    }
}

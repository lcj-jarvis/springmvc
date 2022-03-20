package com.mrlu.springmvc.controller;

import com.mrlu.springmvc.vo.Student;
import com.mrlu.springmvc.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-02-13 15:22
 */

/**
 * 接收请求参数，使用的处理器方法的形参
 * 1、HttpServletRequest
 * 2、HttpServletResponse
 * 3、HttpSession
 * 4、用户提交的数据
 *
 * 接收用户提交的参数：
 *   1、逐个接收
 *   2、对象接收
 *
 *  注意：
 *    在提交请求参数时，get请求方式中文没有乱码
 *    使用post方式提交请求，中文有乱码，需要使用过滤器处理中文的乱码问题
 *
 *   过滤器可以自定义，也可以使用框架提供的过滤器CharacterEncodingFilter
 *   在web.xml中配置CharacterEncodingFilter
 */
@Controller
@RequestMapping("/test")
public class MyController {

    /**
     *  逐个接收请求的参数：
     *    要求：处理器(控制器)方法的形参名和请求中的参数名必须一致
     *         同名的请求参数赋值给同名的参数。跟参数的位置无关。个数不一定要一致
     *  框架接收请求的参数：
     *   1、使用request对象接收请求参数
     *      String strName = request.getParameter("name");
     *      String strAge = request.getParameter("age");
     *
     *   2、springmvc框架通过DispatcherServlet 调勇MyController的doSome()方法
     *      调用方法是，按名称对应，把接收的参数赋值给形参
     *      doSome(String name,Integer.valueOf(strAge))
     *      框架会提供类型转换的功能，能把String转为int，long，float，double等类型
     *
     *   如果没有输入age项，就会提交的是空串"".转换为int类型就会转换失败，出现400的状态码
     *   400状态码是客户端错误，表示提交请求参数的过程中，发生了很多问题
     *
     *   把int类型改成包装类，age接收到的就是null。就不会出现400状态码的问题
     *   但是如果提交的不是数字类型的也会出现400的状态码，因为不是数字类型的无法转化成Integer类型
     */
    @RequestMapping(value = "/receive.do")
    //public ModelAndView doSome(int age,String name,String email){
    public ModelAndView doSome(Integer age,String name){
        System.out.println("age:"+ age+",name:"+name);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("age",age);
        modelAndView.addObject("name",name);
        modelAndView.setViewName("show");

        return modelAndView;
    }

    /**
     * 请求中参数名和处理器方法的形参名不一样
     * @RequestParam: 逐个接收请求参数中，解决请求中参数名和形参名不一样的问题
     *               属性：1、value属性：请求中参数的名称
     *                    2、required 是一个boolean类型，默认是true，
     *                      true：表示请求参数中必须包含此参数
     *                      此时如果在浏览器直接输入
     *                      http://localhost:8080/03_receiveparam_springmvc/test/doOther.do 就会出错
     *                      设置成false就不会出错
     *                   3、defaultValue：请求参数的默认值，如果没有该请求参数或者该请求参数为空串，就采用默认值
     *
     *               位置：使用在处理器方法的形参定义的前面
     */
    @RequestMapping("/doOther.do")
    public ModelAndView doOther(@RequestParam(value = "userAge",required = false,defaultValue = "0")Integer age,
                                @RequestParam(value = "username",required = false) String name){
        System.out.println("age:"+ age+",name:"+name);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("age",age);
        modelAndView.addObject("name",name);

        modelAndView.setViewName("show");

        return modelAndView;
    }

    /**
     *
     * 处理器方法形参是java对象，这个对象的属性名和请求参数名是一样的
     * 框架会创建形参的java对象，给属性赋值。例如：请求参数是name，框架会调用setName()方法赋值
     */
    @RequestMapping("/receiveObject.do")
    public ModelAndView receiveObject(Student student){
        System.out.println("age:"+ student.getAge()+",name:"+student.getName());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("student",student);
        modelAndView.setViewName("show");

        return modelAndView;
    }

    /**
     * 对象的级联属性赋值
     */
    @RequestMapping("/receiveUser.do")
    public ModelAndView receiveUser(User user){
        System.out.println(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",user);
        modelAndView.setViewName("show");
        return modelAndView;
    }

    /**
     *  可以使用Servlet原生的API作为目标方法的参数，
     *  具体支持以下类型
     *  HttpServletRequest
     *  HttpServletResponse
     *  HttpSession
     *  java.security.Principal
     *  Locale
     *  InputStream
     *  OutputStream
     *  Reader
     *  Writer
     *
     */
    @RequestMapping("/receiveServlet.do")
    public void receiveServlet(HttpServletRequest request, HttpServletResponse response, Writer writer) throws IOException {
        System.out.println(request.getParameter("name"));
        System.out.println(request.getParameter("age"));
        writer.write("hello springmvc");
    }

    /**
     * 了解
     * @RequestHeader 映射请求头
     *
     */
    @RequestMapping("/testRequestHeader.do")
    public ModelAndView testRequestHeader(@RequestHeader(value = "Accept-Language")String requestHeader){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("请求头Accept-Language:"+requestHeader);
        modelAndView.setViewName("show");
        return modelAndView;
    }

    /**
     * 了解：
     * @CookieValue： 隐射一个Cookie值，属性同@RequestParam一样
     *     属性value表示cookie的名称
     * 可用于解决Cookie被禁用了
     */
    @RequestMapping("/testCookie.do")
    public ModelAndView testCookie(@CookieValue(value = "JSESSIONID") String sessionId){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("testCookie的JSESSIONID："+sessionId);
        modelAndView.setViewName("show");
        return modelAndView;
    }
}

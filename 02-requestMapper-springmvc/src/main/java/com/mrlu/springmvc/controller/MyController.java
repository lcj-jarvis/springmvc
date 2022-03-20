package com.mrlu.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

/**
 * @RequestMapping
 *  value:所有请求地址的公共部分，叫做模块名称
 *  位置：放在类的上面
 */
@Controller
@RequestMapping("/test")
public class MyController {
    /**
     * 处理用户提交的请求，springmvc中使用方法来处理的
     * 方法是自定义的，可以有多种返回值，多种参数，方法名称自定义的
     */

    /**
     *   准备doSome方法处理请求some.do请求。
     *   @RequestMapping 请求映射，作用是把一个请求地址和一个方法绑定在一起
     *                   一个请求指定一个方法处理
     *            属性：1、value是一个String数组【表示请求的uri地址】
     *                    value的值必须是唯一的，不能重复的，在使用是，推荐地址以“/”
     *
     *                    如果没有公共的模块名称：
     *                    / 表示http://localhost:8080:/工程路径/
     *
     *                    可以一次处理多个请求
     *            位置：1、在方法的上面，常用的
     *                 2、在类的上面
     *     说明：使用RequestMapping修饰的方法叫做处理器方法或者控制器方法
     *      使用@RequestMapping修饰的方法可以处理请求的，类似于Servlet的doGet，doPost
     *
     *   返回值：ModelAndView
     *   Model：数据，请求处理完成后，要显示给用户的数据
     *   View：视图，比如jsp等等
     */
    //@RequestMapping(value = {"/test/some.do","/test/other.do"}) //提取请求地址的公共部分
    @RequestMapping(value = {"/some.do","/other.do"})
    public ModelAndView doSome(){ //doGet
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg","欢迎使用springmvc做web开发");
        modelAndView.addObject("fun","执行的是doSome方法");

        modelAndView.setViewName("show");

        return modelAndView;
    }

    /**
     * @RequestMapping:请求映射
     *             属性：method，表示请求的方式，它的值是RequestMethod类枚举值
     *             例如：表示get请求方式，使用RequestMethod.GET
     *                  post方式，使用RequestMethod.POST
     *    method = RequestMethod.POST只能处理post请求，如果是get请求就会出现以下错误：
     *
     *    HTTP状态 405 - 方法不允许
     *    同样。method = RequestMethod.GET只能处理get请求，如果是post请求也会出现这个错误
     *
     *    【注意】如果不指明请求的方式，两种请求都可以处理
     */
    @RequestMapping(value = "/post.do",method = RequestMethod.POST)
    public ModelAndView doFirst(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg","欢迎使用springmvc做web开发");
        modelAndView.addObject("fun","执行的是doSome方法");

        modelAndView.setViewName("show");

        return modelAndView;
    }

    /**
     * 处理器方法可以包含以下四类参数，这些参数会在系统调用时由系统自动赋值，即程序
     * 员可在方法内直接使用。
     *  HttpServletRequest
     *  HttpServletResponse
     *  HttpSession
     *  请求中所携带的请求参数
     */
    @RequestMapping(value = "/get.do",method = RequestMethod.GET)
    public ModelAndView doSecond(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg","欢迎使用springmvc做web开发");
        modelAndView.addObject("fun","执行的是doSome方法");
        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("password"));

        modelAndView.setViewName("show");
        return modelAndView;
    }

    /**
     * 通过
     * "param"：要求请求映射所匹配的请求必须携带param请求参数。如：params = {"username"}
     *
     * "!param"：要求请求映射所匹配的请求必须不能携带param请求参数。如 params = {"!username"}
     *
     * "param=value"：要求请求映射所匹配的请求必须携带param请求参数且param=value。如 params = {"username=jack"}
     *
     * "param!=value"：要求请求映射所匹配的请求必须携带param请求参数但是param!=value。如 params = {"username!=jack"}
     * 来指定请求参数
     *
     * "header"：要求请求映射所匹配的请求必须携带header请求头信息
     *
     * "!header"：要求请求映射所匹配的请求必须不能携带header请求头信息
     *
     * "header=value"：要求请求映射所匹配的请求必须携带header请求头信息且header=value
     *
     * "header!=value"：要求请求映射所匹配的请求必须携带header请求头信息且header!=value
     *
     * 若当前请求满足@RequestMapping注解的value和method属性，但是不满足headers属性，此时页面显示404错误，即资源未找到
     *
     * 了解：可以使用params和headers来更加精确的映射请求，params和headers支持简单的表达式
     * 以下的方法表示：
     * 请求参数必须包含username和age，且age属性值不能是10。
     * 并且请求头是：Accept-Language: zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7 才可以映射到该请求
     *
     * 注：表示式不能有空格隔开，只能接着写
     * params = {"username","age!=10"}
     * 请求头的要用等号，不能用冒号。如：Accept-Language=值
     * headers = {"Accept-Language=zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7"}
     */
    @RequestMapping(value = "/param.do",method = RequestMethod.GET,params = {"username","age!=10"}
                   , headers = {"Accept-Language=zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7"})
    public ModelAndView doThird(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("冲冲冲");
        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("age"));

        modelAndView.setViewName("show");
        return modelAndView;
    }

    /**
     * 了解
     * Ant风格的资源地址支持3种匹配符
     * ? : 匹配文件名的一个字符
     * * ：匹配文件名的任意多个字符
     * ** ：匹配多重目录
     *
     * 【注】如果是a**a 会被当做单独的*来处理，只能匹配两层目录。所以“**”只能单独使用
     * @RequestMapping 还支持Ant风格的url:
     */
     // /user/*/createUser: 匹配/user/aaa/createUser、/user/bbb/createUser 等 URL
    // /user/**/createUser: 匹配/user/createUser、/user/aaa/bbb/createUser 等 URL
    // /user/createUser??: 匹配/user/createUseraa、/user/createUserbb 等 URL
    @RequestMapping(value = "/**/some.do")
    public ModelAndView doAnt(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("冲冲冲");

        modelAndView.setViewName("show");
        return modelAndView;
    }

    /*
      可以映射URL中的占位符到目标方法的参数中
    */
    @RequestMapping("/pathVariable.do/{id}")
    public ModelAndView testPathVariable(@PathVariable(value = "id")Integer id){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("id的值是："+id);
        modelAndView.setViewName("show");
        return modelAndView;
    }
}

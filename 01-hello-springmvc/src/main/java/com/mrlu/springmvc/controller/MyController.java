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

/**
 *   @Controller:创建处理器对象，对象放在springmvc容器中
 *  位置：在类的上面
 *  和Spring中将的@Service，@Component用法都是一样的
 *
 *  能处理请求的都是控制器（处理器）：MyController能处理请求，叫做后端处理器（back controller）
 *
 */
@Controller
public class MyController {
    /**
     * 处理用户提交的请求，springmvc中使用方法来处理的
     * 方法是自定义的，可以有多种返回值，多种参数，方法名称自定义的
     */

    /**
     *   准备doSome方法处理请求some.do和other.do请求。
     *   @RequestMapping 请求映射，作用是把一个请求地址和一个方法绑定在一起
     *                   一个请求指定一个方法处理
     *            属性：1、value是一个String数组【表示请求的uri地址】
     *                    value的值必须是唯一的，不能重复的，在使用是，推荐地址以“/”
     *
     *                    如果没有公共的模块名称：
     *                    / 表示http://localhost:8080:/工程路径/
     *
     *                    可以一次处理多个请求
     *
     *            位置：1、在方法的上面，常用的
     *                 2、在类的上面
     *     说明：使用RequestMapping修饰的方法叫做处理器方法或者控制器方法
     *      使用@RequestMapping修饰的方法可以处理请求的，类似于Servlet的doGet，doPost
     *
     *   返回值：ModelAndView
     *   Model：数据，请求处理完成后，要显示给用户的数据
     *   View：视图，比如jsp等等
     */
   /* @RequestMapping(value = {"/test/some.do","/test/other.do"})*/
    @RequestMapping(value = {"some.do","other.do"})
    public ModelAndView doSome(){ //doGet
        //处理some.do请求了。相当于service调用处理完成了。
        ModelAndView modelAndView = new ModelAndView();
        //添加数据，框架在请求的最后把数据放入到request作用域中
        //request.setAttribute("msg","欢迎使用springmvc做web开发");
        modelAndView.addObject("msg","欢迎使用springmvc做web开发");
        modelAndView.addObject("fun","执行的是doSome方法");

        //指定视图，指定视图的完整路径
        // "/show.jsp" /对应于webapp目录
        //框架对视图执行的操作是forward操作，request.getRequestDispatcher("/show.jsp").forward(req,resp);
        //modelAndView.setViewName("/show.jsp");

        //把show.jsp放到WEB-INF目录下，防止直接访问到
        //modelAndView.setViewName("/WEB-INF/view/show.jsp");

        //当配置了视图解析器，可以使用逻辑名称（文件名），指定视图
        //框架会使用视图解析器的前缀+逻辑名称+后缀 组合完成路径，这里就是字符串连接操作
        // /WEB-INF/view/ + show + .jsp
        modelAndView.setViewName("show");

        //返回ModelAndView
        return modelAndView;
    }
}

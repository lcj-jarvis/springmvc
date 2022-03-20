package com.mrlu.springmvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrlu.springmvc.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-02-13 15:22
 */

@Controller
@RequestMapping("/test")
public class MyController {

    /**
     * 处理器方法返回String--表示逻辑视图名称，需要配置视图解析器
     */
    @RequestMapping(value = "/returnString-view1.do")
    public String returnString1(HttpServletRequest request,Integer age, String name){
        System.out.println("age:"+ age+",name:"+name);
        request.setAttribute("age",age);
        request.setAttribute("name",name);

        //show:逻辑视图名称，项目中配置了视图解析器
        //框架对视图执行forward转发操作

        return "show";
    }

    /**
     * 处理器方法返回String,表示完整视图路径，此时不能配置视图解析器
     */
    @RequestMapping(value = "/returnString-view2.do")
    public String returnString2(HttpServletRequest request,Integer age, String name){
        System.out.println("age:"+ age+",name:"+name);
        request.setAttribute("age",age);
        request.setAttribute("name",name);

        //show:逻辑视图名称，项目中配置了视图解析器
        //框架对视图执行forward转发操作
        //404 文件[/WEB-INF/view/WEB-INF/view/show.jsp.jsp] 未找到
        //因为有视图解析器，已经有前缀和后缀了。所以要把视图解析器注释掉才可以运行
        return "/WEB-INF/view/show.jsp";
    }


    /**
     * 处理器方法返回void,响应ajax请求
     * 手工实现ajax请求，json数据：代码有重复的：
     * 1、java对象转换为json
     * 2、通过HttpServletResponse输出json数据
     */
    @RequestMapping(value = "/returnVoid-ajax.do")
    public void returnVoid(HttpServletResponse response, Integer age, String name) throws IOException {
        System.out.println("age:"+ age+",name:"+name);
        //处理ajax请求，使用json做数据的格式
        //service调用完成之后，使用Student表示处理结束
        Student student = new Student();
        student.setName(name);
        student.setAge(age);

        String json = "";
        //把结果对象转换为json格式的数据
        if (student != null){
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(student);
            System.out.println("student转换为json"+json);
        }

        //输出数据，响应ajax的请求
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println(json);
        writer.flush();
        writer.close();

       /* 返回服务器的结果{"name":"zhangsan","age":20}*/

        //使用gson处理也行
        /*Gson gson = new Gson();
        String json = gson.toJson(student);
        response.getWriter().write(json);*/
    }

    /**
     * HttpMessageConverter<T> 是 Spring3.0 新添加的一个接口，
     * 负责将请求信息转换为一个对象（类型为 T），将对象（类型为 T）输出为响应信息
     * • HttpMessageConverter<T>接口定义的方法：
     * – Boolean canRead(Class<?> clazz,MediaType mediaType): 指定转换器
     *      可以读取的对象类型，即转换器是否可将请求信息转换为 clazz 类型的对象，
     *      同时指定支持 MIME 类型(text/html,applaiction/json等)
     * – Boolean canWrite(Class<?> clazz,MediaType mediaType):指定转换器
     *      是否可将 clazz 类型的对象写到响应流中，响应流支持的媒体类型 在MediaType 中定义。
     *      – LIst<MediaType> getSupportMediaTypes()：该转换器支持的媒体类型。
     * – T read(Class<? extends T> clazz,HttpInputMessage inputMessage)：
     *       将请求信息流转换为 T 类型的对象。
     * – void write(T t,MediaType contnetType,HttpOutputMessage outputMessage):
     *      将T类型的对象写到响应流中，同时指定相应的媒体类型为 contentType。
     */

    /**
     * 处理器方法返回一个Student，通过框架转为json，响应ajax请求
     * @ResponseBody：
     *    作用：把处理器方法返回对象转为json后，响应ajax请求
     *    位置：方法的定义上面。和其他注解没有顺序的关系
     *
     *  返回对象框架的处理流程：
     *   1、框架会把返回Student类型，调用框架中的ArrayList<HttpMessageConverter>中的每个类的canWrite()方法
     *     检查那个HttpMessageConverter接口的实现类能处理Student类型的数据--MappingJackson2HttpMessageConverter
     *
     *   2、框架会调用实现类的write(),  MappingJackson2HttpMessageConverter的write()方法
     *      把student对象转为json，调用JackSon的ObjectMapper实现转为json
     *
     *       ContextType:application/json;charset=utf-8
     *   3、框架会调用@ResponseBody把2的结果输出到浏览器，ajax请求处理完成
     *
     */
    @ResponseBody
    @RequestMapping(value = "/returnStudent-ajax.do")
    public Student returnStudent(Integer age, String name) {
        System.out.println("age:"+ age+",name:"+name);

        Student student = new Student();
        student.setName(name);
        student.setAge(age);

        //会被框架转换为json
        return student;
    }

    /**
     * 处理器方法返回一个List，通过框架转为json数组，响应ajax请求
     * @ResponseBody：
     *    作用：把处理器方法返回对象转为json后，响应ajax请求
     *    位置：方法的定义上面。和其他注解没有顺序的关系
     *  返回对象框架的处理流程：
     *   1、框架会把返回List类型，调用框架中的ArrayList<HttpMessageConverter>中的每个类的canWrite()方法
     *     检查那个HttpMessageConverter接口的实现类能处理Student类型的数据--MappingJackson2HttpMessageConverter
     *
     *   2、框架会调用实现类的write(),  MappingJackson2HttpMessageConverter的write()方法
     *      把List对象转为json数组，调用JackSon的ObjectMapper实现转为json数组
     *       ContextType:application/json;charset=utf-8
     *   3、框架会调用@ResponseBody把2的结果输出到浏览器，ajax请求处理完成
     *
     */
    @ResponseBody
    @RequestMapping(value = "/returnList-ajax.do")
    public List<Student> returnList(Integer age, String name)  {
        System.out.println("age:"+ age+",name:"+name);
        List<Student> list = new ArrayList<>();

        Student student1 = new Student(name,age);
        Student student2 = new Student("李四",age+1);

        list.add(student1);
        list.add(student2);
        //[{"name":"zhangsan","age":20},{"name":"李四","age":21}]
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/returnMap-ajax.do")
    public Map<Integer,Student> returnMap(Integer age, String name)  {
        System.out.println("age:"+ age+",name:"+name);
        Map<Integer,Student> map = new HashMap<>();

        Student student1 = new Student(name,age);
        Student student2 = new Student("李四",age+1);

        map.put(1,student1);
        map.put(2,student2);

        //{"1":{"name":"zhangsan","age":20},"2":{"name":"李四","age":21}}
        return map;
    }

    /**
     * 处理器方法返回一个String，String表示数据的，不是视图
     * 区分返回值String是数据，还是视图，看有没有@ResponseBody注解
     * 如果有@ResponseBody注解，返回一个String就是数据，反之就是视图
     * dataType:"text", 返回纯文本字符串,用于处理方法返回值是String类型的
     * 这种方式有中文乱码，f12查看：Content-Type: text/plain;charset=ISO-8859-1
     *
     * 发现默认使用ISO-8859-1字符集作为Content-Type，导致中文乱码
     * 解决方案：给RequestMapping增加一个属性produces，使用这个属性指定新的contextType
     *
     *  返回对象框架的处理流程：
     *   1、框架会把返回String类型，调用框架中的ArrayList<HttpMessageConverter>中的每个类的canWrite()方法
     *     检查那个HttpMessageConverter接口的实现类能处理Student类型的数据--StringHttpMessageConverter
     *
     *   2、框架会调用实现类的write(),StringHttpMessageConverter的write()方法
     *      把字符按照指定的编码
     *       ContextType:application/json;charset=utf-8
     *
     *   3、框架会调用@ResponseBody把2的结果输出到浏览器，ajax请求处理完成
     */
    @ResponseBody
    @RequestMapping(value = "/returnStringData-ajax.do",produces = "text/plain;charset=utf-8")
    public String returnStringData(Integer age, String name)  {
        System.out.println("age:"+ age+",name:"+name);
       
        return "Hello SpringMVC 返回对象，表示数据";
    }
}

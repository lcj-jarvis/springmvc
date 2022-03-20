package com.mrlu.springmvc.controller;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-02-13 15:22
 */
@Controller
public class MyController {
    private static final String SUCCESS = "success";

    /**
     * Rest风格的URL
     * 以CRUD为例
     * 新增： /order POST
     * 修改： /order/1 PUT   相当于update?id=1
     * 获取：  /order/1 GET  相当于get?id=1
     * 删除：  /order/1 DELETE 相当于delete?id=1
     *
     * 如何发送PUT和DELETE请求：
     * 1、需要配置HiddenHttpMethodFilter过滤器
     * 2、需要发送post请求
     * 3、需要在发送POST请求时携带一个name="_method"的隐藏域，值为DELETE或PUT
     *
     * 在springmvc的目标方法中如何获取id值？
     *  使用@PathVariable注解
     */
    @RequestMapping(value = "/get.do/{id}",method = RequestMethod.GET)
    public String testRestGet(@PathVariable("id")Integer id){
        System.out.println("查询的订单的id为："+id);
        return SUCCESS;
    }

    // 等价于以下方法
    /*@GetMapping("/get.do/{id}")
    public String testRestGet(@PathVariable("id")Integer id){
        System.out.println("查询的订单的id为："+id);
        return SUCCESS;
    }*/


    @RequestMapping(value = "/post.do",method = RequestMethod.POST)
    public String testRestPost(){
        System.out.println("添加成功");
        return SUCCESS;
    }

    // 等价于以下方法
    /*@PostMapping("/post.do")
    public String testRestPost(){
        System.out.println("添加成功");
        return SUCCESS;
    }*/

    @RequestMapping(value = "/delete.do/{id}",method = RequestMethod.DELETE)
    public String testRestDelete(@PathVariable("id")Integer id){
        System.out.println("删除的订单的id是："+id);
        return SUCCESS;
    }

    // 等价于以下方法
    /*@DeleteMapping(value = "/delete.do/{id}")
    public String testRestDelete(@PathVariable("id")Integer id){
        System.out.println("删除的订单的id是："+id);
        return SUCCESS;
    }*/

    @RequestMapping(value = "/put.do/{id}",method = RequestMethod.PUT)
    public String testRestPut(@PathVariable("id")Integer id){
        System.out.println("修改的订单的id是："+id);
        return SUCCESS;
    }

    // 等价于以下方法
    /*@PutMapping("/put.do/{id}")
    public String testRestPut(@PathVariable("id")Integer id){
        System.out.println("修改的订单的id是："+id);
        return SUCCESS;
    }*/
}

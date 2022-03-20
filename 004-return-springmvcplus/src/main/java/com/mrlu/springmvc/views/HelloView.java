package com.mrlu.springmvc.views;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-02-19 19:35
 */
/*
  自定义视图
*/
@Component
public class HelloView implements View {

    //这个方法返回内容的类型
    @Override
    public String getContentType() {
        return "text/html";
    }

    //渲染视图。可以在这里面操作其他的视图。比如整合Excel。
    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
       response.getWriter().write("hello view,time:"+new Date());
    }
}

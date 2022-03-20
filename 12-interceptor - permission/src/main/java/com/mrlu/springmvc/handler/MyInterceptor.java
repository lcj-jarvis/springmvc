package com.mrlu.springmvc.handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-02-17 15:29
 */
//拦击器类：拦截用户的请求
public class MyInterceptor implements HandlerInterceptor {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器的MyInterceptor的preHandle()");

        String param = "";
        Object name = request.getSession().getAttribute("name");
        if (name != null) {
            param = (String) name;
        }

        if (!"admin".equals(param)){
            request.getRequestDispatcher("/tips.jsp").forward(request,response);
            return  false;
        }
        return true;
    }
}

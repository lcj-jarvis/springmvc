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

    private long begin;

    /**
     * preHandle叫做预处理方法【目标方法调用之前】
     *  可以考虑做权限，日志，事务等等
     *  重要：是整个项目的入口，门户。当preHandle返回true。请求可以被处理
     *       preHandle返回false，请求到此方法就截止
     *  参数：
     *  Object handler ：被拦截的控制器对象MyController
     *  返回值：boolean
     *    true：请求通过拦截器的验证，可以执行处理器方法
     *    false：请求没有通过拦截器的验证，请求到达拦截器就截止了。请求没有被处理。
     *
     *  特点：
     *    1、方法在控制器方法（MyController的doSome方法）之前先执行
     *       用户的请求先到达这个方法
     *    2、在这个方法可以获取请求的信息，验证请求是否符合要求。
     *       可以验证用户是否登录，验证用户是否有权限访问某个连接地址（url）
     *       如果验证失败，可以截断请求，请求不能被处理
     *       如果验证成功，可以放行请求，此时控制器方法才能执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        begin = System.currentTimeMillis();
        System.out.println("拦截器的MyInterceptor的preHandle()");
        String name = request.getParameter("name");
        boolean flag = true;
        if (!"jackson".equals(name)){
            flag = false;
            request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request,response);
        }
        return flag;
    }

    /**
     * postHandle:后处理方法【调用目标方法之后，渲染视图之前】
     *  参数：
     *  Object handler：被拦截的处理器对象MyController
     *  ModelAndView modelAndView:处理器方法的返回值
     *  特点：
     *  1、在处理器方法之后执行的（MyController.doSome()）
     *  2、能够获取到处理器方法的返回值ModelAndView，可以修改ModelAndView中的
     *     数据和视图，可以影响到最后的执行结果
     *
     *     特点：
     *     1、在处理器方法（MyController的doSome方法）之后执行的
     *     2、能够获取到处理器方法的返回值ModelAndView，可以修改ModeAndView中的
     *        数据和视图，可以影响最后的执行结果
     *     3、主要是对原来的执行结果做二次修正
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截器的MyInterceptor的postHandle()");
        //对原来的doSome执行结果，需要调整
        if (modelAndView != null){
            //修改数据
            modelAndView.addObject("date",new Date());
            //修改视图
            modelAndView.setViewName("other");
        }
    }

    /**
     *
     * afterCompletion:最后执行的方法【渲染视图之后调用的】
     *   只要preHandle方法返回true，这个方法就会执行【注意】
     * 参数：
     * Object handler：被拦截的处理器对象MyController
     * Exception ex：程序中发生的异常
     *
     * 特点：
     *   1、在请求处理完成之后执行。框架中规定是当你的视图处理完成之后，对视图执行了forward。就认为请求处理完成
     *   2、一般做资源回收工作，程序请求过程中创建了一些对象，在这里可以删除，把占用的内存回收
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("拦截器的MyInterceptor的afterCompletion()");
        long end = System.currentTimeMillis();
        System.out.println("拦截器执行完毕的总时间："+(end - begin) +"ms");
    }
}

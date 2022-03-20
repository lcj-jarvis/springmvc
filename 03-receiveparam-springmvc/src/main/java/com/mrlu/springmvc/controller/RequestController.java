package com.mrlu.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author 简单de快乐
 * @date 2021-09-24 20:43
 *
 *
 * 设置值到Request域的五种方式
 *
 *
 * BindingAwareModelMap 继承 ModelMap ，实现 Model接口。
 * 而 ModelMap 又继承 Map
 */
@Controller
public class RequestController {

    @GetMapping("test/requestCodeOfHSR.do")
    public String test1(HttpServletRequest request) {
        request.setAttribute("username", "HttpServletRequest");
        return "scope";
    }

    // 推荐使用
    @GetMapping("test/requestCodeOfMAV.do")
    public ModelAndView test5() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("username", "ModelAndView");
        modelAndView.setViewName("scope");
        return modelAndView;
    }

    @GetMapping("test/requestCodeOfMap.do")
    public String test2(Map<String, String> map) {
        map.put("username", "Map");
        // BindingAwareModelMap
        System.out.println(map.getClass().getSimpleName());
        return "scope";
    }

    @GetMapping("test/requestCodeOfModelMap.do")
    public String test3(ModelMap map) {
        map.put("username", "ModelMap");
        // BindingAwareModelMap
        System.out.println(map.getClass().getSimpleName());
        return "scope";
    }

    @GetMapping("test/requestCodeOfModel.do")
    public String test4(Model model) {
        model.addAttribute("username", "Model");
        // BindingAwareModelMap
        System.out.println(model.getClass().getSimpleName());
        return "scope";
    }

    // 设置属性到session域中
    @GetMapping("test/setSession.do")
    public String test5(HttpSession session) {
        session.setAttribute("username", "hello session");
        return "scope";
    }

    // 设置到application域中
    @GetMapping("test/setApplication.do")
    public String test6(HttpSession session) {
        ServletContext servletContext = session.getServletContext();
        servletContext.setAttribute("username", "hello application");
        return "context";
    }
}

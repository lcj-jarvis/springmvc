package com.mrlu.ssm.controller;

import com.mrlu.ssm.domain.Person;
import com.mrlu.ssm.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-02-15 9:26
 */
@Controller
@RequestMapping("/Person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping("/addPerson.do")
    public ModelAndView addPerson(Person person){
        ModelAndView modelAndView = new ModelAndView();
        String tips = "";
        int nums = personService.addPerson(person);
        if (nums > 0){
            tips = person.getName()+"注册成功!!!";
        }
        modelAndView.addObject("tips",tips);
        modelAndView.setViewName("result");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/listPerson.do")
    public List<Person> queryPerson(){
        List<Person> list = personService.queryPersons();
        return list;
    }
}

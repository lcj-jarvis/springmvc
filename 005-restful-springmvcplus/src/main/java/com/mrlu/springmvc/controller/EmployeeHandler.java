package com.mrlu.springmvc.controller;


import com.mrlu.springmvc.dao.DepartmentDao;
import com.mrlu.springmvc.dao.EmployeeDao;
import com.mrlu.springmvc.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeHandler {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    /**
     *  显示所有的员工
     */
    @RequestMapping("/emps")
    public String list(Map<String, Object> map) {
        map.put("employees", employeeDao.getAll());
        return "list";
    }

    /**
     * 删除员工
     */
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id) {
        employeeDao.delete(id);

        return "redirect:/emps";
    }

    /**
     * 这个方法和下面的方法是联合使用的
     */
    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String input(Map<String, Object> map) {
        map.put("departments", departmentDao.getDepartments());
        //SpringMVC表单使用需要
        map.put("employee", new Employee());

        return "input";
    }

    /**
     *
     * @InitBinder 由该注解标识的方法，可以对WebDataBinder对象进行初始化。
     *            WebDataBinder是DataBinder的子类，用于完成由表单字段到JavaBean属性的绑定
     * @InitBinder方法不能有返回值，它必须声明为void
     * @InitBinder方法的参数值通常是WebDataBinder
     */
   /* @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        *//*
         lastName属性不赋值
        *//*
        webDataBinder.setDisallowedFields("lastName");
    }*/

    /**
     * 添加员工
     * BindingResult参数可以获取数据格式化的错误信息
     * BindingResult实际上是Errors类型，使用Errors作为形参也是可以的。
     * 需要注意的一点是：
     * 需要校验的Bean对象和其绑定结果的对象或错误对象成对出现的，他们之间不允许声明其他的形参
     */
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String save(@Valid Employee employee, BindingResult bindingResult,
                       Map<String, Object> map) {
        System.out.println(employee);
        //这样也行
        /*if (bindingResult.getErrorCount() > 0){ */
        if (bindingResult.hasErrors()){
            System.out.println("格式化错误！！！");
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError:fieldErrors) {
                //获取格式化错误的字段和错误的信息【使用JSR 303 它会帮你国际化】
                System.out.println(fieldError.getField()+":"+fieldError.getDefaultMessage());
            }
            //若验证出错，则转向定制的页面
            map.put("departments",departmentDao.getDepartments());
            //你会发现还有表单回显的功能
            return "input";
        }
        employeeDao.save(employee);
        //重定向到显示所有的员工
        return "redirect:/emps";
    }

    /**
     * 用于修改功能
     * 为什么不和上面共用一个input方法处理连个请求呢?因为要给id入参，如果id入参失败，就抛出异常
     *
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "emp/{id}", method = RequestMethod.GET)
    public String input(@PathVariable("id") Integer id, Map<String, Object> map) {
        map.put("employee", employeeDao.get(id));
        map.put("departments", departmentDao.getDepartments());
        return "input";
    }
    /**
     *  辅助修改功能
     *  给lastName入参
     */
    @ModelAttribute
    public void getEmployee(@RequestParam(value = "id",required = false)Integer id,Map<String, Object> map){
        if (id != null){
            Employee employee = employeeDao.get(id);
            //和下面的Employee首字母小写一致
            map.put("employee",employee);
        }
    }
    /**
     * 修改
     * @param employee 表单入参的员工，要配合modelAttribute方法使用，才能给lastName属性入参
     * @return
     */
    @RequestMapping(value = "/emp",method = RequestMethod.PUT)
    public String update(Employee employee){
       employeeDao.save(employee);
       return "redirect:/emps";
    }
}

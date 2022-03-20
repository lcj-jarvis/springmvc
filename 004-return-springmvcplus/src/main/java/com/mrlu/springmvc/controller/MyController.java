package com.mrlu.springmvc.controller;

import com.mrlu.springmvc.domain.Student;
import com.mrlu.springmvc.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-02-13 15:22
 */

/**
 * @SessionAttributes 除了可以通过属性名指定需要放到会话中的属性外【实际上是使用注解的value属性值】
 *  还可以通过模型属性的对象类型指定哪些模型属性需要放到会话中【实际上是使用了注解的types属性值】
 *
 *  注意：该注解只能用在类的上面，不能用在方法的上面
 */

//@SessionAttributes(value = {"user","abc"},types = String.class)
//把user对象和"冲冲冲"字符串放到session域中
@SessionAttributes(value = {"user"},types = String.class)
@Controller
@RequestMapping("/test")
public class MyController {

    /**
     * 目标方法可以添加Map类型（实际上也可以是Model类型或ModeMap类型）的参数
     * 实际上是把map放到了ModelAndView的ModelMap里，把请求转发的的页面放到了ModelAndView的view里
     *
     */
    @RequestMapping("/testMap")
    public String testMap(Map<String,Object> map){
        //org.springframework.validation.support.BindingAwareModelMap
        System.out.println(map.getClass().getName());
        map.put("names", Arrays.asList("jack","mary","john"));
        return "show";
    }

    @RequestMapping("/testSessionAttribute")
    public String testSessionAttribute(Map<String,Object> map){
        User user = new User("jack",22);
        //放到request域中
        map.put("user",user);
        //放到request域中
        map.put("names","冲冲冲");
        return "show";
    }

    //仿真主要在getStudent方法和testModeAttribute方法，以及下面的方法打断点
    //ModelAttributeMethodProcessor类的 resolveArgument方法
    // 和 InvocableHandlerMethod 类的 getMethodArgumentValues方法 和 invokeForRequest方法

    /**
     * 1、有@ModelAttribute标记的方法，会在每个目标方法执行之前被SpringMVC调用【注意】
     * 2、@ModelAttribute注解也可以来修饰目标方法POJO类型的入参，其value属性值有如下的作用
     *    （1）SpringMVC会使用value属性值在implicitModel中查找对应的对象，若存在则会直接传入到目标方法的入参中
     *    （2）SpringMVC会以value会key，POJO类型对象为value，存入到request中
     */
    @ModelAttribute
    public void getStudent(@RequestParam(value = "id",required = false)Integer id
            ,Map<String,Object> map){
        System.out.println("=====ModelAttribute=====");
         if(id != null){
             //模拟从数据库中获取对象
             Student student = new Student(1,"Tom","12345","tom@qq.com",22);
             System.out.println("从数据库中获取的一个对象："+student);
             //map.put("student",student);
             map.put("abc",student);
         }
    }

    /**
     * 运行流程：
     * 1、执行@ModelAttribute注解修饰的方法：从数据库取出对象，把对象放入到Map中，键为student
     * 2、SpringMVC从Map中取出Student对象，并把表单的请求参数赋给Student对象的对应属性
     * 3、SpringMVC把上述对象传入目标方法的参数
     * 注意：在@ModelAtribute修饰的方法中，放入到Map时的键需要和目标方法传入的参数类型的第一个字母小写的字符串一致。
     *
     * SpringMVC确定目标方法POJO类型入参的过程
     * 1、确定一个key:
     *   (1)若目标方法的POJO类型的参数没有使用@ModelAttribute作为修饰，则key为POJO类名的字母小写
     *  （2）若使用了@ModelAttribute来修饰，则key为@ModelAttribute注解的value属性值
     * 2、在implicitModel中查找key对应的对象，若存在，则作为入参传入
     *    （1）若在@ModelAttribute标记的方法中在Map中保存过，且Map和key和1中确定的key一致，则会获取到
     * 3、若implicitModel中不存在key对应的对象，则检查当前Handler是否使用@SessionAttributes注解修饰
     *    若使用了该注解，且@SessionAttributes注解的value属性值包含了1中的key，则会从HttpSession中来获取key
     *    所对应的value，若存在则直接传入到目标方法的入参中，若不存在则将抛出异常。
     * 4、若Handler没有表示@SessionAttributes注解或@SessionAttributes注解的value值中不包含key，则
     *    会通过反射来创建POJO类型的参数，传入到目标方法的参数中
     *
     * 5、SpringMVC会把key和POJO类型的对象，保存到implicitModel中，进而保存到request域中
     *
     * 源码分析的流程：
     * 1、调用@ModelAttribute注解修饰的方法。实际上把@ModelAttribute方法中的Map中的数据放到了implicitModel【ExtendModelMap】中
     * 2、解析请求处理器的目标参数，实际上该目标参数来自于WebDataBinder对象的target属性
     *   （1）创建WebDataBinder对象：
     *      a、确定objectName属性：若传入的attrName的值为"",则objectName为类名的第一个字母小写
     *         【注意】attrName。若目标方法的POJO参数使用了@ModelAttribute来修饰，则attrName值即为@ModelAttribute的value属性值
     *
     *      b、确定target属性：
     *      在implicitModel中查找attrName对应的属性值。若存在，就找到
     *      若不存在：则验证当前Handler（处理器对象）是否使用了@SessionAttributes进行修饰，若使用了，则尝试从session域中
     *              获取attrName所对应的属性值。若session中没有对应的属性值，则抛出异常。【SpringMVC新版没有了这个判断】
     *      若Handler没有使用@SessionAttributes进行修饰，或@SessionAttributes中没有使用value指定的key和attrName相匹配，
     *              通过反射创建POJO类对象
     *   （2）SpringMVC把表单的请求参数赋值给WebDataBinder的target对应的属性
     *   （3）SpringMVC会把WebDataBinder的attrName和target给到implicitModel。进而传到request域对象中
     *   （4）把WebDataBinder的target作为参数传递给目标方法的形参
     */
    @RequestMapping("/testModeAttribute")
    public String testModeAttribute(@ModelAttribute("abc") Student student){
        System.out.println("修改："+student);
        //修改：Student{id=1, name='Tom', password='null', email='tom@qq.com', age=22}
        //这样的话，往数据库里更的话，密码就会被改了
        return "show";
    }


    @RequestMapping("/testView")
    public String testView(){
        System.out.println("=====testView=====");
        return "show";
    }
    @RequestMapping("/testInternational")
    public String testInternational(){
        return "show";
    }


    @RequestMapping("/testDefineView")
    public String testDefineView(){
        System.out.println("自定义视图");
        return "helloView";
    }


    @RequestMapping("/testRedirect")
    public String testRedirect(){
        System.out.println("请求重定向");
        return "redirect:/index.jsp";
    }

}




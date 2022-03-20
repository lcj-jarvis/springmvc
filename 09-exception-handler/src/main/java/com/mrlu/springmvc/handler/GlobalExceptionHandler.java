package com.mrlu.springmvc.handler;

import com.mrlu.springmvc.exception.AgeException;
import com.mrlu.springmvc.exception.NameException;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-02-17 14:35
 *
 *
 * @ControllerAdvice: 控制器增强（也就是说给控制类增强功能--异常处理功能）
 *  位置：在类的上面
 *  特点：
 *      1、必须让框架知道这个注解所在的包名，需要在springmvc配置文件声明组件扫描器
 *       指定@ControllerAdivice所在的包名
 *      2、如果当前的Handler【处理器】中找不到@ExceptionHandler标注的方法来处理发生的异常，
 *         则将去@ControllerAdvice标记的类中查找@ExceptionHandler标记的方法来处理异常。
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @ExceptionHandler方法的入参不能传入Map，若希望把异常信息传到页面上，需要使用ModelAndView作为返回值
     * @ExceptionHandler注解对应ExceptionHandlerExceptionResolver
     */
    /*@ExceptionHandler(value = NameException.class)
    public String doNameException(Exception e, Map<String, Object> map){
        map.put("msg","姓名必须是zs，不能是其他的");
        map.put("exception",e);
        return "nameError";
    }*/

    /**
     * 定义方法，处理发生的异常
     * 处理异常的方法和控制器方法的定义一样，可以有多个参数，可以有ModelAndView，
     * String，void，对象类型的返回值
     *
     * 形参：Exception，表示Controller中抛出的异常对象
     * 通过形参可以获取发生的异常信息
     *
     * @ExceptionHandler(异常的class)： 表示异常的类型，当发生此类型异常时，
     *                                由当前方法处理
     *
     */
    @ExceptionHandler(value = NameException.class)
    public ModelAndView doNameException(Exception e){
        //处理NameException异常

        /*异常处理的逻辑
          1、需要把异常记录下来，记录到数据库，日志文件
             记录日志发生的时间，哪个方法发生的，异常错误内容
          2、发送通知，把异常的信息通过邮件，短信，微信发送给相关人员
          3、给用户友好的提示*/
        System.out.println("NameException异常的信息:"+e.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg","姓名必须是zs，不能是其他的");
        modelAndView.addObject("exception",e);
        modelAndView.setViewName("nameError");
        return modelAndView;
    }

    /**
     * 方法标注的异常有优先级的问题，按照异常从小到大执行。
     * NameException是RuntimeException的子类，所以不执行这个方法。执行doNameException方法
     */
    /*@ExceptionHandler(value = RuntimeException.class)
    public ModelAndView doRuntimeException(Exception e){
        System.out.println("RuntimeException异常的信息:"+e.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg","姓名必须是zs，不能是其他的");
        modelAndView.addObject("exception",e);
        modelAndView.setViewName("nameError");
        return modelAndView;
    }*/

    //处理AgeException
    @ExceptionHandler(value = AgeException.class)
    public ModelAndView doAgeException(Exception e){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg","年龄不能大于80");
        modelAndView.addObject("exception",e);
        modelAndView.setViewName("ageError");
        return modelAndView;
    }

    //处理其他异常，NameException和AgeException以外，不知类型的异常
    //相当于if-else中的else

    /**
     * 处理其他异常，NameException和AgeException以外，不知类型的异常
     * 相当于if-else中的else
     *
     * 测试
     */
    /*@ExceptionHandler
    public ModelAndView doOtherException(Exception e){
       //处理其他异常
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg","其他异常");
        modelAndView.addObject("exception",e);
        modelAndView.setViewName("defaultError");
        return modelAndView;
    }*/
}

package com.mrlu.springmvc.test;

import com.mrlu.springmvc.dao.EmployeeDao;
import com.mrlu.springmvc.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-02-20 15:54
 */
@Controller
public class SpringMvcTest {

    @Autowired
    private EmployeeDao employeeDao;

    /**
     * 使用自定义转换器，将字符串形式的对象转换成对象
     *
     * @param employee
     * @return
     */
    @RequestMapping("/testConversionServiceConverter")
    public String testConversionServiceConverter(@RequestParam(value = "employee", required = false) Employee employee) {
        System.out.println("save:" + employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @ResponseBody
    @RequestMapping("/testJson")
    public Collection<Employee> testJson() {
        return employeeDao.getAll();
    }

    /**
     *上传【实际上这种方式完成不了文件的上传】
     */
    @ResponseBody
    @RequestMapping("/testHttpMessageConverter")
    public String testHttpMessageConverter(@RequestBody String body) throws IOException {
        System.out.println(body);
        return "hello" + new Date();
    }

    /**
     *下载
     */
   /* @RequestMapping("/testResponseEntity")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {
        byte[] body = null;
        ServletContext servletContext = session.getServletContext();
        InputStream in = servletContext.getResourceAsStream("/files/day05(springmvc的执行流程).txt");
        body = new byte[in.available()];
        in.read(body);

        //下载文件名的乱码解决见javaweb的模块
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",
                "attachment;filename=day05(springmvc的执行流程).txt");

        HttpStatus statusCodes = HttpStatus.OK;

        ResponseEntity<byte[]> response = new ResponseEntity<>(body,headers,statusCodes);
        return response;
    }*/

    /**
     *下载
     */
    @ResponseBody
    @RequestMapping("/testResponseEntity")
    public byte[] testResponseEntity(HttpServletResponse response,HttpSession session) throws IOException {

        ServletContext servletContext = session.getServletContext();
        InputStream in = servletContext.getResourceAsStream("/files/day05(springmvc的执行流程).txt");
        byte[] body = new byte[in.available()];
        in.read(body);

        response.setHeader("Content-Disposition" , "attachment;filename=day05(springmvc的执行流程).txt");
        return body;
    }

    /**
     * 用于国际化
     */
    @Autowired
    private ResourceBundleMessageSource messageSource;

    @RequestMapping("/i18n")
    public String testI18n(Locale locale){
       String value =  messageSource.getMessage("i18n.username",null,locale);
       System.out.println(value);
       return "i18n";
    }

    @RequestMapping("/testFileUpload")
    public String testFileUpload(@RequestParam("desc")String desc,
                                 @RequestParam("file")MultipartFile file) throws IOException {
        System.out.println("desc:"+desc);
        String fileName = file.getOriginalFilename();
        System.out.println("文件原始的名字是："+fileName);

        File newFile = new File("D:/入门框架/SpringMVC/自己做的课程笔记(进阶版)",fileName);
        //使用该方法保存文件到本地
        file.transferTo(newFile);

        return "success";
    }
}

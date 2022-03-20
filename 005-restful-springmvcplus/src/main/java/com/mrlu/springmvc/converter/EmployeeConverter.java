package com.mrlu.springmvc.converter;

import com.mrlu.springmvc.domain.Department;
import com.mrlu.springmvc.domain.Employee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 自定义的数据转换器：
 *    1、实现Converter<S, T>接口
 *       S:转换前的类型
 *       T：转换后的类型
 *    2、在配置文件中配置ConversionServiceFactoryBean
 *       以及注解驱动。
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-02-20 16:04
 */
@Component
public class EmployeeConverter implements Converter<String, Employee> {
    @Override
    public Employee convert(String source) {
        if(source != null){
            String[] values = source.split("-");
            if (values != null && values.length == 4){
                String lastName = values[0];
                String email = values[1];
                Integer gender = Integer.parseInt(values[2]);
                Department department = new Department();
                Employee employee = new Employee(null,lastName,email,gender,department);
                System.out.println(source+"--converter--"+employee);
                return employee;
            }
        }
        return null;
    }
}

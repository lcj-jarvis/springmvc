package com.mrlu.springmvc.vo;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-02-13 22:32
 */
//保存请求参数值的一个普通类
public class Student {
    //属性名和请求参数名一样
    private String name;
    private Integer age;

    public Student() {
        System.out.println("Student类的无参构造");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("setName:"+name);
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        System.out.println("setAge:"+age);
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

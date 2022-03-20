<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/19
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--使用springmvc的form标签--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <%--
           1、为什么要使用form标签
             可以更快地开发出表单页面，而且可以更方便的进行表单的回显
           2、注意：
              使用SpringMVC的form标签，要求必须在request域中有一个bean的属性和表单的字段对应上。
              可以通过modolAttribute属性指定绑定的模板属性
              若没有指定该属性，则默认从request域对象中读取command的表单bean。
              如果该属性值也不存在【即对应不上】，则会发生异常
              java.lang.IllegalStateException:
              Neither BindingResult nor plain target object for bean name 'command' available as request attribute

              关于SpringMVC表单更多的描述见尚硅谷ppt
        --%>
        <%--employee和map的key（employee）对应--%>
        <form:form action="${pageContext.request.contextPath}/emp" method="post" modelAttribute="employee" >
            <%--path：表单字段，对应 html 元素的 name 属性，支持级联属性
                      要求path的属性值必须和modelAttribute的里的bean的属性对应【注意】
            --%>
            <c:if test="${employee.id == null}">
                ListName:<form:input path="lastName"></form:input>
                <form:errors path="lastName"></form:errors>
            </c:if>
            <c:if test="${employee.id != null}">
                <form:hidden path="id"></form:hidden>
                <input type="hidden" name="_method" value="PUT"/>
                <%--对于method不能使用form:hidden标签，因为modelAttribute对应的bean中没有_method这个属性--%>
                <%--<form:input path="_method" value="PUT"></form:input>--%>
            </c:if>

            <br/>
            Email:<form:input path="email"></form:input>
            <form:errors path="email"></form:errors>
            <br/>
            <%
                Map<String,String> genders = new HashMap<>();
                genders.put("1","Male");
                genders.put("0","Female");
                request.setAttribute("genders",genders);
            %>
            <%--
             delimiter：多个单选框可以通过delimiter指定分割符
            --%>
            Gender:<br/>
            <form:radiobuttons path="gender" items="${genders}" delimiter="<br/>"></form:radiobuttons>
            <br/>
            <%--
               items:可以是一个List，String[]或者Map
               itemValue:指定选项的value值。可以是集合中bean的一个属性值
               itemLable：指定选项的label【标签】值
            --%>
            Department:<form:select path="department.id" items="${departments}" itemValue="id" itemLabel="departmentName"></form:select>
            <br/>
            <%--
                birth是一个Data类型
                存在以下的几种问题：
                1、数据类型转换
                2、数据类型格式化
                解决这问题的方式，在birth属性【日期类型】上使用以下的注解@DateTimeFormat(pattern="格式化样式")
                例如：@DateTimeFormat(pattern="yyyy-MM-dd")

                使用注解@NumberFormat(pattern = "#,###,###.#")格式化数字

                3、数据校验
                   （1）如何校验？注解？
                      ①使用JSR 303 依赖
                      ②加入hibernate validator依赖
                          <dependency>
                              <groupId>org.hibernate</groupId>
                              <artifactId>hibernate-validator</artifactId>
                              <version>6.1.0.Final</version>
                           </dependency>
                      ③在SpringMVC的配置文件中加入<mvc:annotation-driven />注解驱动
                      ④在bean的属性上添加对应的注解。常用的有：@NotNull @Email @Past @NotEmpty
                           更多见尚硅谷ppt
                      ⑤在目标方法的bean类型的形参前加@Valid注解
                   （2）验证出错转向哪个页面？
                       如果BindingResult包含错误的信息，就转向错误的页面。
                       注意： 需要校验的Bean对象和其绑定结果的对象或错误对象成对出现的，他们之间不允许声明其他的形参
                   （3）错误消息？如何显示，如何把错误信息进行国际化
                       显示错误信息：使用<form:errors path="对应的字段名"></form:errors>标签
                       例如：<form:errors path="lastName"></form:errors>

                       错误信息国际化：
                       1、配置ResourceBundle配置文件【在resources目录下】
                       2、在springmvc中配置国际资源文件
                       <bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
                              <property name="basename" value="i18n"></property>
                       </bean>
            --%>
            Birth<form:input path="birth" />
            <form:errors path="birth"></form:errors>
            <br/>
            Salary<form:input path="salary" /><br/>
            <input type="submit" value="Submit">
        </form:form>
    </body>
</html>

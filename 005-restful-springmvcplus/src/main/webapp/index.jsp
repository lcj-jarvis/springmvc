<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/13
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>首页</title>
    <script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
          $(function () {
              $("#testJson").click(function () {
                  //请求的url
                  var url = this.href;
                  //请求的参数
                  var args = {};
                  $.post(url,args,function (data) {
                      for (var i = 0; i < data.length; i++) {
                          var id = data[i].id;
                          var lastName = data[i].lastName;
                          alert(id+","+lastName);
                      }
                  },"json");
                  /*在这里com.mrlu.springmvc.test.SpringMvcTest测试*/
                  return false;
              });
          })
    </script>
</head>
<body>
     <p>restful风格的crud</p><br/>
     <a href="emps">查询所有的员工</a><br/>
     ===================================<br/>
     <p>自定义转换器</p><br/>
     <form action="testConversionServiceConverter" method="post">
         <%--传入员工的字符串，将它解析成一个对象
         lastname-email-gender-department.id 例如：GG-gg@atguigu.com-0-105
         --%>
         Employee:<input type="text" name="employee">
         <input type="submit" value="提交">
     </form>
     ===================================<br/>
     <p>ajax请求和Json</p>
     <a href="testJson" id="testJson">Test Json</a><br/>
     <form action="testHttpMessageConverter" method="post" enctype="multipart/form-data">
         File:<input type="file" name="file"/><br/>
         Desc:<input type="text" name="desc"/><br/>
         <input type="submit" value="Submit">
     </form>
     <br/>
     <a href="testResponseEntity" >Test Download</a><br/>
     ===================================<br/>
     <p>i18n国际化</p>
     <%--
         关于国际化：
         1、在页面上能够根据浏览器语言设置的情况对文本（不是语言），数值进行本地化处理
         2、可以对bean中获取的国际化资源文件Locale对应的消息
         3、可以通过超链接切换Locale，而不依赖于浏览器的语言设置情况

         解决：
         1、使用jstl的fmt标签
         2、在bean中注入ResourceBundleMessageSource的实例，使用其对应的getMessage方法即可
         3、在SpingMVC的配置文件中配置LocalResolver和LocalChangeInterceptor
     --%>
     <a href="i18n">I18N PAGE</a>
     <br/><br/>
     <a href="i18n?locale=zh_CN">中文</a>
     <a href="i18n?locale=en_US">英文</a>
     ===================================<br/>
     <p>使用SpringMVC框架的MultipartResolver完成文件的上传</p>
     <%--
        1、加入文件上传的依赖
        <dependency>
          <groupId>commons-fileupload</groupId>
          <artifactId>commons-fileupload</artifactId>
          <version>1.3.1</version>
        </dependency>

        2、在SpringMVC配偶文件中配置CommonsMultipartResolver
     --%>
     <form action="testFileUpload" method="post" enctype="multipart/form-data">
         File:<input type="file" name="file"/><br/>
         Desc:<input type="text" name="desc"/><br/>
         <input type="submit" value="Submit">
     </form>
</body>
</html>

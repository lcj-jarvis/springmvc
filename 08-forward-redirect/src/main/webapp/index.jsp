<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/13
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
       <p>当出现方法返回ModelAndView实现forward</p>
       <form action="user/some.do" method="post">
           姓名<input type="text" name="name"><br/>
           年龄<input type="text" name="age"><br/>
           <input type="submit" value="提交">
       </form>
       <br/>
       <br/>
       <br/>
       <p>当出现方法返回ModelAndView实现redirect</p>
       <form action="user/redirect.do">
           姓名<input type="text" name="name"><br/>
           年龄<input type="text" name="age"><br/>
           <input type="submit" value="提交">
       </form>


</body>
</html>

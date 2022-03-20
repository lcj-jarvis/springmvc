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

       <form action="get.do/1" method="get">
              <input type="submit" value="get请求">
       </form>
       <br/>

       <form action="post.do" method="post">
              <input type="submit" value="post请求">
       </form>
       <br/>

       <form action="delete.do/1" method="post">
              <input type="hidden" name="_method" value="DELETE">
              <input type="submit" value="post请求转成delete">
       </form>
       <br/>

       <form action="put.do/1" method="post">
              <input type="hidden" name="_method" value="PUT">
              <input type="submit" value="post请求转成put">
       </form>
       <br/>

</body>
</html>

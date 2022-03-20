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
       <p>springmvc项目第一个</p>
       <a href="test/some.do">发起some.do请求</a>
       <a href="test/other.do">发起other.do请求</a>
       <br/>

       <form action="test/post.do" method="post">
              <input type="submit" value="post请求">
       </form><br/>

       <form action="test/get.do" method="get">
              <input type="text" name="username"/><br/>
              <input type="text" name="password"/><br/>
              <input type="submit" value="get请求">
       </form>

       <br/>
       <form action="test/param.do" method="get">
              <input type="text" name="username"/><br/>
              <input type="text" name="age"/><br/>
              <input type="submit" value="get请求">
       </form>
       <br/>
       <a href="test/aabb/pdd/some.do">Ant风格的url</a>
       <br/>
       <a href="test/pathVariable.do/1">testParamVariable</a>
</body>
</html>

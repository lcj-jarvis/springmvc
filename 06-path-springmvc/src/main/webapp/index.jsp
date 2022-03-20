<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/13
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + request.getContextPath() + "/";

    pageContext.setAttribute("path",path);
%>
<html>
<head>
    <title>首页</title>
    <base href="${pageContext.getAttribute("path")}"/>
</head>
<body>
       <p>springmvc项目第一个</p>
       <a href="user/some.do">发起user/some.do请求的解决方式</a><br/>

       <a href="/user/some.do">发起/user/some.do的错误请求</a><br/>
       <a href="${pageContext.request.contextPath}/user/some.do">发起/user/some.do错误请求的解决方式</a>
</body>
</html>

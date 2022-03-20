<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/17
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        defaultException处理页面<br/>
        ${msg}<br/>
        <%--实际是调用了Exception的getMessage()方法--%>
        异常信息:${exception.message}
</body>
</html>

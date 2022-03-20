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
      <a href="test/testMap">testMap</a>
      <a href="test/testSessionAttribute">testSessionAttribute</a>
      <br/>
      <%--
         模拟修改操作
         1、原始数据：1，TOM,123456,tom@qq.com,22
         2、密码不能被修改
         3、表单回显，模拟操作直接在表单填写对应的属性值
      --%>
      <form action="test/testModeAttribute">
          <input type="hidden" name="id" value="1">
          name:<input type="text" name="name" value="Tom"><br/>
          email:<input type="text" name="email" value="tom@qq.com"><br/>
          age:<input type="text" name="age" value="22"><br/>
          <input type="submit" value="提交">
      </form>
      <br/>
      <a href="test/testView">testView</a>
      <br/>
      <a href="test/testInternational">国际化</a>
      <br/>
      <a href="test/testDefineView">自定义视图</a>
      <br/>
      <a href="test/testRedirect">重定向</a>
</body>
</html>

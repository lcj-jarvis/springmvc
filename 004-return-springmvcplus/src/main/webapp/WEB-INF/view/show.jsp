<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/13
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
      ${names}
      <br/>
      request域里的数据：${requestScope.user}<br/>
      session域里的数据：${sessionScope.user}<br/>
      request域里的数据：${requestScope.names}<br/>
      session域里的数据：${sessionScope.names}<br/>
      request域里的数据：${requestScope.student}<br/>
      ==================================================<br/>
      ${requestScope.abc}
      ${requestScope.student}
      ==================================================<br/>
      国际化，要修改浏览器的语言设置<br/>
      <fmt:message key="i18n.username"></fmt:message><br/>
      <fmt:message key="i18n.password"></fmt:message><br/>
</body>
</html>

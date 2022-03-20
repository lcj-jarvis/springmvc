<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/15
  Time: 9:37
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
    <base href="${pageContext.getAttribute("path")}"/>
    <title>首页</title>
</head>
<body>
      <div align="center">
          <p>SSM整合</p>
          <table>
              <tr>
                  <td>
                      <a href="addPerson.jsp">注册信息</a>
                  </td>
              </tr>
              <tr>
                  <td>
                      <a href="listPerson.jsp">浏览信息</a>
                  </td>
              </tr>
          </table>
      </div>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/16
  Time: 14:25
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
    <title>注册信息</title>
</head>
<body>
     <div align="center">
         <form action="Person/addPerson.do" method="post">
             <table>
                 <tr>
                     <td>姓名：</td>
                     <td><input type="text" name="name"></td>
                 </tr>
                 <tr>
                     <td>年龄：</td>
                     <td><input type="text" name="age"></td>
                 </tr>
                 <tr>
                     <td><input type="submit" value="注册"></td>
                 </tr>
             </table>
         </form>
     </div>
</body>
</html>

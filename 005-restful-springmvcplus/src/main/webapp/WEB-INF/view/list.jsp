<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/13
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <%--静态资源，要处理访问的问题
    --%>
    <%--从webapp目录下开始--%>
    <script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>

    <script type="text/javascript">
        $(function () {
           $(".delete").click(function () {
               var href = $(this).attr("href"); //返回超链接的href值
               alert(href);
               $("#myform").attr("action",href).submit(); //设置表单的action值,并提交
               return false;
           });
        })
    </script>
</head>
<body>
          <form id= "myform" action="" method="post">
              <input type="hidden" name="_method" value="DELETE">
          </form>

          <c:if test="${empty requestScope.employees}">
                没有员工信息！！！
          </c:if>
          <c:if test="${!empty requestScope.employees}">
              <%--这个属性定义了表格单元的内容和边框之间的空间大小--%>
              <table border="1" cellspacing="0" cellpadding="10">
                  <tr>
                      <th>ID</th>
                      <th>LastName</th>
                      <th>Email</th>
                      <th>Gender</th>
                      <th>Department</th>
                      <th>Edit</th>
                      <th>Delete</th>
                  </tr>
                  <c:forEach items="${requestScope.employees}" var="emp">
                      <tr>
                          <td>${emp.id}</td>
                          <td>${emp.lastName}</td>
                          <td>${emp.email}</td>
                          <td>${emp.gender == 0? 'Female':'Males'}</td>
                          <td>${emp.department.departmentName}</td>
                          <td><a href="emp/${emp.id}">Edit</a></td>
                          <%--难点一：超链接的请求只能是get请求，如何把get请求转换为post请求
                              使用jQuery

                              注意：这个超链接。不能用id来绑定单击事件，因为在for循环里面。注意id就重复了
                              有bug。只有第一行的id属性有效，其他的都失效
                          --%>
                          <td><a class="delete" href="emp/${emp.id}">Delete</a></td>
                      </tr>
                  </c:forEach>
              </table>
          </c:if>

          <a href="emp">Add New Employee</a>
</body>
</html>

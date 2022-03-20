<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/16
  Time: 14:44
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
        <title>浏览信息ajax</title>
        <base href="${pageContext.getAttribute("path")}"/>
        <script type="text/javascript" src="lib/jquery-1.7.2.js"></script>
        <script type="text/javascript">
            $(function () {
                getPersonList();

                $("#get").click(function () {
                    getPersonList();
                })

            });

            function getPersonList() {
                $.ajax({
                    url:"Person/listPerson.do",
                    type:"get",
                    dataType:"json",
                    success:function (data) {
                        //清除旧的数据
                        $("#info").html("");

                        //获取新的数据
                        $.each(data,function (i,person) {
                            $("#info").append("<tr>").append("<td>"+person.id+"</td>")
                            .append("<td>"+person.name+"</td>").append("<td>"+person.age+"</td>")
                            .append("</tr>")
                        })
                    }
                })
            }
        </script>
    </head>
    <body>
         <div align="center">
                 <table>
                     <thead>
                     <tr>
                         <th>编号</th>
                         <th>姓名</th>
                         <th>年龄</th>
                     </tr>
                     </thead>
                     <tbody id="info">

                     </tbody>
                 </table>
                 <input id="get" type="submit" value="查询信息">
         </div>
    </body>
</html>

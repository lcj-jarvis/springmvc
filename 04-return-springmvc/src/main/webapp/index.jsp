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
    <script type="text/javascript" src="lib/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#btn").click(function () {
                $.ajax({
                   /* url:"test/returnVoid-ajax.do",*/
                   /* url:"test/returnStudent-ajax.do",*/
                   /* url:"test/returnList-ajax.do",*/
                   /* url:"test/returnMap-ajax.do",*/
                    url:"test/returnStringData-ajax.do",
                    data:{
                        name:"zhangsan",
                        age:20
                    },
                    type:"get",
                   /* dataType:"json",*/
                    dataType:"text", /*返回纯文本字符串,用于处理方法返回值是String类型的*/
                    //resp重服务器端返回的是json的字符串
                    //jQuery会把字符串转为json对象，赋值给resp形参
                    success:function (resp) {
                       /*alert(resp.name+","+resp.age);*/
                        //for循环
                       /* $.each(resp,function (i,student) {
                            alert(student.name+"   "+student.age);
                        })*/
                        alert(resp);
                    }
                })
            })
        })
    </script>
    <title>首页</title>
</head>
<body>
       <p>处理器方法返回String表示视图名称</p>
       <form action="test/returnString-view1.do" method="get">
              姓名：<input type="text" name="name"/><br/>
              年龄：<input type="text" name="age"/><br/>
              <input type="submit" value="提交请求参数">
       </form>
       <br/>

       <p>处理器方法返回String，表示完全视图路径，此时不能配置视图解析器</p>
       <form action="test/returnString-view2.do" method="get">
              姓名：<input type="text" name="name"/><br/>
              年龄：<input type="text" name="age"/><br/>
              <input type="submit" value="提交请求参数">
       </form>
       <br/>

       <button id="btn">发起ajax请求</button>
</body>
</html>

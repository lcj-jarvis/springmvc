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
       <p>提交参数给Controller</p>
       <form action="test/receive.do" method="post">
              姓名：<input type="text" name="name"/><br/>
              年龄：<input type="text" name="age"/><br/>
              <input type="submit" value="提交请求参数">
       </form>
       <br/>
       <p>请求参数和形参名不一致的情况</p>
       <form action="test/doOther.do" method="post">
              姓名：<input type="text" name="username"/><br/>
              年龄：<input type="text" name="userAge"/><br/>
              <input type="submit" value="提交请求参数">
       </form>
       <br/>
       <p>用对象接收请求的参数</p>
       <form action="test/receiveObject.do" method="get">
              姓名：<input type="text" name="name"/><br/>
              年龄：<input type="text" name="age"/><br/>
              <input type="submit" value="提交请求参数">
       </form>

       <br/>
       <p>用对象【级联属性赋值】接收请求的参数</p>
       <form action="test/receiveUser.do" method="get">
              用户名：<input type="text" name="username"/><br/>
              密码：<input type="text" name="password"/><br/>
              <%--级联属性赋值--%>
              省份：<input type="text" name="address.province"/><br/>
              城市：<input type="text" name="address.city"/><br/>
              <input type="submit" value="提交请求参数">
       </form>
       <br/>
       <p>使用servlet原生的API作为方法的目标参数</p>
       <form action="test/receiveServlet.do" method="get">
              姓名：<input type="text" name="name"/><br/>
              年龄：<input type="text" name="age"/><br/>
              <input type="submit" value="提交请求参数">
       </form>

       <br/>
       <a href="test/testRequestHeader.do">@RequestHeader的使用</a>
       <br/>
       <a href="test/testCookie.do">@CookieValue映射cookie</a>

       <br/>
       <a href="test/requestCodeOfHSR.do">通过HttpServletRequest设置请求域</a>
       <br/>
       <a href="test/requestCodeOfMap.do">通过Map设置请求域</a>
       <br/>
       <a href="test/requestCodeOfModelMap.do">通过ModelMap设置请求域</a>
       <br/>
       <a href="test/requestCodeOfModel.do">通过Model设置请求域</a>
       <br/>
       <a href="test/requestCodeOfMAV.do">通过ModelAndView设置请求域</a>
       <br/>
       <a href="test/setSession.do">保存值到session域</a>
       <br/>
       <a href="test/setApplication.do">保存值到application域</a>
</body>
</html>

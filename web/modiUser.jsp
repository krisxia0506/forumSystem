<%--@elvariable id="user" type="forum.servlet.UserServlet"--%>
<%--
Created by IntelliJ IDEA.
forum.beans.User: Administrator
Date: 2022-09-29
Time: 11:20
To change this template use File | Settings | File Templates.
程序5.5
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="checkvaild.jsp" %>
<jsp:useBean id="userDao" class="forum.dao.UserDAO" scope="page"/>
<%@page language="java" import="java.sql.*" %>
<!DOCTYPE>
<html>
<head>
    <title>新闻发布系统V2</title>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
    <script src="js/fun.js"></script>
</head>
<body>
<jsp:include page="common/top.jsp"/>
<div id="content">
    <div class="aside">
        <jsp:include page="common/left.jsp"/>
    </div>
    <div id="main">
        <!-- main begin -->
        <div class="inputform">
            <div class="top-bar">
                <h1>用户修改</h1>
                <div class="breadcrumbs"></div>
            </div>
            <br/>
            <form action="user?action=modi" method="post">
                <input type="hidden" name="id" value="${requestScope.user.id}"/>
                用户名：<input type="text" name="username" value="${requestScope.user.username}" onkeyup="registerCheck()">
                <span id="show"></span><br><br>
                密&nbsp&nbsp&nbsp码：<input type="password" name="password" value="${requestScope.user.password}"><br><br>
                性别：<%
                if ("male".equals("male")) {
            %>
                男<input type="radio" value="male" name="gender" checked/>
                女<input type="radio" value="felmale" name="gender"/>
                <%} else {%>
                男<input type="radio" value="male" name="gender"/>
                女<input type="radio" value="felmale" name="gender" checked/>
                <br>
                <%}%>
                <br><br>
                个人简介:<textarea name="resume" cols="6" rows="6">${requestScope.user.resume}</textarea>
                <br><br>
                <input type="submit" value="修改"/>
                <button onclick="history.go(-1)">返回</button>
                <br>
            </form>
        </div>
        <!--main end -->
    </div>
    <div class="blank20"></div>
    <div class="blank10"></div>
</div>
<%@include file="common/bottom.txt" %>
</body>
</html>
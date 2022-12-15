<%--
Created by IntelliJ IDEA.
forum.beans.User: Administrator
Date: 2022-09-29
Time: 11:20
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="checkvaild.jsp" %>
<jsp:useBean id="userDao" class="forum.dao.UserDAO" scope="page"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
    <title>技术论坛</title>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script src="js/fun.js"></script>
</head>
<body>
<jsp:include page="common/top.jsp"/>
<div id="content">
    <div class="aside">
        <jsp:include page="common/left.jsp"/>
    </div>
    <div id="main">
        <div class="input_form">
            <div class="top-bar">
                <h1>用户修改</h1>
                <div class="breadcrumbs"></div>
            </div>
            <br/>
            <form action="user?action=modify" method="post"
                  onsubmit="return RegisterCheckPassword()&&AjaxCheckUsername(${requestScope.user.username})">
                <input type="hidden" name="id" value="${requestScope.user.id}"/>
                <label for="username">账&nbsp&nbsp&nbsp号：</label>
                <input id="username" type="text" name="username" value="${requestScope.user.username}"
                       onkeyup="AjaxCheckUsername()">
                <span id="show"></span><br><br>
                <label for="nickname">昵&nbsp&nbsp&nbsp称：</label>
                <input id="nickname" type="text" name="nickname" value="${requestScope.user.nickname}">
                <br><br>
                <label for="password">密&nbsp&nbsp&nbsp码：</label>
                <input id="password" type="password" name="password" value="${requestScope.user.password}">
                <span id="showPassword" style="color: red"></span>
                <br><br>
                <label for="passwordCheck">确认密码：</label>
                <input type="password" id="passwordCheck" name="password"
                       onkeydown="$('#showCheckPassword').text('')"></input>
                <span id="showCheckPassword" style="color: red"></span>
                <br><br>
                性别：
                <c:choose>
                    <c:when test="${requestScope.user.gender=='male'}">
                        <label>男<input type="radio" value="male" name="gender" checked/></label>
                        <label>女<input type="radio" value="felmale" name="gender"/></label>
                    </c:when>
                    <c:otherwise>
                        <label>男<input type="radio" value="male" name="gender"/></label>
                        <label>女<input type="radio" value="felmale" name="gender" checked/></label>
                    </c:otherwise>
                </c:choose>
                <br><br>
                <label>
                    个人简介:
                    <textarea name="resume" cols="6" rows="6">${requestScope.user.resume}</textarea>
                </label>
                <br><br>
                <input type="submit" value="修改"/>
                <c:if test="${sessionScope.role==99}">
                    <button onclick="window.local.href='user?action=manage'">返回</button>
                </c:if>
                <br>
            </form>
        </div>

    </div>
    <div class="blank20"></div>
</div>

</body>
</html>
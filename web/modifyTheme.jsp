<%@ page import="forum.beans.Theme" %>
<%@ page import="forum.beans.Theme" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-11-03
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="checkvaild.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="postDAO" class="forum.dao.PostDAO" scope="page"/>
<jsp:useBean id="themeDAO" class="forum.dao.ThemeDAO" scope="page"/>
<%
    String themeId = request.getParameter("themeId");
    Theme theme = themeDAO.getThemeById(themeId);
    request.setAttribute("theme", theme);
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE>
<html>
<head>
    <title>技术论坛系统</title>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
</head>
<body>
<jsp:include page="common/top.jsp"/>
<div id="content">
    <div class="aside">
        <jsp:include page="common/left.jsp"/>
    </div>
    <div id="main">
        <!-- main begin -->
        <div class="input_form">
            <div class="top-bar">
                <h1>版块修改</h1>
                <div class="breadcrumbs"></div>
            </div>
            <br/>
            <form action="theme?action=modify" method="post">
                <input type="hidden" name="themeId" value="${theme.id}"/><br>
                版块标题：<input type="text" name="themeTitle" id="title" value="${theme.themeTitle}"><br><br>
                <br>
                <br>
                版块简介:<textarea name="themeIntroduction" cols="25" rows="5">${theme.themeIntroduction}</textarea>
                <br><br>
                <input type="submit" value="修改"/><br>
            </form>
        </div>

    </div>
    <div class="blank20"></div>

</div>

</body>
</html>

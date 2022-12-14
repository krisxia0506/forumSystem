<%@ page import="java.util.ArrayList" %>
<%@ page import="forum.beans.Theme" %>
<%@ page import="forum.beans.Post" %>
<%@ page import="forum.beans.Post" %>
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
    ArrayList<Theme> themeList = themeDAO.getAllTheme();
    String id = request.getParameter("id");
    Post post = postDAO.getById(id);
    request.setAttribute("themeList", themeList);
    request.setAttribute("post", post);
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE>
<html>
<head>
    <title>技术论坛系统</title>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script src="ckeditor/ckeditor.js"></script>
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
                <h1>帖子修改</h1>
                <div class="breadcrumbs"></div>
            </div>
            <br/>
            <form action="post?action=modify" method="post">
                <input type="hidden" name="author" value="${sessionScope.userId}"/>
                <input type="hidden" name="id" value="${post.id}"/><br>
                <input type="hidden" name="postTime" value="${post.postTime}"/><br>
                标题：<input type="text" name="title" id="title" value="${post.title}"><br><br>
                版块：<select name="theme">
                <c:forEach var="theme" items="${themeList}">
                    <c:choose>
                        <c:when test="${theme.id==post.theme}">
                            <option value="${theme.id}" selected="selected">
                                    ${theme.themeTitle}
                            </option>
                        </c:when>
                        <c:otherwise>
                            <option value="${theme.id}">
                                    ${theme.themeTitle}
                            </option>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>
            </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                关键字：
                <input type="text" name="keyword" id="keyword" value="${post.keyword}"/>
                <br>
                <br>
                内容:<textarea name="postContent" cols="25" rows="5">${post.content}</textarea>
                <br><br>
                <input type="submit" value="修改"/><br>
            </form>
        </div>
        <!--main end -->
    </div>
    <div class="blank20"></div>
    <div class="blank10"></div>
</div>
<%@include file="common/bottom.txt" %>
</body>
<script>
    CKEDITOR.replace('postContent');
</script>
</html>

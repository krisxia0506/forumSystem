<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-11-09
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="forum.beans.Theme" %>
<%@ page import="forum.beans.Theme" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="postDAO" class="forum.dao.PostDAO" scope="page"/>
<jsp:useBean id="themeDAO" class="forum.dao.ThemeDAO" scope="page"/>

<%
    ArrayList<Theme> themeList = themeDAO.getAllTheme();
    request.setAttribute("themeList", themeList);
%>

<!DOCTYPE html>
<html>
<head>
    <title>技术论坛系统</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
</head>
<body>
<jsp:include page="common/top.jsp"/>
<div id="content">
    <div class="aside">
        <jsp:include page="common/left.jsp"/>
    </div>
    <div id="main">
        <div class="post_list">
            <h1 style="margin: 0 0 20px">贴子列表</h1>

            <ul>
                <c:forEach items="${requestScope.postList}" var="post">
                    <li>
                        <div class="dd_lmbt">
                            <div class="dd_lm">[
                                <c:forEach var="theme" items="${themeList}">
                                    <c:choose>
                                        <c:when test="${theme.id==post.theme}">
                                            ${theme.themeTitle}
                                        </c:when>

                                    </c:choose>

                                </c:forEach>]
                            </div>
                            <div class="dd_bt">
                                <a href="post?action=displayPost&postId=${post.id}">${post.title}</a>
                            </div>
                        </div>
                        <div class="dd_time">${post.postTime}</div>
                    </li>
                </c:forEach>
            </ul>
            <c:if test="${requestScope.postList.size()==0}">
                <h3 style="text-align: center;margin-top: 100px">无结果</h3>
            </c:if>
            <div class="page">
                <div>
                    <c:if test="${requestScope.pageCount>1}">
                        <c:choose>
                            <c:when test="${requestScope.pageNo==1}"><%--首页显示--%>
                                <a href="post?action=displayPostList&themeId=${requestScope.themeId}&pageNo=${requestScope.pageNo+1}">下一页</a>
                                ${requestScope.pageNo} / ${requestScope.pageCount}
                                <a href="post?action=displayPostList&themeId=${requestScope.themeId}&pageNo=${requestScope.pageCount}">尾页</a>
                            </c:when>
                            <c:when test="${requestScope.pageNo==requestScope.pageCount}"><%--尾页显示--%>
                                <a href="post?action=displayPostList&themeId=${requestScope.themeId}&pageNo=1">首页</a>
                                ${requestScope.pageNo} / ${requestScope.pageCount}
                                <a href="post?action=displayPostList&themeId=${requestScope.themeId}&pageNo=${requestScope.pageNo-1}">上一页</a>
                            </c:when>
                            <c:otherwise>
                                <a href="post?action=displayPostList&themeId=${requestScope.themeId}&pageNo=1">首页</a>
                                <a href="post?action=displayPostList&themeId=${requestScope.themeId}&pageNo=${requestScope.pageNo-1}">上一页</a>
                                ${requestScope.pageNo} / ${requestScope.pageCount}
                                <a href="post?action=displayPostList&themeId=${requestScope.themeId}&pageNo=${requestScope.pageNo+1}">下一页</a>
                                <a href="post?action=displayPostList&themeId=${requestScope.themeId}&pageNo=${requestScope.pageCount}">尾页</a>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </div>
            </div>
        </div>


    </div>

    <div class="blank20"></div>
</div>


</body>
</html>


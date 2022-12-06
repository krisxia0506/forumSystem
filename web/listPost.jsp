<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-11-09
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="forum.beans.PostType" %>
<%@ page import="forum.beans.PostType" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="postDAO" class="forum.dao.PostDAO" scope="page"/>
<jsp:useBean id="newstypeDAO" class="forum.dao.PostTypeDAO" scope="page"/>

<%
    ArrayList<PostType> postTypeList = newstypeDAO.getAllPostType();
    request.setAttribute("postTypeList", postTypeList);
%>

<!DOCTYPE html>
<html>
<head>
    <title>新闻发布系统V2</title>
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
        <div class="news_list">
            <h1 style="margin: 0 0 20px">贴子列表</h1>

            <ul>
                <c:forEach items="${requestScope.postList}" var="post">
                    <li>
                        <div class="dd_lm">[
                            <c:forEach var="postType" items="${postTypeList}">
                                <c:choose>
                                    <c:when test="${postType.id==post.postType}">
                                        ${postType.postType}
                                    </c:when>

                                </c:choose>

                            </c:forEach>]
                        </div>
                        <div class="dd_bt">
                            <a href="post?action=displayPost&postId=${post.id}">${post.title}</a>
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
                    <c:if test="${pageCount>1}">
                        <c:choose>
                            <c:when test="${pageNo==1}"><%--首页显示--%>
                                <a href="post?action=displayPostList&postTypeId=${postTypeId}&pageNo=${pageNo+1}" >下一页</a>
                                ${pageNo} / ${pageCount}
                                <a href="post?action=displayPostList&postTypeId=${postTypeId}&pageNo=${pageCount}">尾页</a>
                            </c:when>
                            <c:when test="${pageNo==pageCount}"><%--尾页显示--%>
                                <a href="post?action=displayPostList&postTypeId=${postTypeId}&pageNo=1" >首页</a>
                                ${pageNo} / ${pageCount}
                                <a href="post?action=displayPostList&postTypeId=${postTypeId}&pageNo=${pageNo-1}" >上一页</a>
                            </c:when>
                            <c:otherwise>
                                <a href="post?action=displayPostList&postTypeId=${postTypeId}&pageNo=1" >首页</a>
                                <a href="post?action=displayPostList&postTypeId=${postTypeId}&pageNo=${pageNo-1}" >上一页</a>
                                ${pageNo} / ${pageCount}
                                <a href="post?action=displayPostList&postTypeId=${postTypeId}&pageNo=${pageNo+1}" >下一页</a>
                                <a href="post?action=displayPostList&postTypeId=${postTypeId}&pageNo=${pageCount}">尾页</a>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </div>
            </div>
        </div>


    </div>
    <div class="blank10"></div>
    <div class="blank20"></div>
</div>

<%@ include file="common/bottom.txt" %>
</body>
</html>


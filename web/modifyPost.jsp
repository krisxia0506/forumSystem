<%@ page import="java.util.ArrayList" %>
<%@ page import="forum.beans.PostType" %>
<%@ page import="forum.beans.Post" %>
<%@ page import="forum.beans.Post" %>
<%@ page import="forum.beans.PostType" %><%--
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
<jsp:useBean id="newstypeDAO" class="forum.dao.PostTypeDAO" scope="page"/>
<%
    ArrayList<PostType> postTypeList = newstypeDAO.getAllPostType();
    String id = request.getParameter("id");
    Post post = postDAO.getById(id);
    request.setAttribute("postTypeList", postTypeList);
    request.setAttribute("post", post);
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE>
<html>
<head>
    <title>新闻发布系统V2</title>
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
        <div class="inputform">
            <div class="top-bar">
                <h1>新闻修改</h1>
                <div class="breadcrumbs"></div>
            </div>
            <br/>
            <form action="post?action=modi" method="post">
                <input type="hidden" name="author" value="${sessionScope.username}"/>
                <input type="hidden" name="id" value="${post.id}"/><br>
                <input type="hidden" name="pubtime" value="${post.postTime}"/><br>
                标题：<input type="text" name="title" id="title" value="${post.title}"><br><br>
                类别：<select name="postType">
                <c:forEach var="postType" items="${postTypeList}">
                    <c:choose>
                        <c:when test="${postType.id==post.postType}">
                            <option value="${postType.id}" selected="selected">
                                    ${postType.postType}
                            </option>
                        </c:when>
                        <c:otherwise>
                            <option value="${postType.id}">
                                    ${postType.postType}
                            </option>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>
            </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                关键字：
                <input type="text" name="keyword" id="keyword" value="${post.keyword}"/>
                <br>
                <br>
                内容:<textarea name="content" cols="25" rows="5">${post.content}</textarea>
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
</html>

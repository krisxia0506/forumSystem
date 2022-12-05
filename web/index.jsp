<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="forum.beans.PostType" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="postDAO" class="forum.dao.PostDAO" scope="page"/>
<jsp:useBean id="newstypeDAO" class="forum.dao.PostTypeDAO" scope="page"/>

<%
    ArrayList<PostType> postTypeList = newstypeDAO.getAllNewstype();
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
            <h1 style="margin: 0 0 20px">模块列表</h1>

            <ul>
                <c:forEach items="${requestScope.postTypeList}" var="postType">
                    <li>
                        <div class="dd_bt">
                            <a href="post?action=displayPostList&postTypeId=${postType.postTypeId}">${postType.postType}</a>
                        </div>
                    </li>
                </c:forEach>
            </ul>
            <div class="page">
                <div>
                    <c:if test="${pageCount>0}">
                        <c:choose>
                            <c:when test="${pageNo==1}"><%--首页显示--%>
                                <a href="post?pageNo=${pageNo+1}" >下一页</a>
                                ${pageNo} / ${pageCount}
                                <a href="post?pageNo=${pageCount}">尾页</a>
                            </c:when>
                            <c:when test="${pageNo==pageCount}"><%--尾页显示--%>
                                <a href="post?pageNo=1" >首页</a>
                                ${pageNo} / ${pageCount}
                                <a href="post?pageNo=${pageNo-1}" >上一页</a>
                            </c:when>
                            <c:otherwise>
                                <a href="post?pageNo=1" >首页</a>
                                <a href="post?pageNo=${pageNo-1}" >上一页</a>
                                ${pageNo} / ${pageCount}
                                <a href="post?pageNo=${pageNo+1}" >下一页</a>
                                <a href="post?pageNo=${pageCount}">尾页</a>
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


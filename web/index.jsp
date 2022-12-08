<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>
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
        <div class="type_list">
            <h1 style="margin: 0 0 20px">版块中心</h1>

            <ul class="ul1">
                <c:forEach items="${requestScope.postTypeList}" var="postType">
                    <li class="li1">
                        <div>
                            <a class="div403" href="post?action=displayPostList&postTypeId=${postType.id}">
                                <div class="div404">
                                    <div class="typeTitle">${postType.postType}</div>
                                    <div class="typeIntroduction">${postType.typeIntroduction}</div>
                                </div>

                            </a>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div class="blank20"></div>
</div>
<%@ include file="common/bottom.txt" %>
</body>
</html>


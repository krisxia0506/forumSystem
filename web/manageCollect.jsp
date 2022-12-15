<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-11-21
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="checkvaild.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>技术论坛系统</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <script src="js/fun.js"></script>
</head>
<body>
<jsp:include page="common/top.jsp"/>
<div id="content">
    <div class="aside">
        <jsp:include page="common/left.jsp"/>
    </div>
    <div id="main">
        <div class="post_list manage_list">
            <div class="top-bar-manage">
                <h1>
                    我的收藏
                </h1>
                <div class="breadcrumbs"></div>
            </div>
            <br>
            <div class="table">
                <table class="listing" cellpadding="0" cellspacing="0" width="600" border="1">
                    <tbody>
                    <tr>
                        <th class="first" width="20px">序号</th>
                        <th style="width: 200px">帖子标题</th>
                        <th>收藏时间</th>
                        <th style="width: 150px;">取消收藏</th>
                    </tr>
                    <c:forEach items="${requestScope.collectionList}" var="collection" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td width="250px">
                                <a href="post?action=displayPost&postId=${collection.postId}">
                                        ${collection.title}
                                </a>
                            </td>
                            <td>${collection.date}</td>
                            <td width="10px">
                                <a href="collect?action=delete&postId=${collection.postId}"
                                   onclick="return confirm('确定取消收藏？')">
                                    <i class="iconfont">&#xe74b;</i>
                                </a>
                            </td>
                        </tr>

                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>
    </div>

    <div class="blank20"></div>
</div>


</body>
</html>


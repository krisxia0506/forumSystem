<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-12-8
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
                    回帖管理
                </h1>
                <div class="breadcrumbs"></div>
            </div>
            <br>
            <div class="table">
                <table class="listing" cellpadding="0" cellspacing="0" width="600" border="1">
                    <tbody>
                    <tr>
                        <th class="first" width="200px">帖子标题</th>
                        <th style="width: 200px">回帖内容</th>
                        <c:if test="${sessionScope.role==99}">
                            <th style="width: 200px">回帖人</th>
                        </c:if>

                        <th style="width: 150px;">回帖时间</th>
                        <th style="width: 100px;">删除</th>
                    </tr>
                    <c:forEach items="${requestScope.replyList}" var="reply">
                        <tr>
                            <td width="250px">
                                <a href="post?action=displayPost&postId=${reply.postId}">
                                        ${reply.post.title}
                                </a>
                            </td>
                            <td style="text-align: left;width: 100px;">
                                <a href="post?action=displayPost&postId=${reply.postId}">
                                        ${reply.content}
                                </a>
                            </td>
                            <c:if test="${sessionScope.role==99}">
                                <td>${reply.author}</td>
                            </c:if>
                            <td>${reply.replyTime}</td>
                            <td width="10px">
                                <a href="reply?action=delete&replyId=${reply.id}"
                                   onclick="return confirm('确定删除？')">
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


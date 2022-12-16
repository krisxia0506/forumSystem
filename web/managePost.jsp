<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-10-27
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="postDAO" class="forum.dao.PostDAO" scope="page"/>
<!DOCTYPE>
<html>
<head>
    <title>技术论坛系统</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <script src="js/fun.js"></script>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
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
                <c:choose>
                    <c:when test="${sessionScope.role==99}">
                        <h1>帖子管理</h1>
                    </c:when>
                    <c:otherwise>
                        <h1>我的帖子</h1>
                    </c:otherwise>
                </c:choose>

                <button style="margin-left: 450px;" value="发布帖子" onclick="window.location.href='addPost.jsp';">
                    发布帖子
                </button>
            </div>
            <c:if test="${!empty postList}">
                <div class="table">
                    <table id="listing" class="listing" cellspacing="0" cellpadding="0">
                        <tr>
                            <th class="first" width="40">序号</th>
                            <th>帖子标题</th>
                            <c:if test="${sessionScope.role==99}">
                                <th>发帖账号</th>
                            </c:if>
                            <th>发布时间</th>
                            <th style="width: 40px;">修改</th>
                            <th style="width: 40px;">删除</th>
                        </tr>
                        <c:forEach var="post" items="${requestScope.postList}" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td class="title">
                                    <a href="post?action=displayPost&postId=${post.id}">
                                            ${post.title}
                                    </a>
                                </td>
                                <c:if test="${sessionScope.role==99}">
                                    <td>${post.author}</td>
                                </c:if>
                                <td class="ntime">${post.postTime}</td>
                                <td>
                                    <button value="修改" onclick="window.location.href='modifyPost.jsp?id=${post.id}';">修改
                                    </button>

                                </td>
                                <td>
                                    <button value="删除" onclick="deletePost(${post.id});">删除</button>

                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:if>
            <c:if test="${empty postList}">
                <div class="no-data">
                    <p>还没有帖子哦，发个帖子吧</p>
                </div>
                <script>
                    document.getElementById("listing").style.display = "none";
                </script>
            </c:if>
        </div>
    </div>

    <div class="blank20"></div>
</div>
</body>
</html>
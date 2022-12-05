<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script src="js/fun.js"></script>
<link rel="shortcut icon" href="favicon.ico" >
<div id="logo">
    <div id="logo_main">
        <span id="myspan"></span>
    </div>
</div>
<div id="menu">
    <div id="user">
        <c:choose>
            <c:when test="${empty sessionScope.username}">
                <a href="userLogin.jsp">用户登录</a>
            </c:when>
            <c:otherwise>
                当前用户：<c:out value="${sessionScope.username}">
            </c:out>
                |<a href="user?action=logout">退出登录</a>
            </c:otherwise>
        </c:choose>
    </div>
    <div id="menu_list">
        <ul>
            <c:if test="${!empty sessionScope.username}">
                <c:choose>
                    <c:when test="${sessionScope.username=='admin'}">
                        <li><a href="post?action=manage">新闻管理</a></li>
                        |
                        <li><a href="reply?action=manage&username=${sessionScope.username}">评论管理</a></li>
                        |
                        <li><a href="user?action=manage">用户管理</a></li>
                        |
                    </c:when>
                    <c:otherwise>
                        <li><a href="index.jsp">查看新闻</a></li>
                        |
                        <li><a href="reply?action=usermanage&username=${sessionScope.username}">评论管理</a></li>
                        |
                        <li><a href="user?action=modifyView&username=${sessionScope.username}">修改用户</a></li>
                        |
                    </c:otherwise>
                </c:choose>
            </c:if>
            <li><a href="index.jsp"> 首 页</a></li>
        </ul>
    </div>
</div>
<script>
    showTime();
</script>
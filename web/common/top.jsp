<%@ page import="forum.dao.UserDAO" %>
<%@ page import="forum.beans.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script src="js/fun.js"></script>
<link rel="shortcut icon" href="favicon.ico">
<%
    //自动登陆
    String uname = (String) session.getAttribute("username");
    if (uname == null) {
        Cookie[] cookies = request.getCookies();
        String autologin = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("autologin".equals(cookie.getName())) {
                    autologin = cookie.getValue();
                    break;
                }
            }
        }
        if (autologin != null) {
            String[] parts = autologin.split("-");
            String name = parts[0];
            String pwd = parts[1];
            System.out.println(name);
            UserDAO userDAO = new UserDAO();
            User user = userDAO.queryByNamePwd(name, pwd);
            if (user.getId() != null) {
                session.setAttribute("username", name);
                session.setAttribute("role", user.getRole().toString());
                session.setAttribute("userId", user.getId().toString());
            } else {
                response.sendRedirect("userLogin.jsp ");
            }
        }
    }
%>
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
                    <c:when test="${sessionScope.role=='99'}">
                        <li><a href="theme?action=manage">版块管理</a></li>
                        |
                        <li><a href="post?action=manage">帖子管理</a></li>
                        |
                        <li><a href="reply?action=manage">回帖管理</a></li>
                        |
                        <li><a href="user?action=manage">用户管理</a></li>
                        |
                    </c:when>
                    <c:otherwise>
                        <li><a href="post?action=manage">我的帖子</a></li>
                        |
                        <li><a href="reply?action=manage">我的回帖</a></li>
                        |
                        <li><a href="collect?action=manage">我的收藏</a></li>
                        |
                        <li><a href="user?action=modifyView">修改用户</a></li>
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
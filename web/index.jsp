<%@ page import="forum.dao.UserDAO" %>
<%@ page import="forum.beans.User" %>
<%@ page import="forum.beans.Theme" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="forum.dao.ThemeDAO" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    //自动登陆
    /*String uname = (String) session.getAttribute("username");
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
            UserDAO userDAO = new UserDAO();
            User user = userDAO.queryByNamePwd(name, pwd);
            if (user.getId() != null) {
                session.setAttribute("nickname", user.getNickname());
                session.setAttribute("role", user.getRole().toString());
                session.setAttribute("userId", user.getId().toString());
                session.setAttribute("level", user.getLevel());
            } else {
                response.sendRedirect("userLogin.jsp ");
            }
        }
    }*/
    //获取所有帖子类型
    ThemeDAO themeDAO = new ThemeDAO();
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
        <div class="type_list">
            <h1 style="margin: 0 0 20px">版块中心</h1>

            <ul class="ul1">
                <c:forEach items="${requestScope.themeList}" var="theme">
                    <li class="li1">
                        <div>
                            <a class="div403" href="post?action=displayPostList&themeId=${theme.id}">
                                <div class="div404">
                                    <div class="typeTitle">${theme.themeTitle}</div>
                                    <div class="typeIntroduction">${theme.themeIntroduction}</div>
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


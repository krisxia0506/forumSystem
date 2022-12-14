<%@page language="java" contentType="text/html;charset=UTF-8" %>
<%@page import="java.sql.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="checkvaild.jsp" %>

<!DOCTYPE>
<html>
<head>
    <title>技术论坛系统</title>
    <script src="js/fun.js"></script>
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
        <div class="post_list manage_list">
            <div class="top-bar-manage">
                <h1>用户管理</h1>
            </div>
            <br>
            <div class="table">
                <table class="listing" cellspacing="0" cellpadding="0">
                    <tr>
                        <th class="first" width="40">序号</th>
                        <th>用户账号</th>
                        <th>删除</th>
                    </tr>
                    <c:forEach items="${requestScope.usersList}" var="user" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td><a href="user?action=modifyView&userId=${user.id}">
                                    ${user.username}
                            </a>
                            </td>
                            <td>
                                <a href="user?action=delete&userId=${user.id}" onclick="return confirm('确定删除么')">
                                    <i class="iconfont">&#xe74b;</i>
                                </a>
                            </td>
                        </tr>

                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
    <div class="blank20"></div>
</div>


</body>
</html>
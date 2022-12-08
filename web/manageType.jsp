<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-10-27
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="forum.beans.Post" %>
<%@include file="checkvaild.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="postDAO" class="forum.dao.PostDAO" scope="page"/>
<!DOCTYPE>
<html>
<head>
    <title>新闻发布系统V2</title>
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
                <h1>版块管理</h1>
                <button style="margin-left: 450px;" value="发布新闻" onclick="window.location.href='addType.jsp';">
                    新增版块
                </button>
            </div>
            <div class="table">
                <table class="listing" cellspacing="0" cellpadding="0">
                    <tr>
                        <th class="first" width="40">序号</th>
                        <th>版块标题</th>
                        <th>版块简介</th>
                        <th style="width: 40px;">修改</th>
                        <th style="width: 40px;">删除</th>
                    </tr>
                    <c:forEach var="postType" items="${requestScope.postTypeList}" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td class="title">
                                <a href="post?action=displayPostList&postTypeId=${postType.id}">
                                        ${postType.postType}
                                </a>
                            </td>
                            <td class="ntime">${postType.typeIntroduction}</td>
                            <td>
                                <button value="修改"
                                        onclick="window.location.href='modifyType.jsp?postTypeId='+${postType.id};">修改
                                </button>

                            </td>
                            <td>
                                <button value="删除" onclick="deleteType(${postType.id});">删除</button>

                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
    <div class="blank10"></div>
    <div class="blank20"></div>
</div>

<%@include file="common/bottom.txt" %>
</body>

</html>
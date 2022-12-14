<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-10-27
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="forum.beans.Theme" %>
<%@ page import="forum.beans.Theme" %>
<jsp:useBean id="postDAO" class="forum.dao.PostDAO" scope="page"/>
<jsp:useBean id="themeDAO" class="forum.dao.ThemeDAO" scope="page"/>
<jsp:include page="checkvaild.jsp"/>
<%
    request.setCharacterEncoding("UTF-8");
    ArrayList<Theme> themeList = themeDAO.getAllTheme();
%>
<!DOCTYPE>
<html>
<head>
    <title>技术论坛系统</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <script src="ckeditor/ckeditor.js"></script>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8"/>
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
                <h1>发新贴</h1>
            </div>
            <br>
            <form action="post?action=add" method="post" onsubmit="return CheckAddNews()">
                <input type="hidden" name="author" value="${sessionScope.userId}"></input><br>
                标题: <input type="text" name="title" id="title">
                <br><br>
                版块:
                <select name="theme" id="">
                    <%
                        for (Theme theme : themeList) {
                    %>
                    <option value="<%=theme.getId()%>">
                        <%=theme.getThemeTitle()%>
                    </option>
                    <%}%>
                </select>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                关键字： <input type="text" name="keyword" id="keyword"><br><br>
                内容：<textarea name="postContent" cols="25" rows="5"
                               id="contentcontent"></textarea><br><br>
                <input type="submit" value="发布"></input>
            </form>
        </div>
    </div>
    <div class="blank10"></div>
    <div class="blank20"></div>
</div>
<%@include file="common/bottom.txt" %>
</body>
<script>
    function CheckAddNews() {
        var content = CKEDITOR.instances.contentcontent.getData();
        var title = $("#title").val()
        if (title === "" || content === "") {
            alert('标题和内容不能为空');
            return false;
        }
        return true;
    }

    CKEDITOR.replace('postContent');
</script>
</html>

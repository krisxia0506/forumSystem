<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE>
<html>
<head>
    <title>技术论坛系统</title>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script src="js/fun.js"></script>
</head>
<body>
<jsp:include page="common/top.jsp"/>
<div id="content">
    <div class="aside">
        <jsp:include page="common/left.jsp"/>
    </div>
    <div id="main">
        <!-- main begin -->
        <div class="inputform">
            <form action="user?action=login" method="post">
                <label>
                    账&nbsp;&nbsp;&nbsp;号：
                    <input type="text" name="username"></input>
                </label><br><br>
                <label>
                    密&nbsp;&nbsp;&nbsp;码：
                    <input type="password" name="password"></input>
                </label><br><br>
                自动登陆：
                <input type="radio" name="nopwd" value="<%=0%>" checked>不启用
                <input type="radio" name="nopwd" value="<%=60*60*24*7%>">一周
                <input type="radio" name="nopwd" value="<%=60*60*24*15%>">半个月
                <input type="radio" name="nopwd" value="<%=60*60*24*31%>">一个月
                <br><br>
                <input type="submit" value="登录"></input>
                <button><a href="userRegister.jsp">用户注册</a></button>

                <br>
            </form>
            <br>
            <div id="loginError" style="color: red;font-size: 20px">${requestScope.msg}</div>
        </div>
        <!--main end -->
    </div>
    <div class="blank20"></div>
    <div class="blank10"></div>
</div>
<%@include file="common/bottom.txt" %>

</body>
</html>


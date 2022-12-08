<%@ page import="java.util.Objects" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE>
<html>
<head>
    <title>新闻发布系统V2</title>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script src="js/fun.js"></script>
    <script type="text/javascript">
        let t = 5;
        setInterval("refer()", 1000);

        function refer() {
            if (t == 0) {
                location.href = "userLogin.jsp";
            }
            document.getElementById('show').innerHTML = "" + t + "秒后跳转到登陆页面";
            t--;
        }
    </script>
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
            <div class="top-bar">
                <h1>注册成功</h1>
                <div class="breadcrumbs"></div>
            </div>
            <br/>
            <div class="registerSuccess"><span id="show">5秒后跳转到登陆页面</span>
                <br>
                <a href="userLogin.jsp">返回登陆</a>
            </div>

        </div>
        <!--main end -->
    </div>
    <div class="blank20"></div>
    <div class="blank10"></div>
</div>
<%@include file="common/bottom.txt" %>
</body>
<style>
    .registerSuccess {
        font-size: 20px;
    }

    .registerSuccess a {
        border: black 1px solid;
        text-decoration: black;
    }
</style>
</html>
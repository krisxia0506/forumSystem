<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE>
<html>
<head>
    <title>新闻发布系统V2</title>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
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
            <div class="top-bar">
                <h1>用户注册</h1>
                <div class="breadcrumbs"></div>
            </div>
            <br/>

            <form action="userVeriRegister.jsp" method="post" onsubmit="return Check()">
                用户名：<input type="text" id="username" name="username" onkeyup="registerCheck()"></input>
                <span id="show"></span><br/><br/>
                密&nbsp;&nbsp;&nbsp;码：<input type="password" id="password" name="password"></input><br/><br/>
                性&nbsp;&nbsp;&nbsp;别： 男<input type="radio" value="male" name="gender" checked>
                女<input type="radio" value="female" name="gender"><br>
                个人简介<textarea name="resume" cols="12" rows="6"></textarea><br/><br/>
                <input type="submit" value="提交">
                <button onclick="history.go(-1)">返回</button>
                <div id="loginError" style="display: none;color: red;font-size: 20px">注册失败</div>
                <br/>
            </form>
        </div>
        <!--main end -->
    </div>
    <div class="blank20"></div>
    <div class="blank10"></div>
</div>
<%@include file="common/bottom.txt" %>
</body>
<script>
    var errori = '<%=request.getParameter("error")%>';
    if (errori === '1') {
        var loginError = document.getElementById("loginError");
        loginError.style.display = "block";
    }
    function Check()
    {
        var username= document.getElementById("username").value
        var password =document.getElementById("password").value
        if ( username.length<5||username.length>10)
        {
            alert("用户名长度应为5-10位");
            return false;
        }
        if ( password.length<5||password.length>10)
        {
            alert("密码长度应为5-10位");
            return false;
        }

        return true;
    }
</script>
</html>
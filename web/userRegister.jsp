<%@ page language="java" contentType="text/html;charset=UTF-8" %>
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
            <div class="top-bar">
                <h1>用户注册</h1>
                <div class="breadcrumbs"></div>
            </div>
            <br/>

            <form action="user?action=register" method="post"
                  onsubmit="return RegisterCheckPassword()&&AjaxCheckUsername()">
                昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：<input type="text" name="nickname" id="nickname"
                                                                placeholder="请输入昵称" required/><br/><br>
                用&nbsp;户&nbsp;名：<input type="text" id="username" name="username"
                                          onkeyup="AjaxCheckUsername()"></input>
                <span id="show"></span><br/><br/>
                密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" id="password" name="password"></input>
                <span id="showPassword" style="color: red"></span><br/><br/>
                确认密码：<input type="password" id="passwordCheck" name="password"
                                onkeydown="document.getElementById('showCheckPassword').innerHTML=''"></input>
                <span id="showCheckPassword" style="color: red"></span><br/><br/><br/><br/>
                性&nbsp;&nbsp;&nbsp;别： 男<input type="radio" value="male" name="gender" checked>
                女<input type="radio" value="female" name="gender"><br>
                个人简介<textarea name="resume" cols="12" rows="6"></textarea><br/><br/>
                <input type="submit" value="提交">
                <button onclick="history.go(-1)">返回</button>
                <div id="loginError" style="color: red;font-size: 20px">${requestScope.msg}</div>
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
</html>
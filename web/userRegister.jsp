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
        <div class="input_form">
            <div class="top-bar">
                <h1>用户注册</h1>
                <div class="breadcrumbs"></div>
            </div>
            <br/>

            <form action="user?action=register" method="post"
                  onsubmit="return RegisterCheckPassword()&&AjaxCheckUsername()">
                <label for="nickname">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</label>
                <input type="text" name="nickname" id="nickname"
                       placeholder="请输入昵称" required/><br/><br>
                <label for="username">用&nbsp;户&nbsp;名：</label>
                <input type="text" id="username" name="username" onkeyup="AjaxCheckUsername()"></input>
                <span id="show"></span><br/><br/>
                <label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
                <input type="password" id="password" name="password"></input>
                <span id="showPassword" style="color: red"></span><br/><br/>
                <label for="passwordCheck">确认密码：</label>
                <input type="password" id="passwordCheck" name="password"
                       onkeydown="$('#showCheckPassword').text('')"></input>
                <span id="showCheckPassword" style="color: red"></span><br/><br/><br/><br/>
                <label>
                    性&nbsp;&nbsp;&nbsp;别： 男
                    <input type="radio" value="male" name="gender" checked>
                </label>
                <label>
                    女
                    <input type="radio" value="female" name="gender">
                </label><br>
                <label for="resume">个人简介</label>
                <textarea id="resume" name="resume" cols="12" rows="6"></textarea>
                <br/><br/>
                <input type="submit" value="提交">
                <button onclick="history.go(-1)">返回</button>
                <div id="loginError" style="color: red;font-size: 20px">${requestScope.msg}</div>
                <br/>
            </form>
        </div>
    </div>
    <div class="blank20"></div>
</div>

</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-12-06
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE>
<html>
<head>
    <title>技术论坛系统</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
  <meta http-equiv="content-type" content="text/html" charset="UTF-8"/>
</head>
<body>
<jsp:include page="common/top.jsp"/>
<div id="content">
  <div class="aside">
    <jsp:include page="common/left.jsp"/>
  </div>
    <div id="main">
        <div class="input_form post_list manage_list">
            <div class="top-bar-manage">
                <h1>新增版块</h1>
            </div>
            <br>
            <form action="theme?action=add" method="post" onsubmit="return CheckAdd()">
                <br><br>
                版块标题:<input type="text" name="themeTitle" id="title" value=""><br><br>
                版块简介:<textarea id="typeIntroduction" name="typeIntroduction" cols="25" rows="5"></textarea>
                <br><br>
                <input type="submit" value="新增"/><br>
            </form>
        </div>
    </div>

    <div class="blank20"></div>
</div>

</body>
<script>
    function CheckAdd() {
        if (document.getElementById("title").value === "" || document.getElementById("typeIntroduction").value === "") {
            alert('标题和内容不能为空');
            return false;
        }
        return true;
    }
</script>
</html>

<%@ page import="java.util.Objects" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE>
<html>
<head>
    <title>新闻发布系统V2</title>
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
                <h1>注册确认</h1>
                <div class="breadcrumbs"></div>
            </div>
            <br/>
            <%
                request.setCharacterEncoding("utf-8");
                String uName = request.getParameter("username");
                String uPwd = request.getParameter("password");
                String uGender = request.getParameter("gender");
                if (Objects.equals(uGender, "male")) {
                    uGender = "男";
                } else {
                    uGender = "女";
                }
                String uResume = request.getParameter("resume");
            %>

            <table>
                <tr>
                    <td>用户名：</td>
                    <td><%=uName%>
                    </td>
                </tr>
                <tr>
                    <td>密码：</td>
                    <td><%=uPwd%>
                    </td>
                </tr>
                <tr>
                    <td>性别：</td>
                    <td><%=uGender%>
                    </td>
                </tr>
                <tr>
                    <td>个人简介:</td>
                    <td><%=uResume%>
                    </td>
                </tr>
            </table>

            <form action="user?action=register" method="post">
                <div style="display: none">
                    用户名：<input type="text" name="username"
                                  value="<%=request.getParameter("username")%>"></input>
                    密&nbsp&nbsp码：<input type="text" name="password"
                                          value="<%=request.getParameter("password")%>"></input>
                    性&nbsp&nbsp别：<input type="text" name="gender"
                                          value="<%=request.getParameter("gender")%>"></input>
                    个人简介<textarea name="resume" cols="20" rows="6"><%=request.getParameter("resume")%></textarea>
                </div>

                <input class="btn" type="submit" value="注册"></input>
                <button><a href="javascript:history.go(-1)"
                           style="text-decoration: underline;">返回</a></button>
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
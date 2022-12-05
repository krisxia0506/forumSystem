<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-11-21
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="checkvaild.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>新闻发布系统V2</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <script src="js/fun.js"></script>
</head>
<body>
<jsp:include page="common/top.jsp"/>
<div id="content">
    <div class="aside">
        <jsp:include page="common/left.jsp"/>
    </div>
    <div id="main">
        <div class="comment_list">
            <div class="top-bar">
                <h1 style="text-align: left">
                    <a href="post?action=disp&id=${requestScope.post.id}">
                        ${requestScope.post.title}
                    </a>
                </h1>
            </div>

            <span class="jjs">
        网友评论仅供网友表达个人看法，并不表名本网站同意其观点或证实其描述。
      </span>

            <div class="fbpl">
                <span class="fd">发表评论</span>
                <span class="fdr">
          <i class="iconfont">&#xe654;</i>
          <button id="btnSubmitBottom" onclick="if (CheckComment()){form_pl.submit()}">发表</button>
        </span><br><br>
                <form action="reply?action=add" id="form_pl" method="post" >
                    <input type="hidden" name="newsid" value="${requestScope.post.id}">
                    <input type="hidden" name="commentauthor" value="${sessionScope.username}">
                    <textarea name="reply" id="commentMessage"></textarea>
                </form>
                <div class="blank10"></div>
            </div>

        </div>
        <div class="yc"></div>
        <div class="mian">
            <c:forEach items="${requestScope.replyList}" var="reply">
                <div class="nr">
                    <i class="iconfont">&#xe8c8;</i>
                    <span>评论人：[${reply.commentauthor}]</span>
                    &nbsp;&nbsp;${reply.commenttime}&nbsp;&nbsp;
                    <div class="clear"></div>
                    <div class="blank20"></div>
                    <div class="comment_p">${reply.reply}</div>
                </div>
            </c:forEach>
        </div>

    </div>
    <div class="blank10"></div>
    <div class="blank20"></div>
</div>

<%@ include file="common/bottom.txt" %>
</body>
<script>
    function CheckComment()
    {
        if ( document.getElementById("commentMessage").value.length===0)
        {
            alert('说点什么吧');
            return false;
        }
        return true;
    }
</script>
</html>


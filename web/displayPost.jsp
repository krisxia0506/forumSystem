<%@ page import="forum.beans.Post" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-10-27
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:useBean id="postDAO" class="forum.dao.PostDAO" scope="page"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
    <title>新闻发布系统V2</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
</head>
<body>
<jsp:include page="common/top.jsp"/>
<div id="content">
    <div class="aside">
        <jsp:include page="common/left.jsp"/>
    </div>
    <div id="main">
        <div class="post_list">
            <div class="top-bar">
                <h1 style="text-align: left;">${requestScope.post.title}
                </h1>
            </div>
            <div class="post_time">
                <span class="left-t">${requestScope.post.postTime}</span>

                <span class="left-t"><i class="iconfont">&#xe8c8;</i>${requestScope.post.author}</span>
                <span class="right-t">
                    <c:choose>
                        <c:when test="${requestScope.isCollected}">
                            <button onclick="window.location.href = 'collect?action=delete&postId=${requestScope.post.id}'">已收藏</button>
                        </c:when>
                        <c:otherwise>
                            <button onclick="window.location.href = 'collect?action=add&postId=${requestScope.post.id}'">收藏</button>
                        </c:otherwise>
                    </c:choose>
                    （单击：${requestScope.post.hits})
                </span>
                <div class="clear"></div>
            </div>
            <%--正文--%>
            <div class="left_zw" style="position: relative">
                ${requestScope.post.content}
            </div>
            <%--回帖--%>

            <div class="reply">
                    <span class="jjs">
        网友回帖仅供网友表达个人看法，并不表名本网站同意其观点或证实其描述。
      </span>

                <div class="fbpl">

                    <span class="fd"><i class="iconfont">&#xe663;</i>回帖</span>
                    <span class="fdr">
          <i class="iconfont">&#xe654;</i>
          <button id="btnSubmitBottom" onclick="if (CheckReply()){form_pl.submit()}">回帖</button>
        </span><br><br>
                    <form action="reply?action=add" id="form_pl" method="post">
                        <input type="hidden" name="postId" value="${requestScope.post.id}">
                        <input type="hidden" name="replyAuthor" value="${sessionScope.username}">
                        <textarea name="replyContent" id="replyContent"></textarea>
                    </form>
                    <div class="blank10"></div>
                </div>

            </div>
            <div class="mian">
                <c:forEach items="${requestScope.replyList}" var="reply">
                    <div class="nr">
                        <i class="iconfont">&#xe8c8;</i>
                        <span>回帖人：[${reply.author}]</span>
                        &nbsp;&nbsp;${reply.replyTime}&nbsp;&nbsp;
                        <div class="clear"></div>
                        <div class="blank20"></div>
                        <div class="comment_p">${reply.content}</div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <%--相关新闻--%>
        <c:if test="${requestScope.relatePost.size() > 0}">
            <div class="div624 border-top-darshd">
                <h4 class="padding-left20" style="margin-top: 0px;color: rgb(30,80,162);">
                    相关新闻：
                </h4>
                <ul class="padding-left20" style="margin-top: -20px">
                    <c:forEach items="${requestScope.relatePost}" var="rPost">
                        <li>
                            <a href="post?action=displayPost&postId=${rPost.id}">${rPost.title}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
    </div>


</div>

</div>
<div class="blank10"></div>
<div class="blank20"></div>
</div>

<%@include file="common/bottom.txt" %>
</body>
</html>

<%@ page import="java.util.ArrayList" %>
<%@ page import="forum.beans.*" %>
<%@ page import="forum.dao.ReplyDAOImpl" %>
<%@ page import="forum.dao.ReplyDAO" %>
<%@ page import="forum.dao.UserDAO" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="postDAO" class="forum.dao.PostDAO" scope="page"/>
<jsp:useBean id="themeDAO" class="forum.dao.ThemeDAO" scope="page"/>
<link rel="stylesheet" href="css/left.css">

<%
    //获取热门版块
    ArrayList<Theme> hotTheme = themeDAO.getHotTheme();
    request.setAttribute("hotTheme", hotTheme);
    //获取热贴
    ArrayList<Post> hotPostList = postDAO.getHotPost();
    request.setAttribute("hotPostList", hotPostList);
    //获取热门回帖
    ReplyDAO replyDAO = new ReplyDAOImpl();
    request.setAttribute("hotReplyList", replyDAO.getHotReply());
%>
<div class="sidesec1">
    <div class="sidesec_bt"><span>站内检索</span></div>
    <hr>
    <div class="sideform">
        <form action="post" method="get" id="formQ">
            <div id="leftdiv">
                <input type="hidden" value="query" name="action"></input>
                <input id="q" type="text" value="请输入关键字" name="keyword" onfocus="OnEnter(this)"
                       onblur="OnExit(this)"></input>
            </div>
            <div id="rightdiv">
                <i class="iconfont" style="font-size: 27px;cursor:pointer;" onclick="submitFun()">&#xeafe;</i>
            </div>
        </form>
    </div>
</div>
<div class="sidesec">
    <div class="sidesec_bt"><span>热门版块</span></div>
    <hr>
    <br>
    <div class="sidesec_list">
        <ul>
            <c:forEach var="theme" items="${hotTheme}" varStatus="status">
                <li>
                    <a class="a101" href="post?action=displayPostList&themeId=${theme.id}">
                        <div>${theme.themeTitle}</div>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<div class="sidesec">
    <div class="sidesec_bt"><span>热点帖子</span></div>
    <hr>
    <div class="sidesec_list">
        <ul>
            <c:forEach var="post" items="${hotPostList}" varStatus="status">
                <li>
                    <a href="post?action=displayPost&postId=${post.id}">.${post.title}</a><span>...</span>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<div class="sidesec">
    <div class="sidesec_bt"><span>热门回帖</span></div>
    <hr>
    <div class="sidesec_list">
        <ul>
            <c:forEach var="reply" items="${hotReplyList}">
                <li>
                    .<a href="post?action=displayPost&postId=${reply.postId}">
                        ${reply.content}
                </a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<script src="js/left.js"></script>
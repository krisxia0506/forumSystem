<%@ page import="java.util.ArrayList" %>
<%@ page import="forum.beans.*" %>
<%@ page import="forum.dao.ReplyDAOImpl" %>
<%@ page import="forum.dao.ReplyDAO" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="postDAO" class="forum.dao.PostDAO" scope="page"/>
<jsp:useBean id="postTypeDAO" class="forum.dao.PostTypeDAO" scope="page"/>
<link rel="stylesheet" href="css/left.css">

<%
    //获取热门版块
    ArrayList<PostType> hotPostType = postTypeDAO.getHotPostType();
    request.setAttribute("hotPostType", hotPostType);
    //获取热贴
    ArrayList<Post> hotPostList = postDAO.getHotPost();
    request.setAttribute("hotPostList", hotPostList);
    //获取所有帖子类型
    ArrayList<PostType> postTypeList = postTypeDAO.getAllPostType();
    request.setAttribute("postTypeList", postTypeList);
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
    <div class="sidesec_list">
        <ul>
            <c:forEach var="postType" items="${hotPostType}" varStatus="status">
                <li>
                    <a class="a101" href="post?action=displayPostList&postTypeId=${postType.id}">
                        <div>${postType.postType}</div>
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
                <li class="li2">
                    .<a href="post?action=displayPost&postId=${post.id}">${post.title}</a>
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
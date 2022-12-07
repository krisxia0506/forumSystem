<%@ page import="java.util.ArrayList" %>
<%@ page import="forum.beans.*" %>
<%@ page import="forum.dao.ReplyDAOImpl" %>
<%@ page import="forum.dao.ReplyDAO" %>
<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="postDAO" class="forum.dao.PostDAO" scope="page"/>
<jsp:useBean id="postTypeDAO" class="forum.dao.PostTypeDAO" scope="page"/>
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
    request.setAttribute("hotReplyList", replyDAO.getTop5());


%>
<div class="sidesec">
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
                    .<a href="post?action=displayPostList&postTypeId=${postType.id}">${postType.postType}</a>
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
                    .<a href="reply?action=displayPost&newsid=${reply.postId}">
                        ${reply.content}
                </a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<script>
    //获取地址栏参数//可以是中文参数
    function getUrlParam(key) {
        // 获取参数
        var url = window.location.search;
        // 正则筛选地址栏
        var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
        // 匹配目标参数
        var result = url.substr(1).match(reg);
        //返回参数值
        return result ? decodeURIComponent(result[2]) : null;
    }
    if (getUrlParam("keyword")){
        document.getElementById("q").value = getUrlParam("keyword");
    }

</script>
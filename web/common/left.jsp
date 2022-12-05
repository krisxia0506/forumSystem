<%@ page import="java.util.ArrayList" %>
<%@ page import="forum.beans.*" %>
<%@ page import="forum.dao.ReplyDAOImpl" %>
<%@ page import="forum.dao.ReplyDAO" %>
<%@ page import="forum.dao.ReplyDAOImpl" %>
<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="postDAO" class="forum.dao.PostDAO" scope="page"/>
<jsp:useBean id="newstypeDAO" class="forum.dao.PostTypeDAO" scope="page"/>
<%
    ArrayList<Post> hotPostList = postDAO.getHotNews();
    request.setAttribute("hotNewsList", hotPostList);
    ArrayList<PostType> postTypeList = newstypeDAO.getAllNewstype();
    request.setAttribute("postTypeList", postTypeList);
    ReplyDAO replyDAO = new ReplyDAOImpl();
    request.setAttribute("newComment", replyDAO.getTop5());

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
    <div class="sidesec_bt"><span>热点新闻</span></div>
    <hr>
    <div class="sidesec_list">
        <ul>
            <c:forEach var="post" items="${hotNewsList}" varStatus="status">
                <li>
                    .<a href="post?action=displayPost&postId=${post.id}">${post.title}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<div class="sidesec">
    <div class="sidesec_bt"><span>最新评论</span></div>
    <hr>
    <div class="sidesec_list">
        <ul>
            <c:forEach var="reply" items="${newComment}">
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
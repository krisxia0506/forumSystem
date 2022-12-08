package forum.servlet;

import forum.beans.Post;
import forum.beans.Reply;
import forum.dao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created on 2022-11-09 16:27
 *
 * @author Xia Jiayi
 */
@WebServlet("/post")
public class PostServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        ArrayList<Post> postList = new ArrayList<Post>();
        PostDAO postDAO = new PostDAO();
        UserDAO userDAO = new UserDAO();
        ReplyDAOImpl replyDAO = new ReplyDAOImpl();
        PostPageDAO postPageDAO = new PostPageDAO();
        int pageNo = 1;
        int pageSize = 6;
        String strPageNo = request.getParameter("pageNo");
        if (strPageNo!=null){
            pageNo=Integer.parseInt(strPageNo);
        }

        String func = request.getParameter("action");
        if (func == null) {
            func = "";
        }
        switch (func) {
            case "query": {
                String keyword = request.getParameter("keyword");
                postList = postDAO.getPostByKeyword(keyword);
                request.setAttribute("postList", postList);
                request.getRequestDispatcher("listPost.jsp").forward(request, response);
                break;
            }
            case "modify": {
                Post post = new Post();
                String id = request.getParameter("id");
                String author = request.getParameter("author");
                String postTime = request.getParameter("postTime");
                String title = request.getParameter("title");
                String postType = request.getParameter("postType");
                String keyword = request.getParameter("keyword");
                String content = request.getParameter("content");
                post.setId(Integer.parseInt(id));
                post.setAuthor(author);
                post.setPostTime(postTime);
                post.setTitle(title);
                post.setTheme(postType);
                post.setKeyword(keyword);
                post.setContent(content);
                if (postDAO.modifyPost(post)) {
                    request.getRequestDispatcher("post?action=manage").forward(request, response);
                } else {
                    request.getRequestDispatcher("post?action=manage").forward(request, response);
                }
                break;
            }
            case "manage" : {
                String role = (String) session.getAttribute("role");
                if (Objects.equals(role, "99")) {
                    postList = postDAO.getAllPost();
                } else {
                    String userId = (String) session.getAttribute("userId");
                    postList = postDAO.getPostByUserId(userId);
                }

                request.setAttribute("postList", postList);
                request.getRequestDispatcher("managePost.jsp").forward(request, response);
                break;
            }

            case "del" : {
                String postId = request.getParameter("postId");
                if (postDAO.deletePostById(postId)) {
                    request.getRequestDispatcher("post?action=manage").forward(request, response);
                } else {
                    System.out.println();
                }
                break;
            }
            case "add" : {
                Post post = new Post();
                String userId = (String) session.getAttribute("userId");
                String title = request.getParameter("title");
                String postType = request.getParameter("postType");
                String keyword = request.getParameter("keyword");
                String content = request.getParameter("content");
                post.setAuthor(userId);
                post.setTitle(title);
                post.setTheme(postType);
                post.setKeyword(keyword);
                post.setContent(content);
                if (postDAO.addPost(post)) {
                    userDAO.increasePostTimes(userId);
                    //查询刚才发布的帖子的id
                    String postId = postDAO.queryLastPost();
                    //转到刚才发布的帖子
                    request.getRequestDispatcher("post?action=displayPost&postId=" + postId).forward(request, response);
                } else {
                    System.out.println("发布失败");
                    request.getRequestDispatcher("post?action=manage").forward(request, response);
                }
                break;
            }
            case "displayPost" : {
                String postId = request.getParameter("postId");
                boolean isCollected = false;
                if (postDAO.getById(postId).getId() != null) {
                    postDAO.increaseHits(postId);
                    //获取帖子详情
                    Post post = postDAO.getById(postId);
                    //获取回帖
                    ArrayList<Reply> replyList = replyDAO.getByPostId(postId);
                    //获取是否收藏
                    String userId = (String) session.getAttribute("userId");
                    if (userId != null) {
                        CollectDAO collectDAO = new CollectDAO();
                        isCollected = collectDAO.isCollected(userId, postId);
                        request.setAttribute("isCollected", isCollected);
                    }
                    request.setAttribute("post", post);
                    request.setAttribute("replyList", replyList);
                    request.setAttribute("relatePost", postDAO.getRelate(postId));
                    request.getRequestDispatcher("displayPost.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                break;
            }
            //根据模块查询该模块的帖子列表
            case "displayPostList" : {
                String postTypeId = request.getParameter("postTypeId");
                postList = postPageDAO.getNewsByPage(pageNo, pageSize, postTypeId);
                Integer pageCount = postPageDAO.getPageCount(pageSize, postTypeId);
                request.setAttribute("pageCount", pageCount);
                request.setAttribute("pageNo", pageNo);
                request.setAttribute("postList", postList);
                request.setAttribute("postTypeId", postTypeId);
                request.getRequestDispatcher("listPost.jsp").forward(request, response);
                break;
            }
            default : {
                String uname = null;
                uname = (String) session.getAttribute("username");
                if (uname == null) {
                    Cookie[] cookies = request.getCookies();
                    String autologin = null;
                    if (cookies != null) {
                        for (Cookie cookie : cookies) {
                            if ("autologin".equals(cookie.getName())) {
                                autologin = cookie.getValue();
                                break;
                            }
                        }
                    }
                    if (autologin != null) {
                        String[] parts = autologin.split("-");
                        String name = parts[0];
                        String pwd = parts[1];
                        if (userDAO.queryByNamePwd(name, pwd).getId() != null) {
                            response.sendRedirect("userLogin.jsp ");
                        } else {
                            session.setAttribute("username", name);
                        }
                    }
                }
                postList = postPageDAO.getNewsByPage(pageNo, pageSize,null);
                Integer pageCount = postPageDAO.getPageCount(pageSize,null);
                request.setAttribute("pageCount",pageCount);
                request.setAttribute("pageNo",pageNo);
                request.setAttribute("newsList", postList);
                request.getRequestDispatcher("listPost.jsp").forward(request, response);
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

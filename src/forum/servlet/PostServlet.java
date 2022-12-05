package forum.servlet;

import forum.beans.Post;
import forum.dao.PostDAO;
import forum.dao.PostPageDAO;
import forum.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

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
            case "query" : {
                String keyword = request.getParameter("keyword");
                postList = postDAO.getNewsByKeyword(keyword);
                request.setAttribute("newsList", postList);
                request.getRequestDispatcher("listNews.jsp").forward(request, response);
                break;
            }
            case "modi" : {
                Post post = new Post();
                String id = request.getParameter("id");
                String author = request.getParameter("author");
                String pubtime = request.getParameter("pubtime");
                String title = request.getParameter("title");
                String newstype = request.getParameter("newstype");
                String keyword = request.getParameter("keyword");
                String content = request.getParameter("content");
                post.setId(Integer.parseInt(id));
                post.setAuthor(author);
                post.setPostTime(pubtime);
                post.setTitle(title);
                post.setPostType(newstype);
                post.setKeyword(keyword);
                post.setContent(content);
                if (postDAO.modifyNews(post)) {
                    request.getRequestDispatcher("forum").forward(request, response);
                } else {
                    request.getRequestDispatcher("forum").forward(request, response);
                }
                break;
            }
            case "manage" : {
                postList = postDAO.getAllNews();
                request.setAttribute("postList", postList);
                request.getRequestDispatcher("managePost.jsp").forward(request, response);
                break;
            }
            case "del" : {
                String newsId = request.getParameter("id");
                if (postDAO.deleteById(newsId)) {
                    request.getRequestDispatcher("forum").forward(request, response);
                } else {
                    System.out.println();
                }
                break;
            }
            case "add" : {
                Post post = new Post();
                String author = request.getParameter("author");
                String title = request.getParameter("title");
                String newstype = request.getParameter("newstype");
                String keyword = request.getParameter("keyword");
                String content = request.getParameter("content");
                post.setAuthor(author);
                post.setTitle(title);
                post.setPostType(newstype);
                post.setKeyword(keyword);
                post.setContent(content);
                if (postDAO.insertNews(post)) {
                    request.getRequestDispatcher("forum").forward(request, response);
                } else {
                    System.out.println("发布失败");
                    request.getRequestDispatcher("forum").forward(request, response);
                }
                break;
            }
            case "displayPost" : {
                String postId = request.getParameter("postId");
                if (postDAO.getById(postId).getId() != null) {
                    postDAO.increaseAc(postId);
                    Post post = postDAO.getById(postId);
                    request.setAttribute("post", post);
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
                postList = postPageDAO.getNewsByPage(pageNo, pageSize,postTypeId);
                Integer pageCount = postPageDAO.getPageCount(pageSize,postTypeId);
                request.setAttribute("pageCount",pageCount);
                request.setAttribute("pageNo",pageNo);
                request.setAttribute("postList", postList);
                request.setAttribute("postTypeId",postTypeId);
                request.getRequestDispatcher("listNews.jsp").forward(request, response);
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
                        UserDAO userDAO = new UserDAO();
                        if (!userDAO.queryByNamePwd(name, pwd)) {
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
                request.getRequestDispatcher("listNews.jsp").forward(request, response);
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

package forum.servlet;

import forum.beans.Post;
import forum.beans.Reply;
import forum.beans.User;
import forum.dao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created on 2022-12-08 16:27
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
                String postType = request.getParameter("theme");
                String keyword = request.getParameter("keyword");
                String content = request.getParameter("postContent");
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
            case "manage": {
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

            case "delete": {
                String postId = request.getParameter("postId");
                if (postDAO.deletePostById(postId)) {
                    request.getRequestDispatcher("post?action=manage").forward(request, response);
                } else {
                    System.out.println("delete failed");
                    request.getRequestDispatcher("post?action=manage").forward(request, response);
                }
                break;
            }
            case "add": {
                Post post = new Post();
                String userId = (String) session.getAttribute("userId");
                String title = request.getParameter("title");
                String postType = request.getParameter("theme");
                String keyword = request.getParameter("keyword");
                String content = request.getParameter("postContent");
                post.setAuthor(userId);
                post.setTitle(title);
                post.setTheme(postType);
                post.setKeyword(keyword);
                post.setContent(content);
                if (postDAO.addPost(post)) {
                    userDAO.increasePostTimes(userId);
                    //??????????????????
                    User user = userDAO.queryByUserId(userId);
                    session.setAttribute("level", user.getLevel());
                    //??????????????????????????????id
                    String postId = postDAO.queryLastPost();
                    //???????????????????????????
                    request.getRequestDispatcher("post?action=displayPost&postId=" + postId).forward(request, response);
                } else {
                    System.out.println("????????????");
                    request.getRequestDispatcher("post?action=manage").forward(request, response);
                }
                break;
            }
            case "displayPost": {
                String postId = request.getParameter("postId");
                String reply = request.getParameter("reply");
                boolean isCollected = false;
                if (postDAO.getById(postId).getId() != null) {
                    if (!Objects.equals(reply, "1")) {
                        //???????????????
                        postDAO.increaseHits(postId);
                    }
                    //??????????????????
                    Post post = postDAO.getById(postId);
                    //????????????
                    ArrayList<Reply> replyList = replyDAO.getReplyByPostId(postId);
                    //??????????????????
                    String userId = (String) session.getAttribute("userId");
                    if (userId != null) {
                        CollectionDAO collectionDAO = new CollectionDAO();
                        isCollected = collectionDAO.isCollected(userId, postId);
                        request.setAttribute("isCollected", isCollected);
                    }
                    request.setAttribute("post", post);
                    request.setAttribute("replyList", replyList);
                    //??????????????????
                    request.setAttribute("relatePost", postDAO.getRelate(postId));
                    request.getRequestDispatcher("displayPost.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                break;
            }
            //??????????????????????????????????????????
            case "displayPostList": {
                String themeId = request.getParameter("themeId");
                String strPageNo = request.getParameter("pageNo");


                if (strPageNo != null) {
                    pageNo = Integer.parseInt(strPageNo);
                }
                postList = postPageDAO.getPostByPage(pageNo, pageSize, themeId);
                //?????????
                Integer pageCount = postPageDAO.getPageCount(pageSize, themeId);

                request.setAttribute("pageCount", pageCount);
                request.setAttribute("pageNo", pageNo);
                request.setAttribute("postList", postList);
                if (!postList.isEmpty()) {
                    request.setAttribute("themeTitle", postList.get(0).getTheme());
                }
                request.getRequestDispatcher("listPost.jsp").forward(request, response);
                break;
            }
            default: {
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }
}

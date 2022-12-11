package forum.servlet;

import forum.beans.Post;
import forum.beans.Reply;
import forum.dao.PostDAO;
import forum.dao.ReplyDAO;
import forum.dao.ReplyDAOImpl;
import forum.dao.UserDAO;

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
 * Created on 2022-11-21 11:05
 *
 * @author Xia Jiayi
 */
@WebServlet("/reply")
public class ReplyServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ReplyDAO replyDAO = new ReplyDAOImpl();
        UserDAO userDAO = new UserDAO();
        ArrayList<Reply> replyList = new ArrayList<Reply>();
        ArrayList<Post> postList = new ArrayList<>();
        HttpSession session = request.getSession();
        String func = request.getParameter("action");
        if (func == null) {
            func = "";
        }
        switch (func) {
            case "add" : {
                Reply reply = new Reply();
                String postId = request.getParameter("postId");
                String replyAuthor = (String) session.getAttribute("userId");
                String replyContent = request.getParameter("replyContent");
                //判断是否登陆
                if (Objects.equals(replyAuthor, null)) {
                    response.sendRedirect("userLogin.jsp");
                } else {
                    reply.setContent(replyContent);
                    reply.setAuthor(replyAuthor);
                    reply.setPostId(Integer.parseInt(postId));
                    if (replyDAO.insertReply(reply)) {
                        userDAO.increasePostTimes(replyAuthor);
                        request.getRequestDispatcher("post?action=displayPost&postId=" + postId).forward(request, response);
                    } else {
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                }
                break;
            }
            case "delete": {
                String replyId = request.getParameter("replyId");
                if (replyDAO.deleteById(replyId)) {
                    request.getRequestDispatcher("reply?action=manage").forward(request, response);
                } else {
                    request.getRequestDispatcher("index.jsp?error=1").forward(request, response);
                }
                break;
            }
            case "manage": {//评论管理
                String role = (String) session.getAttribute("role");
                if (Objects.equals(role, "99")) {//管理员
                    replyList = replyDAO.getAll();
                } else {//普通用户
                    String userId = (String) session.getAttribute("userId");
                    replyList = replyDAO.getByUserId(userId);
                }
                PostDAO postDAO = new PostDAO();
                postList = postDAO.getAllPost();
                //遍历
                for (Reply reply : replyList) {
                    for (Post post : postList) {
                        if (Objects.equals(post.getId(), reply.getPostId())) {
                            reply.setPost(post);
                        }
                    }
                }
                request.setAttribute("replyList", replyList);
                request.getRequestDispatcher("manageReply.jsp").forward(request, response);
                break;
            }
            default : request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

package forum.servlet;

import forum.beans.Post;
import forum.beans.Reply;
import forum.dao.PostDAO;
import forum.dao.ReplyDAO;
import forum.dao.ReplyDAOImpl;

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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ReplyDAO replyDAO = new ReplyDAOImpl();
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
                String replyAuthor = request.getParameter("replyAuthor");
                String replyContent = request.getParameter("replyContent");
                if (Objects.equals(replyAuthor, "")) {
                    response.sendRedirect("userLogin.jsp");
                } else {
                    reply.setContent(replyContent);
                    reply.setAuthor(replyAuthor);
                    reply.setPostId(Integer.parseInt(postId));
                    if (replyDAO.insert(reply)) {
                        request.getRequestDispatcher("post?action=displayPost&postId=" + postId).forward(request, response);

                    } else {
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                }
                break;
            }
            case "del" : {
                String id = request.getParameter("id");
                String username = request.getParameter("username");
                if (replyDAO.deleteById(id)) {
                    if ("admin".equals(username)) {
                        request.getRequestDispatcher("reply?action=manage").forward(request, response);

                    } else {
                        request.getRequestDispatcher("reply?action=usermanage&username=" + username).forward(request, response);
                    }
                } else {
                    request.getRequestDispatcher("index.jsp?error=1").forward(request, response);
                }
                break;
            }
            case "manage": {//评论管理
                String role = (String) session.getAttribute("role");
                if (Objects.equals(role, "99")) {
                    replyList = replyDAO.getAll();
                } else {
                    String username = (String) session.getAttribute("username");
                    replyList = replyDAO.getByUsername(username);
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

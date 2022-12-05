package forum.servlet;

import forum.beans.*;
import forum.dao.ReplyDAO;
import forum.dao.ReplyDAOImpl;
import forum.dao.PostDAO;

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
        //按news id查询评论
        switch (func) {
            case "disp" : {
                PostDAO postDAO = new PostDAO();
                String newsid = request.getParameter("newsid");
                replyList = replyDAO.getByNewsId(Integer.parseInt(newsid));
                request.setAttribute("commentList", replyList);
                Post post = postDAO.getById(newsid);
                request.setAttribute("news", post);
                request.getRequestDispatcher("listComment.jsp").forward(request, response);


                break;
            }
            case "add" : {
                Reply comm = new Reply();
                String newsid = request.getParameter("newsid");
                String commentauthor = request.getParameter("commentauthor");
                String comment = request.getParameter("comment");
                comm.setContent(comment);
                comm.setAuthor(commentauthor);
                comm.setPostId(Integer.parseInt(newsid));
                if (replyDAO.insert(comm)) {
                    request.getRequestDispatcher("comment?action=disp&newsid=" + newsid).forward(request, response);

                } else {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                break;
            }
            case "del" : {
                String id = request.getParameter("id");
                String username = request.getParameter("username");
                if (replyDAO.deleteById(id)) {
                    if ("admin".equals(username)) {
                        request.getRequestDispatcher("comment?action=manage").forward(request, response);

                    } else {
                        request.getRequestDispatcher("comment?action=usermanage&username=" + username).forward(request, response);
                    }
                } else {
                    request.getRequestDispatcher("listComment.jsp?error=1").forward(request, response);
                }
                break;
            }
            case "manage" : {//管理员评论管理
                replyList = replyDAO.getAll();
                PostDAO postDAO = new PostDAO();
                postList = postDAO.getAllNews();
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
            case "usermanage" : {//用户评论管理
                String username = request.getParameter("username");

                if (session.getAttribute("username").equals(username)) {
                    replyList = replyDAO.getByUsername(username);

                    PostDAO postDAO = new PostDAO();
                    postList = postDAO.getAllNews();

                    //遍历
                    for (Reply reply : replyList) {
                        for (Post post : postList) {
                            if (Objects.equals(post.getId(), reply.getPostId())) {
                                reply.setPost(post);
                            }
                        }
                    }
                    request.setAttribute("commentList", replyList);
                    request.getRequestDispatcher("manageReply.jsp").forward(request, response);

                } else {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                break;
            }
            default : request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

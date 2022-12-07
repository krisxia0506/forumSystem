package forum.servlet;

import forum.dao.CollectDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created on 2022-12-07 10:09
 *
 * @author Xia Jiayi
 */
@WebServlet("/collect")
public class CollectServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        CollectDAO collectDAO = new CollectDAO();
        String userId = (String) session.getAttribute("userId");
        String func = request.getParameter("action");
        switch (func) {
            case "add": {
                String postId = request.getParameter("postId");
                if (collectDAO.collectPost(userId, postId)) {
                    request.getRequestDispatcher("post?action=displayPost&postId=" + postId).forward(request, response);
                } else {
                    System.out.println("收藏失败");
                    request.getRequestDispatcher("post?action=displayPost&postId=" + postId).forward(request, response);
                }
                break;
            }
            case "delete": {
                String postId = request.getParameter("postId");
                if (collectDAO.cancelCollect(userId, postId)) {
                    request.getRequestDispatcher("post?action=displayPost&postId=" + postId).forward(request, response);
                } else {
                    System.out.println("取消收藏失败");
                    request.getRequestDispatcher("post?action=displayPost&postId=" + postId).forward(request, response);
                }
                break;
            }
            case "manage": {
                break;
            }
        }
    }
}

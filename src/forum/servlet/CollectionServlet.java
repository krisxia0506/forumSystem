package forum.servlet;

import forum.beans.Collection;
import forum.dao.CollectionDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created on 2022-12-07 10:09
 *
 * @author Xia Jiayi
 */
@WebServlet("/collect")
public class CollectionServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        CollectionDAO collectionDAO = new CollectionDAO();
        String userId = (String) session.getAttribute("userId");
        String func = request.getParameter("action");
        switch (func) {
            case "add": {
                String postId = request.getParameter("postId");
                if (collectionDAO.collectPost(userId, postId)) {
                    response.getWriter().write("collectSuccess");
                    //request.getRequestDispatcher("post?action=displayPost&postId=" + postId).forward(request, response);
                } else {
                    System.out.println("收藏失败");
                    request.getRequestDispatcher("post?action=displayPost&postId=" + postId).forward(request, response);
                }
                break;
            }
            case "delete": {
                String postId = request.getParameter("postId");

                if (collectionDAO.cancelCollect(userId, postId)) {
                    response.getWriter().write("cancelCollectSuccess");
                    //request.getRequestDispatcher("collect?action=manage").forward(request, response);

                } else {
                    System.out.println("取消收藏失败");
                    request.getRequestDispatcher("post?action=displayPost&postId=" + postId).forward(request, response);
                }
                break;
            }
            case "manage": {
                ArrayList<Collection> collectionList = new ArrayList<Collection>();
                collectionList = collectionDAO.getCollectionByUserId(userId);
                request.setAttribute("collectionList", collectionList);
                request.getRequestDispatcher("manageCollect.jsp").forward(request, response);
                break;
            }
        }
    }
}

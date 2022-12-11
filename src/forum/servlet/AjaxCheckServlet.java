package forum.servlet;

import forum.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 2022-11-22 22:39
 *
 * @author Xia Jiayi
 */
@WebServlet("/checkName")
public class AjaxCheckServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        UserDAO userDAO = new UserDAO();
        if (username != null) {
            if (userDAO.queryByUsername(username)) {
                //用户名已存在
                response.getWriter().write("1");
            } else {
                response.getWriter().write("0");
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

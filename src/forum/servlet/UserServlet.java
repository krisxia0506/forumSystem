package forum.servlet;

import forum.beans.User;
import forum.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created on 2022-11-21 18:25
 *
 * @author Xia Jiayi
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        UserDAO userDAO = new UserDAO();
        ArrayList<User> usersList = new ArrayList<User>();
        String id = request.getParameter("id");
        String uname = request.getParameter("username");
        String pwd = request.getParameter("password");
        String gender = request.getParameter("gender");
        String resume = request.getParameter("resume");

        String func = request.getParameter("action");
        if (func == null) {
            func = "";
        }
        switch (func) {
            case "manage":
                usersList = userDAO.queryAll();
                request.setAttribute("usersList", usersList);
                request.getRequestDispatcher("manageUser.jsp").forward(request, response);
                break;
            case "delete":
                if (userDAO.deleteById(id)) {
                    request.getRequestDispatcher("user?action=manage").forward(request, response);
                } else {
                    System.out.println("deleteUser error");
                    request.getRequestDispatcher("user?action=manage").forward(request, response);
                }
                break;
            case "login": {
                String nopwd = request.getParameter("nopwd");
                User user = new User();
                if (id != null) {
                    user.setId(Integer.valueOf(id));
                }
                user.setUsername(uname);
                user.setPassword(pwd);
                user.setGender(gender);
                user.setResume(resume);
                user = userDAO.queryByNamePwd(uname, pwd);
                if (user.getId() != null) {
                    session.setAttribute("username", uname);
                    session.setAttribute("role", user.getRoleId().toString());
                    session.setAttribute("userId", user.getId().toString());
                    if (nopwd != null) {
                        Cookie cookie = new Cookie("autologin", uname + "-" + pwd);
                        cookie.setMaxAge(Integer.parseInt(nopwd));
                        cookie.setPath(request.getContextPath());
                        response.addCookie(cookie);
                    }
                    response.sendRedirect("index.jsp");
                } else {
                    response.sendRedirect("userLogin.jsp?error=1");
                }
                break;
            }
            case "register": {
                User user = new User();
                user.setUsername(uname);
                user.setPassword(pwd);
                user.setGender(gender);
                user.setResume(resume);
                if (userDAO.insertUser(user)) {
                    response.sendRedirect("userRegisterSuccess.jsp");
                } else {
                    response.sendRedirect("userRegister.jsp?error=1");
                }
                break;
            }
            case "modi": {
                User user = new User();
                user.setId(Integer.valueOf(id));
                user.setUsername(uname);
                user.setPassword(pwd);
                user.setGender(gender);
                user.setResume(resume);
                if (userDAO.updataUserById(user)) {
                    String username = (String) session.getAttribute("username");
                    if (Objects.equals(username, "admin")) {
                        request.getRequestDispatcher("user?action=manage").forward(request, response);
                    } else {
                        session.setAttribute("username", uname);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                } else {
                    System.out.println("用户修改失败，请联系管理员");
                }
                break;
            }
            case "modifyView": {//修改用户页面
                String realUsername = (String) session.getAttribute("username");
                String role = (String) session.getAttribute("role");
                User user = new User();
                if (Objects.equals(role, "99")) {//如果是管理员
                    user = userDAO.queryByName(uname);
                } else {//如果是普通用户
                    user = userDAO.queryByName(realUsername);
                }
                request.setAttribute("user", user);
                request.getRequestDispatcher("modifyUser.jsp").forward(request, response);
                break;
            }
            case "logout": {
                session.invalidate();
                Cookie cookie = new Cookie("autologin", "msg");
                cookie.setMaxAge(0);
                cookie.setPath(request.getContextPath());
                response.addCookie(cookie);
                response.sendRedirect("index.jsp");
                break;
            }
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

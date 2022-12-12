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
 * Created on 2022-12-11 18:25
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

        String func = request.getParameter("action");
        if (func == null) {
            func = "";
        }
        switch (func) {
            case "manage": {
                usersList = userDAO.queryAll();
                request.setAttribute("usersList", usersList);
                request.getRequestDispatcher("manageUser.jsp").forward(request, response);
                break;
            }
            case "delete": {
                String userId = request.getParameter("userId");
                if (userDAO.deleteById(userId)) {
                    request.getRequestDispatcher("user?action=manage").forward(request, response);
                } else {
                    System.out.println("deleteUser error");
                    request.getRequestDispatcher("user?action=manage").forward(request, response);
                }
                break;
            }
            case "login": {
                String nopwd = request.getParameter("nopwd");
                String username = request.getParameter("username");
                String pwd = request.getParameter("password");
                User user = new User();
                user = userDAO.queryByNamePwd(username, pwd);
                if (user.getId() != null) {
                    session.setAttribute("role", user.getRole().toString());
                    session.setAttribute("nickname", user.getNickname());
                    session.setAttribute("userId", user.getId().toString());
                    session.setAttribute("level", user.getLevel());
                    if (nopwd != null) {
                        Cookie cookie = new Cookie("autologin", username + "-" + pwd);
                        cookie.setMaxAge(Integer.parseInt(nopwd));
                        cookie.setPath(request.getContextPath());
                        response.addCookie(cookie);
                    }
                    response.sendRedirect("index.jsp");
                } else {
                    request.setAttribute("msg", "登陆失败，请检查用户名和密码");
                    request.getRequestDispatcher("userLogin.jsp").forward(request, response);
                }
                break;
            }
            case "register": {
                User user = new User();
                String username = request.getParameter("username");
                String pwd = request.getParameter("password");
                String nickname = request.getParameter("nickname");
                String gender = request.getParameter("gender");
                String resume = request.getParameter("resume");
                user.setUsername(username);
                user.setPassword(pwd);
                user.setNickname(nickname);
                user.setGender(gender);
                user.setResume(resume);
                if (userDAO.insertUser(user)) {
                    response.sendRedirect("userRegisterSuccess.jsp");
                } else {
                    request.setAttribute("msg", "注册失败");
                    request.getRequestDispatcher("userRegister.jsp").forward(request, response);
                }
                break;
            }
            case "modify": {
                String id = request.getParameter("id");
                String username = request.getParameter("username");
                String pwd = request.getParameter("password");
                String nickname = request.getParameter("nickname");
                String gender = request.getParameter("gender");
                String resume = request.getParameter("resume");
                User user = new User();
                user.setId(Integer.valueOf(id));
                user.setUsername(username);
                user.setPassword(pwd);
                user.setNickname(nickname);
                user.setGender(gender);
                user.setResume(resume);
                if (userDAO.updateUserById(user)) {
                    String role = (String) session.getAttribute("role");
                    if (Objects.equals(role, "99")) {//管理员
                        request.getRequestDispatcher("user?action=manage").forward(request, response);
                    } else {
                        session.setAttribute("nickname", nickname);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                } else {
                    System.out.println("用户修改失败，请联系管理员");
                }
                break;
            }
            case "modifyView": {//修改用户页面
                String userId = request.getParameter("userId");
                String sessionId = (String) session.getAttribute("userId");
                String role = (String) session.getAttribute("role");
                User user = new User();
                if (Objects.equals(role, "99")) {//如果是管理员
                    user = userDAO.queryByUserId(userId);
                } else {//如果是普通用户
                    user = userDAO.queryByUserId(sessionId);
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

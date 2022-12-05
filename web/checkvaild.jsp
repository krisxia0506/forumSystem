<%@ page import="java.util.Scanner" %>
<%@ page import="forum.dao.UserDAO" %><%--
  Created by IntelliJ IDEA.
  forum.beans.User: Administrator
  Date: 2022-09-29
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
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
        if (autologin == null) {
            response.sendRedirect("userLogin.jsp");
        } else {
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
%>
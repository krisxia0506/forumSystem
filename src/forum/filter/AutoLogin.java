package forum.filter;

import forum.beans.User;
import forum.dao.UserDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created on 2022-12-11 15:00
 *
 * @author Xia Jiayi
 */
@WebFilter(filterName = "AutoLogin", urlPatterns = "/index.jsp")
public class AutoLogin implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("AutoLoginFilter is Success");
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession();
        String uname = (String) session.getAttribute("username");
        if (uname == null) {
            Cookie[] cookies = httpRequest.getCookies();
            String autologin = null;
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("autologin".equals(cookie.getName())) {
                        autologin = cookie.getValue();
                        break;
                    }
                }
            }
            if (autologin != null) {
                String[] parts = autologin.split("-");
                String name = parts[0];
                String pwd = parts[1];
                UserDAO userDAO = new UserDAO();
                User user = userDAO.queryByNamePwd(name, pwd);
                if (user.getId() != null) {
                    session.setAttribute("username", name);
                    session.setAttribute("role", user.getRole().toString());
                    session.setAttribute("userId", user.getId().toString());
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    httpResponse.sendRedirect("userLogin.jsp ");
                }
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}

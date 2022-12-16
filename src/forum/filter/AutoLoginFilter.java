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
public class AutoLoginFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("AutoLoginFilter运行");
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String uname = (String) session.getAttribute("nickname");
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
            if (autologin != null) {
                String[] parts = autologin.split("-");
                String name = parts[0];
                String pwd = parts[1];
                UserDAO userDAO = new UserDAO();
                User user = userDAO.queryByNamePwd(name, pwd);
                if (user.getId() != null) {
                    session.setAttribute("nickname", user.getNickname());
                    session.setAttribute("role", user.getRole().toString());
                    session.setAttribute("userId", user.getId().toString());
                    session.setAttribute("level", user.getLevel());
//                    System.out.println("自动登陆成功，昵称：" + user.getNickname());
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("销毁");
    }
}

package forum.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Created on 2022-12-12 17:42
 *
 * @author Xia Jiayi
 */
@WebFilter(filterName = "CheckLoginFilter", urlPatterns = {"/post", "/theme", "/reply", "/collect", "/managePost.jsp", "/manageTheme.jsp", "/manageUser.jsp", "/modifyUser.jsp", "/modifyTheme.jsp", "/modifyPost.jsp"})
public class CheckLoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("CheckLoginFilter运行");
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String nickname = (String) request.getSession().getAttribute("nickname");
        String action = request.getParameter("action");
        if (!Objects.equals(action, "displayPostList") && !Objects.equals(action, "displayPost") && nickname == null) {
//            System.out.println("CheckLoginFilter：需要登录跳转登录");
            request.getRequestDispatcher("userLogin.jsp").forward(request, response);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

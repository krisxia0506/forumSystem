package forum.servlet;

import forum.beans.Theme;
import forum.dao.ThemeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created on 2022-12-06 13:16
 *
 * @author Xia Jiayi
 */
@WebServlet("/theme")
public class ThemeServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ThemeDAO themeDAO = new ThemeDAO();
        Theme theme = new Theme();
        ArrayList<Theme> themeList = new ArrayList<>();
        String func = request.getParameter("action");
        switch (func) {
            case "add": {
                String themeTitle = request.getParameter("themeTitle");
                String typeIntroduction = request.getParameter("typeIntroduction");
                theme.setThemeTitle(themeTitle);
                theme.setThemeIntroduction(typeIntroduction);
                if (themeDAO.addTheme(theme)) {
                    request.getRequestDispatcher("theme?action=manage").forward(request, response);
                } else {
                    System.out.println("添加版块失败");
                    request.setAttribute("msg", "添加版块失败");
                    request.getRequestDispatcher("theme?action=manage").forward(request, response);
                }

                break;
            }
            case "manage": {
                themeList = themeDAO.getAllTheme();
                request.setAttribute("themeList", themeList);
                request.getRequestDispatcher("manageTheme.jsp").forward(request, response);
                break;
            }
            case "delete": {
                String themeId = request.getParameter("themeId");
                if (themeDAO.deleteThemeById(themeId)) {
                    request.getRequestDispatcher("theme?action=manage").forward(request, response);
                } else {
                    System.out.println("删除版块失败");
                    request.setAttribute("msg", "删除版块失败");
                    request.getRequestDispatcher("theme?action=manage").forward(request, response);
                }

                break;
            }
            case "modify": {
                String themeId = request.getParameter("themeId");
                String themeTitle = request.getParameter("themeTitle");
                String themeIntroduction = request.getParameter("themeIntroduction");
                theme.setId(Integer.valueOf(themeId));
                theme.setThemeTitle(themeTitle);
                theme.setThemeIntroduction(themeIntroduction);
                if (themeDAO.modifyTheme(theme)) {
                    request.getRequestDispatcher("theme?action=manage").forward(request, response);
                } else {
                    request.setAttribute("msg", "修改版块失败");
                    request.getRequestDispatcher("modifyTheme.jsp?themeId=" + themeId).forward(request, response);
                }
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

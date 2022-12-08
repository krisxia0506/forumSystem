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
                String postType1 = request.getParameter("postType");
                String typeIntroduction = request.getParameter("typeIntroduction");
                theme.setThemeTitle(postType1);
                theme.setThemeIntroduction(typeIntroduction);
                themeDAO.addTheme(theme);
                request.getRequestDispatcher("theme?action=manage").forward(request, response);
                break;
            }
            case "manage": {
                themeList = themeDAO.getAllTheme();
                request.setAttribute("postTypeList", themeList);
                request.getRequestDispatcher("manageTheme.jsp").forward(request, response);
                break;
            }
            case "delete": {
                String postTypeId = request.getParameter("postTypeId");
                themeDAO.deleteThemeById(postTypeId);
                request.getRequestDispatcher("theme?action=manage").forward(request, response);
                break;
            }
            case "modify": {
                String postTypeId = request.getParameter("postTypeId");
                String postType1 = request.getParameter("postType");
                String typeIntroduction = request.getParameter("typeIntroduction");
                theme.setId(Integer.valueOf(postTypeId));
                theme.setThemeTitle(postType1);
                theme.setThemeIntroduction(typeIntroduction);
                if (themeDAO.modifyTheme(theme)) {
                    request.getRequestDispatcher("theme?action=manage").forward(request, response);
                } else {
                    request.getRequestDispatcher("modifyTheme.jsp?postTypeId=" + postTypeId).forward(request, response);
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

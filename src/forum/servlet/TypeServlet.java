package forum.servlet;

import forum.beans.PostType;
import forum.dao.PostTypeDAO;

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
@WebServlet("/type")
public class TypeServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        PostTypeDAO postTypeDAO = new PostTypeDAO();
        PostType postType = new PostType();
        ArrayList<PostType> postTypeList = new ArrayList<>();
        String func = request.getParameter("action");
        switch (func) {
            case "add": {
                String postType1 = request.getParameter("postType");
                String typeIntroduction = request.getParameter("typeIntroduction");
                postType.setPostType(postType1);
                postType.setTypeIntroduction(typeIntroduction);
                postTypeDAO.addPostType(postType);
                request.getRequestDispatcher("type?action=manage").forward(request, response);
                break;
            }
            case "manage": {
                postTypeList = postTypeDAO.getAllPostType();
                request.setAttribute("postTypeList", postTypeList);
                request.getRequestDispatcher("manageType.jsp").forward(request, response);
                break;
            }
            case "delete": {
                String postTypeId = request.getParameter("postTypeId");
                postTypeDAO.deletePostTypeById(postTypeId);
                request.getRequestDispatcher("type?action=manage").forward(request, response);
                break;
            }
            case "modify": {
                String postTypeId = request.getParameter("postTypeId");
                String postType1 = request.getParameter("postType");
                String typeIntroduction = request.getParameter("typeIntroduction");
                postType.setId(Integer.valueOf(postTypeId));
                postType.setPostType(postType1);
                postType.setTypeIntroduction(typeIntroduction);
                if (postTypeDAO.modifyPostType(postType)) {
                    request.getRequestDispatcher("type?action=manage").forward(request, response);
                } else {
                    request.getRequestDispatcher("modifyType.jsp?postTypeId=" + postTypeId).forward(request, response);
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

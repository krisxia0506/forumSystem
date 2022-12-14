package forum.dao;

import forum.beans.Post;

import java.util.ArrayList;

/**
 * Created on 2022-11-23 16:38
 *
 * @author Xia Jiayi
 */
public class PostPageDAO {
    /**
     * 根据每页帖子条数返回共有多少页
     *
     * @param pageSize 每页显示的记录数
     * @return 共多少页
     */
    public int getPageCount(int pageSize, String theme) {
        PostDAO postDAO = new PostDAO();
        int recordCount = 0, t1 = 0, t2 = 0;
        //帖子总条数
        recordCount = postDAO.getPostCountByType(theme);
        //取余
        t1 = recordCount % pageSize;
        //取商
        t2 = recordCount / pageSize;
        //如果取余是0，就把t2的值返回，否则t2+1的值返回
        return t1 == 0 ? t2 : t2 + 1;
    }

    /**
     * 获取指定页的帖子
     *
     * @param pageNo   指定页
     * @param pageSize 每页显示的帖子条数
     * @return 指定页的帖子
     */
    public ArrayList<Post> getPostByPage(int pageNo, int pageSize, String postTypeId) {
        PostDAO postDAO = new PostDAO();
        ArrayList<Post> postList = new ArrayList<Post>();
        int start = (pageNo - 1) * pageSize;
        postList = postDAO.getPostBySCT(start, pageSize, postTypeId);
        return postList;
    }
}

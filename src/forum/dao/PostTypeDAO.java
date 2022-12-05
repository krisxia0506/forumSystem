package forum.dao;

import forum.beans.PostType;
import forum.util.DBGet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created on 2022-10-27 23:05
 *
 * @author Xia Jiayi
 */
public class PostTypeDAO {
    public ArrayList<PostType> getAllNewstype() {
        PostType postType = null;
        ArrayList<PostType> postTypeList = new ArrayList<PostType>();
        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            conn = DBGet.getConnection();
            stmt = conn.createStatement();
            String sql = "select * from post_type";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                postType = new PostType();
                postType.setPostTypeId(rs.getInt("post_type_id"));
                postType.setPostType(rs.getString("post_type"));
                postTypeList.add(postType);
            }
        } catch (SQLException e1) {
            System.out.println("getAllNewstype"+e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        return postTypeList;
    }
}

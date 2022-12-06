package forum.dao;

import forum.beans.PostType;
import forum.util.DBGet;

import java.sql.*;
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
            System.out.println("getAllNewstype" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        return postTypeList;
    }

    public boolean addPostType(String postType) {

        boolean result = false;
        int n = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "insert into post_type(post_type) value(?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, postType);
            n = ps.executeUpdate();
        } catch (SQLException e1) {
            System.out.println("insert" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        if (n > 0) {
            result = true;
        }
        return result;
    }
}

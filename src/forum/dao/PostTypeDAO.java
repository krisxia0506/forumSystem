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
    public ArrayList<PostType> getAllPostType() {
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
                postType.setId(rs.getInt("post_type_id"));
                postType.setPostType(rs.getString("post_type"));
                postType.setTypeIntroduction(rs.getString("type_introduction"));
                postTypeList.add(postType);
            }
        } catch (SQLException e1) {
            System.out.println("getAllPostType" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        return postTypeList;
    }

    public boolean addPostType(PostType postType) {

        boolean result = false;
        int n = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "insert into post_type(post_type,type_introduction) value(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, postType.getPostType());
            ps.setString(2, postType.getTypeIntroduction());
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

    /**
     * 根据id删除
     */
    public boolean deletePostTypeById(String id) {
        boolean result = false;
        int n = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "delete from post_type where post_type_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            n = ps.executeUpdate();
        } catch (SQLException e1) {
            System.out.println("delete" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        if (n > 0) {
            result = true;
        }
        return result;
    }

    /**
     * 根据id查询
     */
    public PostType getPostTypeById(String id) {
        PostType postType = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "select * from post_type where post_type_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                postType = new PostType();
                postType.setId(rs.getInt("post_type_id"));
                postType.setPostType(rs.getString("post_type"));
                postType.setTypeIntroduction(rs.getString("type_introduction"));
            }
        } catch (SQLException e1) {
            System.out.println("getPostTypeById" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        return postType;
    }

    /**
     * 根据id修改
     */
    public boolean modifyPostType(PostType postType) {
        boolean result = false;
        int n = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "update post_type set post_type = ?,type_introduction = ? where post_type_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, postType.getPostType());
            ps.setString(2, postType.getTypeIntroduction());
            ps.setInt(3, postType.getId());
            n = ps.executeUpdate();
        } catch (SQLException e1) {
            System.out.println("update" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        if (n > 0) {
            result = true;
        }
        return result;
    }
}

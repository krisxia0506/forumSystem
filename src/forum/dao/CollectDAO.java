package forum.dao;

import forum.util.DBGet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created on 2022-12-07 10:31
 *
 * @author Xia Jiayi
 */
public class CollectDAO {

    /**
     * 收藏帖子
     */
    public boolean collectPost(String username, String postId) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "insert into collection(user_id, post_id) values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, postId);
            ps.executeUpdate();
        } catch (SQLException e1) {
            System.out.println("collectPost" + e1);
            return false;
        } finally {
            DBGet.closeConnection(conn);
        }
        return true;
    }

    /**
     * 查询是否已收藏
     */
    public boolean isCollected(String username, String postId) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "select * from collection where user_id=? and post_id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, postId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e1) {
            System.out.println("isCollected" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        return false;
    }

    /**
     * 取消收藏
     */
    public boolean cancelCollect(String username, String postId) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "delete from collection where user_id=? and post_id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, postId);
            ps.executeUpdate();
        } catch (SQLException e1) {
            System.out.println("cancelCollect" + e1);
            return false;
        } finally {
            DBGet.closeConnection(conn);
        }
        return true;
    }
}

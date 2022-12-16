package forum.dao;

import forum.beans.Collection;
import forum.util.DBGet;
import forum.util.DateTimeUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created on 2022-12-07 10:31
 *
 * @author Xia Jiayi
 */
public class CollectionDAO {

    /**
     * 收藏帖子
     *
     * @param userId 用户id
     * @param postId 帖子id
     * @return 是否收藏成功
     */
    public boolean collectPost(String userId, String postId) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "insert into collection values(null,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userId);
            ps.setString(2, postId);
            ps.setString(3, DateTimeUtil.getNowStr());
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
     *
     * @param userId 用户id
     * @param postId 帖子id
     * @return 是否已收藏
     */
    public boolean isCollected(String userId, String postId) {
        boolean flag = false;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "select * from collection where user_id=? and post_id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userId);
            ps.setString(2, postId);
            rs = ps.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        } catch (SQLException e1) {
            System.out.println("isCollected" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        return flag;
    }

    /**
     * 取消收藏
     *
     * @param userId 用户id
     * @param postId 帖子id
     * @return 是否取消收藏成功
     */
    public boolean cancelCollect(String userId, String postId) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "delete from collection where user_id=? and post_id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userId);
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

    /**
     * 根据用户id查询收藏列表
     *
     * @param userId 用户id
     * @return 该用户的收藏列表
     */
    public ArrayList<Collection> getCollectionByUserId(String userId) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        ArrayList<Collection> collectionList = new ArrayList<Collection>();
        try {
            conn = DBGet.getConnection();
            String sql = "select * from collection join post p on p.post_id = collection.post_id where user_id = ? order by post_time desc";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Collection collection = new Collection();
                collection.setId(rs.getInt("id"));
                collection.setUserId(rs.getString("user_id"));
                collection.setPostId(rs.getString("post_id"));
                collection.setDate(rs.getString("date"));
                collection.setTitle(rs.getString("post_title"));
                collectionList.add(collection);
            }
        } catch (SQLException e1) {
            System.out.println("getCollectedPost" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        return collectionList;
    }
}

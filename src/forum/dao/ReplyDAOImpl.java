package forum.dao;

import forum.beans.Reply;
import forum.util.DBGet;
import forum.util.DateTimeUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created on 2022-11-21 10:41
 *
 * @author Xia Jiayi
 */
public class ReplyDAOImpl implements ReplyDAO {
    /**
     * 发表回帖
     *
     * @param reply 回帖对象
     * @return 是否发表成功
     */
    public boolean insertReply(Reply reply) {
        boolean result = false;
        int n = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        reply.setReplyTime(DateTimeUtil.getNowStr());
        try {
            conn = DBGet.getConnection();
            String sql = "insert into reply(reply_content,reply_user,reply_time,post_id) values(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, reply.getContent());
            ps.setString(2, reply.getAuthor());
            ps.setString(3, reply.getReplyTime());
            ps.setInt(4, reply.getPostId());
            n = ps.executeUpdate();
        } catch (SQLException e1) {
            System.out.println(e1 + "commentDAOImpl");
        } finally {
            DBGet.closeConnection(conn);
        }
        if (n > 0) result = true;
        return result;
    }

    /**
     * 根据帖子id查询回帖
     *
     * @param postId 帖子id
     * @return 回帖列表
     */
    public ArrayList<Reply> getReplyByPostId(String postId) {
        Reply reply = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        ArrayList<Reply> replyList = new ArrayList<Reply>();
        try {
            conn = DBGet.getConnection();
            String sql = "select * from reply join user u on u.id = reply.reply_user where post_id = ? order by reply_time desc";
            ps = conn.prepareStatement(sql);
            ps.setString(1, postId);
            rs = ps.executeQuery();

            while (rs.next()) {
                reply = new Reply();
                reply.setId(rs.getInt("reply_id"));
                reply.setContent(rs.getString("reply_content"));
                reply.setAuthor(rs.getString("nickname"));
                reply.setLevel(rs.getString("level"));
                reply.setReplyTime(rs.getString("reply_time"));
                reply.setPostId(rs.getInt("post_id"));
                replyList.add(reply);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBGet.closeConnection(conn);
        }
        return replyList;
    }

    /**
     * 根据用户id查询回帖
     *
     * @param userId 用户id
     * @return 回帖列表
     */
    public ArrayList<Reply> getReplyByUserId(String userId) {
        Reply reply = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        ArrayList<Reply> replyList = new ArrayList<Reply>();
        try {
            conn = DBGet.getConnection();
            String sql = "select reply_id, reply_content, reply_time, post_id from reply join user u on u.id = reply.reply_user where reply_user = ? order by reply_time desc;";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                reply = new Reply();
                reply.setId(rs.getInt("reply_id"));
                reply.setContent(rs.getString("reply_content"));
                reply.setReplyTime(rs.getString("reply_time"));
                reply.setPostId(rs.getInt("post_id"));
                replyList.add(reply);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBGet.closeConnection(conn);
        }
        return replyList;
    }

    /**
     * 查询所有回帖
     *
     * @return 回帖列表
     */
    public ArrayList<Reply> getAll() {
        Reply reply = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        ArrayList<Reply> replyList = new ArrayList<Reply>();
        try {
            conn = DBGet.getConnection();
            String sql = "select reply_id, reply_content, username, reply_time, post_id\n" +
                    "from reply\n" +
                    "         join user u on reply.reply_user = u.id\n" +
                    "order by reply_time desc;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                reply = new Reply();
                reply.setId(rs.getInt("reply_id"));
                reply.setContent(rs.getString("reply_content"));
                reply.setAuthor(rs.getString("username"));
                reply.setReplyTime(rs.getString("reply_time"));
                reply.setPostId(rs.getInt("post_id"));
                replyList.add(reply);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBGet.closeConnection(conn);
        }
        return replyList;
    }

    /**
     * 根据回帖id删除回帖
     *
     * @param ReplyId 回帖id
     * @return 是否删除成功
     */
    public boolean deleteReplyById(String ReplyId) {
        boolean result = false;
        int n = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "delete from reply where reply_id= ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, ReplyId);
            n = ps.executeUpdate();
        } catch (SQLException e1) {
            System.out.println(e1 + "deleteReplyById");
        } finally {
            DBGet.closeConnection(conn);
        }
        if (n > 0) result = true;
        return result;
    }

    /**
     * 根据回帖时间获取热门回帖
     *
     * @return 回帖列表
     */
    public ArrayList<Reply> getHotReply() {
        Reply reply = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        ArrayList<Reply> replyList = new ArrayList<Reply>();
        try {
            conn = DBGet.getConnection();
            String sql = "select * from reply order by reply_time desc limit 5";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String commenttext = rs.getString("reply_content");
                //截取过长的评论
                if (commenttext.length() > 18) {
                    commenttext = commenttext.substring(0, 18) + "......";
                }
                reply = new Reply();
                reply.setId(rs.getInt("reply_id"));
                reply.setContent(commenttext);
                reply.setPostId(rs.getInt("post_id"));
                replyList.add(reply);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBGet.closeConnection(conn);
        }
        return replyList;
    }
}


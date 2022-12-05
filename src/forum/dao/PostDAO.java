package forum.dao;

import forum.beans.Post;
import forum.util.DBGet;
import forum.util.DateTimeUtil;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created on 2022-10-27 10:30
 *
 * @author Xia Jiayi
 */
public class PostDAO {

    /**
     * 查看所有新闻
     *
     * @return ArrayList<News>
     */
    public ArrayList<Post> getAllNews() {
        Post post = null;
        ArrayList<Post> postList = new ArrayList<Post>();
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = DBGet.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "select * from post";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                post = new Post();
                post.setId(rs.getInt("post_id"));
                post.setTitle(rs.getString("post_title"));
                post.setContent(rs.getString("post_content"));
                post.setAuthor(rs.getString("post_author"));
                post.setPostTime(rs.getString("post_time"));
                post.setKeyword(rs.getString("post_keyword"));
                post.setHits(rs.getString("post_hits"));
                post.setPostType(rs.getString("post_type_id"));
                postList.add(post);
            }
        } catch (SQLException e1) {
            System.out.println("getAllNews" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        return postList;
    }

    /**
     * 插入一条新闻
     *
     * @param post 新闻对象
     * @return boolean
     */
    public boolean insertNews(Post post) {

        boolean result = false;
        int n = 0;
        post.setPostTime(DateTimeUtil.getNowStr());
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "insert into post(post_title,post_content,post_author,post_time,post_keyword,post_type_id) value(?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            ps.setString(3, post.getAuthor());
            ps.setString(4, post.getPostTime());
            ps.setString(5, post.getKeyword());
            ps.setString(6, post.getPostType());
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
     * 根据ID查询指定新闻
     *
     * @param id 新闻id
     * @return News
     */
    public Post getById(String id) {
        Post post = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "select * from post where post_id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                post = new Post();
                post.setId(rs.getInt("post_id"));
                post.setTitle(rs.getString("post_title"));
                post.setContent(rs.getString("post_content"));
                post.setAuthor(rs.getString("post_author"));
                post.setPostTime(rs.getString("post_time"));
                post.setKeyword(rs.getString("post_keyword"));
                post.setHits(rs.getString("post_hits"));
                post.setPostType(rs.getString("post_type_id"));
            }
        } catch (SQLException e1) {
            System.out.println("getById" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        return post;
    }

    /**
     * 增加访问量
     *
     * @param id 新闻id
     */
    public void increaseAc(String id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "update post set post_hits = post.post_hits +1 where post_id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e1) {
            System.out.println("increaseAc" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
    }

    /**
     * 相关新闻
     *
     * @param id 新闻id
     * @return 相关新闻列表
     */
    public ArrayList<Post> getRelate(String id) {
        Post post = null;
        ArrayList<Post> postList = new ArrayList<Post>();
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = DBGet.getConnection();
            String sql = "SELECT * from post where post_type_id=(select post_type_id from post where post_id=?) and post_id != ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                post = new Post();
                post.setId(rs.getInt("post_id"));
                post.setTitle(rs.getString("post_title"));
                postList.add(post);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBGet.closeConnection(conn);
        }
        return postList;
    }

    /**
     * 修改新闻
     *
     * @param post 新闻对象
     * @return 是否成功
     */
    public boolean modifyNews(Post post) {
        boolean result = false;
        int n = 0;
        Connection conn = null;
        String sql = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            sql = "update post set post_title=?,post_content=?,post_author=?,post_time=?,post_keyword=?,post_type_id=? where post_id =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            ps.setString(3, post.getAuthor());
            ps.setString(4, post.getPostTime());
            ps.setString(5, post.getKeyword());
            ps.setString(6, post.getPostType());
            ps.setString(7, String.valueOf(post.getId()));
            n = ps.executeUpdate();
        } catch (SQLException e1) {
            System.out.println("modify" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        if (n > 0) {
            result = true;
        }
        return result;
    }

    /**
     * 删除新闻
     *
     * @param id 新闻id
     * @return 是否成功
     */
    public boolean deleteById(String id) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ReplyDAOImpl commentDAO = new ReplyDAOImpl();

        try {
            conn = DBGet.getConnection();
            String sql = "DELETE FROM post WHERE post_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            if (ps.executeUpdate() == 1) {
                //删除对应新闻的评论
                commentDAO.deleteByNewsId(id);
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBGet.closeConnection(conn);
        }
        return result;
    }

    /**
     * 热点新闻
     *
     * @return 热点新闻列表
     */
    public ArrayList<Post> getHotNews() {
        Post post = null;
        ArrayList<Post> postList = new ArrayList<Post>();
        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            conn = DBGet.getConnection();
            stmt = conn.createStatement();
            String sql = "select * from post order by post_hits desc limit 5";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                post = new Post();
                post.setId(rs.getInt("post_id"));
                String title = rs.getString("post_title");
                //截取过长的新闻标题
                if (title.length() > 18) {
                    title = title.substring(0, 18) + "......";
                }
                post.setTitle(title);
                post.setContent(rs.getString("post_content"));
                post.setAuthor(rs.getString("post_author"));
                post.setPostTime(rs.getString("post_time"));
                post.setKeyword(rs.getString("post_keyword"));
                post.setHits(rs.getString("post_hits"));
                post.setPostType(rs.getString("post_type_id"));
                postList.add(post);
            }
        } catch (SQLException e1) {
            System.out.println("getHotNews" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        return postList;
    }

    /**
     * 按关键字查询新闻
     *
     * @param keyword 关键字
     * @return 新闻列表
     */
    public ArrayList<Post> getNewsByKeyword(String keyword) {
        Post post = null;
        ArrayList<Post> postList = new ArrayList<Post>();
        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "select * from post where post_keyword = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, keyword);
            rs = ps.executeQuery();
            while (rs.next()) {
                post = new Post();
                post.setId(rs.getInt("post_id"));
                post.setTitle(rs.getString("post_title"));
                post.setContent(rs.getString("post_content"));
                post.setAuthor(rs.getString("post_author"));
                post.setPostTime(rs.getString("post_time"));
                post.setKeyword(rs.getString("post_keyword"));
                post.setHits(rs.getString("post_hits"));
                post.setPostType(rs.getString("post_type_id"));
                postList.add(post);
            }
        } catch (SQLException e1) {
            System.out.println("getNewsByKeyword" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        return postList;
    }

    /**
     * 查询新闻条数
     */
    public int getNewsCount(String postTypeId) {
        int count = 0;
        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            conn = DBGet.getConnection();
            stmt = conn.createStatement();
            String sql = "select count(*) from post where post_type_id='" + postTypeId + "'";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e1) {
            System.out.println("getNewsCount" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        return count;
    }

    /**
     * 根据起始、条数、类型查询新闻
     *
     * @param start      起始
     * @param count      条数
     * @param postTypeId 类型
     */
    public ArrayList<Post> getNewsByST(int start, int count, String postTypeId) {
        Post post = null;
        ArrayList<Post> postList = new ArrayList<Post>();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "select * from post where post_type_id=? order by post_id desc limit ?,?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, postTypeId);
            ps.setInt(2, start);
            ps.setInt(3, count);
            rs = ps.executeQuery();
            while (rs.next()) {
                post = new Post();
                post.setId(rs.getInt("post_id"));
                post.setTitle(rs.getString("post_title"));
                post.setContent(rs.getString("post_content"));
                post.setAuthor(rs.getString("post_author"));
                post.setPostTime(rs.getString("post_time"));
                post.setKeyword(rs.getString("post_keyword"));
                post.setHits(rs.getString("post_hits"));
                post.setPostType(rs.getString("post_type_id"));
                postList.add(post);
            }
        } catch (SQLException e1) {
            System.out.println("getNewsByPage" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        return postList;
    }

    /**
     * 相关新闻
     *
     * @param postTypeId 新闻id
     * @return 相关新闻列表
     */
    public ArrayList<Post> getPostByType(String postTypeId) {
        Post post = null;
        ArrayList<Post> postList = new ArrayList<Post>();
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = DBGet.getConnection();
            String sql = "SELECT * from post where post_type_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, postTypeId);
            rs = ps.executeQuery();
            while (rs.next()) {
                post = new Post();
                post.setId(rs.getInt("post_id"));
                post.setTitle(rs.getString("post_title"));
                postList.add(post);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBGet.closeConnection(conn);
        }
        return postList;
    }

}
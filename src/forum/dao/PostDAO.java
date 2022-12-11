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
     * 查看所有帖子
     *
     * @return 所有帖子列表
     */
    public ArrayList<Post> getAllPost() {
        Post post = null;
        ArrayList<Post> postList = new ArrayList<Post>();
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = DBGet.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "select post_id, post_title, post_content,username post_author, post_time, post_keyword, theme, post_hits from post join user u on u.id = post.post_author order by post_time desc ";
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
                post.setTheme(rs.getString("theme"));
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
     * 发帖
     *
     * @param post 帖子对象
     * @return 是否成功
     */
    public boolean addPost(Post post) {

        boolean result = false;
        int n = 0;
        post.setPostTime(DateTimeUtil.getNowStr());
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "insert into post value(null,?,?,?,?,?,?,0)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            ps.setString(3, post.getAuthor());
            ps.setString(4, post.getPostTime());
            ps.setString(5, post.getKeyword());
            ps.setString(6, post.getTheme());
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
     * 根据ID查询指定帖子
     *
     * @param id 帖子id
     * @return News
     */
    public Post getById(String id) {
        Post post = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "select post_id, post_title, post_content,nickname post_author, post_time, post_keyword, theme, post_hits from post join user u on u.id = post.post_author where post_id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                post = new Post();
                post.setId(rs.getInt("post_id"));
                post.setTitle(rs.getString("post_title"));
                String result = rs.getString("post_content").replaceAll("(\\r\\n|\\n|\\n\\r)", "<br/>");
                post.setContent(result);
                post.setAuthor(rs.getString("post_author"));
                post.setPostTime(rs.getString("post_time"));
                post.setKeyword(rs.getString("post_keyword"));
                post.setHits(rs.getString("post_hits"));
                post.setTheme(rs.getString("theme"));
            }
        } catch (SQLException e1) {
            System.out.println("getById" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        return post;
    }

    /**
     * 增加点击量
     *
     * @param id 帖子id
     */
    public void increaseHits(String id) {
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
     * 相关帖子
     *
     * @param id 帖子id
     * @return 相关帖子列表
     */
    public ArrayList<Post> getRelate(String id) {
        Post post = null;
        ArrayList<Post> postList = new ArrayList<Post>();
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = DBGet.getConnection();
            String sql = "SELECT * from post where theme=(select theme from post where post_id=?) and post_id != ?";
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
    public boolean modifyPost(Post post) {
        boolean result = false;
        int n = 0;
        Connection conn = null;
        String sql = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            sql = "update post set post_title=?,post_content=?,post_author=?,post_time=?,post_keyword=?,theme=? where post_id =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            ps.setString(3, post.getAuthor());
            ps.setString(4, post.getPostTime());
            ps.setString(5, post.getKeyword());
            ps.setString(6, post.getTheme());
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
     * 删除帖子
     *
     * @param id 帖子id
     * @return 是否成功
     */
    public boolean deletePostById(String id) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ReplyDAOImpl replyDAO = new ReplyDAOImpl();

        try {
            conn = DBGet.getConnection();
            String sql = "DELETE FROM post WHERE post_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            if (ps.executeUpdate() == 1) {
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
     * 热贴
     *
     * @return 热贴列表
     */
    public ArrayList<Post> getHotPost() {
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
                //截取过长的帖子标题
                if (title.length() > 18) {
                    title = title.substring(0, 18) + "......";
                }
                post.setTitle(title);
                post.setContent(rs.getString("post_content"));
                post.setAuthor(rs.getString("post_author"));
                post.setPostTime(rs.getString("post_time"));
                post.setKeyword(rs.getString("post_keyword"));
                post.setHits(rs.getString("post_hits"));
                post.setTheme(rs.getString("theme"));
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
     * 按标题、关键字、内容模糊查询帖子
     *
     * @param keyword 关键字
     * @return 帖子列表
     */
    public ArrayList<Post> getPostByKeyword(String keyword) {
        Post post = null;
        ArrayList<Post> postList = new ArrayList<Post>();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "select * from post where post_keyword like ? or post_title like ? or post_content like ?";
            ps = conn.prepareStatement(sql);
            keyword = "%" + keyword + "%";
            ps.setString(1, keyword);
            ps.setString(2, keyword);
            ps.setString(3, keyword);
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
                post.setTheme(rs.getString("theme"));
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
     * 根据版块查询帖子条数
     *
     * @param theme 版块
     * @return 该类型帖子的总数
     */
    public int getPostCountByType(String theme) {
        int count = 0;
        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            conn = DBGet.getConnection();
            stmt = conn.createStatement();
            String sql = "select count(*) from post where theme='" + theme + "'";
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
     * @param start 起始
     * @param count 条数
     * @param theme 版块
     */
    public ArrayList<Post> getPostBySCT(int start, int count, String theme) {
        Post post = null;
        ArrayList<Post> postList = new ArrayList<Post>();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "select * from post where theme=? order by post_id desc limit ?,?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, theme);
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
                post.setTheme(rs.getString("theme"));
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
     * 找最新发布的一条新闻的id
     */
    public String queryLastPost() {
        String postId = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "select * from post order by post_time desc limit 1";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                postId = rs.getString("post_id");
            }
        } catch (SQLException e1) {
            System.out.println("getNewsByPage" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        return postId;
    }

    /**
     * 根据作者找帖子
     *
     * @param userId 作者
     * @return 该用户所发表的帖子列表
     */
    public ArrayList<Post> getPostByUserId(String userId) {
        Post post = null;
        ArrayList<Post> postList = new ArrayList<Post>();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "select * from post where post_author = ? order by post_time desc";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userId);
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
                post.setTheme(rs.getString("theme"));
                postList.add(post);
            }
        } catch (SQLException e1) {
            System.out.println("getNewsByKeyword" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        return postList;
    }

}
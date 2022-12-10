package forum.dao;
import forum.beans.User;
import forum.util.DBGet;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created on 2022-10-12 13:28
 * 6.2
 *
 * @author Xia Jiayi
 */
public class UserDAO {
    public User queryByNamePwd(String uName, String password) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        User user = new User();
        try {
            conn = DBGet.getConnection();
            String sql = "select * from user where username=? and password=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, uName);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(Integer.valueOf(rs.getString("role")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBGet.closeConnection(conn);
        }
        return user;
    }

    public boolean deleteById(String id) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "DELETE FROM user WHERE id = ?";
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

    public boolean updateUserById(User user) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "UPDATE user SET username=?,password=?,gender=?,resume=? WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getGender());
            ps.setString(4, user.getResume());
            ps.setInt(5, user.getId());
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

    public boolean insertUser(User user) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "INSERT INTO user VALUES(null,1,?,?,?,?,null,1)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getGender());
            ps.setString(4, user.getResume());
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
     * 查询所有用户
     */
    public ArrayList<User> queryAll() {
        ArrayList<User> userList = new ArrayList<User>();
        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            conn = DBGet.getConnection();
            stmt = conn.createStatement();
            String sql = "select * from user";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getString("gender"));
                user.setResume(rs.getString("resume"));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("queryAll"+e);
        } finally {
            DBGet.closeConnection(conn);
        }
        return userList;
    }

    public User queryByName(String uname) {
        User user = new User();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "select * from user where username=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, uname);
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getString("gender"));
                user.setResume(rs.getString("resume"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBGet.closeConnection(conn);
        }

        return user;
    }

    /**
     * 增加发帖次数
     */
    public void increasePostTimes(String author) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "update user set post_times = post_times+1 where id = ?;";
            ps = conn.prepareStatement(sql);
            ps.setString(1, author);
            if (ps.executeUpdate() > 0) {
                level_procedure();
            }
        } catch (SQLException e1) {
            System.out.println("increaseAc" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
    }

    /**
     * 调用存储过程
     */
    public void level_procedure() {
        Connection conn = null;
        CallableStatement callableStatement = null;
        try {
            conn = DBGet.getConnection();
            String sql = "{call level_procedure()}";
            callableStatement = conn.prepareCall(sql);
            callableStatement.execute();
        } catch (SQLException e1) {
            System.out.println("level_procedure" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
    }
}


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
    public boolean queryByNamePwd(String uName, String password) {
        boolean result = false;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "select * from user where username=? and password=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, uName);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBGet.closeConnection(conn);
        }
        return result;
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

    public boolean updataUserById(User user) {
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
            String sql = "INSERT INTO user VALUES(null,?,?,?,?)";
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
}


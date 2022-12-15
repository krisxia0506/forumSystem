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
                user.setNickname(rs.getString("nickname"));
                user.setRole(Integer.valueOf(rs.getString("role")));
                user.setLevel(rs.getString("level"));
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
            String sql = "UPDATE user SET username=?,password=?,nickname=?,gender=?,resume=? WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNickname());
            ps.setString(4, user.getGender());
            ps.setString(5, user.getResume());
            ps.setInt(6, user.getId());
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
     * 注册用户
     *
     * @param user 用户对象
     * @return 是否成功
     */
    public boolean insertUser(User user) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "INSERT INTO user VALUES(null,1,?,?,?,?,?,'技术小白',1)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNickname());
            ps.setString(4, user.getGender());
            ps.setString(5, user.getResume());
            if (ps.executeUpdate() == 1) {
                result = true;
            }
        } catch (SQLException e) {
            System.out.println("注册失败");
        } finally {
            DBGet.closeConnection(conn);
        }
        return result;
    }

    /**
     * 查询所有用户
     *
     * @return 用户列表
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
            System.out.println("queryAll" + e);
        } finally {
            DBGet.closeConnection(conn);
        }
        return userList;
    }

    /**
     * 根据id查询用户
     *
     * @param id 用户id
     * @return User
     */
    public User queryByUserId(String id) {
        User user = new User();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "select * from user where id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setGender(rs.getString("gender"));
                user.setResume(rs.getString("resume"));
                user.setLevel(rs.getString("level"));
            }
        } catch (SQLException e) {
            System.out.println("根据id查询用户失败");
        } finally {
            DBGet.closeConnection(conn);
        }

        return user;
    }

    public boolean queryByUsername(String username) {
        boolean flag = false;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "select * from user where username=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            //如果有数据，说明用户名已经存在
            if (rs.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBGet.closeConnection(conn);
        }

        return flag;
    }

    /**
     * 增加发帖次数
     *
     * @param userId 用户id
     */
    public void increasePostTimes(String userId) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "update user set post_times = post_times+1 where id = ?;";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userId);
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
     * 调用存储过程，更新用户等级
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


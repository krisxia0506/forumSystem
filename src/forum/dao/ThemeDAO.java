package forum.dao;

import forum.beans.Theme;
import forum.util.DBGet;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created on 2022-10-27 23:05
 *
 * @author Xia Jiayi
 */
public class ThemeDAO {
    public ArrayList<Theme> getAllTheme() {
        Theme theme = null;
        ArrayList<Theme> themeList = new ArrayList<Theme>();
        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            conn = DBGet.getConnection();
            stmt = conn.createStatement();
            String sql = "select * from theme";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                theme = new Theme();
                theme.setId(rs.getInt("theme_id"));
                theme.setThemeTitle(rs.getString("theme_title"));
                theme.setThemeIntroduction(rs.getString("theme_introduction"));
                themeList.add(theme);
            }
        } catch (SQLException e1) {
            System.out.println("getAllPostType" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        return themeList;
    }

    public boolean addTheme(Theme theme) {

        boolean result = false;
        int n = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "insert into theme(theme_title,theme_introduction) value(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, theme.getThemeTitle());
            ps.setString(2, theme.getThemeIntroduction());
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
    public boolean deleteThemeById(String id) {
        boolean result = false;
        int n = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "delete from theme where theme_id = ?";
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
    public Theme getThemeById(String id) {
        Theme theme = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "select * from theme where theme_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                theme = new Theme();
                theme.setId(rs.getInt("theme_id"));
                theme.setThemeTitle(rs.getString("theme_title"));
                theme.setThemeIntroduction(rs.getString("theme_introduction"));
            }
        } catch (SQLException e1) {
            System.out.println("getPostTypeById" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        return theme;
    }

    /**
     * 根据id修改
     */
    public boolean modifyTheme(Theme theme) {
        boolean result = false;
        int n = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "update theme set theme_title = ?,theme_introduction = ? where theme_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, theme.getThemeTitle());
            ps.setString(2, theme.getThemeIntroduction());
            ps.setInt(3, theme.getId());
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

    /**
     * 获取热门版块
     */
    public ArrayList<Theme> getHotTheme() {
        Theme theme = null;
        ArrayList<Theme> themeList = new ArrayList<Theme>();
        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            conn = DBGet.getConnection();
            stmt = conn.createStatement();
            String sql = "select theme.theme_id, theme_title, theme.theme_introduction\n" +
                    "from theme\n" +
                    "         join (select theme, sum(post_hits) as hits\n" +
                    "               from post\n" +
                    "               group by theme) as post_count\n" +
                    "              on theme.theme_id = post_count.theme\n" +
                    "order by hits desc\n" +
                    "limit 6;";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                theme = new Theme();
                theme.setId(rs.getInt("theme_id"));
                theme.setThemeTitle(rs.getString("theme_title"));
                theme.setThemeIntroduction(rs.getString("theme_introduction"));
                themeList.add(theme);
            }
        } catch (SQLException e1) {
            System.out.println("getHotPostType" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        return themeList;
    }
}

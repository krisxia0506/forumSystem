/*
 * 6.1
 * */

package forum.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBGet {
    static String sDBDriver = "com.mysql.cj.jdbc.Driver";
    static String sConnStr = "jdbc:mysql://localhost:3306/forumdb?useUnicode=true&characterEncoding=utf-8";
    static String username = "root";
    static String password = "mysql";

    //Connection1
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(sDBDriver);
            conn = DriverManager.getConnection(sConnStr, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    //Close
    public static void closeConnection(Connection conn) {
        try {

            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();


        }
    }
}
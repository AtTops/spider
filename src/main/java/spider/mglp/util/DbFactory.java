package spider.mglp.util;

import java.sql.*;

/**
 * @author 王海
 * @version V1.0
 * @Description 数据库操作的一些封装
 */
public class DbFactory {
    //    private static String USER = "root";
//    private static String PASSWORD = "abc..123";
//    private static String DB_URL = "jdbc:mysql://localhost:3306/items_url";
    private static Connection conn = null;
    // 获取连接
    public static Connection getConnection() {
        try {
            // 仅有的一个XmlConfigReader的实例（instance）调用getJdbcConfig
            JdbcConfig jdbcConfig = XmlConfigReader.getInstance().getJdbcConfig();
            Class.forName(jdbcConfig.getDriverName());
            //System.out.println(jdbcConfig.toString());
            conn = DriverManager.getConnection(jdbcConfig.getUrlValue(),jdbcConfig.getUserNameValue(), jdbcConfig.getPasswordValue());
            //conn = DriverManager.getConnection(DB_URL,USER, PASSWORD);
            System.out.println("数据库连接成功！");
        } catch (Exception e) {
            System.out.println("数据库连接异常！！！");
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close(); // 关闭数据库连接
                System.out.println("数据库连接已断开！！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 获得Statement对象
    public static Statement getStmt(Connection conn) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }

    public static void closeStmt(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 获得PreparedStatement对象
    public static PreparedStatement getPreStmt(Connection conn,String sql) {
        PreparedStatement preStmt = null;
        try {
            preStmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preStmt;
    }
    public static void closePreStmt(PreparedStatement preStmt) {
        try {
            if (preStmt != null) {
                preStmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*public static ResultSet executeQuery(Statement stmt, String sql) {
        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }*/
    public static void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DbFactory.getConnection();
        DbFactory.closeConnection(conn);
    }
}

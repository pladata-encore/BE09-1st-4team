package com.starfoxKiosk.common;

import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate {

    public static Connection getConnection() {
        Properties props = new Properties();
        Connection con = null;
        try {
            props.load(new FileReader("src/main/java/com/starfoxKiosk/config/jdbc-info.properties"));
            String url = props.getProperty("url");
            con = DriverManager.getConnection(url, props);
            con.setAutoCommit(false);  // 수동 커밋 모드
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public static void close(Connection con) {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Statement stmt) {
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void commit(Connection con) {
        try {
            if (con != null && !con.isClosed()) con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void rollback(Connection con) {
        try {
            if (con != null && !con.isClosed()) con.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

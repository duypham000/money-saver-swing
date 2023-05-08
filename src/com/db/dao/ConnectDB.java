package com.db.dao;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author khanh
 */
public class ConnectDB {

    private String DB_URL = "jdbc:mysql://localhost:3306/moneysaver";
    private String USER_NAME = "root";
    private String PASSWORD = "";

    private Connection conn = null;

    public ConnectDB() {
        conn = getConnection();
    }

    public ResultSet query(String q) {
        if (conn == null) {
            return null;
        }
        try {
            PreparedStatement st = conn
                    .prepareStatement(q);
            ResultSet rs = st.executeQuery();
            return rs;
        } catch (Exception e) {
        }
        return null;
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = (Connection) DriverManager
                    .getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
}

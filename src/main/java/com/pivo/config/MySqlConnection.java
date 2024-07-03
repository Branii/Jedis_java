package com.pivo.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

    public static Connection getConnection() throws SQLException {

        String jdbcUrl = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "";
        Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
        return conn;

    }
    
}

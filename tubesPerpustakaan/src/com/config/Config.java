package com.config;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Config {
    
    // private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db_perpuspbo";
    private static final String user = "root";
    private static final String pass = "";

    public static Connection getConnection() throws SQLException{
        Connection conn = null;
        conn = DriverManager.getConnection(url, user, pass);
        if(conn==null){
            throw new SQLException("Tidak terkoneksi dengan database.");
        }
        return conn;
    }

}

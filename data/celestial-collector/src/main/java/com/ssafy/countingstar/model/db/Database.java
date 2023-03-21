package com.ssafy.countingstar.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.ssafy.countingstar.resource.DatabaseSecret;

public class Database {
    private static Database instance;
    private Connection connection;

    private Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/COUNTINGSTAR",DatabaseSecret.host, DatabaseSecret.port), DatabaseSecret.username, DatabaseSecret.password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
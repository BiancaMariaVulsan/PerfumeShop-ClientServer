package com.example.productservice.model.persistence;

import java.sql.*;
import java.util.Properties;

public class ConnectionFactory {
    private static final String url = "jdbc:postgresql://localhost:5432/parfume_shop";
    private static final ConnectionFactory singleInstance = new ConnectionFactory();

    private Connection connect() {
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "Vulsan2001");
        try {
            Connection conn = DriverManager.getConnection(url, props);
            System.out.println("Connected to database");
            return conn;
        } catch (SQLException e) {
            System.out.println("Error: database connection failed");
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getConnection(){
        return singleInstance.connect();
    }

    public static void close(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Closing database connection" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void close(Statement statement){
        try {
            statement.close();
        } catch (SQLException e) {
            System.out.println("Closing database connection"  + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void close(ResultSet result){
        try {
            result.close();
        } catch (SQLException e) {
            System.out.println("Closing database connection" + e.getMessage());
            e.printStackTrace();
        }
    }
}

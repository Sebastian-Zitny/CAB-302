package com.example.jth_mathgame.mathgame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnection {
    private static SqliteConnection instance = null;
    private Connection connection;
    private final String url = "jdbc:sqlite:users.db";

    private SqliteConnection() throws SQLException {
        connection = DriverManager.getConnection(url);
    }

    public static SqliteConnection getInstance() throws SQLException{
        if (instance == null) {
            instance = new SqliteConnection();
        }
        return instance;
    }

    public Connection openConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Cannot close database connection");
        } finally {
            instance = null;
        }
    }
}

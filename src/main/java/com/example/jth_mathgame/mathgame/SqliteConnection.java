package com.example.jth_mathgame.mathgame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Represents a connection to the database file
 */
public class SqliteConnection {
    private static SqliteConnection instance = null;
    private Connection connection;
    private final String url = "jdbc:sqlite:users.db";

    private SqliteConnection() throws SQLException {
        connection = DriverManager.getConnection(url);
    }

    /**
     * A instance of the connection
     * @return A instance of the connection
     */
    public static SqliteConnection getInstance() throws SQLException{
        if (instance == null) {
            instance = new SqliteConnection();
        }
        return instance;
    }

    /**
     * @return Opens the connection to the database
     */
    public Connection openConnection() {
        return connection;
    }

    /**
     * Closes the connection to the database
     */
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

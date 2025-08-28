package com.example.jth_mathgame.mathgame;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {

    private Connection connection;

    /**
     * An interface that interacts with the database
     */
    public UserDAO() {
        connection = SqliteConnection.getInstance();
        createTables();
    }

    private void createTables() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS users ("
                    + "UserID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,"
                    + "Username TEXT NOT NULL UNIQUE,"
                    + "Password TEXT NOT NULL,"
                    + "Email TEXT"
                    + "Phone TEXT,"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    INSERT INTO users
                    (Username, Password, Email, Phone) VALUES (?, ?, ?, ?)""");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhone());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(String username) {
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM users
                    WHERE username IS (?)""");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet == null) return null; // Can't find user
            return new User(
                    resultSet.getInt("UserID"),
                    resultSet.getString("Username"),
                    resultSet.getString("Password"),
                    resultSet.getString("Email"),
                    resultSet.getString("Phone"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void authUser(String username, String password) {
        //TODO
    }

    @Override
    public List<User> getAllUsers() {
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM users""");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet == null) return null; // User table is empty

            List<User> userList = new ArrayList<>();

            while (resultSet.next()) { // Iterate through rows and add to list
                userList.add(new User(resultSet.getInt("UserID"),
                        resultSet.getString("Username"),
                        resultSet.getString("Password"),
                        resultSet.getString("Email"),
                        resultSet.getString("Phone")));
            }

            return userList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
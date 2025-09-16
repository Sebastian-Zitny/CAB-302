package com.example.jth_mathgame.mathgame;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {

    private final Connection connection;

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
                    + "Email TEXT,"
                    + "Phone TEXT,"
                    + "Score INTEGER"
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
                    (Username, Password, Email, Phone, Score) VALUES (?, ?, ?, ?, ?)""");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhone());
            statement.setInt(5,user.getScore());
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
                    resultSet.getString("Phone"),
                    resultSet.getInt("Score"));

        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getScore(int Id){
        try{

            PreparedStatement statement = connection.prepareStatement("""
                    SELECT Score
                    FROM users
                    WHERE UserID IS (?)
                    """);
            statement.setInt(1,Id);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.getInt("Score");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setScore(int Id, int score){
        try{
            PreparedStatement statement = connection.prepareStatement("""
                    UPDATE users
                    SET Score = ?
                    WHERE UserID IS (?)
                    """);
            statement.setInt(1,score);
            statement.setInt(2,Id);
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void authUser(String username, String password) {
        //TODO: Perhaps this could be used instead to authenticate?
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
                        resultSet.getString("Phone"),
                        resultSet.getInt("Score")));
            }

            return userList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
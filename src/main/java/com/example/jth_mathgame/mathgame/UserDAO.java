package com.example.jth_mathgame.mathgame;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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
                    + "PasswordHash TEXT NOT NULL,"
                    + "PasswordSalt TEXT NOT NULL,"
                    + "Email TEXT,"
                    + "Phone TEXT"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(User user) {
        // Get Hash and salt for password
        String[] passArray;
        try {
            passArray = generateHash(user.getPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    INSERT INTO users
                    (Username, PasswordHash, PasswordSalt, Email, Phone) VALUES (?, ?, ?, ?, ?)""");
            statement.setString(1, user.getUsername());
            statement.setString(2, passArray[0]);
            statement.setString(3, passArray[1]);
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPhone());
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
            if (resultSet == null) return null; // TODO: Throw exception here instead
            return new User(
                    resultSet.getInt("UserID"),
                    resultSet.getString("Username"),
                    resultSet.getString("Email"),
                    resultSet.getString("Phone"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param username Username in database
     * @return String array with PasswordHash and PasswordSalt
     */
    @Override
    public String[] getUserPasswordArray(String username) {
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    SELECT PasswordHash, PasswordSalt
                    FROM users
                    WHERE Username = ?""");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet == null) return null; // TODO: Throw exception here instead

            resultSet.first(); // Go back to start of row
            return new String[] {
                    resultSet.getString("PasswordHash"),
                    resultSet.getString("PasswordSalt")
            };
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String[] generateHash(String password) throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        // TODO: Delete below lines
        System.out.println(Arrays.toString(hashedPassword));
        System.out.println(Arrays.toString(salt));
        //
        return new String[] {Arrays.toString(hashedPassword), Arrays.toString(salt)};
    }

    public Boolean verifyHash(String password, String passwordChallengeString, String saltString)
    {
        // Convert Strings to char[]
        byte[] passwordChallenge = passwordChallengeString.getBytes(StandardCharsets.UTF_8);
        byte[] salt = saltString.getBytes(StandardCharsets.UTF_8);

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512"); // Need to handle this exception
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        md.update(salt);

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        return hashedPassword.equals(passwordChallenge);
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
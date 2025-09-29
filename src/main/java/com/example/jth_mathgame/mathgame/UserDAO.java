package com.example.jth_mathgame.mathgame;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.jth_mathgame.mathgame.MainApplication.currentUserId;

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
                    + "PasswordHash BLOB NOT NULL,"
                    + "PasswordSalt BLOB NOT NULL,"
                    + "Email TEXT UNIQUE,"
                    + "Phone TEXT"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(User user) throws DuplicateUserException {
        // Get Hash and salt for password
        ArrayList<byte[]> passArrayList;
        try {
            passArrayList = generateHash(user.getPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    INSERT INTO users
                    (Username, Password, Email, Phone, Score) VALUES (?, ?, ?, ?, ?)""");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhone());
            statement.setInt(5,user.getScore());
            statement.setString(1, user.getUsername());
            statement.setBytes(2, passArrayList.get(0));
            statement.setBytes(3, passArrayList.get(1));
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPhone());
            statement.execute();
        } catch (SQLException e) {
            // Duplicate username/email
            if (e.getMessage().contains("UNIQUE constraint failed")) {
                throw new DuplicateUserException();
            }
            // Something else is wrong
            e.printStackTrace();
        }
    }

    @Override
    // TODO: Perhaps below two methods can be merged
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
    /** Gets user data from database using the user id
     * @param id Id number of user
     * @return User
     */
    public User getUserFromId(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM users
                    WHERE UserID IS (?)""");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet == null) {
                return null; // TODO: Throw exception here instead
            }
            else{
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
    public ArrayList<byte[]> getUserPasswordArray(String username) {
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    SELECT PasswordHash, PasswordSalt
                    FROM users
                    WHERE Username = ?""");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                ArrayList<byte[]> returnList = new ArrayList<>();
                returnList.add(resultSet.getBytes("PasswordHash"));
                returnList.add(resultSet.getBytes("PasswordSalt"));
                return returnList;
            }
            throw new SQLException();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Something is wrong with getting the user password");
    }

    private ArrayList<byte[]> generateHash(String password) throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        ArrayList<byte[]> returnList = new ArrayList<>();
        returnList.add(hashedPassword);
        returnList.add(salt);
        return returnList;
    }

    public Boolean verifyHash(String password, byte[] passwordChallenge, byte[] salt)
    {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512"); // Need to handle this exception
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        md.update(salt);

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        return Arrays.equals(hashedPassword, passwordChallenge);
    }

    /** Sets a new password in the database for the user
     * @param password New plain-text password to be set in the database
     */
    public void setPassword(int id, String password) {
        // Get new password
        ArrayList<byte[]> passArrayList;
        try {
            passArrayList = generateHash(password);
            password = null; // Deletes string
        } catch (NoSuchAlgorithmException e) {
            System.err.println("There was a problem setting new password");
            throw new RuntimeException(e);
        }

        // Set new password
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    UPDATE users
                    SET PasswordHash = (?), PasswordSalt = (?)
                    WHERE UserID = (?)""");
            statement.setBytes(1, passArrayList.get(0));
            statement.setBytes(2, passArrayList.get(1));
            statement.setInt(3, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("There was a problem with changing user password");
        }
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

    /**
     * Deletes user account
     */
    public void deleteAccount() {
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    DELETE FROM users WHERE UserID = ?""");
            statement.setInt(1, currentUserId);
            statement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.err.println("There was a problem with deleting user account");
        }
    }
}
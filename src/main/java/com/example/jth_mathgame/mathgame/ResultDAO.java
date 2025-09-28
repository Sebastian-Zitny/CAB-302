package com.example.jth_mathgame.mathgame;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultDAO implements IResultDAO {
    private final Connection connection;

    public ResultDAO() {
        connection = SqliteConnection.getInstance();
        createTables();
    }

    private void createTables() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS results ("
                    + "ResultID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,"
                    + "UserID INTEGER NOT NULL,"
                    + "Correct INTEGER NOT NULL,"
                    + "Incorrect INTEGER NOT NULL,"
                    + "Gamemode TEXT NOT NULL,"
                    + "Date TEXT NOT NULL,"
                    + "FOREIGN KEY(UserID) REFERENCES users(userID)"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            System.err.println("Problem creating result table");
            e.printStackTrace();
        }
    }
    /**
     * @param result
     * Adds a result record and stores it in the database
     */
    @Override
    public void addResult(Result result) {
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    INSERT INTO results
                    (UserID, Correct, Incorrect, Gamemode, Date) VALUES (?, ?, ?, ?, ?)""");
            statement.setInt(1, result.userId());
            statement.setInt(2, result.numCorrect());
            statement.setInt(3, result.numIncorrect());
            statement.setString(4, result.gamemode());
            statement.setString(5, result.date());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Problem creating new result record");
        }
    }

    /**
     * @param userId
     * Removes all records associated with a user
     */
    @Override
    public void removeAllUserResults(int userId) {
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    DELETE FROM results WHERE UserID = ?""");
            statement.setInt(1, userId);
            statement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.err.println("There was a problem with deleting user results");
        }
    }

    /**
     * @param userId
     * @return A list with all result records associated with a user
     *
     */
    @Override
    public List<Result> getUserResults(int userId){
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM results
                    WHERE UserID = ?""");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet == null) return null; // Result table is empty

            List<Result> resultList = new ArrayList<>();

            while (resultSet.next()) { // Iterate through rows and add to list
                resultList.add(new Result(resultSet.getInt("UserID"),
                        resultSet.getInt("Correct"),
                        resultSet.getInt("Incorrect"),
                        resultSet.getString("Gamemode"),
                        resultSet.getString("Date")));
            }
            return resultList;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Problem getting user results");
            return null;
        }
    }

    /**
     * @return A list of all results
     */
    @Override
    public List<Result> getAllResults() {
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM results""");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet == null) return null; // Result table is empty

            List<Result> resultList = new ArrayList<>();

            while (resultSet.next()) { // Iterate through rows and add to list
                resultList.add(new Result(resultSet.getInt("UserID"),
                        resultSet.getInt("Correct"),
                        resultSet.getInt("Incorrect"),
                        resultSet.getString("Gamemode"),
                        resultSet.getString("Date")));
            }
            return resultList;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Problem getting all user results");
            return null;
        }
    }
}

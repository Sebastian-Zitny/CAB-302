package com.example.jth_mathgame.mathgame;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultDAO implements IResultDAO {

    protected ResultDAO() {
        createTables();
    }

    private static class ResultDAOHolder {
        private final static ResultDAO INSTANCE = new ResultDAO();
    }

    public static ResultDAO getInstance() {
        return ResultDAOHolder.INSTANCE;
    }

    private void createTables() {
        // Create table if not exists
        SqliteConnection database = null;
        try {
            database = SqliteConnection.getInstance();
            Connection connection = database.openConnection();
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
        } finally {
            if (database != null) database.closeConnection();
        }
    }
    
    /**
     * @param result
     * Adds a result record and stores it in the database
     */
    @Override
    public void addResult(Result result) {
        SqliteConnection database = null;
        try {
            database = SqliteConnection.getInstance();
            Connection connection = database.openConnection();
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
        } finally {
            if (database != null) database.closeConnection();
        }
    }

    /**
     * Removes all records associated with a user
     * @param userId User Id in database
     */
    @Override
    public void removeAllUserResults(int userId) {
        SqliteConnection database = null;
        try {
            database = SqliteConnection.getInstance();
            Connection connection = database.openConnection();
            PreparedStatement statement = connection.prepareStatement("""
                    DELETE FROM results WHERE UserID = ?""");
            statement.setInt(1, userId);
            statement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.err.println("There was a problem with deleting user results");
        } finally {
            if (database != null) database.closeConnection();
        }
    }

    /**
     * @param userId User Id in database
     * @return A list with all result records associated with a user
     *
     */
    @Override
    public List<Result> getUserResults(int userId){
        SqliteConnection database = null;
        try {
            database = SqliteConnection.getInstance();
            Connection connection = database.openConnection();
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
        } finally {
            if (database != null) database.closeConnection();
        }
    }

    /**
     * @return A list of all results
     */
    @Override
    public List<Result> getAllResults() {
        SqliteConnection database = null;
        try {
            database = SqliteConnection.getInstance();
            Connection connection = database.openConnection();
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
        } finally {
            if (database != null) database.closeConnection();
        }
    }
}

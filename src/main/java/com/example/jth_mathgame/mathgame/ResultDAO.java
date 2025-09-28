package com.example.jth_mathgame.mathgame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
            System.err.println("Problem creating new result record");
            e.printStackTrace();
        }
    }

    /**
     * @param userId
     * Removes all records associated with a user
     */
    @Override
    public void removeAllUserResults(int userId) {

    }

    /**
     * @param userId
     * @return A list with all result records associated with a user
     *
     */
    @Override
    public List<Result> getUserResults(int userId) {
        return List.of();
    }

    /**
     * @return A list of all results
     */
    @Override
    public List<Result> getAllResults() {
        return List.of();
    }
}

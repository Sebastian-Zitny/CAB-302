package com.example.jth_mathgame.mathgame;

import java.util.Date;

/**
 * Result of game
 * @param userId User id of the player
 * @param numCorrect Number of questions answered correctly
 * @param numIncorrect Number of questions answered incorrectly
 * @param gamemode The gamemode the result came from
 * @param date The date the result was created
 */
public record Result(
        int userId,
        int numCorrect,
        int numIncorrect,
        String gamemode,
        String date
)
{
    public Result(int userId, int numCorrect, int numIncorrect, String gamemode) {
        this(userId, numCorrect, numIncorrect, gamemode, new Date().toString());
    }

    @Override
    public String toString() {
        return "UserId: " + userId + ", "
        + "Correct answers: " + numCorrect + ", "
        + "Incorrect answers: " + numIncorrect + ", "
        + "Gamemode: " + gamemode + ", "
        + "Date: " + date;
    }
}

package com.example.jth_mathgame.mathgame;

public class Game {
    /**
     * Inputs result into database
     * @param correct Correct answers
     * @param incorrect Incorrect answers
     */
    public static void inputResult(int correct, int incorrect) {
        MainApplication.resultdao.addResult(new Result(MainApplication.currentUserId, correct, incorrect, "Timed"));
    }
}

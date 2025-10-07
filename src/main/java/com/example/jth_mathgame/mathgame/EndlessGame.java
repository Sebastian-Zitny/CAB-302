package com.example.jth_mathgame.mathgame;

public class EndlessGame {
    /**
     * Inputs result into database
     * @param correct Correct answers
     * @param incorrect Incorrect answers
     */
    public static void inputEndlessResult(int correct, int incorrect) {
        MainApplication.resultdao.addResult(new Result(MainApplication.currentUserId, correct, incorrect, "Endless"));
    }
}

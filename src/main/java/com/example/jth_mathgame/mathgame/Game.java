package com.example.jth_mathgame.mathgame;

import static com.example.jth_mathgame.mathgame.MainApplication.currentUserId;

public class Game {
    /**
     * Inputs result into database
     *
     * @param correct   Correct answers
     * @param incorrect Incorrect answers
     */
    public static void inputResult(int correct, int incorrect) {
        ResultDAO resultdao = ResultDAO.getInstance();
        resultdao.addResult(new Result(currentUserId, correct, incorrect, "Timed"));
    }
}
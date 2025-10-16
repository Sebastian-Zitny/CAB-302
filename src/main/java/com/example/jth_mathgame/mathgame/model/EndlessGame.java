package com.example.jth_mathgame.mathgame.model;

import com.example.jth_mathgame.mathgame.MainApplication;
import com.example.jth_mathgame.mathgame.Result;
import com.example.jth_mathgame.mathgame.ResultDAO;

public class EndlessGame {
    /**
     * Inputs result into database
     * @param correct Correct answers
     * @param incorrect Incorrect answers
     */
    public static void inputEndlessResult(int correct, int incorrect) {
        ResultDAO resultdao = ResultDAO.getInstance();
        resultdao.addResult(new Result(MainApplication.currentUserId, correct, incorrect, "Endless"));
    }
}

package com.example.jth_mathgame.mathgame;

import java.util.Date;

public record Result(
        int userId,
        int numCorrect,
        int numIncorrect,
        String gamemode,
        Date date
)
{
    public Result(int userId, int numCorrect, int numIncorrect, String gamemode) {
        this(userId, numCorrect, numIncorrect, gamemode, new Date());
    }
}

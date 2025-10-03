package com.example.jth_mathgame.mathgame;

import java.util.List;

public interface IResultDAO {
    void addResult(Result result);
    void removeAllUserResults(int userId);
    List<Result> getUserResults(int userId);
    List<Result> getAllResults();
    List<Score> getScores();
}

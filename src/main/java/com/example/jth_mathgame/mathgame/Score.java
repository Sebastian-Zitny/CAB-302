package com.example.jth_mathgame.mathgame;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Score {
    private StringProperty usernameProperty;

    public StringProperty getUsernameProperty() {
        return usernameProperty;
    }
    private IntegerProperty scoreProperty;

    public IntegerProperty getScoreProperty() {
        return scoreProperty;
    }
    private IntegerProperty correctAnswersProperty;
    public IntegerProperty getCorrectAnswersProperty() {
        return correctAnswersProperty;
    }
    private IntegerProperty incorrectAnswersProperty;
    public IntegerProperty getIncorrectAnswersProperty() {
        return incorrectAnswersProperty;
    }

    public Score(String username, int correctAnswers, int incorrectAnswers){
        this.usernameProperty = new SimpleStringProperty(username);
        this.scoreProperty = new SimpleIntegerProperty(correctAnswers - incorrectAnswers);
        this.correctAnswersProperty = new SimpleIntegerProperty(correctAnswers);
        this.incorrectAnswersProperty = new SimpleIntegerProperty(incorrectAnswers);
    }

}

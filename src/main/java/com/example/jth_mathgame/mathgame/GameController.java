package com.example.jth_mathgame.mathgame;

import com.example.jth_mathgame.mathgame.engine.GameEngine;
import com.example.jth_mathgame.mathgame.model.Equation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.temporal.ChronoUnit;

public class GameController {

    @FXML private Label levelLabel;           // "Level: X"
    @FXML private Label timeLabel;            // "Time: mm:ss"
    @FXML private Label questionLabel;        // "a × b = ?"
    @FXML private TextField inputField;       // answer box
    @FXML private Label answerResponseLabel;  // feedback line
    @FXML private Button submitButton;
    @FXML private Button quitButton;

    private final GameEngine engine = new GameEngine();
    private Timeline timer;
    private Equation current;

    @FXML
    public void initialize() {
        startRound(3); // 3-minute timed run
    }

    private void startRound(int minutes){
        stopTimerIfAny();
        engine.startRound(java.time.Duration.of(minutes, ChronoUnit.MINUTES));
        updateHud();
        nextQuestion();
        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            engine.tickOneSecond();
            updateTimeLabel();
            if (engine.timeUp()){
                stopTimerIfAny();
                endRound();
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.playFromStart();
    }

    private void stopTimerIfAny(){
        if (timer != null) { timer.stop(); timer = null; }
    }

    private void nextQuestion(){
        current = engine.nextEquation();
        questionLabel.setText(current.prompt());
        inputField.clear();
        inputField.requestFocus();
    }

    private void updateHud(){
        levelLabel.setText("Level: " + engine.getLevel());
        updateTimeLabel();
    }

    private void updateTimeLabel(){
        int s = engine.getSecondsLeft();
        timeLabel.setText(String.format("Time: %02d:%02d", s/60, s%60));
    }

    @FXML
    protected void onSubmitButtonClick() {
        if (current == null) return;
        String raw = inputField.getText().trim();
        if (raw.isEmpty()) return;

        int val;
        try { val = Integer.parseInt(raw); }
        catch (NumberFormatException ex){
            answerResponseLabel.setTextFill(Color.ORANGE);
            answerResponseLabel.setText("Please enter a whole number.");
            return;
        }

        boolean ok = engine.submit(current, val);
        if (ok){
            answerResponseLabel.setTextFill(Color.GREEN);
            answerResponseLabel.setText("Correct! +10  •  Mods: " + engine.getEarnedModifiers());
        } else {
            answerResponseLabel.setTextFill(Color.RED);
            answerResponseLabel.setText("Incorrect. Answer was " + current.answer());
        }
        levelLabel.setText("Level: " + engine.getLevel());

        if (!engine.timeUp()) nextQuestion();
        else endRound();
    }

    private void endRound(){
        submitButton.setDisable(true);
        questionLabel.setText("Time! Final score: " + engine.getScore());
        answerResponseLabel.setTextFill(Color.WHITE);
        answerResponseLabel.setText("Modifiers earned: " + engine.getEarnedModifiers());
    }

    @FXML
    protected void onQuitButtonClick() throws IOException {
        stopTimerIfAny();
        Stage stage = (Stage) quitButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }
}
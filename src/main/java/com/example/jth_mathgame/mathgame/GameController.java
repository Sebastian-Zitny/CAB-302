package com.example.jth_mathgame.mathgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class GameController {
    @FXML
    private Button quitButton;
    @FXML
    private Label levelLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private int Time = 30;
    @FXML
    private Label questionLabel;
    @FXML
    private TextField inputField;
    @FXML
    private Label answerResponseLabel;
    @FXML
    private Button submitButton;
    @FXML
    private VBox submitVBox;
    @FXML
    private Timeline timeline;
    @FXML
    private int fixedTime1;
    @FXML
    private int fixedTime2;
    @FXML
    private Random randomGenerator;
    @FXML
    public int score = 1;


    @FXML
    public void initialize() {
        // TODO: Maybe convert FXML padding to code
        // Defines some fonts
        Font smallFont = new Font("Arial", 20);
        Font smallFontBold = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20);

        levelLabel.setText("Level:" + score);

        randomGenerator = new Random();

        timeLabel.setText(String.valueOf(Time));
        timeLabel.setStyle("-fx-font-size: 40px;");

        quitButton.setFont(smallFont);

        levelLabel.setFont(new Font("Arial", 26));

        timeLabel.setFont(smallFont);

        questionLabel.setFont(new Font("Arial", 58));

        inputField.setFont(new Font("Arial", 29));

        answerResponseLabel.setFont(smallFontBold);
        answerResponseLabel.setPadding(new Insets(20));

        quitButton.setFont(smallFont);
        submitButton.setFont(smallFont);
        submitVBox.setAlignment(Pos.CENTER);
        mockQuestion();
        startTimer();
    }
    // TODO give error message and disable gameplay rather than force back to menu
    private void startTimer() {
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    Time--;
                    if (Time >= 0) {
                        timeLabel.setText(String.valueOf(Time));
                    } else {
                        // timer finished
                        timeline.stop();
                        try {
                            Stage stage = (Stage) timeLabel.getScene().getWindow();
                            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu-view.fxml"));
                            Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
                            stage.setScene(scene);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                })
        );

        timeline.setCycleCount(Time + 1);
        timeline.play();
    }
    // TODO: Delete when no longer needed
    // Creates a hardcoded mock question
    private void mockQuestion() {

        fixedTime1 = randomGenerator.nextInt(11);
        String stringTime1 = String.valueOf(fixedTime1);
        fixedTime2 = randomGenerator.nextInt(11);
        String stringTime2 = String.valueOf(fixedTime2);
        questionLabel.setText(stringTime1 + " + " + stringTime2 + " * " + score);


    }


    // Go back to menu when quit button clicked
    @FXML
    protected void onQuitButtonClick() throws IOException {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }

    //TODO: Compare user input against answer when clicked
    @FXML
    protected void onSubmitButtonClick() {

        int equalsInt = Integer.parseInt(inputField.getText());

        if (equalsInt == (fixedTime1+fixedTime2) * score) {
            answerResponseLabel.setText("Correct!");
            answerResponseLabel.setTextFill(Color.GREEN);

            score ++;
            mockQuestion();
            levelLabel.setText("Level:" + score);


            // levelLabel
        }
        else {
            answerResponseLabel.setText("Incorrect!");
            answerResponseLabel.setTextFill(Color.RED);
            if (score >= 2) {
                score--;
                mockQuestion();
                levelLabel.setText("Level:" + score);
            }
            else {
                mockQuestion();
                levelLabel.setText("Level:" + score);
            }

        }
        //answerCorrect(Objects.equals(inputField.getText(), "19")); //TODO: Delete when no longer needed

    }
}

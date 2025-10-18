package com.example.jth_mathgame.mathgame.controller;

import com.example.jth_mathgame.mathgame.model.Game;
import com.example.jth_mathgame.mathgame.MainApplication;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
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
    private int int1;
    @FXML
    private int int2;
    @FXML
    private Random randomGenerator;
    @FXML
    public int score = 1;


    // For input to database
    public int databaseCorrect = 0;
    public int databaseIncorrect = 0;



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
        GenerateQuestion();
        startTimer();
    }
    // TODO give error message and disable gameplay rather than force back to menu
    private void startTimer() {
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    Time--;


                    if (timeLabel == null || timeLabel.getScene() == null) {
                        if (timeline != null) {
                            timeline.stop();
                        }
                        return;
                    }

                    if (Time >= 0) {
                        timeLabel.setText(String.valueOf(Time));
                    } else {
                        // timer finished
                       Game.inputResult(databaseCorrect, databaseIncorrect);
                        timeline.stop();
                        try {
                            Stage stage = (Stage) timeLabel.getScene().getWindow();
                            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu-view.fxml"));
                            Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
                            stage.setScene(scene);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        Platform.runLater(() -> {
                            try {
                                Stage stage = (Stage) quitButton.getScene().getWindow();
                                if (stage == null) {
                                    return;
                                }
                                FXMLLoader fxmlLoader = new FXMLLoader(
                                        MainApplication.class.getResource("menu-view.fxml")
                                );
                                Scene scene = new Scene(
                                        fxmlLoader.load(),
                                        MainApplication.WIDTH,
                                        MainApplication.HEIGHT
                                );
                                stage.setScene(scene);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });

                    }
                })
        );

        timeline.setCycleCount(Timeline.INDEFINITE); 
        timeline.play();
    }
    private void GenerateQuestion() {
        int1 = randomGenerator.nextInt(11);
        String stringInt1 = String.valueOf(int1);
        int2 = randomGenerator.nextInt(11);
        String stringInt2 = String.valueOf(int2);
        questionLabel.setText(stringInt1 + " + " + stringInt2 + " * " + score);
    }

    // Go back to menu when quit button clicked
    @FXML
    protected void onQuitButtonClick() throws IOException {
        Game.inputResult(databaseCorrect, databaseIncorrect);
        Stage stage = (Stage) quitButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }


    //TODO: Compare user input against answer when clicked
    @FXML
    public void onSubmitButtonClick() {

        int equalsInt = Integer.parseInt(inputField.getText());

        if (equalsInt == (int1+int2) * score) {
            answerResponseLabel.setText("Correct!");
            answerResponseLabel.setTextFill(Color.GREEN);

            databaseCorrect++;
            score++;
            GenerateQuestion();
            levelLabel.setText("Level:" + score);
        }
        else {

            if (score > 1) {
                answerResponseLabel.setText("Incorrect!");
                answerResponseLabel.setTextFill(Color.RED);
                databaseIncorrect++;
                score--;
                GenerateQuestion();
                levelLabel.setText("Level:" + score);
            }
            else {
                answerResponseLabel.setText("Incorrect!");
                answerResponseLabel.setTextFill(Color.RED);
                databaseIncorrect++;
                GenerateQuestion();
                levelLabel.setText("Level:" + score);
            }



        }

    }
}

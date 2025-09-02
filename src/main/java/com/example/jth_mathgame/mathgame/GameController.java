package com.example.jth_mathgame.mathgame;

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

import java.io.IOException;
import java.util.Objects;

public class GameController {
    @FXML
    private Button quitButton;
    @FXML
    private Label levelLabel;
    @FXML
    private Label timeLabel;
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
    public void initialize() {
        musicPlayer.playMusic("src/sounds/music0.wav");
        // TODO: Maybe convert FXML padding to code
        Font smallFont = new Font("Arial", 20);
        Font smallFontBold = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20);

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
    }

    // TODO: Delete when no longer needed
    private void mockQuestion() {
        questionLabel.setText("9 + 10");
    }

    // TODO: Implement method
    private void answerCorrect(boolean answer){
        if (answer) {
            answerResponseLabel.setText("Correct!");
            answerResponseLabel.setTextFill(Color.GREEN);
        }
        else {
            answerResponseLabel.setText("Incorrect!");
            answerResponseLabel.setTextFill(Color.RED);
        }
    }

    @FXML
    protected void onQuitButtonClick() throws IOException {
        // Go back to menu when quit button clicked
        musicPlayer.stopMusic();
        soundPlayer.playSound("src/sounds/button_3.wav");
        Stage stage = (Stage) quitButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }

    //TODO: Make the submit button do something
    @FXML
    protected void onSubmitButtonClick() {
        soundPlayer.playSound("src/sounds/button_3.wav");
        answerCorrect(Objects.equals(inputField.getText(), "19")); //TODO: Delete when no longer needed
    }
}

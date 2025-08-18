package com.example.jth_mathgame.mathgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label titleText;
    @FXML
    private Button startButton;
    @FXML
    private Button statsButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button quitButton;

    @FXML
    protected void onStartGameClick() throws IOException {
        Stage stage = (Stage) startButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
    @FXML
    public void initialize() {
        // Title text
        titleText.setText("Math Game");
        titleText.setFont(new Font("Impact", 58));

        // Adjust buttons
        startButton.setFont(new Font("Arial", 40));
        statsButton.setFont(new Font("Arial", 40));
        settingsButton.setFont(new Font("Arial", 40));
        quitButton.setFont(new Font("Arial", 40));
    }
}
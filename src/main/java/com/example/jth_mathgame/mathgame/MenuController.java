package com.example.jth_mathgame.mathgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class MenuController {
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
    protected void onStartGameClick() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        soundPlayer.playSound("src/sounds/button_3.wav");
        // Change window when start button clicked
        Stage stage = (Stage) startButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
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

    @FXML
    protected void onQuitButtonClick() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        soundPlayer.playSound("src/sounds/button_3.wav");
        // Close window when quit button clicked
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void toBeAdded() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        soundPlayer.playSound("src/sounds/button_3.wav");
        // Use on features not yet implemented
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "To be added...", ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    protected void onSettingsButtonClick() throws IOException {
        soundPlayer.playSound("src/sounds/button_3.wav");
        Stage stage = (Stage) settingsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("settings-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void onStatsButtonClick() throws IOException {
        soundPlayer.playSound("src/sounds/button_3.wav");
        Stage stage = (Stage) settingsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("stats-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }
}
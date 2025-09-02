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

import java.io.IOException;

public class InitialMenuController {
    @FXML
    private Label titleText;
    @FXML
    private Button LoginButton;
    @FXML
    private Button RegisterButton;
    @FXML
    private Button quitButton;

    @FXML
    protected void onLoginClick() throws IOException {
        // Change window when start button clicked
        soundPlayer.playSound("src/sounds/button_3.wav");
        Stage stage = (Stage) LoginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("loginMenu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void onRegisterClick() throws IOException {
        // Change window when start button clicked
        soundPlayer.playSound("src/sounds/button_3.wav");
        Stage stage = (Stage) LoginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("registerMenu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }
    @FXML
    public void initialize() {
        // Title text
        titleText.setText("Math Game");
        titleText.setFont(new Font("Impact", 58));

        // Adjust buttons
        LoginButton.setFont(new Font("Arial", 40));
        RegisterButton.setFont(new Font("Arial", 40));
        quitButton.setFont(new Font("Arial", 40));
    }

    @FXML
    protected void onQuitButtonClick() {
        // Close window when quit button clicked
        soundPlayer.playSound("src/sounds/button_3.wav");
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void toBeAdded() {
        // Use on features not yet implemented
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "To be added...", ButtonType.OK);
        alert.showAndWait();
    }
}
package com.example.jth_mathgame.mathgame.controller;

import com.example.jth_mathgame.mathgame.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The initial/welcome window that the user see when starting the program
 */
public class InitialMenuController {
    @FXML
    private Label titleText;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button quitButton;

    /**
     * Sets up window
     */
    @FXML
    public void initialize() {
        // Title text
        titleText.setText("Math Blitz");
        titleText.setFont(new Font("Impact", 58));

        // Adjust buttons
        loginButton.setFont(new Font("Arial", 40));
        registerButton.setFont(new Font("Arial", 40));
        quitButton.setFont(new Font("Arial", 40));
    }

    /**
     * Switch window to the login screen
     */
    @FXML
    protected void onLoginClick() throws IOException {
        // Change window when login button clicked
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("loginMenu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }

    /**
     * Switch window to the login screen
     */
    @FXML
    protected void onRegisterClick() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("registerMenu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }

    /**
     * Closes app
     */
    @FXML
    protected void onQuitButtonClick() {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

}
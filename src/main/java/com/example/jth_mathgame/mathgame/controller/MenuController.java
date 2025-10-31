package com.example.jth_mathgame.mathgame.controller;

import com.example.jth_mathgame.mathgame.MainApplication;
import com.example.jth_mathgame.mathgame.model.Menu;
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

/**
 * The main menu which lets the user start the game, view stats, or change settings
 */
public class MenuController {
    @FXML
    private Label titleText;
    @FXML
    private Button timedButton;
    @FXML
    private Button endlessButton;
    @FXML
    private Button statsButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button logoutButton;

    /**
     * Set up window
     */
    @FXML
    public void initialize() {
        // Title text
        titleText.setText("Math Blitz");
        titleText.setFont(new Font("Impact", 58));

        // Adjust buttons
        Font buttonFont = new Font("Arial", 40);
        endlessButton.setFont(buttonFont);
        timedButton.setFont(buttonFont);
        statsButton.setFont(buttonFont);
        settingsButton.setFont(buttonFont);
        logoutButton.setFont(buttonFont);
    }

    /**
     * Switches to timed mode window
     */
    @FXML
    protected void onStartGameClick() throws IOException {
        // Change window when start button clicked
        Stage stage = (Stage) timedButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }

    /**
     * Switch to endless mode window
     * @throws IOException Switches to endless mode window
     */
    @FXML
    protected void onStartEndlessGameClick() throws IOException {
        // Change window when start button clicked
        Stage stage = (Stage) endlessButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("EndlessGame-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }

    /**
     * Closes app
     */
    @FXML
    protected void onQuitButtonClick() {
        // Close window when quit button clicked
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }

    /**
     * @throws IOException Logs out user and returns to welcome menu
     */
    @FXML
    private void onLogoutClick() throws IOException {
        Menu.Logout();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("initialMenu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Use as a placeholder for unimplemented features
     */
    @FXML
    protected void toBeAdded() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "To be added...", ButtonType.OK);
        alert.showAndWait();
    }

    /**
     * Switch to settings window
     */
    @FXML
    protected void onSettingsButtonClick() throws IOException {
        Stage stage = (Stage) settingsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("settings-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }

    /**
     * Switch to stats window
     */
    @FXML
    protected void onStatsButtonClick() throws IOException {
        Stage stage = (Stage) settingsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("stats-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }
}
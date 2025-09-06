package com.example.jth_mathgame.mathgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {
    @FXML
    private ChoiceBox<String> difficultyChoice;
    @FXML
    private CheckBox soundCheck;
    @FXML
    private Button backButton;

    @FXML
    public void initialize() {
        // Add difficulty options
        difficultyChoice.getItems().addAll("Easy", "Medium", "Hard");

        // Load saved values from Settings
        difficultyChoice.setValue(Settings.getDifficulty());
        soundCheck.setSelected(Settings.isSoundEnabled());

        // Back button logic
        backButton.setOnAction(event -> {
            // Save changes globally
            Settings.setDifficulty(difficultyChoice.getValue());
            Settings.setSoundEnabled(soundCheck.isSelected());
            Settings.save(); // persist to file

            try {
                Stage stage = (Stage) backButton.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

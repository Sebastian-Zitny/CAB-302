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
        difficultyChoice.setValue(Settings.getDifficulty()); // load current
        soundCheck.setSelected(Settings.isSoundEnabled());

        // Back button logic
        backButton.setOnAction(event -> {
            // Save settings globally before leaving
            Settings.setDifficulty(difficultyChoice.getValue());
            Settings.setSoundEnabled(soundCheck.isSelected());

            try {
                Stage stage = (Stage) backButton.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menu-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



    public String getDifficulty() {
        return difficultyChoice.getValue();
    }

    public boolean isSoundEnabled() {
        return soundCheck.isSelected();
    }
}

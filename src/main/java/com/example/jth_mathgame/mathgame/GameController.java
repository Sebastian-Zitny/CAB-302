package com.example.jth_mathgame.mathgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

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
    private Button submitButton;
    @FXML
    private VBox submitVBox;

    @FXML
    public void initialize() {
        Font smallFont = new Font("Arial", 20);

        quitButton.setFont(smallFont);

        levelLabel.setFont(new Font("Arial", 26));

        timeLabel.setFont(smallFont);

        questionLabel.setFont(new Font("Arial", 58));

        inputField.setFont(new Font("Arial", 29));

        quitButton.setFont(smallFont);
        submitButton.setFont(smallFont);
        submitVBox.setAlignment(Pos.CENTER);
    }

    @FXML
    protected void onQuitButtonClick() throws IOException {
        // Go back to menu when quit button clicked
        Stage stage = (Stage) quitButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }
}

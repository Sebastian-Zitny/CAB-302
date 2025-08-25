package com.example.jth_mathgame.mathgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginMenuController {
    @FXML
    public TextField UsernameTextField;
    @FXML
    public TextField PasswordTextField;
    @FXML
    private Label titleText;
    @FXML
    private Button submitButton;



    @FXML
    protected void onSubmitClick() throws IOException {
        // Change window when start button clicked
        Stage stage = (Stage) submitButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    public void initialize() {
        // Title text
        titleText.setText("Math Game");
        titleText.setFont(new Font("Impact", 58));

        // Adjust buttons
        submitButton.setFont(new Font("Arial", 40));
    }


}
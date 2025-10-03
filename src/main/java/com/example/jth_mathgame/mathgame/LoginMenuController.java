package com.example.jth_mathgame.mathgame;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.jth_mathgame.mathgame.MainApplication.currentUserId;
import static com.example.jth_mathgame.mathgame.MainApplication.userdao;

public class LoginMenuController {
    @FXML
    public TextField usernameTextField;
    @FXML
    public PasswordField passwordTextField;
    @FXML
    private Label titleText;
    @FXML
    private Button submitButton;
    @FXML
    private Button backButton;

    @FXML
    protected void onSubmitClick() throws IOException {
        soundPlayer.playSound("src/sounds/button_3.wav");
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        // Looks for user with username
        User user = userdao.getUser(username);
        if (user != null) {
            ArrayList<byte[]> passwordArrayList = userdao.getUserPasswordArray(username);

            // Verify password
            if (userdao.verifyHash(password, passwordArrayList.get(0), passwordArrayList.get(1))) {
                // Login success
                System.out.println("Login successful: " + username);
                currentUserId = user.getId();
                System.out.println("User Id: " + currentUserId);
                Stage stage = (Stage) submitButton.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
                stage.setScene(scene);
            }
            else {
                // Failed login
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText("Invalid credentials");
                alert.setContentText("Please check your username and password.");
                alert.showAndWait();
            }
        } else {
            // Failed login
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText("Invalid credentials");
            alert.setContentText("Please check your username and password.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void onBackClick() throws IOException {
        soundPlayer.playSound("src/sounds/button_3.wav");
        // Change window when back button clicked
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("initialMenu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    public void initialize() {
        // Title text
        titleText.setText("Math Game");
        titleText.setFont(new Font("Impact", 58));

        // Adjust button
        submitButton.setFont(new Font("Arial", 40));

        EventHandler<KeyEvent> enterEventHandler = ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                try {
                    onSubmitClick();
                } catch (IOException e) {
                    System.err.println("Submit button is throwing exception");
                }
            }
        };

        usernameTextField.setOnKeyPressed(enterEventHandler);
        passwordTextField.setOnKeyPressed(enterEventHandler);
    }


}
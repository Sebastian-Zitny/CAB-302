package com.example.jth_mathgame.mathgame;

import javafx.event.Event;
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
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static com.example.jth_mathgame.mathgame.MainApplication.userdao;

public class RegisterMenuController {
    @FXML
    public TextField usernameTextField;
    @FXML
    public PasswordField passwordTextField;
    @FXML
    public TextField emailTextField;
    @FXML
    public TextField phoneTextField;
    @FXML
    private Label titleText;
    @FXML
    private Button registerButton;
    @FXML
    private Button backButton;



    @FXML
    protected void onRegisterClick() throws IOException {
        // Create a new user when register button clicked
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();

        if (!username.isEmpty() && !password.isEmpty() && !email.isEmpty() && !phone.isEmpty()) {
            User newUser = new User(username, password, email, phone);
            userdao.addUser(newUser);

            System.out.println("Registered: " + newUser.getUsername());

            // Change window when register button clicked
            Stage stage = (Stage) registerButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("initialMenu-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
            stage.setScene(scene);
        }
        else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Register Failed");
            alert.setHeaderText("Invalid credentials");
            alert.setContentText("something isn't filled in");
            alert.showAndWait();
        }



    }

    @FXML
    protected void onBackClick() throws IOException {
        // Change window when start button clicked
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

        // Adjust buttons
        registerButton.setFont(new Font("Arial", 40));

        EventHandler<KeyEvent> enterEventHandler = ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                try {
                    onRegisterClick();
                } catch (IOException e) {
                    System.err.println("Submit button is throwing exception");
                }
            }
        };

        usernameTextField.setOnKeyPressed(enterEventHandler);
        passwordTextField.setOnKeyPressed(enterEventHandler);
        emailTextField.setOnKeyPressed(enterEventHandler);
        phoneTextField.setOnKeyPressed(enterEventHandler);
    }


}
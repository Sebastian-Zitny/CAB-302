package com.example.jth_mathgame.mathgame.controller;

import com.example.jth_mathgame.mathgame.*;
import com.example.jth_mathgame.mathgame.MainApplication;
import com.example.jth_mathgame.mathgame.model.RegisterMenu;
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

/**
 * Lets the user create a new account
 */
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

    private final UserDAO USERDAO = UserDAO.getInstance();

    /**
     * Set up window
     */
    @FXML
    public void initialize() {
        // Title text
        titleText.setText("Math Blitz");
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
        usernameTextField.setPromptText("Required");

        passwordTextField.setOnKeyPressed(enterEventHandler);
        passwordTextField.setPromptText("Required");

        emailTextField.setOnKeyPressed(enterEventHandler);
        emailTextField.setPromptText("Required");

        phoneTextField.setOnKeyPressed(enterEventHandler);
        phoneTextField.setPromptText("Optional");
    }

    /**
     * Attempt to create new user and return to welcome screen if successful
     */
    @FXML
    protected void onRegisterClick() throws IOException {
        // Create a new user when register button clicked
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();

        User newUser = new User(username, password, email, phone);
        try {
            // Validate user details
            RegisterMenu.validateUserRegistration(newUser);
        } catch (RegisterUserException e) {
            Alert alert = RegisterMenu.getAlertReason(e);
            alert.showAndWait();
            return;
        }
        try {
            USERDAO.addUser(newUser);
        } catch (DuplicateUserException e) {
            RegisterMenu.DuplicateRegisterFailed();
            return;
        }

        // Change window when register button clicked
        Stage stage = (Stage) registerButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("initialMenu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }

    /**
     * @throws IOException Return to welcome screen
     */
    @FXML
    protected void onBackClick() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("initialMenu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }
}
package com.example.jth_mathgame.mathgame.controller;

import com.example.jth_mathgame.mathgame.model.LoginMenu;
import com.example.jth_mathgame.mathgame.MainApplication;
import com.example.jth_mathgame.mathgame.User;
import com.example.jth_mathgame.mathgame.UserDAO;
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

import static com.example.jth_mathgame.mathgame.MainApplication.currentUserId;

/**
 * Displays the login screen to let the user in to their account
 */
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

    private final UserDAO USERDAO = UserDAO.getInstance();

    /**
     * Sets up window
     */
    @FXML
    public void initialize() {
        // Title text
        titleText.setText("Math Blitz");
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

    /**
     * Try to log user in and proceed to main menu if successful
     */
    @FXML
    protected void onSubmitClick() throws IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        // Looks for user with username
        User user = USERDAO.getUser(username);
        if (user != null) { // User does exist
            if (LoginMenu.CheckLogin(username, password)) {
                // Login success
                currentUserId = user.getId();
                System.out.println("User Id: " + currentUserId);
                Stage stage = (Stage) submitButton.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
                stage.setScene(scene);
            }
            else {
                // Password Incorrect
                failedLogin();
            }
        } else {
            // Username not found
            failedLogin();
        }
    }

    /**
     * Display message on failed login attempt
     */
    private static void failedLogin() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Failed");
        alert.setHeaderText("Invalid credentials");
        alert.setContentText("Please check your username and password.");
        alert.showAndWait();
    }

    /**
     * Return to welcome menu
     */
    @FXML
    protected void onBackClick() throws IOException {
        // Change window when back button clicked
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("initialMenu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }
}
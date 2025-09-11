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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            try {
                // Validate user details
                validateUserRegistration(newUser);
            }
            catch (RegisterUserException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Register Failed");
                if (e.getMessage().contains("phone")) {
                    alert.setHeaderText("Invalid phone number");
                    alert.setContentText("Please make sure phone is a valid Australian/International number.");
                }
                alert.showAndWait();
                return;
            }
            try {
                userdao.addUser(newUser);
            } catch (DuplicateUserException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Register Failed");
                alert.setHeaderText("Duplicate Username/Email");
                alert.setContentText("Please use another username/email");
                alert.showAndWait();
                return;
            }

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
            alert.setContentText("Something isn't filled in");
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

    private void validateUserRegistration(User user) throws RegisterUserException {

        // Username
        // Between 3-15 characters

        // Password
        // Must have at least 8 characters. 1 Uppercase, 1 lowercase, 1 number

        // Email
        // Must be formatted like email (example@example.com)

        // Phone
        // Either formatted as 0XXXXXXXXX (australian) or international number (+xxx...)
        Pattern pattern = Pattern.compile("0[0-9]{9}|\\+[0-9]+");
        Matcher matcher = pattern.matcher(user.getPhone());
        if (!matcher.matches()) {
            throw new RegisterUserException("Invalid phone number");
        }

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
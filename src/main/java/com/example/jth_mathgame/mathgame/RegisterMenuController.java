package com.example.jth_mathgame.mathgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterMenuController {
    @FXML
    public TextField usernameTextField;
    @FXML
    public TextField passwordTextField;
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
        soundPlayer.playSound("src/sounds/button_3.wav");
        // Create a new user when register button clicked
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();

        if (!username.isEmpty() && !password.isEmpty() && !email.isEmpty() && !phone.isEmpty()) {
            User newUser = new User(username, password, email, phone, 1);

            IUserDAO dao = new UserDAO();
            dao.addUser(newUser);

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
        soundPlayer.playSound("src/sounds/button_3.wav");
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
    }


}
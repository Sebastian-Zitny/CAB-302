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
    public TextField usernameTextField;
    @FXML
    public TextField passwordTextField;
    @FXML
    private Label titleText;
    @FXML
    private Button submitButton;
    @FXML
    private Button backButton;

    private static final IUserDAO dao = new MockUserDAO();

    @FXML
    protected void onSubmitClick() throws IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        // Retrieves all users and looks for matching username, else null
        User foundUser = dao.getAllUsers().stream()
                .filter(c -> c.getUsername().equals(username))
                .findFirst()
                .orElse(null);

        if (foundUser != null && foundUser.getPassword().equals(password)) {
            // Login success
            System.out.println("Login successful: " + username);
            // TODO: The user needs to be persistent throughout the entire program
            Stage stage = (Stage) submitButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
            stage.setScene(scene);
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
    }


}
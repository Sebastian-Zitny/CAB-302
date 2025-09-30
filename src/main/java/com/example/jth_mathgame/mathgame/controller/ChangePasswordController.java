package com.example.jth_mathgame.mathgame.controller;

import com.example.jth_mathgame.mathgame.model.ChangePassword;
import com.example.jth_mathgame.mathgame.MainApplication;
import com.example.jth_mathgame.mathgame.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.jth_mathgame.mathgame.MainApplication.currentUserId;

public class ChangePasswordController {
    @FXML
    private PasswordField currentPasswordField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private Button backButton;
    @FXML
    private Button confirmButton;
    private final UserDAO USERDAO = UserDAO.getInstance();

    @FXML
    protected void onConfirmClick() throws IOException {
        String currentPassword = currentPasswordField.getText();
        String newPassword = newPasswordField.getText();
        
        if (ChangePassword.checkPassword(currentPassword)) {
            if (ChangePassword.checkPasswordValid(newPassword)) {
                USERDAO.setPassword(currentUserId, newPassword);
                onBackClick(); // Shortcut back
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Password change Failed");
                alert.setHeaderText("New password invalid");
                alert.setContentText("Please make sure password is valid.\n" +
                        "Password must have at least 8 characters, 1 uppercase, 1 lowercase, and 1 number.");
                alert.showAndWait();
            }
        } else { // Current password is incorrect
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Password change Failed");
            alert.setHeaderText("Current password invalid");
            alert.setContentText("Current password is incorrect.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void onBackClick() throws IOException {
        // Change window when back button clicked
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("settings-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        stage.setScene(scene);
    }
}

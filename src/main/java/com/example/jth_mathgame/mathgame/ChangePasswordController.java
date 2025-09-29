package com.example.jth_mathgame.mathgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        // Check current password
        // TODO: There has to be a cleaner way to do this
        ArrayList<byte[]> passwordArrayList = USERDAO.getUserPasswordArray(USERDAO.getUserFromId(currentUserId).getUsername());
        if (USERDAO.verifyHash(currentPassword, passwordArrayList.get(0), passwordArrayList.get(1))) { // Verify password
            // Check new password formatting
            // Must have at least 8 characters. 1 Uppercase, 1 lowercase, 1 number
            Pattern pattern = Pattern.compile("(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}");
            Matcher matcher = pattern.matcher(newPassword);

            if (!matcher.matches()) { // Invalid
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Password change Failed");
                alert.setHeaderText("New password invalid");
                alert.setContentText("Please make sure password is valid.\n" +
                        "Password must have at least 8 characters, 1 uppercase, 1 lowercase, and 1 number.");
                alert.showAndWait();
                return;
            }
            // Valid
            USERDAO.setPassword(currentUserId, newPassword);
            onBackClick(); // Shortcut back
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

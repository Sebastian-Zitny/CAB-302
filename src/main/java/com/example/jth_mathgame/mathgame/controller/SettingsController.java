package com.example.jth_mathgame.mathgame.controller;

import com.example.jth_mathgame.mathgame.MainApplication;
import com.example.jth_mathgame.mathgame.ResultDAO;
import com.example.jth_mathgame.mathgame.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

import static com.example.jth_mathgame.mathgame.MainApplication.currentUserId;

/**
 * Allows the user to change their settings
 */
public class SettingsController {
    @FXML
    private Button backButton;
    @FXML
    private Button changePasswordButton;
    @FXML
    private Button deleteAccountButton;
    private final UserDAO USERDAO = UserDAO.getInstance();
    private final ResultDAO RESULTDAO = ResultDAO.getInstance();

    /**
     * Set up window
     */
    @FXML
    public void initialize() {
        // Back button logic
        backButton.setOnAction(event -> {
            try {
                Stage stage = (Stage) backButton.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Confirms if the user wants to delete their account.
     */
    @FXML
    public void deleteAccount() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete your account?\n" +
                "This will also delete all your stats.",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            RESULTDAO.removeAllUserResults(currentUserId);
            USERDAO.deleteAccount();
            logout();
        }
    }

    /**
     * Switches to change password window
     */
    @FXML
    private void onChangePasswordClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("changePassword-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
        Stage stage = (Stage) changePasswordButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Logs out user
     */
    @FXML
    private void logout() {
        try {
            currentUserId = null; // Logs user out
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("initialMenu-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
            Stage stage = (Stage) deleteAccountButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

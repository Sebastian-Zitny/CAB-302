package com.example.jth_mathgame.mathgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.jth_mathgame.mathgame.MainApplication.currentUserId;
import static com.example.jth_mathgame.mathgame.MainApplication.userdao;

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

    @FXML
    protected void onSubmitClick() throws IOException {
        String username = usernameTextField.getText();

        // Looks for user with username
        User user = userdao.getUser(username);
        if (user != null) {
            // TODO: I can't get array to pass so I did this. There has to be a better way to do this
            String[] returnedPasswordArray = userdao.getUserPasswordArray(username);
            String[] passwordArray = new String[2];
            System.arraycopy(returnedPasswordArray, 0, passwordArray, 0, returnedPasswordArray.length);
            // Verify password
            if (userdao.verifyHash(user.getPassword(), passwordArray[0], passwordArray[1])) {
                // Login success
                System.out.println("Login successful: " + username);
                currentUserId = user.getId();
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
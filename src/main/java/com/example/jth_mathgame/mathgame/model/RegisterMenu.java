package com.example.jth_mathgame.mathgame.model;

import com.example.jth_mathgame.mathgame.RegisterUserException;
import com.example.jth_mathgame.mathgame.User;
import javafx.scene.control.Alert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterMenu {
    public static void validateUserRegistration(User user) throws RegisterUserException {

        // Initialise variables
        Pattern pattern;
        Matcher matcher;

        // Username
        // Between 3-15 characters, no spaces
        pattern = Pattern.compile("^[\\S]{3,15}$");
        matcher = pattern.matcher(user.getUsername());
        if (!matcher.matches()) {
            throw new RegisterUserException("Invalid username");
        }

        // Password
        // Must have at least 8 characters. 1 Uppercase, 1 lowercase, 1 number
        pattern = Pattern.compile("(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}");
        matcher = pattern.matcher(user.getPassword());
        if (!matcher.matches()) {
            throw new RegisterUserException("Invalid password");
        }

        // Email
        // Must be formatted like email (example@example.com)
        pattern = Pattern.compile("[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z0-9.]*[A-Za-z0-9]+");
        matcher = pattern.matcher(user.getEmail());
        if (!matcher.matches()) {
            throw new RegisterUserException("Invalid email address");
        }

        // Phone
        // Either formatted as 0XXXXXXXXX (australian), international number (+xxx...) or blank
        pattern = Pattern.compile("0[0-9]{9}|\\+[0-9]+");
        matcher = pattern.matcher(user.getPhone());
        if (user.getPhone().isEmpty()) { return; } // Blank
        if (!matcher.matches()) {
            throw new RegisterUserException("Invalid phone number");
        }
    }

    public static Alert getAlertReason(RegisterUserException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Register Failed");
        if (e.getMessage().contains("username")) {
            alert.setHeaderText("Invalid username");
            alert.setContentText("Please make sure username is valid.\n" +
                    "Username should be between 3-15 characters with no spaces.");
        } else if (e.getMessage().contains("password")) {
            alert.setHeaderText("Invalid password");
            alert.setContentText("Please make sure password is valid.\n" +
                    "Password must have at least 8 characters, 1 uppercase, 1 lowercase, and 1 number.");
        } else if (e.getMessage().contains("email")) {
            alert.setHeaderText("Invalid email address");
            alert.setContentText("Please make sure email is valid.");
        } else if (e.getMessage().contains("phone")) {
            alert.setHeaderText("Invalid phone number");
            alert.setContentText("Please make sure phone is a valid Australian/International number, or leave it blank.");
        }
        return alert;
    }

    public static void DuplicateRegisterFailed() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Register Failed");
        alert.setHeaderText("Duplicate Username/Email");
        alert.setContentText("Please use another username/email");
        alert.showAndWait();
        return;
    }
}

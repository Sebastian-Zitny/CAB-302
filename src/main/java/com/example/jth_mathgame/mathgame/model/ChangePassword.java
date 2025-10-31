package com.example.jth_mathgame.mathgame.model;

import com.example.jth_mathgame.mathgame.UserDAO;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.jth_mathgame.mathgame.MainApplication.currentUserId;

/**
 * Window that allows users to change their passwords
 */
public class ChangePassword {

    /**
     * Checks to see if the "current password" is correct
     * @param currentPassword The current user's password
     * @return True if the password matches the database
     */
    public static boolean checkPassword(String currentPassword) {
        final UserDAO USERDAO = UserDAO.getInstance();

        ArrayList<byte[]> passwordArrayList = USERDAO.getUserPasswordArray(USERDAO.getUserFromId(currentUserId).getUsername());
        // Verify password
        return USERDAO.verifyHash(currentPassword, passwordArrayList.get(0), passwordArrayList.get(1));
    }

    /**
     * Checks if password meets certain criteria
     * @param newPassword The new password
     * @return True if password meets criteria
     */
    public static boolean checkPasswordValid(String newPassword) {
        // Check new password formatting
        // Must have at least 8 characters. 1 Uppercase, 1 lowercase, 1 number
        Pattern pattern = Pattern.compile("(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}");
        Matcher matcher = pattern.matcher(newPassword);
        return matcher.matches();
    }
}

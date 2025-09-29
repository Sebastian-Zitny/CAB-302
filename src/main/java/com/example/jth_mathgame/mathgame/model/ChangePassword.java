package com.example.jth_mathgame.mathgame.model;

import com.example.jth_mathgame.mathgame.UserDAO;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.jth_mathgame.mathgame.MainApplication.currentUserId;

public class ChangePassword {

    public static boolean checkPassword(String oldPassword) {
        final UserDAO USERDAO = UserDAO.getInstance();

        ArrayList<byte[]> passwordArrayList = USERDAO.getUserPasswordArray(USERDAO.getUserFromId(currentUserId).getUsername());
        // Verify password
        return USERDAO.verifyHash(oldPassword, passwordArrayList.get(0), passwordArrayList.get(1));
    }
    public static boolean checkPasswordValid(String newPassword) {
        // Check new password formatting
        // Must have at least 8 characters. 1 Uppercase, 1 lowercase, 1 number
        Pattern pattern = Pattern.compile("(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}");
        Matcher matcher = pattern.matcher(newPassword);
        return matcher.matches();
    }
}

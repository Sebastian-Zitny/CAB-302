package com.example.jth_mathgame.mathgame.model;

import com.example.jth_mathgame.mathgame.UserDAO;

import java.util.ArrayList;

/**
 * Displays the login screen to let the user in to their account
 */
public class LoginMenu {
    /**
     * @param username Username given by user
     * @param password Password given by user
     * @return True if password is correct
     */
    public static boolean CheckLogin(String username, String password) {
        final UserDAO USERDAO = UserDAO.getInstance();

        ArrayList<byte[]> passwordArrayList = USERDAO.getUserPasswordArray(username);

        return (USERDAO.verifyHash(password, passwordArrayList.get(0), passwordArrayList.get(1)));
    }
}

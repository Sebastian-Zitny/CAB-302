package com.example.jth_mathgame.mathgame.model;

import com.example.jth_mathgame.mathgame.UserDAO;

import java.util.ArrayList;

public class LoginMenu {
    public static boolean CheckLogin(String username, String password) {
        final UserDAO USERDAO = UserDAO.getInstance();

        ArrayList<byte[]> passwordArrayList = USERDAO.getUserPasswordArray(username);

        return (USERDAO.verifyHash(password, passwordArrayList.get(0), passwordArrayList.get(1)));
    }
}

package com.example.jth_mathgame.mathgame;

import java.util.ArrayList;

public class LoginMenu {
    public static boolean CheckLogin(String username, String password) {
        final UserDAO USERDAO = UserDAO.getInstance();

        ArrayList<byte[]> passwordArrayList = USERDAO.getUserPasswordArray(username);

        return (USERDAO.verifyHash(password, passwordArrayList.get(0), passwordArrayList.get(1)));
    }
}

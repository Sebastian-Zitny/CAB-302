package com.example.jth_mathgame.mathgame.model;

import static com.example.jth_mathgame.mathgame.MainApplication.currentUserId;

public class Menu {
    public static void Logout() {
        currentUserId = null;
    }
}

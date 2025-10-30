package com.example.jth_mathgame.mathgame.model;

import static com.example.jth_mathgame.mathgame.MainApplication.currentUserId;

/**
 * The main menu which lets the user start the game, view stats, or change settings
 */
public class Menu {
    /**
     * Logs out user
     */
    public static void Logout() {
        currentUserId = null;
    }
}

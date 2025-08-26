package com.example.jth_mathgame.mathgame;

// use the following to get settings values
// String diff = Settings.getDifficulty();
// boolean sound = Settings.isSoundEnabled();

public class Settings {
    private static String difficulty = "Easy";
    private static boolean soundEnabled = true;

    public static String getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(String newDifficulty) {
        difficulty = newDifficulty;
    }

    public static boolean isSoundEnabled() {
        return soundEnabled;
    }

    public static void setSoundEnabled(boolean enabled) {
        soundEnabled = enabled;
    }
}

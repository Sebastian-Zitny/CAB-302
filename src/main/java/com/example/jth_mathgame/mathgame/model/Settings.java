package com.example.jth_mathgame.mathgame.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Allows the user to change their settings
 */
public class Settings {
    private static String difficulty = "Easy";
    private static boolean soundEnabled = true;

    private static final String SETTINGS_FILE = "settings.txt";

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

    /**
     * Save current settings to file
     */
    public static void save() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SETTINGS_FILE))) {
            writer.println("difficulty=" + difficulty);
            writer.println("sound=" + soundEnabled);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load settings from file if exists
     */
    public static void load() {
        Path path = Path.of(SETTINGS_FILE);
        if (!Files.exists(path)) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(SETTINGS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("difficulty=")) {
                    difficulty = line.substring("difficulty=".length());
                } else if (line.startsWith("sound=")) {
                    soundEnabled = Boolean.parseBoolean(line.substring("sound=".length()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

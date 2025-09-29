package com.example.jth_mathgame.mathgame;

import com.example.jth_mathgame.mathgame.model.Settings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class SettingsTest {

    @AfterEach
    void cleanup() {
        Settings.setDifficulty("Easy");
        Settings.setSoundEnabled(true);
        File f = new File("settings.txt");
        if (f.exists()) {
            f.delete();
        }
    }

    @Test
    void defaults_areExpected() {
        assertEquals("Easy", Settings.getDifficulty());
        assertTrue(Settings.isSoundEnabled());
    }

    @Test
    void updates_applyCorrectly() {
        Settings.setDifficulty("Hard");
        Settings.setSoundEnabled(false);

        assertEquals("Hard", Settings.getDifficulty());
        assertFalse(Settings.isSoundEnabled());
    }

    @Test
    void saveAndLoad_persistsSettings() {
        Settings.setDifficulty("Medium");
        Settings.setSoundEnabled(false);
        Settings.save();

        Settings.setDifficulty("Easy");
        Settings.setSoundEnabled(true);

        Settings.load();

        assertEquals("Medium", Settings.getDifficulty());
        assertFalse(Settings.isSoundEnabled());
    }
}
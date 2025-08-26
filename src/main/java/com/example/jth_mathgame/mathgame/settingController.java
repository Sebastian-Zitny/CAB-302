package com.example.jth_mathgame.mathgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class settingController {
    @FXML
    private static Slider masterVolume = new Slider(0,1,soundPlayer.getVolume());
    // need to find a way to save slider values
    @FXML
    private Slider musicVolume;
    @FXML
    private Label masterVolumeLabel;
    @FXML
    private Label musicVolumeLabel;
    @FXML
    private Button backButton;

    @FXML
    public void initialize(){
        masterVolume.setValue(soundPlayer.getVolume());
    }

    @FXML
    protected void backToMenu() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        soundPlayer.playSound("src/sounds/button_3.wav");
        soundPlayer.setVolume(masterVolume.getValue());
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), main.WIDTH, main.HEIGHT);
        stage.setScene(scene);
    }
}


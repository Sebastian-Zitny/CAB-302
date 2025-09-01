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
    private static Slider soundVolume = new Slider(0,1,0);
    // need to find a way to save slider values
   // private static double volume = soundPlayer.getVolume();
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
        soundPlayer.volume.bind(soundVolume.valueProperty());
        soundVolume.setValue(0.5);
    }

    @FXML
    protected void backToMenu() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        soundPlayer.playSound("src/sounds/button_3.wav");
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), main.WIDTH, main.HEIGHT);
        stage.setScene(scene);
    }
   // @FXML
   // protected void setSoundVolume(){soundPlayer.setVolume(soundVolume.getValue());}
}


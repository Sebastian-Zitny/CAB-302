package com.example.jth_mathgame.mathgame;
import java.io.File;
import java.io.IOException;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class soundPlayer {
    public static DoubleProperty volume = new SimpleDoubleProperty(0.5);


    static void playSound(String sound){
           Media media = new Media(new File(sound).toURI().toString());
           MediaPlayer noise = new MediaPlayer(media);
           if (Settings.isSoundEnabled()) {
               noise.play();
           }
           else {

           }
    }
}

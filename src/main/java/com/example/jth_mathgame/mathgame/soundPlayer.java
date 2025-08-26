package com.example.jth_mathgame.mathgame;
import java.io.File;
import java.io.IOException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class soundPlayer {
    private static double volume;

    public static double getVolume() {
        return volume;
    }

    public static void setVolume(double svolume) {
        volume = svolume;
    }

    static void playSound(String sound){
           Media media = new Media(new File(sound).toURI().toString());
           MediaPlayer noise = new MediaPlayer(media);
           noise.setVolume(volume);
           noise.play();
    }
}

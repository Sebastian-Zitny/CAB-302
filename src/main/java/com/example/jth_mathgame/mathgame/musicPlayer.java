package com.example.jth_mathgame.mathgame;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class musicPlayer {
    private static double volume;

    private static MediaPlayer noise;

    public static double getVolume() {
        return volume;
    }

    public static void setVolume(double svolume) {
        volume = svolume;
    }

    static void playMusic(String music){
        Media media = new Media(new File(music).toURI().toString());
        noise = new MediaPlayer(media);
       // noise.setVolume(volume); will use later when implementing volume

        noise.setOnEndOfMedia(new Runnable() {  // repeats the music track
            @Override
            public void run() {
                noise.seek(Duration.ZERO);
            }
        });
        noise.play();
    }
    static void stopMusic(){
        noise.stop();
    }

}
// music source: https://www.youtube.com/watch?v=2oHZld5jfrA
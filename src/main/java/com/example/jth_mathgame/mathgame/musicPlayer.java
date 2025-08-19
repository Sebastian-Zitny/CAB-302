package com.example.jth_mathgame.mathgame;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
public class musicPlayer {
   static void playSound(String sound) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
           File file = new File(sound);
           AudioInputStream audiostream = AudioSystem.getAudioInputStream(file);
           Clip clip = AudioSystem.getClip();
           clip.open(audiostream);
           clip.start();
    }
}

package utils;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioHandler {
    // im using wav file
    private static AudioInputStream audioInputStream;
    private static Clip clip;

    public static void playAudio(String filepath) {
        try {
            // load audio file
            audioInputStream = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
            // get clip
            clip = AudioSystem.getClip();
            // open audioInputStream to the clip
            clip.open(audioInputStream);
            // start the audio clip
            clip.start();
        } catch (Exception e) {
            System.out.println("Audio file not found" + filepath);
        }
    }
}

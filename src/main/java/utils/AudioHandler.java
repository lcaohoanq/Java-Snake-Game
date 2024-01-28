package utils;

import errors.FileException;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioHandler {
    // im using wav file
    private static AudioInputStream audioInputStream;
    private static Clip clip;
    private static String path;

    public static boolean playAudio(String filepath) {
        try {
            path = filepath;
            File file = new File(filepath);
            if (filepath.isEmpty() || !file.exists()) {
                return false;
            }
            // load audio file
            audioInputStream = AudioSystem.getAudioInputStream(file.getAbsoluteFile());
            // get clip
            clip = AudioSystem.getClip();
            // open audioInputStream to the clip
            clip.open(audioInputStream);
            // start the audio clip
            clip.start();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean isEmptyPath() {
        return path.isEmpty() ? true : false;
    }
}

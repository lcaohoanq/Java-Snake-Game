package utils;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AudioHandler {
    public static InputStream inputStreamCurrent;
    public static boolean path = true;
    private static AudioInputStream audioInputStream;
    private static Clip clip;

    public boolean isEmptyPath() {
        return !path;
    }

    public void playAudio(InputStream inputStream) {

        if(!path) {
            log.info("Audio path is empty");
            return;
        }

        try {
            if (inputStreamCurrent != null) {
                throw new IOException("Audio file is missing");
            }
            // Create a byte array to store the audio data
            byte[] audioData = inputStream.readAllBytes();

            // Use ByteArrayInputStream to create an AudioInputStream
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(audioData);
            audioInputStream = AudioSystem.getAudioInputStream(byteArrayInputStream);

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

}

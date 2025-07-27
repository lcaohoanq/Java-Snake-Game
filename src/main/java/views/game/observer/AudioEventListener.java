package views.game.observer;

import java.io.InputStream;
import utils.AudioHandler;
import constants.ResourcePaths;
import lombok.extern.slf4j.Slf4j;

/**
 * Concrete implementation of GameEventListener for playing audio on game events
 */
@Slf4j
public class AudioEventListener implements GameEventListener {
    
    private final AudioHandler audioHandler;
    private final boolean soundEnabled;
    
    public AudioEventListener(AudioHandler audioHandler, boolean soundEnabled) {
        this.audioHandler = audioHandler;
        this.soundEnabled = soundEnabled;
    }
    
    @Override
    public void onAppleEaten(int score) {
        if (soundEnabled && audioHandler != null) {
            // Play regular apple eating sound
            InputStream inputStream = getClass().getResourceAsStream(ResourcePaths.URL_EATING2);
            audioHandler.playAudio(inputStream);
            log.debug("Playing regular apple eating sound");
        }
    }
    
    @Override
    public void onBigAppleEaten(int score, int newDelay) {
        if (soundEnabled && audioHandler != null) {
            // Play big apple eating sound
            InputStream inputStream = getClass().getResourceAsStream(ResourcePaths.URL_EATING);
            audioHandler.playAudio(inputStream);
            log.debug("Playing big apple eating sound");
        }
    }
    
    @Override
    public void onBigAppleAppeared() {
        // No sound for big apple appearance
    }
    
    @Override
    public void onGameOver(int finalScore) {
        if (soundEnabled && audioHandler != null) {
            // Play game over sound
            InputStream inputStream = getClass().getResourceAsStream(ResourcePaths.URL_GAME_OVER);
            audioHandler.playAudio(inputStream);
            log.debug("Playing game over sound");
        }
    }
}
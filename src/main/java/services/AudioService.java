package services;

import java.io.InputStream;
import utils.AudioHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * Singleton service for managing game audio
 */
@Slf4j
public class AudioService {
    
    private static AudioService instance;
    private final AudioHandler audioHandler;
    private boolean soundEnabled = true;
    
    // Private constructor to prevent direct instantiation
    private AudioService() {
        audioHandler = new AudioHandler();
        log.info("AudioService initialized");
    }
    
    /**
     * Get the singleton instance of AudioService
     * 
     * @return The AudioService instance
     */
    public static synchronized AudioService getInstance() {
        if (instance == null) {
            instance = new AudioService();
        }
        return instance;
    }
    
    /**
     * Play an audio file from an input stream
     * 
     * @param inputStream The input stream of the audio file
     */
    public void playAudio(InputStream inputStream) {
        if (soundEnabled && inputStream != null) {
            audioHandler.playAudio(inputStream);
        }
    }
    
    /**
     * Check if sound is enabled
     * 
     * @return true if sound is enabled, false otherwise
     */
    public boolean isSoundEnabled() {
        return soundEnabled;
    }
    
    /**
     * Set whether sound is enabled
     * 
     * @param enabled true to enable sound, false to disable
     */
    public void setSoundEnabled(boolean enabled) {
        this.soundEnabled = enabled;
        log.info("Sound {} enabled", enabled ? "is" : "is not");
    }
    
    /**
     * Toggle sound on/off
     * 
     * @return The new sound enabled state
     */
    public boolean toggleSound() {
        soundEnabled = !soundEnabled;
        log.info("Sound toggled to {}", soundEnabled ? "on" : "off");
        return soundEnabled;
    }
}
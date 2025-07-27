package views.game.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Publisher class for the Observer Pattern
 * Manages game event listeners and notifies them of events
 */
public class GameEventPublisher {
    
    private final List<GameEventListener> listeners = new ArrayList<>();
    
    /**
     * Add a listener to receive game events
     * 
     * @param listener The listener to add
     */
    public void addListener(GameEventListener listener) {
        listeners.add(listener);
    }
    
    /**
     * Remove a listener from receiving game events
     * 
     * @param listener The listener to remove
     */
    public void removeListener(GameEventListener listener) {
        listeners.remove(listener);
    }
    
    /**
     * Notify all listeners that a regular apple was eaten
     * 
     * @param score The current score after eating the apple
     */
    public void notifyAppleEaten(int score) {
        for (GameEventListener listener : listeners) {
            listener.onAppleEaten(score);
        }
    }
    
    /**
     * Notify all listeners that a big apple was eaten
     * 
     * @param score The current score after eating the big apple
     * @param newDelay The new game speed after eating the big apple
     */
    public void notifyBigAppleEaten(int score, int newDelay) {
        for (GameEventListener listener : listeners) {
            listener.onBigAppleEaten(score, newDelay);
        }
    }
    
    /**
     * Notify all listeners that a big apple appeared
     */
    public void notifyBigAppleAppeared() {
        for (GameEventListener listener : listeners) {
            listener.onBigAppleAppeared();
        }
    }
    
    /**
     * Notify all listeners that the game is over
     * 
     * @param finalScore The final score when the game ended
     */
    public void notifyGameOver(int finalScore) {
        for (GameEventListener listener : listeners) {
            listener.onGameOver(finalScore);
        }
    }
}
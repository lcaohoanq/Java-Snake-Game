package views.game.observer;

/**
 * Observer Pattern interface for game events
 * Defines callbacks for various game events
 */
public interface GameEventListener {
    
    /**
     * Called when a regular apple is eaten
     * 
     * @param score The current score after eating the apple
     */
    void onAppleEaten(int score);
    
    /**
     * Called when a big apple is eaten
     * 
     * @param score The current score after eating the big apple
     * @param newDelay The new game speed after eating the big apple
     */
    void onBigAppleEaten(int score, int newDelay);
    
    /**
     * Called when a big apple appears on the board
     */
    void onBigAppleAppeared();
    
    /**
     * Called when the game is over
     * 
     * @param finalScore The final score when the game ended
     */
    void onGameOver(int finalScore);
}
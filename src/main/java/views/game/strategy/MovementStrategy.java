package views.game.strategy;

/**
 * Strategy Pattern interface for snake movement
 * Defines different movement strategies for the snake
 */
public interface MovementStrategy {
    /**
     * Move the snake based on the strategy
     * 
     * @param x The array of x-coordinates for the snake segments
     * @param y The array of y-coordinates for the snake segments
     * @param dots The number of segments in the snake
     */
    void move(int[] x, int[] y, int dots);
}
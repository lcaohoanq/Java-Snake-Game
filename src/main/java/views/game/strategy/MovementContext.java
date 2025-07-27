package views.game.strategy;

/**
 * Context class for the Movement Strategy pattern
 * Manages the current movement strategy and delegates movement operations
 */
public class MovementContext {
    
    private MovementStrategy strategy;
    private final int dotSize;
    
    public MovementContext(int dotSize) {
        this.dotSize = dotSize;
        // Default to right movement
        this.strategy = new RightMovement(dotSize);
    }
    
    /**
     * Set the movement strategy based on direction
     * 
     * @param leftDirection Whether left movement is active
     * @param rightDirection Whether right movement is active
     * @param upDirection Whether up movement is active
     * @param downDirection Whether down movement is active
     */
    public void setStrategy(boolean leftDirection, boolean rightDirection, 
                           boolean upDirection, boolean downDirection) {
        if (leftDirection) {
            this.strategy = new LeftMovement(dotSize);
        } else if (rightDirection) {
            this.strategy = new RightMovement(dotSize);
        } else if (upDirection) {
            this.strategy = new UpMovement(dotSize);
        } else if (downDirection) {
            this.strategy = new DownMovement(dotSize);
        }
    }
    
    /**
     * Execute the current movement strategy
     * 
     * @param x The array of x-coordinates for the snake segments
     * @param y The array of y-coordinates for the snake segments
     * @param dots The number of segments in the snake
     */
    public void executeStrategy(int[] x, int[] y, int dots) {
        strategy.move(x, y, dots);
    }
}
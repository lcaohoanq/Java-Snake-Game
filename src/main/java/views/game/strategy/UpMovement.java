package views.game.strategy;

/**
 * Concrete strategy for moving the snake upward
 */
public class UpMovement implements MovementStrategy {
    
    private final int dotSize;
    
    public UpMovement(int dotSize) {
        this.dotSize = dotSize;
    }
    
    @Override
    public void move(int[] x, int[] y, int dots) {
        // Move body segments
        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }
        
        // Move head upward
        y[0] -= dotSize;
    }
}
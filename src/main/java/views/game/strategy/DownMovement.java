package views.game.strategy;

/**
 * Concrete strategy for moving the snake downward
 */
public class DownMovement implements MovementStrategy {
    
    private final int dotSize;
    
    public DownMovement(int dotSize) {
        this.dotSize = dotSize;
    }
    
    @Override
    public void move(int[] x, int[] y, int dots) {
        // Move body segments
        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }
        
        // Move head downward
        y[0] += dotSize;
    }
}
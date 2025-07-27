package views.game.command;

import lombok.Getter;
import lombok.Setter;

/**
 * Class to track the current direction state of the snake
 */
@Getter
@Setter
public class DirectionState {
    
    private boolean leftDirection = false;
    private boolean rightDirection = true; // Default direction is right
    private boolean upDirection = false;
    private boolean downDirection = false;
    
    /**
     * Reset the direction state to the default (right)
     */
    public void resetToDefault() {
        leftDirection = false;
        rightDirection = true;
        upDirection = false;
        downDirection = false;
    }
}
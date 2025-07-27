package views.game.command;

import views.game.strategy.MovementContext;

/**
 * Concrete command for moving the snake left
 */
public class MoveLeftCommand implements Command {
    
    private final MovementContext movementContext;
    private final DirectionState directionState;
    
    public MoveLeftCommand(MovementContext movementContext, DirectionState directionState) {
        this.movementContext = movementContext;
        this.directionState = directionState;
    }
    
    @Override
    public void execute() {
        // Only change direction if not currently moving right
        if (!directionState.isRightDirection()) {
            directionState.setLeftDirection(true);
            directionState.setUpDirection(false);
            directionState.setDownDirection(false);
            directionState.setRightDirection(false);
            
            // Update the movement strategy
            movementContext.setStrategy(
                directionState.isLeftDirection(),
                directionState.isRightDirection(),
                directionState.isUpDirection(),
                directionState.isDownDirection()
            );
        }
    }
}
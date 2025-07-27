package views.game.command;

import views.game.strategy.MovementContext;

/**
 * Concrete command for moving the snake right
 */
public class MoveRightCommand implements Command {
    
    private final MovementContext movementContext;
    private final DirectionState directionState;
    
    public MoveRightCommand(MovementContext movementContext, DirectionState directionState) {
        this.movementContext = movementContext;
        this.directionState = directionState;
    }
    
    @Override
    public void execute() {
        // Only change direction if not currently moving left
        if (!directionState.isLeftDirection()) {
            directionState.setRightDirection(true);
            directionState.setUpDirection(false);
            directionState.setDownDirection(false);
            directionState.setLeftDirection(false);
            
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
package views.game.command;

import views.game.strategy.MovementContext;

/**
 * Concrete command for moving the snake up
 */
public class MoveUpCommand implements Command {
    
    private final MovementContext movementContext;
    private final DirectionState directionState;
    
    public MoveUpCommand(MovementContext movementContext, DirectionState directionState) {
        this.movementContext = movementContext;
        this.directionState = directionState;
    }
    
    @Override
    public void execute() {
        // Only change direction if not currently moving down
        if (!directionState.isDownDirection()) {
            directionState.setUpDirection(true);
            directionState.setRightDirection(false);
            directionState.setLeftDirection(false);
            directionState.setDownDirection(false);
            
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
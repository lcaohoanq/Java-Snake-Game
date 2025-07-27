package views.game.command;

import views.game.strategy.MovementContext;

/**
 * Concrete command for moving the snake down
 */
public class MoveDownCommand implements Command {
    
    private final MovementContext movementContext;
    private final DirectionState directionState;
    
    public MoveDownCommand(MovementContext movementContext, DirectionState directionState) {
        this.movementContext = movementContext;
        this.directionState = directionState;
    }
    
    @Override
    public void execute() {
        // Only change direction if not currently moving up
        if (!directionState.isUpDirection()) {
            directionState.setDownDirection(true);
            directionState.setRightDirection(false);
            directionState.setLeftDirection(false);
            directionState.setUpDirection(false);
            
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
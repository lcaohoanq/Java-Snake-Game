package views.game.command;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import views.game.strategy.MovementContext;

/**
 * Invoker class for the Command Pattern
 * Maps key events to commands and executes them
 */
public class CommandInvoker {
    
    private final Map<Integer, Command> keyCommands = new HashMap<>();
    
    /**
     * Register a command to be executed when a specific key is pressed
     * 
     * @param keyCode The key code from KeyEvent
     * @param command The command to execute
     */
    public void registerCommand(int keyCode, Command command) {
        keyCommands.put(keyCode, command);
    }
    
    /**
     * Execute the command associated with the given key code
     * 
     * @param keyCode The key code from KeyEvent
     * @return true if a command was executed, false otherwise
     */
    public boolean executeCommand(int keyCode) {
        Command command = keyCommands.get(keyCode);
        if (command != null) {
            command.execute();
            return true;
        }
        return false;
    }
    
    /**
     * Set up the default key mappings for snake movement
     * 
     * @param movementContext The movement context to use
     * @param directionState The direction state to modify
     */
    public void setupDefaultCommands(MovementContext movementContext, DirectionState directionState) {
        registerCommand(KeyEvent.VK_LEFT, new MoveLeftCommand(movementContext, directionState));
        registerCommand(KeyEvent.VK_RIGHT, new MoveRightCommand(movementContext, directionState));
        registerCommand(KeyEvent.VK_UP, new MoveUpCommand(movementContext, directionState));
        registerCommand(KeyEvent.VK_DOWN, new MoveDownCommand(movementContext, directionState));
    }
}
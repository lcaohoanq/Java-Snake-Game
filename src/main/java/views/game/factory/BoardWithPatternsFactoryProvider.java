package views.game.factory;

import enums.GameMode;
import lombok.extern.slf4j.Slf4j;

/**
 * Provider class for getting the appropriate board factory with design patterns
 */
@Slf4j
public class BoardWithPatternsFactoryProvider {
    
    /**
     * Gets the appropriate factory based on the game mode
     * 
     * @param mode The game mode
     * @return The appropriate factory for the game mode
     */
    public static BoardWithPatternsFactory getFactory(GameMode mode) {
        if (mode == null) {
            log.error("Mode is null, defaulting to NoMaze");
            return new NoMazeBoardWithPatternsFactory();
        }

        return switch (mode) {
            case NO_MAZE -> new NoMazeBoardWithPatternsFactory();
            case BOX -> new BoxBoardWithPatternsFactory();
            // Add more cases for other board types as needed
            default -> {
                log.warn("Unknown mode: {}, defaulting to NoMaze", mode);
                yield new NoMazeBoardWithPatternsFactory();
            }
        };
    }
}
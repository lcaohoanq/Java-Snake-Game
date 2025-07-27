package views.game.factory;

import models.UserScore;
import views.base.BoardWithPatterns;

/**
 * Factory interface for creating boards with design patterns
 */
public interface BoardWithPatternsFactory {
    
    /**
     * Creates a board with design patterns implementation
     * 
     * @param user The user score object
     * @return A board with design patterns implementation
     */
    BoardWithPatterns createBoard(UserScore user);
}
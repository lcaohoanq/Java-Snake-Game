package views.game.factory;

import models.UserScore;
import views.base.Board;

/**
 * Factory interface for creating different types of game boards.
 * This follows the Factory Pattern to encapsulate board creation logic.
 */
public interface BoardFactory {
    /**
     * Creates a board of a specific type with the given user.
     * 
     * @param user The user playing the game
     * @return A concrete Board implementation
     */
    Board createBoard(UserScore user);
}
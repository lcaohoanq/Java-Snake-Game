package views.game.factory;

import models.UserScore;
import views.base.Board;
import views.game.Box;

/**
 * Concrete factory implementation for creating Box boards.
 */
public class BoxFactory implements BoardFactory {
    @Override
    public Board createBoard(UserScore user) {
        return new Box(user);
    }
}
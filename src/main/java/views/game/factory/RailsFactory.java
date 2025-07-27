package views.game.factory;

import models.UserScore;
import views.base.Board;
import views.game.Rails;

/**
 * Concrete factory implementation for creating Rails boards.
 */
public class RailsFactory implements BoardFactory {
    @Override
    public Board createBoard(UserScore user) {
        return new Rails(user);
    }
}
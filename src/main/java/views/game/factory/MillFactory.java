package views.game.factory;

import models.UserScore;
import views.base.Board;
import views.game.Mill;

/**
 * Concrete factory implementation for creating Mill boards.
 */
public class MillFactory implements BoardFactory {
    @Override
    public Board createBoard(UserScore user) {
        return new Mill(user);
    }
}
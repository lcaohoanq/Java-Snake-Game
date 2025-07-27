package views.game.factory;

import models.UserScore;
import views.base.Board;
import views.game.NoMaze;

/**
 * Concrete factory implementation for creating NoMaze boards.
 */
public class NoMazeFactory implements BoardFactory {
    @Override
    public Board createBoard(UserScore user) {
        return new NoMaze(user);
    }
}
package views.game.factory;

import models.UserScore;
import views.base.BoardWithPatterns;
import views.game.boards.NoMazeBoardWithPatterns;

/**
 * Factory implementation for creating NoMaze boards with design patterns
 */
public class NoMazeBoardWithPatternsFactory implements BoardWithPatternsFactory {
    
    @Override
    public BoardWithPatterns createBoard(UserScore user) {
        return new NoMazeBoardWithPatterns(user);
    }
}
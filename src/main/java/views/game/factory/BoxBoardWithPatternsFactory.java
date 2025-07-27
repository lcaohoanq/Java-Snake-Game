package views.game.factory;

import models.UserScore;
import views.base.BoardWithPatterns;
import views.game.boards.BoxBoardWithPatterns;

/**
 * Factory implementation for creating Box boards with design patterns
 */
public class BoxBoardWithPatternsFactory implements BoardWithPatternsFactory {
    
    @Override
    public BoardWithPatterns createBoard(UserScore user) {
        return new BoxBoardWithPatterns(user);
    }
}
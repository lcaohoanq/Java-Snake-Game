package views.game.factory;

import enums.GameMode;
import org.junit.jupiter.api.Test;
import views.base.BoardWithPatterns;
import views.game.boards.BoxBoardWithPatterns;
import views.game.boards.NoMazeBoardWithPatterns;

import static org.junit.jupiter.api.Assertions.*;

class BoardWithPatternsFactoryProviderTest {

    @Test
    void getFactoryForNoMazeMode() {
        // When
        BoardWithPatternsFactory factory = BoardWithPatternsFactoryProvider.getFactory(GameMode.NO_MAZE);
        
        // Then
        assertNotNull(factory);
        assertInstanceOf(NoMazeBoardWithPatternsFactory.class, factory);
    }

    @Test
    void getFactoryForBoxMode() {
        // When
        BoardWithPatternsFactory factory = BoardWithPatternsFactoryProvider.getFactory(GameMode.BOX);
        
        // Then
        assertNotNull(factory);
        assertInstanceOf(BoxBoardWithPatternsFactory.class, factory);
    }

    @Test
    void getFactoryForUnknownMode() {
        // When
        BoardWithPatternsFactory factory = BoardWithPatternsFactoryProvider.getFactory(GameMode.UNKNOWN);
        
        // Then
        assertNotNull(factory);
        assertInstanceOf(NoMazeBoardWithPatternsFactory.class, factory); // Should default to NoMaze
    }

    @Test
    void getFactoryForNullMode() {
        // When
        BoardWithPatternsFactory factory = BoardWithPatternsFactoryProvider.getFactory(null);
        
        // Then
        assertNotNull(factory);
        assertInstanceOf(NoMazeBoardWithPatternsFactory.class, factory); // Should default to NoMaze
    }
}
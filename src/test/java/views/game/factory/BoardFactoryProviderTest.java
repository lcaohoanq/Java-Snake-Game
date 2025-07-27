package views.game.factory;

import models.UserScore;
import org.junit.jupiter.api.Test;
import views.base.Board;
import views.game.*;

import static org.junit.jupiter.api.Assertions.*;

class BoardFactoryProviderTest {

    @Test
    void testGetFactoryForNoMaze() {
        BoardFactory factory = BoardFactoryProvider.getFactory("NoMaze");
        assertNotNull(factory);
        assertInstanceOf(NoMazeFactory.class, factory);
        
        // Create a test user
        UserScore testUser = new UserScore(1, "Test User", "test@example.com", "password", 0, null);
        
        // Create a board using the factory
        Board board = factory.createBoard(testUser);
        assertNotNull(board);
        assertInstanceOf(NoMaze.class, board);
    }

    @Test
    void testGetFactoryForBox() {
        BoardFactory factory = BoardFactoryProvider.getFactory("Box");
        assertNotNull(factory);
        assertInstanceOf(BoxFactory.class, factory);
        
        // Create a test user
        UserScore testUser = new UserScore(1, "Test User", "test@example.com", "password", 0, null);
        
        // Create a board using the factory
        Board board = factory.createBoard(testUser);
        assertNotNull(board);
        assertInstanceOf(Box.class, board);
    }

    @Test
    void testGetFactoryForClassic() {
        // Classic mode uses NoMaze board
        BoardFactory factory = BoardFactoryProvider.getFactory("Classic");
        assertNotNull(factory);
        assertInstanceOf(NoMazeFactory.class, factory);
    }

    @Test
    void testGetFactoryForUnknownMode() {
        // Unknown mode should default to NoMaze
        BoardFactory factory = BoardFactoryProvider.getFactory("UnknownMode");
        assertNotNull(factory);
        assertInstanceOf(NoMazeFactory.class, factory);
    }

    @Test
    void testGetFactoryWithNullMode() {
        // Null mode should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            BoardFactoryProvider.getFactory(null);
        });
    }
}
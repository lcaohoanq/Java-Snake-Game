package views.game.factory;

import org.junit.jupiter.api.Test;
import views.base.BoardWithPatterns;
import views.game.boards.BoxBoardWithPatterns;
import views.game.boards.NoMazeBoardWithPatterns;

import static org.junit.jupiter.api.Assertions.*;

class BoardWithPatternsFactoryProviderTest {

    @Test
    void getFactoryForNoMazeMode() {
        // Given
        String mode = "NoMaze";
        
        // When
        BoardWithPatternsFactory factory = BoardWithPatternsFactoryProvider.getFactory(mode);
        
        // Then
        assertNotNull(factory);
        assertInstanceOf(NoMazeBoardWithPatternsFactory.class, factory);
    }

    @Test
    void getFactoryForBoxMode() {
        // Given
        String mode = "Box";
        
        // When
        BoardWithPatternsFactory factory = BoardWithPatternsFactoryProvider.getFactory(mode);
        
        // Then
        assertNotNull(factory);
        assertInstanceOf(BoxBoardWithPatternsFactory.class, factory);
    }

    @Test
    void getFactoryForUnknownMode() {
        // Given
        String mode = "UnknownMode";
        
        // When
        BoardWithPatternsFactory factory = BoardWithPatternsFactoryProvider.getFactory(mode);
        
        // Then
        assertNotNull(factory);
        assertInstanceOf(NoMazeBoardWithPatternsFactory.class, factory); // Should default to NoMaze
    }

    @Test
    void getFactoryForNullMode() {
        // Given
        String mode = null;
        
        // When
        BoardWithPatternsFactory factory = BoardWithPatternsFactoryProvider.getFactory(mode);
        
        // Then
        assertNotNull(factory);
        assertInstanceOf(NoMazeBoardWithPatternsFactory.class, factory); // Should default to NoMaze
    }
}
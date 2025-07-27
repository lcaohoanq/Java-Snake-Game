package views.game;

import enums.GameMode;
import lombok.extern.slf4j.Slf4j;
import models.UserScore;
import views.base.BoardWithPatterns;
import views.game.factory.BoardWithPatternsFactory;
import views.game.factory.BoardWithPatternsFactoryProvider;

import javax.swing.*;
import java.awt.*;

/**
 * Snake game implementation using design patterns
 */
@Slf4j
public class SnakeWithPatterns extends JFrame {

    // Singleton instance
    private static SnakeWithPatterns instance;
    
    private BoardWithPatterns board;
    private UserScore currentUser;
    private GameMode mode;

    private SnakeWithPatterns() {
        initUI();
    }

    private SnakeWithPatterns(UserScore user, GameMode mode) {
        this.currentUser = user;
        this.mode = mode;
        initUI();
    }
    
    /**
     * Get the singleton instance of SnakeWithPatterns
     * If an instance already exists, it will be disposed and a new one created
     */
    public static synchronized SnakeWithPatterns getInstance(UserScore user, GameMode mode) {
        if (instance != null) {
            instance.dispose();
        }
        instance = new SnakeWithPatterns(user, mode);
        return instance;
    }
    
    /**
     * Get the singleton instance with default parameters
     */
    public static synchronized SnakeWithPatterns getInstance() {
        if (instance == null) {
            instance = new SnakeWithPatterns();
        }
        return instance;
    }

    private void initUI() {
        // Use the factory pattern to create the appropriate board
        BoardWithPatternsFactory factory = BoardWithPatternsFactoryProvider.getFactory(mode);
        board = factory.createBoard(currentUser);
        
        add(board);

        setResizable(false);
        pack();

        setTitle("Snake Game with Design Patterns");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = SnakeWithPatterns.getInstance();
            ex.setVisible(true);
        });
    }
}
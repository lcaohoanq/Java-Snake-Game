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

    private BoardWithPatterns board;
    private UserScore currentUser;
    private GameMode mode;

    public SnakeWithPatterns() {
        initUI();
    }

    public SnakeWithPatterns(UserScore user, GameMode mode) {
        this.currentUser = user;
        this.mode = mode;
        initUI();
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
            JFrame ex = new SnakeWithPatterns();
            ex.setVisible(true);
        });
    }
}
package views;

import java.awt.EventQueue;
import javax.swing.JFrame;

import models.Board;

public class Snake {
    private JFrame snakeFrame;

    public Snake() {
        initUI();
    }

    private void initUI() {
        snakeFrame = new JFrame();
        snakeFrame.add(new Board());

        snakeFrame.setResizable(false);
        snakeFrame.pack();

        snakeFrame.setTitle("Snake");
        snakeFrame.setLocationRelativeTo(null);
        snakeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        snakeFrame.setVisible(false);
    }

    public void startGame() {
        snakeFrame.setVisible(true);
    }

    public void stopGame() {
        snakeFrame.dispose();
    }
}

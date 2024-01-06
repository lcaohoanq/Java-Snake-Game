package views;

import javax.swing.JFrame;

import models.Board;

public class Snake extends JFrame {

    public Snake() {
        initUI();
    }

    private void initUI() {
        add(new Board());

        setResizable(false);
        pack();
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
    }

    public void startGame() {
        setVisible(true);
    }

    public void stopGame() {
        dispose();
    }
}

package views;

import javax.swing.JFrame;

import models.board.*;

public class Snake extends JFrame {
    String mode;

    public Snake(String mode) {
        initUI(mode);
    }

    private void initUI(String mode) {
        checkMode(mode);
        setResizable(false);
        pack();
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(false);
    }

    private void checkMode(String mode) {
        if (mode.equals("Classic")) {
            add(new NoMaze()); // NoMaze is the default mode
        }
        if (mode.equals("NoMaze")) {
            add(new NoMaze());  // NoMaze is the default mode
        }
        if (mode.equals("Box")) {
            add(new Box());
        }
        if (mode.equals("Tunnel")) {
            add(new Tunnel());
        }
        if (mode.equals("Mill")) {
            add(new Mill());
        }
        if (mode.equals("Rails")) {
            add(new Rails());
        }
        if (mode.equals("Apartment")) {
            add(new Apartment());
        }
        if (mode.equals("Campaign")) {
            add(new Campaign());
        }
    }

    public void startGame() {
        setVisible(true);
    }

    public void stopGame() {
        dispose();
    }
}

package views;

import javax.swing.JFrame;

import models.BoxMode;
import models.NoMazeMode;
import models.TunnelMode;

public class Snake extends JFrame {

    String mode;

    public Snake(String mode) {
        initUI(mode);
    }

    private void initUI(String mode) {
        if (mode.equals("Box")) {
            add(new BoxMode());
        }
        if (mode.equals("Tunnel")) {
            add(new TunnelMode());
        }
        if (mode.equals("NoMaze")) {
            add(new NoMazeMode());
        }

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

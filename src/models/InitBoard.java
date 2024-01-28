package models;

import javax.swing.*;

import constants.Paths;
import utils.AudioHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public abstract class InitBoard extends JPanel implements ActionListener {
    public static int score = 0;
    public static boolean inGame = true;
    protected final int B_WIDTH = 800;
    protected final int B_HEIGHT = 850;
    protected final int DOT_SIZE = 10;
    protected final int ALL_DOTS = 900;
    protected final int RAND_POS = 69;
    protected final int DELAY = 50;
    protected final int[] x = new int[ALL_DOTS];
    protected final int[] y = new int[ALL_DOTS];
    protected int dots;
    protected int apple_count = 0;
    protected int apple_x;
    protected int bigApple_x;
    protected int bigApple_y;
    protected int apple_y;
    protected boolean leftDirection = false;
    protected boolean rightDirection = true;
    protected boolean upDirection = false;
    protected boolean downDirection = false;
    protected Timer timer;
    protected Timer bigAppleTimer;
    protected final int DECREASE_DELAY = 2;
    protected Image ball;
    protected Image apple;
    protected Image head;
    protected Image bigApple;
    protected Image wall;

    protected JButton playAgainButton;
    protected JButton exitButton;
    protected JLabel scoreLabel;
    protected final int BIG_APPLE_TIMER = 5000;
    protected JProgressBar bigAppleProgressBar;
    protected int line;
    protected JPanel bottomPanel = new JPanel();

    public InitBoard() {
        initBoard();
    }

    protected void initBoard() {
        System.out.println("Hell Mode!");
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);
        bottomPanel.setVisible(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setLayout(new BorderLayout());
        loadImages();
        initGame();
        initLine();
        initBottomPanel();
        initPlayAgainButton();
        initExitButton();

    }

    protected void initLine() {
        line = B_HEIGHT - 50; // Adjust this value as needed
    }

    protected abstract void initBottomPanel();

    public void renderProgressBar() {
        // Display the progress bar
        bigAppleProgressBar.setVisible(true);
        // Start the progress bar
        bigAppleProgressBar.setValue(100);
        // Start the timer
        Timer progressBarTimer = new Timer(45, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = bigAppleProgressBar.getValue();
                if (value > 0) {
                    bigAppleProgressBar.setValue(value - 1);
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        progressBarTimer.start();
    }
    
    protected abstract void initPlayAgainButton();

    protected abstract void initExitButton();

    protected abstract void loadImages();

    protected abstract void initGame();

    protected abstract void doDrawing(Graphics g);

    protected abstract void updateScore();

    protected abstract void gameOver(Graphics g);

    protected abstract void resetGame();

    protected abstract void checkApple();

    protected void checkScore() {
        score++;
    }

    protected void checkBigScore() {
        score += 5;
    }

    public void move() {

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    protected abstract void checkCollision();

    protected abstract void locateApple();

    public abstract void locateBigApple();

    public void setBigAppleTime() {
        // neu ma bigAppleTimer dang null thi tao mot timer moi
        if (bigAppleTimer != null) {
            bigAppleTimer.stop();
        }

        bigAppleTimer = new Timer(BIG_APPLE_TIMER, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bigAppleTimer.stop();
                AudioHandler.playAudio(Paths.URL_BIG_APPLE_DIS);
                apple_count = 0;
                locateApple();
                bigAppleProgressBar.setVisible(false);
            }
        });
        bigAppleProgressBar.setVisible(true);
        bigAppleTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    protected class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }

}

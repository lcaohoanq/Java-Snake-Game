package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import constants.Path;
import model.Account;
import model.Score;
import utils.AudioHandler;
import utils.DataHandler;

public class Board extends JPanel implements ActionListener {

    private final int B_WIDTH = 500;
    private final int B_HEIGHT = 500;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private int DELAY = 50;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    public static int score = 0;
    private int dots;
    private int apple_x;
    private int apple_y;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    public static boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;

    private JButton playAgainButton;
    private JButton exitButton;
    private boolean scoreWrittenToFile = false;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setLayout(null);
        loadImages();
        initGame();
        initPlayAgainButton();
        initExitButton();

    }

    private void initPlayAgainButton() {
        playAgainButton = new JButton("Play Again");
        playAgainButton.setFont(new Font("Roboto", Font.BOLD, 10));
        playAgainButton.setBackground(Color.GREEN);
        playAgainButton.setForeground(Color.WHITE);
        playAgainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Reset game parameters and restart the game
                resetGame();
            }
        });
        playAgainButton.setSize(100, 50);
        add(playAgainButton);
        playAgainButton.setVisible(false); // Initially, hide the button
    }

    private void initExitButton() {
        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Roboto", Font.BOLD, 10));
        exitButton.setBackground(Color.RED);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        exitButton.setSize(100, 50);
        add(exitButton);
        exitButton.setVisible(false); // Initially, hide the button
    }

    private void loadImages() {

        ImageIcon iid = new ImageIcon("src/resources/dot.png");
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon("src/resources/apple.png");
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon("src/resources/head.png");
        head = iih.getImage();
    }

    private void initGame() {

        dots = 3;

        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        locateApple();
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        if (inGame) {

            g.drawImage(apple, apple_x, apple_y, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {
            gameOver(g);
            drawScore(g);
            writeScoreToFile();
        }
    }

    private void writeScoreToFile() {
        if (!scoreWrittenToFile) {
            String username = LoginFormController.username;
            DataHandler.scoreList.add(new Score(username, score));
            System.out.println("Data Score " + DataHandler.scoreList);

            if (DataHandler.writeScore(Path.urlScore)) {
                scoreWrittenToFile = true;
                System.out.println(scoreWrittenToFile);
                System.out.println("sau khi ghi file ");
            } else {
                System.out.println("Failed to write score to file");
            }
        }
    }

    private void gameOver(Graphics g) {

        String msg = "Game Over";
        Font big = new Font("Roboto", Font.BOLD, 30);
        FontMetrics metr = getFontMetrics(big);

        g.setColor(Color.white);
        g.setFont(big);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
        // Show the "Play Again" and "Exit" button after displaying "Game Over" message
        playAgainButton.setVisible(true);
        playAgainButton.setBounds((B_WIDTH - 220) / 2, B_HEIGHT / 2 + 30, 100, 30);
        exitButton.setVisible(true);
        exitButton.setBounds((B_WIDTH - 220) / 2 + 120, B_HEIGHT / 2 + 30, 100, 30);
    }

    private void drawScore(Graphics g) {
        String msg = " Your score is " + score;
        Font small = new Font("Roboto", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 1, B_HEIGHT / 1);
    }

    private void resetGame() {
        // Reset game variables here
        // For example:
        score = 0;
        dots = 3;
        inGame = true;
        // Reset any other necessary game state variables

        // Hide the "Play Again" button again
        playAgainButton.setVisible(false);
        exitButton.setVisible(false);

        // Restart the timer and initialize the game
        timer.stop();
        initGame();
        timer.start();
    }

    private void checkApple() {

        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            dots++;
            checkScore();
            locateApple();
            AudioHandler.playAudio("src/resources/eating2.wav");
        }
    }

    private void checkScore() {
        score++;
    }

    private void move() {

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

    private void checkCollision() {

        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            y[0] = 0;
        }

        if (y[0] < 0) {
            y[0] = B_HEIGHT - DOT_SIZE;
        }

        if (x[0] >= B_WIDTH) {
            x[0] = 0;
        }

        if (x[0] < 0) {
            x[0] = B_WIDTH - DOT_SIZE;
        }

        if (!inGame) {
            AudioHandler.playAudio("src/resources/gameover.wav");
            timer.stop();
        }
    }

    private void locateApple() {

        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

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

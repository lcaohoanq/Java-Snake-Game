package models;

import javax.swing.*;

import constants.Paths;
import controllers.LoginController;
import styles.Borders;
import styles.Colors;
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

    public void initBottomPanel() {
        // Initialize the JLabel for live score display
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setForeground(Color.white);
        scoreLabel.setFont(new Font("Roboto", Font.BOLD, 14));
        scoreLabel.setBounds(10, B_HEIGHT - 30, 100, 20);
        scoreLabel.setVisible(true);

        // Initialize the JProgressBar for big apple countdown
        bigAppleProgressBar = new JProgressBar(0, 100);
        bigAppleProgressBar.setPreferredSize(new Dimension(400, 20));
        bigAppleProgressBar.setValue(100);
        bigAppleProgressBar.setStringPainted(true);
        bigAppleProgressBar.setForeground(new Color(250, 0, 0));
        bigAppleProgressBar.setBackground(new Color(240, 240, 240));
        bigAppleProgressBar.setVisible(false);

        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBackground(Colors.OTHER_OPTIONS);
        bottomPanel.setBorder(Borders.BOTTOM_SCORE_PROGRESS_BAR);
        bottomPanel.add(scoreLabel, BorderLayout.WEST);
        bottomPanel.add(bigAppleProgressBar, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    }

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

    public void initPlayAgainButton() {
        playAgainButton = new JButton("Play Again");
        playAgainButton.setFont(new Font("Roboto", Font.BOLD, 10));
        playAgainButton.setBackground(Color.GREEN);
        playAgainButton.setForeground(Color.WHITE);
        playAgainButton.addActionListener(e -> {
            // Reset game parameters and restart the game
            resetGame();
        });
        playAgainButton.setSize(100, 50);
        add(playAgainButton, BorderLayout.WEST);
        playAgainButton.setVisible(false); // Initially, hide the button
    }

    public void initExitButton() {
        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Roboto", Font.BOLD, 10));
        exitButton.setBackground(Color.RED);
        exitButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        exitButton.setSize(100, 50);
        add(exitButton, BorderLayout.EAST);
        exitButton.setVisible(false); // Initially, hide the button
    }

    protected void loadImages() {
        ImageIcon iid = new ImageIcon(Paths.URL_DOT);
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon(Paths.URL_APPLE);
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon(Paths.URL_HEAD);
        head = iih.getImage();

        ImageIcon iib = new ImageIcon(Paths.URL_BIG_APPLE);
        bigApple = iib.getImage();
    };

    public void initGame() {

        dots = 3;
        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        locateApple();
        timer = new Timer(DELAY, this);
        System.out.println(timer.getDelay());
        timer.start();
    }

    public void doDrawing(Graphics g) {
        if (inGame) {
            if (apple_count % 5 == 0 && apple_count != 0) {
                g.drawImage(bigApple, bigApple_x, bigApple_y, this);
            } else {
                g.drawImage(apple, apple_x, apple_y, this);
            }

            scoreLabel.setText("Score: " + score);
            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.setColor(Color.ORANGE); // Head color
                } else {
                    g.setColor(Color.GREEN); // Body color
                }
                g.fillOval(x[z], y[z], DOT_SIZE, DOT_SIZE);
            }
            // what is the purpose of above for loop?

            Toolkit.getDefaultToolkit().sync();
        } else {
            gameOver(g);
            updateScore();
        }
    }

    public void updateScore() {
        String username = LoginController.username;
        String score;
        if (username != null) {
            score = String.valueOf(NoMazeMode.score);
            // DBServices.excuteOther();
            // DBServices.updateUsernameScore(username, score);
        }
    }

    public void gameOver(Graphics g) {

        String msg = "Game Over";
        Font big = new Font("Roboto", Font.BOLD, 30);
        FontMetrics metr = getFontMetrics(big);

        g.setColor(Color.white);
        g.setFont(big);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, (B_HEIGHT - 50) / 2);
        // Show the "Play Again" and "Exit" button after displaying "Game Over" message
        playAgainButton.setVisible(true);
        playAgainButton.setBounds((B_WIDTH - 220) / 2, B_HEIGHT / 2 + 30, 100, 30);
        exitButton.setVisible(true);
        exitButton.setBounds((B_WIDTH - 220) / 2 + 120, B_HEIGHT / 2 + 30, 100, 30);

        // Hide the progress bar
        bigAppleProgressBar.setVisible(false);
    }

    public void resetGame() {
        // Reset game variables here
        // For example:
        score = 0;
        dots = 3;
        apple_count = 0;
        inGame = true;
        bigApple_x = -100;
        bigApple_y = -100;

        // Reset the snake's direction, auto move to right when re-start
        leftDirection = false;
        rightDirection = true;
        upDirection = false;
        downDirection = false;

        // Reset the snake's position
        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
        // Reset any other necessary game state variables

        // Hide the "Play Again" button again
        playAgainButton.setVisible(false);
        exitButton.setVisible(false);

        // Restart the timer and initialize the game
        timer.stop();
        initGame();
        timer.start();
    }

    public void checkApple() {

        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            dots++;
            checkScore();
            apple_count++;
            locateApple();
            if (score % 5 != 0) {
                AudioHandler.playAudio(Paths.URL_EATING);
            }
            System.out.println("apple");
            return;
        }
        if ((x[0] >= bigApple_x) && (x[0] <= bigApple_x + 2 * DOT_SIZE)
                && (y[0] >= bigApple_y) && (y[0] <= bigApple_y + 2 * DOT_SIZE)) {
            dots += 5;
            checkBigScore();

            // change the game speed
            int newDelay = Math.max(timer.getDelay() - DECREASE_DELAY, 0);
            timer.setDelay(newDelay);
            System.out.println(timer.getDelay());

            // disable the big apple progress bar
            bigAppleProgressBar.setVisible(false);

            // check the big apple are eaten
            bigAppleTimer.stop();

            apple_count = 0;
            locateApple();
            System.out.println("big apple");
            AudioHandler.playAudio(Paths.URL_EATING2);
        }
    }

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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
        g.setColor(Color.white);
        g.drawLine(0, line, B_WIDTH, line);
    }

    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkApple();
            checkCollision();
            move();
        }

        repaint();
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

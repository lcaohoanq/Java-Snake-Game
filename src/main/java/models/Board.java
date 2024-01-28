package models;

import constants.Messages;
import constants.Paths;
import constants.Sizes;
import constants.Titles;
import controllers.LoginController;
import services.DBServices;
import styles.Borders;
import styles.Colors;
import styles.Fonts;
import utils.AudioHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements ActionListener {

    // Board dimensions and settings
    private final int DOT_SIZE = 10;        // Size of the snake's body
    private final int ALL_DOTS = 900;       // Maximum number of dots on the board
    private final int RAND_POS = 29;        // Random positioning parameter
    private final int DELAY = 50;           // Timer delay for the game loop
    // Snake position and movement
    private final int[] x = new int[ALL_DOTS];  // X-coordinate of each snake dot
    private final int[] y = new int[ALL_DOTS];  // Y-coordinate of each snake dot
    private final int DECREASE_DELAY = 2;       // Decrease delay for faster snake movement
    // Game state variables
    private int score = 0;            // Player's score
    private boolean inGame = true;    // Flag indicating whether the game is currently active
    private int dots;                          // Current number of snake dots
    private int apple_count = 0;               // Counter for regular apples
    private int apple_x;                       // X-coordinate of a regular apple
    private int bigApple_x;                    // X-coordinate of a big apple
    private int bigApple_y;                    // Y-coordinate of a big apple
    private int apple_y;                       // Y-coordinate of a regular apple

    // Snake movement directions
    private boolean leftDirection = false;     // Flag for moving left
    private boolean rightDirection = true;     // Flag for moving right
    private boolean upDirection = false;       // Flag for moving up
    private boolean downDirection = false;     // Flag for moving down

    // Game timers and images
    private Timer timer;                       // Timer for regular game events
    private Timer bigAppleTimer;               // Timer for big apple appearance
    private Image ball;                        // Snake body image
    private Image apple;                       // Regular apple image
    private Image head;                        // Snake head image
    private Image bigApple;                    // Big apple image

    // UI components
    private JButton playAgainButton;           // Button to play the game again
    private JButton exitButton;                // Button to exit the game
    private JLabel scoreLabel;                 // Label to display the player's score
    private int lineBottom;                    // Bottom line
    private JProgressBar bigAppleProgressBar;  // Progress bar for big apple timer
    private JPanel bottomPanel = new JPanel(); // Panel for UI components at the bottom

    public Board() {
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setBackground(Colors.OTHER_OPTIONS_L);
        setFocusable(true);
        bottomPanel.setVisible(true);
        setPreferredSize(Sizes.SIZE_BOARD);
        setLayout(new BorderLayout());
        loadImages();
        initGame();

        initBottomPanel();
        initLine();
        initPlayAgainButton();
        initExitButton();

    }

    private void initLine() {
        lineBottom = Sizes.HEIGHT_BOARD - Sizes.LINE_SPACE_FROM_BOTTOM; // Adjust this value as needed
    }

    private void initScoreLabel() {
        // Initialize the JLabel for live score display
        scoreLabel = new JLabel(Titles.SCORE_LIVE);
        scoreLabel.setForeground(Color.white);
        scoreLabel.setFont(Fonts.SCORE_LIVE);
        scoreLabel.setBounds(10, Sizes.HEIGHT_BOARD - 30, 100, 20);
        scoreLabel.setVisible(true);
    }

    private void initProgressBar() {
        // Initialize the JProgressBar for big apple countdown
        bigAppleProgressBar = new JProgressBar(Sizes.MIN_PROGRESS_BAR, Sizes.MAX_PROGRESS_BAR);
        bigAppleProgressBar.setPreferredSize(Sizes.SIZE_PROGRESS_BAR);
        bigAppleProgressBar.setValue(100);
        bigAppleProgressBar.setStringPainted(true);
        bigAppleProgressBar.setForeground(Colors.PROGRESS_BAR_LOADING);
        bigAppleProgressBar.setBackground(Colors.PRIMARY_COLOR_L);
        bigAppleProgressBar.setVisible(false);
    }

    private void initBottomPanel() {
        initScoreLabel();
        initProgressBar();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBackground(Colors.OTHER_OPTIONS_L);
        bottomPanel.setBorder(Borders.BOTTOM_SCORE_PROGRESS_BAR);
        bottomPanel.add(scoreLabel, BorderLayout.WEST);
        bottomPanel.add(bigAppleProgressBar, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void renderProgressBar() {
        // Display the progress bar
        bigAppleProgressBar.setVisible(true);
        // Start the progress bar
        bigAppleProgressBar.setValue(100);
        // Start the timer
        Timer progressBarTimer = new Timer(45, e -> {
            int value = bigAppleProgressBar.getValue();
            if (value > 0) {
                bigAppleProgressBar.setValue(value - 1);
            } else {
                ((Timer) e.getSource()).stop();
            }
        });
        progressBarTimer.start();
    }

    private void initPlayAgainButton() {
        playAgainButton = new JButton(Titles.PLAY_AGAIN);
        playAgainButton.setFont(Fonts.PLAY_EXIT_BUTTON);
        playAgainButton.setBackground(Colors.TEXT_COLOR_L);
        playAgainButton.setForeground(Colors.PRIMARY_COLOR_L);
        playAgainButton.addActionListener(e -> {
            // Reset game parameters and restart the game
            resetGame();
        });
        playAgainButton.setPreferredSize(Sizes.SIZE_BUTTON_GAME_OVER);
        add(playAgainButton, BorderLayout.WEST);
        playAgainButton.setVisible(false); // Initially, hide the button
    }

    private void initExitButton() {
        exitButton = new JButton(Titles.EXIT);
        exitButton.setFont(Fonts.PLAY_EXIT_BUTTON);
        exitButton.setBackground(Colors.PROGRESS_BAR_LOADING);
        exitButton.setForeground(Colors.PRIMARY_COLOR_L);
        exitButton.addActionListener(e -> {
            if (Messages.IS_CONFIRM_EXIT() == JOptionPane.YES_OPTION) {
                SwingUtilities.getWindowAncestor(this).dispose();
            }
        });
        exitButton.setPreferredSize(Sizes.SIZE_BUTTON_GAME_OVER);
        add(exitButton, BorderLayout.EAST);
        exitButton.setVisible(false); // Initially, hide the button
    }

    private void loadImages() {

        ball = new ImageIcon(Paths.URL_DOT).getImage();

        apple = new ImageIcon(Paths.URL_APPLE).getImage();

        head = new ImageIcon(Paths.URL_HEAD).getImage();

        bigApple = new ImageIcon(Paths.URL_BIG_APPLE).getImage();
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
        g.setColor(Colors.PRIMARY_COLOR_L);
        g.drawLine(0, lineBottom, Sizes.WIDTH_BOARD, lineBottom);
    }

    private void doDrawing(Graphics g) {
        if (inGame) {
            if (apple_count % 5 == 0 && apple_count != 0) {
                g.drawImage(bigApple, bigApple_x, bigApple_y, this);
            } else {
                g.drawImage(apple, apple_x, apple_y, this);
            }
            scoreLabel.setText("Score: " + score);
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
            updateScore();
        }
    }

    private void updateScore() {
        String username = LoginController.username;
        if (username.isEmpty()) {
            return;
        }
        DBServices.executeOther();
        DBServices.updateUsernameScore(username, String.valueOf(this.score));
    }

    private void gameOver(Graphics g) {

        String msg = Titles.GAME_OVER;
        FontMetrics metr = getFontMetrics(Fonts.GAME_OVER);

        g.setColor(Colors.PRIMARY_COLOR_L);
        g.setFont(Fonts.GAME_OVER);
        g.drawString(msg, (Sizes.WIDTH_BOARD - metr.stringWidth(msg)) / 2, (Sizes.HEIGHT_BOARD - 100) / 2);
        // Show the "Play Again" and "Exit" button after displaying "Game Over" message
        playAgainButton.setVisible(true);
        playAgainButton.setBounds((Sizes.WIDTH_BOARD - 260) / 2, (Sizes.HEIGHT_BOARD - 50) / 2, 130, 50);
        exitButton.setVisible(true);
        exitButton.setBounds((Sizes.WIDTH_BOARD - 260) / 2 + 140, (Sizes.HEIGHT_BOARD - 50) / 2, 130, 50);

        // Hide the progress bar
        bigAppleProgressBar.setVisible(false);
    }

    private void resetGame() {
        // Reset game variables here
        // For example:
        score = 0;
        dots = 3;
        apple_count = 0;
        inGame = true;
        bigApple_x = -100;
        bigApple_y = -100;
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

    private void checkApple() {

        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            dots++;
            checkScore();
            apple_count++;
            locateApple();
            if (score % 5 != 0) {
                if (isOnSound()) {
                    setAudio(Paths.URL_EATING);
                }
            }
            return;
        }
        if ((x[0] >= bigApple_x) && (x[0] <= bigApple_x + 2 * DOT_SIZE)
                && (y[0] >= bigApple_y) && (y[0] <= bigApple_y + 2 * DOT_SIZE)) {
            dots += 5;
            checkBigScore();

            // change the game speed
            int newDelay = Math.max(timer.getDelay() - DECREASE_DELAY, 0);
            timer.setDelay(newDelay);

            // disable the big apple progress bar
            bigAppleProgressBar.setVisible(false);

            // check the big apple are eaten
            bigAppleTimer.stop();

            apple_count = 0;
            locateApple();
            if (isOnSound()) {
                setAudio(Paths.URL_EATING2);
            }
        }
    }

    private void checkScore() {
        score++;
    }

    private void checkBigScore() {
        score += 5;
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
                break;
            }
        }

        if (y[0] >= Sizes.HEIGHT_BOARD - 50) {
            y[0] = 0;
        }

        if (y[0] < 0) {
            y[0] = Sizes.HEIGHT_BOARD - 50 - DOT_SIZE;
        }

        if (x[0] >= Sizes.WIDTH_BOARD) {
            x[0] = 0;
        }

        if (x[0] < 0) {
            x[0] = Sizes.WIDTH_BOARD - DOT_SIZE;
        }

        if (!inGame) {
            if (isOnSound()) {
                setAudio(Paths.URL_GAME_OVER);
            }
            timer.stop();
        }
    }

    private void locateApple() {

        if (apple_count % 5 == 0 && apple_count != 0) {
            locateBigApple();
        } else {
            bigApple_x = -100;
            int r = (int) (Math.random() * RAND_POS);
            apple_x = ((r * DOT_SIZE));

            bigApple_y = -100;
            r = (int) (Math.random() * RAND_POS);
            apple_y = ((r * DOT_SIZE) + DOT_SIZE);
        }
    }

    public void locateBigApple() {
        if (isOnSound()) {
            setAudio(Paths.URL_BIG_APPLE_APP);
        }
        int r = (int) (Math.random() * RAND_POS);
        bigApple_x = ((r * DOT_SIZE));
        apple_x = -100;

        r = (int) (Math.random() * RAND_POS);
        bigApple_y = ((r * DOT_SIZE));
        apple_y = -100;
        setBigAppleTime();
        renderProgressBar();

    }

    public void setBigAppleTime() {
        // neu ma bigAppleTimer dang null thi tao mot timer moi
        if (bigAppleTimer != null) {
            bigAppleTimer.stop();
        }

        // Apple-related variables
        // Timer for big apple appearance
        int BIG_APPLE_TIMER = 5000;
        bigAppleTimer = new Timer(BIG_APPLE_TIMER, e -> {
            bigAppleTimer.stop();
            if (isOnSound()) {
                setAudio(Paths.URL_BIG_APPLE_DIS);
            }
            apple_count = 0;
            locateApple();
            bigAppleProgressBar.setVisible(false);
        });
        bigAppleProgressBar.setVisible(true);
        bigAppleTimer.start();
    }

    private boolean isOnSound() {
        return !AudioHandler.isEmptyPath();
    }

    private void setAudio(String path) {
        AudioHandler.playAudio(path);
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
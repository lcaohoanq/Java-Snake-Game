package models;

import constants.Paths;
import constants.Sizes;
import controllers.LoginController;
import services.DBServices;
import styles.Borders;
import styles.Colors;
import utils.AudioHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements ActionListener {

    public static int score = 0;
    public static boolean inGame = true;
    private final int B_WIDTH = 500;
    private final int B_HEIGHT = 550;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 50;
    private final int[] x = new int[ALL_DOTS];
    private final int[] y = new int[ALL_DOTS];
    private int dots;
    private int apple_count = 0;
    private int apple_x;
    private int bigApple_x;
    private int bigApple_y;
    private int apple_y;
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private Timer timer;
    private Timer bigAppleTimer;
    private final int DECREASE_DELAY = 2;
    private Image ball;
    private Image apple;
    private Image head;
    private Image bigApple;

    private JButton playAgainButton;
    private JButton exitButton;
    private JLabel scoreLabel;
    private final int BIG_APPLE_TIMER = 5000;
    private JProgressBar bigAppleProgressBar;
    private int line;
    private JPanel bottomPanel = new JPanel();

    public Board() {
        initBoard();
    }

    private void initBoard() {
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

    private void initLine() {
        line = B_HEIGHT - 50; // Adjust this value as needed
    }

    private void initBottomPanel() {
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

    private void renderProgressBar() {
        // Display the progress bar
        bigAppleProgressBar.setVisible(true);

        // Reset the progress bar value to 100
        bigAppleProgressBar.setValue(100);

        Timer timer = new Timer(50, new ActionListener() {
            private int counter = 100;

            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the snake has hit the big apple or if the game is over
                if (apple_count == 0 || !inGame) {
                    counter = 100; // Reset the counter
                    bigAppleProgressBar.setValue(counter);
                    bigAppleProgressBar.setVisible(false); // Hide the progress bar
                    ((Timer) e.getSource()).stop(); // Stop the timer
                } else {
                    counter -= 2;
                    bigAppleProgressBar.setValue(counter);
                }
            }
        });

        // Start the timer
        timer.start();
    }

    private void initPlayAgainButton() {
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

    private void initExitButton() {
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

    private void loadImages() {

        ImageIcon iid = new ImageIcon(Paths.URL_DOT);
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon(Paths.URL_APPLE);
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon(Paths.URL_HEAD);
        head = iih.getImage();

        ImageIcon iib = new ImageIcon(Paths.URL_BIG_APPLE);
        bigApple = iib.getImage();
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
        g.setColor(Color.white);
        g.drawLine(0, line, B_WIDTH, line);
    }

    private void doDrawing(Graphics g) {
        if (inGame) {
            if (apple_count % 5 == 0 && apple_count != 0) {
                g.drawImage(bigApple, bigApple_x, bigApple_y, this);
                renderProgressBar();
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
            // what is the purpose of above for loop?

            Toolkit.getDefaultToolkit().sync();
        } else {
            gameOver(g);
            updateScore();
        }
    }

    private void updateScore() {
        String username = LoginController.username;
        String score;
        if (username != null) {
            score = String.valueOf(Board.score);
            // DBServices.excuteOther();
            // DBServices.updateUsernameScore(username, score);
        }
    }

    private void gameOver(Graphics g) {

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

        if (y[0] >= B_HEIGHT - 50) {
            y[0] = 0;
        }

        if (y[0] < 0) {
            y[0] = B_HEIGHT - 50 - DOT_SIZE;
        }

        if (x[0] >= B_WIDTH) {
            x[0] = 0;
        }

        if (x[0] < 0) {
            x[0] = B_WIDTH - DOT_SIZE;
        }

        if (!inGame) {
            AudioHandler.playAudio(Paths.URL_GAME_OVER);
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
        AudioHandler.playAudio(Paths.URL_BIG_APPLE_APP);
        int r = (int) (Math.random() * RAND_POS);
        bigApple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        bigApple_y = ((r * DOT_SIZE));

        setBigAppleTime();

    }

    public void setBigAppleTime() {
        // neu ma bigAppleTimer dang null thi tao mot timer moi
        if (bigAppleTimer != null) {
            bigAppleTimer.stop();
        }

        bigAppleTimer = new Timer(BIG_APPLE_TIMER, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bigApple_x = -100;
                bigApple_y = -100;
                bigAppleTimer.stop();
                AudioHandler.playAudio(Paths.URL_BIG_APPLE_DIS);
                apple_count = 0;
                locateApple();
                bigAppleProgressBar.setVisible(false);
            }
        });
        bigAppleTimer.start();
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
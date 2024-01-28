package models;


import constants.Paths;
import controllers.LoginController;
import services.DBServices;
import styles.Borders;
import styles.Colors;
import utils.AudioHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TunnelMode extends InitBoard{

    protected int wallThickness = 20;

    public TunnelMode() {
        super();
    }

    @Override
    public void initLine() {
        line = B_HEIGHT - 50; // Adjust this value as needed
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
    public void loadImages() {

        ImageIcon iid = new ImageIcon(Paths.URL_DOT);
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon(Paths.URL_APPLE);
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon(Paths.URL_HEAD);
        head = iih.getImage();

        ImageIcon iib = new ImageIcon(Paths.URL_BIG_APPLE);
        bigApple = iib.getImage();

        ImageIcon iiw = new ImageIcon(Paths.URL_WALL);
        wall = iiw.getImage();
    }

    @Override
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
        g.setColor(Color.white);
        g.drawLine(0, line, B_WIDTH, line);
        for (int i = 0; i < 200; i += 20) {
            g.drawImage(wall, i, 0, this);
            g.drawImage(wall, i, B_HEIGHT - 70, this);
        }
        for (int i = 600; i < B_WIDTH; i += 20) {
            g.drawImage(wall, i, 0, this);
            g.drawImage(wall, i, B_HEIGHT - 70, this);
        }

        for (int i = 20; i < 200; i += 20) {
            g.drawImage(wall, 0, i, this);
            g.drawImage(wall, B_WIDTH - 20, i, this);
        }

        for (int i = 600; i < B_HEIGHT - 70; i += 20) {
            g.drawImage(wall, 0, i, this);
            g.drawImage(wall, B_WIDTH - 20, i, this);
        }

        for (int i = 220; i < 600; i += 20) {
            g.drawImage(wall, i, 200, this);
            g.drawImage(wall, i, B_HEIGHT - 270, this);
        }
    }

    @Override
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

    @Override
    public void updateScore() {
        String username = LoginController.username;
        String score;
        if (username != null) {
            score = String.valueOf(NoMazeMode.score);
            // DBServices.excuteOther();
            // DBServices.updateUsernameScore(username, score);
        }
    }

    @Override
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

    @Override
    public void resetGame() {
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

    @Override
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

    @Override
    public void checkScore() {
        score++;
    }

    @Override
    public void checkBigScore() {
        score += 5;
    }

    @Override
    public void checkCollision() {

        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
                break;
            }
        }

        if (y[0] > B_HEIGHT - 50 && ((x[0] >= 0 && x[0] <= 200) || (x[0] >= 600 && x[0] < B_WIDTH))) {
            inGame = false;
        }
    
        if (y[0] >= B_HEIGHT - 50 && (x[0] > 200 && x[0] < 600)) {
            y[0] = 0;
        }
    
        if (y[0] < 20 && ((x[0] >= 0 && x[0] <= 200) || (x[0] >= 600 && x[0] < B_WIDTH))) {
            inGame = false;
        }
    
        if (y[0] < 0 && (x[0] > 200 && x[0] < 600)) {
            y[0] = B_HEIGHT - 50;
        }
    
        if (x[0] > B_WIDTH && ((y[0] >= 0 && y[0] <= 200) || (y[0] >= B_HEIGHT - 270 && y[0] < B_HEIGHT - 50))) {
            inGame = false;
        }
    
        if (x[0] >= B_WIDTH && (y[0] > 200 && y[0] < B_HEIGHT - 250)) {
            x[0] = 0;
        }
    
        if (x[0] < 20 && ((y[0] >= 0 && y[0] <= 200) || (y[0] >= B_HEIGHT - 270 && y[0] < B_HEIGHT - 50))) {
            inGame = false;
        }
    
        if (x[0] < 0 && (y[0] > 200 && y[0] < B_HEIGHT - 250)) {
            x[0] = B_WIDTH;
        }

        if (!inGame) {
            AudioHandler.playAudio(Paths.URL_GAME_OVER);
            timer.stop();
        }
    }

    @Override
    public void locateApple() {

        if (apple_count % 5 == 0 && apple_count != 0) {
            locateBigApple();
        } else {
            bigApple_x = -100;
            int r = (int) (Math.random() * (RAND_POS - 2 * wallThickness));
            apple_x = ((r + wallThickness) * DOT_SIZE);

            bigApple_y = -100;
            r = (int) (Math.random() * (RAND_POS - 2 * wallThickness));
            apple_y = ((r + wallThickness) * DOT_SIZE);
        }
    }

    @Override
    public void locateBigApple() {
        AudioHandler.playAudio(Paths.URL_BIG_APPLE_APP);
        int r = (int) (Math.random() * (RAND_POS - 2 * wallThickness));
        bigApple_x = ((r + wallThickness) * DOT_SIZE);
        apple_x = -100;

        r = (int) (Math.random() * (RAND_POS - 2 * wallThickness));
        bigApple_y = ((r + wallThickness) * DOT_SIZE);
        apple_y = -100;
        setBigAppleTime();
        renderProgressBar();

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

}

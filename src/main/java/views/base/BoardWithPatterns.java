package views.base;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import models.UserScore;
import services.AudioService;
import services.ServiceLocator;
import services.UserService;
import views.UIPrompts;
import constants.ResourcePaths;
import styles.UISizes;
import styles.UILabels;
import styles.UIBorders;
import styles.UIColors;
import styles.UIFonts;
import views.MenuView;
import views.game.command.CommandInvoker;
import views.game.command.DirectionState;
import views.game.observer.AudioEventListener;
import views.game.observer.GameEventListener;
import views.game.observer.GameEventPublisher;
import views.game.observer.ScoreUpdateListener;
import views.game.strategy.MovementContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.InputStream;

/**
 * Abstract base class for game boards with design patterns applied
 */
@Slf4j
public abstract class BoardWithPatterns extends JPanel implements ActionListener {

    // Board dimensions and settings
    protected final int DOT_SIZE = 10;        // Size of the snake's body
    protected final int RAND_POS = 29;        // Random positioning parameter
    private final int ALL_DOTS = 900;       // Maximum number of dots on the board
    // Snake position and movement
    protected final int[] x = new int[ALL_DOTS];  // X-coordinate of each snake dot
    protected final int[] y = new int[ALL_DOTS];  // Y-coordinate of each snake dot
    private final int DELAY = 50;           // Timer delay for the game loop
    private final int DECREASE_DELAY = 2;       // Decrease delay for faster snake movement
    protected boolean inGame = true;    // Flag indicating whether the game is currently active
    protected int dots;                          // Current number of snake dots
    protected int apple_count = 0;               // Counter for regular apples
    protected int apple_x;                       // X-coordinate of a regular apple
    protected int bigApple_x;                    // X-coordinate of a big apple
    protected int bigApple_y;                    // Y-coordinate of a big apple
    protected int apple_y;                       // Y-coordinate of a regular apple
    // Game timers and images
    protected Timer timer;                       // Timer for regular game events
    
    // Design Pattern: Singleton for services
    protected final AudioService audioService = AudioService.getInstance();
    protected final UserService userService;
    
    // Design Pattern: Strategy for movement
    protected final DirectionState directionState = new DirectionState();
    protected final MovementContext movementContext;
    
    // Design Pattern: Command for input handling
    protected final CommandInvoker commandInvoker = new CommandInvoker();
    
    // Design Pattern: Observer for game events
    protected final GameEventPublisher eventPublisher = new GameEventPublisher();
    
    // Game state variables
    @Getter
    private int score = 0;            // Player's score
    private Timer bigAppleTimer;               // Timer for big apple appearance
    private Image ball;                        // Snake body image
    private Image apple;                       // Regular apple image
    private Image head;                        // Snake head image
    private Image bigApple;                    // Big apple image
    // UI components
    private JLabel gameOverLabel;              // Label to display the "Game Over" message
    private JPanel gameOverPanel;              // Panel for UI components at the game over
    private JButton playAgainButton;           // Button to play the game again
    private JButton exitButton;                // Button to exit the game
    private JPanel playAgainExitButtonPanel;   // Panel for UI components at the game over
    private JButton backToMainMenuButton;      // Button to go back to the main menu
    private JPanel backToMainMenuButtonPanel;  // Panel for UI components at the game over
    private JLabel scoreLabel;                 // Label to display the player's score
    private int lineBottom;                    // Bottom line
    private JProgressBar bigAppleProgressBar;  // Progress bar for big apple timer
    private final JPanel bottomPanel = new JPanel(); // Panel for UI components at the bottom
    private final JPanel gameOverButtonPanel = new JPanel(); // Panel for UI components at the game over

    private UserScore currentUser;

    // Add a default constructor
    public BoardWithPatterns() {
        userService = ServiceLocator.getInstance().getService(UserService.class);
        movementContext = new MovementContext(DOT_SIZE);
        log.info("Board created with no user");
        initBoard();
    }

    public BoardWithPatterns(UserScore user) {
        userService = ServiceLocator.getInstance().getService(UserService.class);
        movementContext = new MovementContext(DOT_SIZE);
        this.currentUser = user;
        log.info("Board created with user: {}", user != null ? user.getUsername() : "null");
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setBackground(UIColors.OTHER_OPTIONS_L);
        setFocusable(true);
        bottomPanel.setVisible(true);
        setPreferredSize(UISizes.SIZE_BOARD);

        setLayout(new BorderLayout());
        loadImages();
        initGame();

        initBottomPanel();
        initLine();
        initGameOverPanel();
        
        // Set up command invoker with default commands
        commandInvoker.setupDefaultCommands(movementContext, directionState);
        
        // Set up event listeners
        eventPublisher.addListener(new ScoreUpdateListener(scoreLabel));
        eventPublisher.addListener(new AudioEventListener(audioService.isSoundEnabled() ? 
                                                        new utils.AudioHandler() : null, 
                                                        audioService.isSoundEnabled()));
        eventPublisher.addListener(new GameEventListener() {
            @Override
            public void onAppleEaten(int score) {
                // Additional game logic for apple eaten
            }
            
            @Override
            public void onBigAppleEaten(int score, int newDelay) {
                // Additional game logic for big apple eaten
            }
            
            @Override
            public void onBigAppleAppeared() {
                // Additional game logic for big apple appeared
                renderProgressBar();
            }
            
            @Override
            public void onGameOver(int finalScore) {
                // Additional game logic for game over
                timer.stop();
            }
        });

        // Print debug info
        if (currentUser != null) {
            log.info("Game initialized for user: {}", currentUser.getUsername());
        } else {
            log.info("Game initialized with no user");
        }
    }

    private void initLine() {
        lineBottom =
            UISizes.HEIGHT_BOARD - UISizes.LINE_SPACE_FROM_BOTTOM; // Adjust this value as needed
    }

    private void initScoreLabel() {
        // Initialize the JLabel for live score display
        scoreLabel = new JLabel(UILabels.SCORE_LIVE);
        scoreLabel.setForeground(Color.white);
        scoreLabel.setFont(UIFonts.SCORE_LIVE);
        scoreLabel.setBounds(10, UISizes.HEIGHT_BOARD - 30, 100, 20);
        scoreLabel.setVisible(true);
    }

    private void initProgressBar() {
        // Initialize the JProgressBar for big apple countdown
        bigAppleProgressBar = new JProgressBar(UISizes.MIN_PROGRESS_BAR, UISizes.MAX_PROGRESS_BAR);
        bigAppleProgressBar.setPreferredSize(UISizes.SIZE_PROGRESS_BAR);
        bigAppleProgressBar.setValue(100);
        bigAppleProgressBar.setStringPainted(true);
        bigAppleProgressBar.setForeground(UIColors.PROGRESS_BAR_LOADING);
        bigAppleProgressBar.setBackground(UIColors.PRIMARY_COLOR_L);
        bigAppleProgressBar.setVisible(false);
    }

    private void initBottomPanel() {
        initScoreLabel();
        initProgressBar();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBackground(UIColors.OTHER_OPTIONS_L);
        bottomPanel.setBorder(UIBorders.BOTTOM_SCORE_PROGRESS_BAR);
        bottomPanel.add(scoreLabel, BorderLayout.WEST);
        bottomPanel.add(bigAppleProgressBar, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    protected void renderProgressBar() {
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

    private void initGameOverTitle() {
        gameOverPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        gameOverLabel = new JLabel(UILabels.GAME_OVER);
        gameOverPanel.setBackground(UIColors.OTHER_OPTIONS_L);
        gameOverLabel.setForeground(UIColors.PRIMARY_COLOR_L);
        gameOverLabel.setBackground(UIColors.OTHER_OPTIONS_L);
        gameOverLabel.setFont(UIFonts.GAME_OVER);
        gameOverLabel.setBounds((UISizes.WIDTH_BOARD - 260) / 2,
            (UISizes.HEIGHT_BOARD - 50) / 2 - 50, 260, 50);
        gameOverPanel.add(gameOverLabel);
    }

    private void initPlayAgainButton() {
        playAgainButton = new JButton(UILabels.PLAY_AGAIN);
        playAgainButton.setFont(UIFonts.PLAY_EXIT_BUTTON);
        playAgainButton.setBackground(UIColors.TEXT_COLOR_L);
        playAgainButton.setForeground(UIColors.PRIMARY_COLOR_L);
        playAgainButton.setPreferredSize(UISizes.SIZE_BUTTON_GAME_OVER);
        playAgainButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                // Reset game parameters and restart the game
                resetGame();
            });
        });
    }

    private void initExitButton() {
        exitButton = new JButton(UILabels.EXIT);
        exitButton.setFont(UIFonts.PLAY_EXIT_BUTTON);
        exitButton.setBackground(UIColors.PROGRESS_BAR_LOADING);
        exitButton.setForeground(UIColors.PRIMARY_COLOR_L);
        exitButton.addActionListener(e -> {
            if (UIPrompts.IS_CONFIRM_EXIT() == JOptionPane.YES_OPTION) {
                SwingUtilities.getWindowAncestor(this).dispose();
            }
        });
        exitButton.setPreferredSize(UISizes.SIZE_BUTTON_GAME_OVER);
    }

    private void initPlayAgainExitButtonPanel() {
        initPlayAgainButton();
        initExitButton();
        playAgainExitButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        playAgainExitButtonPanel.setBackground(UIColors.OTHER_OPTIONS_L);
        playAgainExitButtonPanel.add(playAgainButton);
        playAgainExitButtonPanel.add(exitButton);
    }

    private void initBackToMainMenuButton() {
        backToMainMenuButton = new JButton(UILabels.BACK_TO_MAIN_MENU);
        backToMainMenuButton.setFont(UIFonts.PLAY_EXIT_BUTTON);
        backToMainMenuButton.setBackground(UIColors.BACK_TO_MAIN_MENU);
        backToMainMenuButton.setForeground(UIColors.PRIMARY_COLOR_L);
        backToMainMenuButton.addActionListener(e -> {
            SwingUtilities.getWindowAncestor(this).dispose();
            new MenuView().setVisible(true);
        });
        backToMainMenuButton.setPreferredSize(UISizes.SIZE_BUTTON_GAME_OVER_BACK_TO_MAIN_MENU);
    }

    private void initBackToMainMenuButtonPanel() {
        initBackToMainMenuButton();
        backToMainMenuButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backToMainMenuButtonPanel.setBackground(UIColors.OTHER_OPTIONS_L);
        backToMainMenuButtonPanel.add(backToMainMenuButton);
    }

    private void initGameOverPanel() {
        initGameOverTitle();
        initPlayAgainExitButtonPanel();
        initBackToMainMenuButtonPanel();
        gameOverButtonPanel.setLayout(new BorderLayout());
        gameOverButtonPanel.setBackground(UIColors.OTHER_OPTIONS_L);
        gameOverButtonPanel.setBorder(UIBorders.GAME_OVER_ELEMENT);
        gameOverButtonPanel.add(gameOverPanel, BorderLayout.NORTH);
        gameOverButtonPanel.add(playAgainExitButtonPanel, BorderLayout.CENTER);
        gameOverButtonPanel.add(backToMainMenuButtonPanel, BorderLayout.SOUTH);
        gameOverButtonPanel.setVisible(false);
        add(gameOverButtonPanel, BorderLayout.CENTER);
    }

    protected void loadImages() {
        ball = new ImageIcon(getClass().getResource(ResourcePaths.URL_DOT)).getImage();
        apple = new ImageIcon(getClass().getResource(ResourcePaths.URL_APPLE)).getImage();
        head = new ImageIcon(getClass().getResource(ResourcePaths.URL_HEAD)).getImage();
        bigApple = new ImageIcon(getClass().getResource(ResourcePaths.URL_BIG_APPLE)).getImage();
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
        g.setColor(UIColors.PRIMARY_COLOR_L);
        g.drawLine(0, lineBottom, UISizes.WIDTH_BOARD, lineBottom);
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
        }
    }

    public int compareDatabaseAndCurrentScore(int dbScore, int currentScore) {
        return dbScore - currentScore;
    }

    public void updateScore() {
        if (currentUser == null) {
            log.info("No user logged in, score not saved");
            return;
        }

        try {
            // Get the database score
            UserScore dbUser = userService.findById(currentUser.getUserId());
            if (dbUser == null) {
                log.info("User not found in database, score not saved");
                return;
            }

            // Only update if current score is higher than stored score
            if (this.score > dbUser.getScore()) {
                log.info("New high score! Updating from {} to {}", dbUser.getScore(), this.score);
                userService.updateScore(currentUser, this.score);
                
                // Show a notification to the user
                JOptionPane.showMessageDialog(
                    this,
                    "New high score: " + this.score + "!",
                    "High Score",
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                log.info("Score not higher than previous best: {}", dbUser.getScore());
            }
        } catch (Exception e) {
            log.error("Error updating score: {}", e.getMessage(), e);
        }
    }

    // Make sure game over calls updateScore
    private void gameOver(Graphics g) {
        gameOverButtonPanel.setVisible(true);
        playAgainButton.setVisible(true);
        exitButton.setVisible(true);
        backToMainMenuButton.setVisible(true);
        bigAppleProgressBar.setVisible(false);
        
        // Notify observers of game over
        eventPublisher.notifyGameOver(score);
        
        // Call updateScore to update the user's score
        updateScore();
    }

    private void resetGame() {
        // Reset game variables here
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
        
        // Reset direction state
        directionState.resetToDefault();
        
        // Update movement strategy
        movementContext.setStrategy(
            directionState.isLeftDirection(),
            directionState.isRightDirection(),
            directionState.isUpDirection(),
            directionState.isDownDirection()
        );
        
        // Hide the "Play Again" button again
        playAgainButton.setVisible(false);
        exitButton.setVisible(false);
        backToMainMenuButton.setVisible(false);
        
        // Ensure that the gameOverButtonPanel is not visible
        gameOverButtonPanel.setVisible(false);
        
        // Restart the timer and initialize the game
        timer.stop();
        initGame();
        timer.start();
    }

    private void checkApple() {
        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            dots++;
            score++;
            apple_count++;
            locateApple();
            
            // Notify observers of apple eaten
            eventPublisher.notifyAppleEaten(score);
            
            return;
        }
        
        if ((x[0] >= bigApple_x) && (x[0] <= bigApple_x + 2 * DOT_SIZE)
            && (y[0] >= bigApple_y) && (y[0] <= bigApple_y + 2 * DOT_SIZE)) {
            dots += 5;
            score += 5;

            // change the game speed
            int newDelay = Math.max(timer.getDelay() - DECREASE_DELAY, 0);
            timer.setDelay(newDelay);

            // disable the big apple progress bar
            bigAppleProgressBar.setVisible(false);

            // check the big apple are eaten
            bigAppleTimer.stop();

            apple_count = 0;
            locateApple();
            
            // Notify observers of big apple eaten
            eventPublisher.notifyBigAppleEaten(score, newDelay);
        }
    }

    // Design Pattern: Strategy for movement
    private void move() {
        // Use the movement context to execute the current strategy
        movementContext.executeStrategy(x, y, dots);
    }

    protected abstract void checkCollision();

    protected abstract void locateApple();

    protected abstract void locateBigApple();

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
            if (audioService.isSoundEnabled()) {
                InputStream inputStream = getClass().getResourceAsStream(
                    ResourcePaths.URL_BIG_APPLE_DIS);
                audioService.playAudio(inputStream);
            }
            apple_count = 0;
            locateApple();
            bigAppleProgressBar.setVisible(false);
        });
        
        // Notify observers of big apple appeared
        eventPublisher.notifyBigAppleAppeared();
        
        bigAppleTimer.start();
    }

    protected boolean isOnSound() {
        return audioService.isSoundEnabled();
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
            // Design Pattern: Command for input handling
            // Use the command invoker to execute the appropriate command
            commandInvoker.executeCommand(e.getKeyCode());
        }
    }
}
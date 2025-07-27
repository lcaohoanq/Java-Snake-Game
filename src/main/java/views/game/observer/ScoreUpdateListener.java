package views.game.observer;

import javax.swing.JLabel;
import lombok.extern.slf4j.Slf4j;

/**
 * Concrete implementation of GameEventListener for updating score display
 */
@Slf4j
public class ScoreUpdateListener implements GameEventListener {
    
    private final JLabel scoreLabel;
    
    public ScoreUpdateListener(JLabel scoreLabel) {
        this.scoreLabel = scoreLabel;
    }
    
    @Override
    public void onAppleEaten(int score) {
        updateScoreDisplay(score);
        log.info("Regular apple eaten. New score: {}", score);
    }
    
    @Override
    public void onBigAppleEaten(int score, int newDelay) {
        updateScoreDisplay(score);
        log.info("Big apple eaten. New score: {}, New delay: {}", score, newDelay);
    }
    
    @Override
    public void onBigAppleAppeared() {
        // No score update needed when big apple appears
        log.info("Big apple appeared on the board");
    }
    
    @Override
    public void onGameOver(int finalScore) {
        log.info("Game over. Final score: {}", finalScore);
    }
    
    private void updateScoreDisplay(int score) {
        if (scoreLabel != null) {
            scoreLabel.setText("Score: " + score);
        }
    }
}
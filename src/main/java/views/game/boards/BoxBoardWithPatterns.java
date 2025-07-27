package views.game.boards;

import lombok.extern.slf4j.Slf4j;
import models.UserScore;
import views.base.BoardWithPatterns;

import java.awt.*;

/**
 * Box board implementation using design patterns
 */
@Slf4j
public class BoxBoardWithPatterns extends BoardWithPatterns {

    private final int BOX_SIZE = 50;
    private final int BOX_OFFSET = 100;

    public BoxBoardWithPatterns() {
        super();
    }

    public BoxBoardWithPatterns(UserScore user) {
        super(user);
    }

    @Override
    protected void checkCollision() {
        for (int z = dots; z > 0; z--) {
            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
                break;
            }
        }

        // Check if the snake hits the bottom line
        if (y[0] >= getHeight() - 40) {
            inGame = false;
        }

        // Check if the snake hits the top of the board
        if (y[0] < 0) {
            inGame = false;
        }

        // Check if the snake hits the left side of the board
        if (x[0] < 0) {
            inGame = false;
        }

        // Check if the snake hits the right side of the board
        if (x[0] >= getWidth()) {
            inGame = false;
        }

        // Check if the snake hits the box
        if (isSnakeHitBox()) {
            inGame = false;
        }

        if (!inGame) {
            timer.stop();
        }
    }

    private boolean isSnakeHitBox() {
        // Check if the snake's head is inside the box
        return (x[0] >= BOX_OFFSET && x[0] < BOX_OFFSET + BOX_SIZE &&
                y[0] >= BOX_OFFSET && y[0] < BOX_OFFSET + BOX_SIZE);
    }

    @Override
    protected void locateApple() {
        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));

        // Check if the apple is located on the snake's body
        for (int z = 0; z < dots; z++) {
            if ((x[z] == apple_x) && (y[z] == apple_y)) {
                locateApple(); // Recursively call locateApple to find a new position
                return;
            }
        }

        // Check if the apple is located below the bottom line
        if (apple_y >= getHeight() - 40) {
            locateApple(); // Recursively call locateApple to find a new position
            return;
        }

        // Check if the apple is located on the big apple
        if ((apple_x >= bigApple_x) && (apple_x <= bigApple_x + 2 * DOT_SIZE)
                && (apple_y >= bigApple_y) && (apple_y <= bigApple_y + 2 * DOT_SIZE)) {
            locateApple(); // Recursively call locateApple to find a new position
            return;
        }

        // Check if the apple is located in the box
        if (apple_x >= BOX_OFFSET && apple_x < BOX_OFFSET + BOX_SIZE &&
                apple_y >= BOX_OFFSET && apple_y < BOX_OFFSET + BOX_SIZE) {
            locateApple(); // Recursively call locateApple to find a new position
            return;
        }

        // Check if apple_count is a multiple of 5 and not 0
        if (apple_count % 5 == 0 && apple_count != 0) {
            locateBigApple();
            setBigAppleTime();
        }
    }

    @Override
    protected void locateBigApple() {
        int r = (int) (Math.random() * RAND_POS);
        bigApple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        bigApple_y = ((r * DOT_SIZE));

        // Check if the big apple is located on the snake's body
        for (int z = 0; z < dots; z++) {
            if ((x[z] >= bigApple_x) && (x[z] <= bigApple_x + 2 * DOT_SIZE)
                    && (y[z] >= bigApple_y) && (y[z] <= bigApple_y + 2 * DOT_SIZE)) {
                locateBigApple(); // Recursively call locateBigApple to find a new position
                return;
            }
        }

        // Check if the big apple is located below the bottom line
        if (bigApple_y >= getHeight() - 40) {
            locateBigApple(); // Recursively call locateBigApple to find a new position
            return;
        }

        // Check if the big apple is located on the apple
        if ((apple_x >= bigApple_x) && (apple_x <= bigApple_x + 2 * DOT_SIZE)
                && (apple_y >= bigApple_y) && (apple_y <= bigApple_y + 2 * DOT_SIZE)) {
            locateBigApple(); // Recursively call locateBigApple to find a new position
            return;
        }

        // Check if the big apple is located in the box
        if (bigApple_x >= BOX_OFFSET && bigApple_x < BOX_OFFSET + BOX_SIZE &&
                bigApple_y >= BOX_OFFSET && bigApple_y < BOX_OFFSET + BOX_SIZE) {
            locateBigApple(); // Recursively call locateBigApple to find a new position
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draw the box obstacle
        g.setColor(Color.GRAY);
        g.fillRect(BOX_OFFSET, BOX_OFFSET, BOX_SIZE, BOX_SIZE);
    }
}
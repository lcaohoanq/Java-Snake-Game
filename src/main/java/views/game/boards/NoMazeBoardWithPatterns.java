package views.game.boards;

import lombok.extern.slf4j.Slf4j;
import models.UserScore;
import views.base.BoardWithPatterns;

import java.awt.*;

/**
 * NoMaze board implementation using design patterns
 */
@Slf4j
public class NoMazeBoardWithPatterns extends BoardWithPatterns {

    public NoMazeBoardWithPatterns() {
        super();
    }

    public NoMazeBoardWithPatterns(UserScore user) {
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

        if (!inGame) {
            timer.stop();
        }
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
        }
    }
}
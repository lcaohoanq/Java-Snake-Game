package models;

import constants.Paths;
import utils.AudioHandler;

public class NoMazeMode extends InitBoard {

    public NoMazeMode() {
        super();
    }

    @Override
    public void loadImages() {

        super.loadImages();
    }

    @Override
    public void checkCollision() {

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

    @Override
    public void locateApple() {

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

    @Override
    public void locateBigApple() {
        AudioHandler.playAudio(Paths.URL_BIG_APPLE_APP);
        int r = (int) (Math.random() * RAND_POS);
        bigApple_x = ((r * DOT_SIZE));
        apple_x = -100;

        r = (int) (Math.random() * RAND_POS);
        bigApple_y = ((r * DOT_SIZE));
        apple_y = -100;
        setBigAppleTime();
        renderProgressBar();

    }

}
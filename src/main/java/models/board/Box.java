package models.board;

import constants.Paths;
import constants.Sizes;

import javax.swing.*;
import java.awt.*;

public class Box extends Board {
    protected int wallThickness = 20;
    private Image wall;

    public Box() {
        super();
    }

    @Override
    protected void loadImages() {
        super.loadImages();
        wall = new ImageIcon(Paths.URL_WALL).getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i <= Sizes.WIDTH_BOARD; i += 20) {
            g.drawImage(wall, i, 0, this);
            g.drawImage(wall, i, Sizes.HEIGHT_BOARD - 70, this);
        }
        for (int i = 0; i < Sizes.HEIGHT_BOARD - 70; i += 20) {
            g.drawImage(wall, 0, i, this);
            g.drawImage(wall, Sizes.WIDTH_BOARD - 10, i, this);
        }
    }

    @Override
    protected void checkCollision() {
        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
                break;
            }
        }

        if (y[0] >= Sizes.HEIGHT_BOARD - 70) {
            inGame = false;
        }

        if (y[0] < 20) {
            inGame = false;
        }

        if (x[0] >= Sizes.WIDTH_BOARD) {
            inGame = false;
        }

        if (x[0] < 20) {
            inGame = false;
        }

        if (!inGame) {
            if (isOnSound()) {
                setAudio(Paths.URL_GAME_OVER);
            }
            timer.stop();
        }
    }

    @Override
    protected void locateApple() {
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
    protected void locateBigApple() {
        if (isOnSound()) {
            setAudio(Paths.URL_BIG_APPLE_APP);
        }
        int r = (int) (Math.random() * (RAND_POS - 2 * wallThickness));
        bigApple_x = ((r + wallThickness) * DOT_SIZE);
        apple_x = -100;

        r = (int) (Math.random() * (RAND_POS - 2 * wallThickness));
        bigApple_y = ((r + wallThickness) * DOT_SIZE);
        apple_y = -100;
        setBigAppleTime();
        renderProgressBar();
    }
}

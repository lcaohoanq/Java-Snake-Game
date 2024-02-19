package models.board;

import constants.Paths;
import constants.Sizes;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class Mill extends Board {
    protected int wallThickness = 20;
    private Image wall;

    public  Mill() {
        super();

        //adjust to prevent hitting the side wall
        leftDirection = false;
        rightDirection = false;
        upDirection = false;
        downDirection = true;
    }

    @Override
    public void loadImages() {
        super.loadImages();
        wall = new ImageIcon(getClass().getResource(Paths.URL_WALL)).getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //draw the left horizontal wall from x = 0 to x = 140 at y = 420
        for (int i = 0; i < 160; i += 20) {
            g.drawImage(wall, i, Sizes.HEIGHT_BOARD - 50 - 80, this);
        }
        //draw the right horizontal wall from x = 340 to x = 500 at y = 60
        for (int i = 340; i < Sizes.WIDTH_BOARD; i += 20) {
            g.drawImage(wall, i, 60, this);
        }
        //draw the vertical walls at x = 140, from y = 0 to y = 340
        for (int i = 0; i < 340; i += 20) {
            g.drawImage(wall, 140, i, this);
        }
        //draw the vertical walls at x = 340, from y = 500 to y = 160
        for (int i = 160; i < 500; i += 20) {
            g.drawImage(wall, 340, i, this);
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

        // snake can go through the walls north
        if (y[0] < 0) {
            y[0] = Sizes.HEIGHT_BOARD - 50 - DOT_SIZE;
        }

        // snake can go through the walls south
        if(y[0] >= Sizes.HEIGHT_BOARD - 50){
            y[0] = 0;
        }

        // snake can go through the walls east
        if (x[0] >= Sizes.WIDTH_BOARD) {
            x[0] = 0;
        }

        // snake can go through the walls west
        if (x[0] < 0) {
            x[0] = Sizes.WIDTH_BOARD - DOT_SIZE;
        }

        // snake hit the vertical wall at x = 140, from y = 0 to y = 340
        if(x[0] == 140 && y[0] < 340 && y[0] > 0){
            inGame = false;
        }

        // snake hit the vertical wall at x = 340, from y = 500 to y = 160
        if(x[0] == 340 && y[0] < 500 && y[0] > 160){
            inGame = false;
        }

        // snake hit the horizontal wall from x = 0 to x = 140 at y = 420
        if(y[0] == Sizes.HEIGHT_BOARD - 50 - 80 && x[0] < 140 && x[0] > 0){
            inGame = false;
        }

        // snake hit the horizontal wall from x = 340 to x = 500 at y = 60
        if(y[0] == 60 && x[0] < Sizes.WIDTH_BOARD && x[0] > 340){
            inGame = false;
        }

        if (!inGame) {
            if (isOnSound()) {
                InputStream inputStream = getClass().getResourceAsStream(Paths.URL_GAME_OVER);
                audioHandler.playAudio(inputStream);
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
            InputStream inputStream = getClass().getResourceAsStream(Paths.URL_BIG_APPLE_APP);
            audioHandler.playAudio(inputStream);
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

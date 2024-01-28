package models;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import constants.Paths;
import utils.AudioHandler;

public class BoxMode extends InitBoard {
    protected int wallThickness = 20;

    public BoxMode() {
        super();
    }

    @Override
    public void loadImages() {

        super.loadImages();
        ImageIcon iiw = new ImageIcon(Paths.URL_WALL);
        wall = iiw.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
        g.setColor(Color.white);
        g.drawLine(0, line, B_WIDTH, line);
        for (int i = 0; i <= B_WIDTH; i += 20) {
            g.drawImage(wall, i, 0, this);
            g.drawImage(wall, i, B_HEIGHT - 70, this);
        }
        for (int i = 0; i < B_HEIGHT - 70; i += 20) {
            g.drawImage(wall, 0, i, this);
            g.drawImage(wall, B_WIDTH - 10, i, this);
        }
    }

    @Override
    public void checkCollision() {

        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
                break;
            }
        }

        if (y[0] >= B_HEIGHT - 70) {
            inGame = false;
        }

        if (y[0] < 20) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            inGame = false;
        }

        if (x[0] < 20) {
            inGame = false;
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

}

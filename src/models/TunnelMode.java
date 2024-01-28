package models;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import constants.Paths;
import controllers.LoginController;
import utils.AudioHandler;

public class TunnelMode extends InitBoard {

    protected int wallThickness = 20;

    public TunnelMode() {
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
}

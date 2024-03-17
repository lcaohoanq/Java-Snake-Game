package models.board;

import constants.ResourcePaths;
import styles.UISizes;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class Apartment extends Board {
    protected int wallThickness = 20;
    private Image wall;

    public Apartment(){
        super();
    }

    @Override
    public void loadImages() {
        super.loadImages();
        wall = new ImageIcon(getClass().getResource(ResourcePaths.URL_WALL)).getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //draw the horizontal walls at y = 0, from x = 0 to x 80
        for (int i = 0; i < 80; i += 20) {
            g.drawImage(wall, i, 0, this);
        }
        //draw the horizontal walls at y = 120, from x = 160 to x 340
        for (int i = 160; i < UISizes.WIDTH_BOARD - 80; i += 20) {
            g.drawImage(wall, i, 0, this);
        }
        //draw the vertical walls at x = 0, from y = 0 to y 120
        for (int i = 0; i < 100; i += 20) {
            g.drawImage(wall, 0, i, this);
        }
        //draw the horizontal walls at y = 140, from x = 0 to x 200
        for (int i = 0; i < 200; i += 20) {
            g.drawImage(wall, i, 140, this);
        }
        //draw the horizontal walls at y = 140, from x = 300 to max width
        for (int i = 300; i < UISizes.WIDTH_BOARD; i += 20) {
            g.drawImage(wall, i, 140, this);
        }
        //draw the vertical walls at x = 200, from y = 0 to y 160
        for (int i = 0; i < 160; i += 20) {
            g.drawImage(wall, 200, i, this);
        }
        //draw the horizontal walls at y = 400, from x = 0 to max width
        for (int i = 0; i < UISizes.WIDTH_BOARD; i += 20) {
            g.drawImage(wall, i, 260, this);
        }
        //draw the vertical walls at x = 300, from y = 260 to y 500
        for (int i = 260; i < UISizes.HEIGHT_BOARD - 50; i += 20) {
            g.drawImage(wall, 300, i, this);
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
            y[0] = UISizes.HEIGHT_BOARD - 50 - DOT_SIZE;
        }

        // snake can go through the walls south
        if(y[0] >= UISizes.HEIGHT_BOARD - 50){
            y[0] = 0;
        }

        // snake can go through the walls east
        if (x[0] >= UISizes.WIDTH_BOARD) {
            x[0] = 0;
        }

        // snake can go through the walls west
        if (x[0] < 0) {
            x[0] = UISizes.WIDTH_BOARD - DOT_SIZE;
        }

        // snake hit the top left horizontal wall
        if(x[0] < 80 && y[0] < 20){
            inGame = false;
        }

        // snake hit the top left vertical wall
        if(x[0] <= 20 && y[0] < 100){
            inGame = false;
        }

        // snake hit the top middle horizontal wall (from x = 160 to 420, y = 0)
        if(x[0] >= 160 && x[0] < 420 && y[0] < 20){
            inGame = false;
        }

        // snake hit the first left horizontal middle chunk wall
        if(x[0] < 11*20 && y[0] >= 7*20 && y[0] < 8*20){
           inGame = false;
        }

        // snake hit the second right horizontal middle chunk wall
        if(x[0] >= UISizes.WIDTH_BOARD - 200 && y[0] >= 7*20 && y[0] < 8*20){
            inGame = false;
        }

        // snake hit the third horizontal middle wall
        if(y[0] >= UISizes.HEIGHT_BOARD - 50 - 12*20 && y[0] <= UISizes.HEIGHT_BOARD - 50 - 11*20){
            inGame = false;
        }

        // snake hit the second vertical wall
        if(x[0] >= 10*20 && x[0] < 11*20 && y[0] >= 20 && y[0] <= 140){
            inGame = false;
        }

        // snake hit the third vertical wall
        if(x[0] >= 15*20 && x[0] <= 16*20 && y[0] >= UISizes.HEIGHT_BOARD - 50 - 12*20){
            inGame = false;
        }

        if (!inGame) {
            if (isOnSound()) {
                InputStream inputStream = getClass().getResourceAsStream(ResourcePaths.URL_GAME_OVER);
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
            InputStream inputStream = getClass().getResourceAsStream(ResourcePaths.URL_BIG_APPLE_APP);
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

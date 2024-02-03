package models.board;

import constants.Paths;
import constants.Sizes;

import javax.swing.*;
import java.awt.*;

public class Apartment extends Board {
    protected int wallThickness = 20;
    private Image wall;

    @Override
    public void loadImages() {
        super.loadImages();
        wall = new ImageIcon(getClass().getResource(Paths.URL_WALL)).getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //draw the horizontal walls at y = 0, from x = 0 to x 80
        for (int i = 0; i < 80; i += 20) {
            g.drawImage(wall, i, 0, this);
        }
        //draw the horizontal walls at y = 120, from x = 160 to x 340
        for (int i = 160; i < Sizes.WIDTH_BOARD - 80; i += 20) {
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
        for (int i = 300; i < Sizes.WIDTH_BOARD; i += 20) {
            g.drawImage(wall, i, 140, this);
        }
        //draw the vertical walls at x = 200, from y = 0 to y 160
        for (int i = 0; i < 160; i += 20) {
            g.drawImage(wall, 200, i, this);
        }
        //draw the horizontal walls at y = 400, from x = 0 to max width
        for (int i = 0; i < Sizes.WIDTH_BOARD; i += 20) {
            g.drawImage(wall, i, 260, this);
        }
        //draw the vertical walls at x = 300, from y = 260 to y 500
        for (int i = 260; i < Sizes.HEIGHT_BOARD - 50; i += 20) {
            g.drawImage(wall, 300, i, this);
        }
    }
    
    @Override
    protected void checkCollision() {

    }

    @Override
    protected void locateApple() {

    }

    @Override
    protected void locateBigApple() {

    }
}

package models.board;

import constants.Paths;
import constants.Sizes;

import javax.swing.*;
import java.awt.*;

public class Mill extends Board {
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

    }

    @Override
    protected void locateApple() {

    }

    @Override
    protected void locateBigApple() {

    }
}

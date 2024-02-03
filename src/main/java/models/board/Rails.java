package models.board;

import constants.Paths;
import constants.Sizes;

import javax.swing.*;
import java.awt.*;

public class Rails extends Board {

    private int wallThickness = 20;
    private Image wall;

    public Rails() {
        super();
    }

    @Override
    protected void loadImages() {
        super.loadImages();
        wall = new ImageIcon(getClass().getResource(Paths.URL_WALL)).getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //draw the top and bottom horizontal walls
        for (int i = 0; i < Sizes.WIDTH_BOARD; i += 20) {
            g.drawImage(wall, i, 0, this);
            g.drawImage(wall, i, Sizes.HEIGHT_BOARD - 70, this);
        }
        //draw the 2 left vertical walls
        for (int i = 0; i < 200; i += 20) {
            g.drawImage(wall, 0, i, this);
            g.drawImage(wall, 0, Sizes.HEIGHT_BOARD - 70 - i, this);
        }
        //draw the 2 right vertical walls
        for (int i = 0; i < 200; i += 20) {
            g.drawImage(wall, Sizes.WIDTH_BOARD - 20, i, this);
            g.drawImage(wall, Sizes.WIDTH_BOARD - 20, Sizes.HEIGHT_BOARD - 70 - i, this);
        }
        //draw the 2 middle horizontal walls
        for (int i = 100; i < 400; i += 20) {
            g.drawImage(wall, i, 140, this);
            g.drawImage(wall, i, Sizes.HEIGHT_BOARD - 50 - 160, this);
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

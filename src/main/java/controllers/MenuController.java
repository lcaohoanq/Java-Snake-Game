package controllers;

import constants.Messages;
import constants.Paths;
import utils.AudioHandler;
import views.MenuView;
import views.Snake;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuController implements MouseListener {

    private MenuView menuView;

    public MenuController(MenuView menuView) {
        this.menuView = menuView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == menuView.jButton_1) {
            EventQueue.invokeLater(() -> {
                Snake snake = new Snake();
                snake.startGame();
            });
        }
        if (e.getSource() == menuView.jButton_2) {

        }
        if (e.getSource() == menuView.jButton_3) {

        }
        if (e.getSource() == menuView.jButton_4) {
            AudioHandler.playAudio(Paths.URL_EATING);
            Messages.IS_NOT_SUPPORT();
        }
        if (e.getSource() == menuView.jButton_5) {
            AudioHandler.playAudio(Paths.URL_EATING);
            Messages.IS_NOT_SUPPORT();
        }
        if (e.getSource() == menuView.jButton_6) {
            AudioHandler.playAudio(Paths.URL_EATING);
            Messages.IS_NOT_SUPPORT();
        }
        if (e.getSource() == menuView.jButton_7) {
            AudioHandler.playAudio(Paths.URL_EATING);
            Messages.IS_NOT_SUPPORT();
        }
        if (e.getSource() == menuView.jButton_8) {
            AudioHandler.playAudio(Paths.URL_EATING);
            Messages.IS_NOT_SUPPORT();
        }
        if (e.getSource() == menuView.jButton_9) {
            AudioHandler.playAudio(Paths.URL_EATING);
            Messages.IS_NOT_SUPPORT();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == menuView.jButton_1) {
            menuView.setHoverButton(menuView.jButton_1);
        }
        if (e.getSource() == menuView.jButton_2) {
            menuView.setHoverButton(menuView.jButton_2);
        }
        if (e.getSource() == menuView.jButton_3) {
            menuView.setHoverButton(menuView.jButton_3);
        }
        if (e.getSource() == menuView.jButton_4) {
            menuView.setHoverButton(menuView.jButton_4);
        }
        if (e.getSource() == menuView.jButton_5) {
            menuView.setHoverButton(menuView.jButton_5);
        }
        if (e.getSource() == menuView.jButton_6) {
            menuView.setHoverButton(menuView.jButton_6);
        }
        if (e.getSource() == menuView.jButton_7) {
            menuView.setHoverButton(menuView.jButton_7);
        }
        if (e.getSource() == menuView.jButton_8) {
            menuView.setHoverButton(menuView.jButton_8);
        }
        if (e.getSource() == menuView.jButton_9) {
            menuView.setHoverButton(menuView.jButton_9);
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == menuView.jButton_1) {
            menuView.setUnHoverButton(menuView.jButton_1);
        }
        if (e.getSource() == menuView.jButton_2) {
            menuView.setUnHoverButton(menuView.jButton_2);
        }
        if (e.getSource() == menuView.jButton_3) {
            menuView.setUnHoverButton(menuView.jButton_3);
        }
        if (e.getSource() == menuView.jButton_4) {
            menuView.setUnHoverButton(menuView.jButton_4);
        }
        if (e.getSource() == menuView.jButton_5) {
            menuView.setUnHoverButton(menuView.jButton_5);
        }
        if (e.getSource() == menuView.jButton_6) {
            menuView.setUnHoverButton(menuView.jButton_6);
        }
        if (e.getSource() == menuView.jButton_7) {
            menuView.setUnHoverButton(menuView.jButton_7);
        }
        if (e.getSource() == menuView.jButton_8) {
            menuView.setUnHoverButton(menuView.jButton_8);
        }
        if (e.getSource() == menuView.jButton_9) {
            menuView.setUnHoverButton(menuView.jButton_9);
        }
    }

}

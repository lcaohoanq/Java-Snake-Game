package controllers;

import constants.Messages;
import constants.Paths;
import utils.AudioHandler;
import views.MenuView;
import views.Snake;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuController implements MouseListener, ActionListener {

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
        if (menuView.getStatusToggle()) {
            if (e.getSource() == menuView.jButton_1) {
                menuView.setHoverButton(true, "dark", menuView.jButton_1);
            }
            if (e.getSource() == menuView.jButton_2) {
                menuView.setHoverButton(true, "dark", menuView.jButton_2);
            }
            if (e.getSource() == menuView.jButton_3) {
                menuView.setHoverButton(true, "dark", menuView.jButton_3);
            }
            if (e.getSource() == menuView.jButton_4) {
                menuView.setHoverButton(true, "dark", menuView.jButton_4);
            }
            if (e.getSource() == menuView.jButton_5) {
                menuView.setHoverButton(true, "dark", menuView.jButton_5);
            }
            if (e.getSource() == menuView.jButton_6) {
                menuView.setHoverButton(true, "dark", menuView.jButton_6);
            }
            if (e.getSource() == menuView.jButton_7) {
                menuView.setHoverButton(true, "dark", menuView.jButton_7);
            }
            if (e.getSource() == menuView.jButton_8) {
                menuView.setHoverButton(true, "dark", menuView.jButton_8);
            }
            if (e.getSource() == menuView.jButton_9) {
                menuView.setHoverButton(true, "dark", menuView.jButton_9);
            }
        } else {
            if (e.getSource() == menuView.jButton_1) {
                menuView.setHoverButton(true, "light", menuView.jButton_1);
            }
            if (e.getSource() == menuView.jButton_2) {
                menuView.setHoverButton(true, "light", menuView.jButton_2);
            }
            if (e.getSource() == menuView.jButton_3) {
                menuView.setHoverButton(true, "light", menuView.jButton_3);
            }
            if (e.getSource() == menuView.jButton_4) {
                menuView.setHoverButton(true, "light", menuView.jButton_4);
            }
            if (e.getSource() == menuView.jButton_5) {
                menuView.setHoverButton(true, "light", menuView.jButton_5);
            }
            if (e.getSource() == menuView.jButton_6) {
                menuView.setHoverButton(true, "light", menuView.jButton_6);
            }
            if (e.getSource() == menuView.jButton_7) {
                menuView.setHoverButton(true, "light", menuView.jButton_7);
            }
            if (e.getSource() == menuView.jButton_8) {
                menuView.setHoverButton(true, "light", menuView.jButton_8);
            }
            if (e.getSource() == menuView.jButton_9) {
                menuView.setHoverButton(true, "light", menuView.jButton_9);
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (menuView.getStatusToggle()) {
            if (e.getSource() == menuView.jButton_1) {
                menuView.setHoverButton(false, "dark", menuView.jButton_1);
            }
            if (e.getSource() == menuView.jButton_2) {
                menuView.setHoverButton(false, "dark", menuView.jButton_2);
            }
            if (e.getSource() == menuView.jButton_3) {
                menuView.setHoverButton(false, "dark", menuView.jButton_3);
            }
            if (e.getSource() == menuView.jButton_4) {
                menuView.setHoverButton(false, "dark", menuView.jButton_4);
            }
            if (e.getSource() == menuView.jButton_5) {
                menuView.setHoverButton(false, "dark", menuView.jButton_5);
            }
            if (e.getSource() == menuView.jButton_6) {
                menuView.setHoverButton(false, "dark", menuView.jButton_6);
            }
            if (e.getSource() == menuView.jButton_7) {
                menuView.setHoverButton(false, "dark", menuView.jButton_7);
            }
            if (e.getSource() == menuView.jButton_8) {
                menuView.setHoverButton(false, "dark", menuView.jButton_8);
            }
            if (e.getSource() == menuView.jButton_9) {
                menuView.setHoverButton(false, "dark", menuView.jButton_9);
            }
        } else {
            if (e.getSource() == menuView.jButton_1) {
                menuView.setHoverButton(false, "light", menuView.jButton_1);
            }
            if (e.getSource() == menuView.jButton_2) {
                menuView.setHoverButton(false, "light", menuView.jButton_2);
            }
            if (e.getSource() == menuView.jButton_3) {
                menuView.setHoverButton(false, "light", menuView.jButton_3);
            }
            if (e.getSource() == menuView.jButton_4) {
                menuView.setHoverButton(false, "light", menuView.jButton_4);
            }
            if (e.getSource() == menuView.jButton_5) {
                menuView.setHoverButton(false, "light", menuView.jButton_5);
            }
            if (e.getSource() == menuView.jButton_6) {
                menuView.setHoverButton(false, "light", menuView.jButton_6);
            }
            if (e.getSource() == menuView.jButton_7) {
                menuView.setHoverButton(false, "light", menuView.jButton_7);
            }
            if (e.getSource() == menuView.jButton_8) {
                menuView.setHoverButton(false, "light", menuView.jButton_8);
            }
            if (e.getSource() == menuView.jButton_9) {
                menuView.setHoverButton(false, "light", menuView.jButton_9);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Off")) {
            menuView.setAudio("");
        }
    }
}

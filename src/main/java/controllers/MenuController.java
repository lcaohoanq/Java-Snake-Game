package controllers;

import constants.Messages;
import constants.Paths;
import utils.AudioHandler;
import utils.ToggleHandler;
import views.MenuView;
import views.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuController implements MouseListener, ActionListener {

    private MenuView menuView;
    private MenuView.MenuModern menuModern;

    public MenuController(MenuView menuView) {
        this.menuView = menuView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == menuView.jButton_1) {
            EventQueue.invokeLater(() -> {
                new Snake("Classic").startGame();
            });
        }
        if (e.getSource() == menuView.jButton_2) {
            EventQueue.invokeLater(() -> {
                menuModern = menuView.new MenuModern();
                menuModern.setVisible(true);
            });
        }
        if (e.getSource() == menuView.jButton_3) {
            EventQueue.invokeLater(() -> {
                new Snake("Campaign").startGame();
            });
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
        if (menuView.isActive()) {
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
            }
        }
    }


    @Override
    public void mouseExited(MouseEvent e) {
        if (menuView.isActive()) {
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
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Off")) {
            menuView.setAudio("");
        }
    }

    public static class MenuModernController extends JFrame implements MouseListener {

        private MenuView.MenuModern menuModern;

        public MenuModernController(MenuView.MenuModern menuModern) {
            this.menuModern = menuModern;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == menuModern.jButton_4) {
                EventQueue.invokeLater(() -> {
                    new Snake("NoMaze").startGame();
                });
            }
            if (e.getSource() == menuModern.jButton_5) {
                EventQueue.invokeLater(() -> {
                    new Snake("Box").startGame();
                });
            }
            if (e.getSource() == menuModern.jButton_6) {
                EventQueue.invokeLater(() -> {
                    new Snake("Tunnel").startGame();
                });
            }
            if (e.getSource() == menuModern.jButton_7) {
                EventQueue.invokeLater(() -> {
                    new Snake("Mill").startGame();
                });
            }
            if (e.getSource() == menuModern.jButton_8) {
                EventQueue.invokeLater(() -> {
                    new Snake("Rails").startGame();
                });
            }
            if (e.getSource() == menuModern.jButton_9) {
                EventQueue.invokeLater(() -> {
                    new Snake("Apartment").startGame();
                });
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
            if (menuModern.getStatusToggle()) {
                if (e.getSource() == menuModern.jButton_4) {
                    menuModern.setHoverButton(true, "dark", menuModern.jButton_4);
                }
                if (e.getSource() == menuModern.jButton_5) {
                    menuModern.setHoverButton(true, "dark", menuModern.jButton_5);
                }
                if (e.getSource() == menuModern.jButton_6) {
                    menuModern.setHoverButton(true, "dark", menuModern.jButton_6);
                }
                if (e.getSource() == menuModern.jButton_7) {
                    menuModern.setHoverButton(true, "dark", menuModern.jButton_7);
                }
                if (e.getSource() == menuModern.jButton_8) {
                    menuModern.setHoverButton(true, "dark", menuModern.jButton_8);
                }
                if (e.getSource() == menuModern.jButton_9) {
                    menuModern.setHoverButton(true, "dark", menuModern.jButton_9);
                }
            } else {
                if (e.getSource() == menuModern.jButton_4) {
                    menuModern.setHoverButton(true, "light", menuModern.jButton_4);
                }
                if (e.getSource() == menuModern.jButton_5) {
                    menuModern.setHoverButton(true, "light", menuModern.jButton_5);
                }
                if (e.getSource() == menuModern.jButton_6) {
                    menuModern.setHoverButton(true, "light", menuModern.jButton_6);
                }
                if (e.getSource() == menuModern.jButton_7) {
                    menuModern.setHoverButton(true, "light", menuModern.jButton_7);
                }
                if (e.getSource() == menuModern.jButton_8) {
                    menuModern.setHoverButton(true, "light", menuModern.jButton_8);
                }
                if (e.getSource() == menuModern.jButton_9) {
                    menuModern.setHoverButton(true, "light", menuModern.jButton_9);
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (menuModern.getStatusToggle()) {
                if (e.getSource() == menuModern.jButton_4) {
                    menuModern.setHoverButton(false, "dark", menuModern.jButton_4);
                }
                if (e.getSource() == menuModern.jButton_5) {
                    menuModern.setHoverButton(false, "dark", menuModern.jButton_5);
                }
                if (e.getSource() == menuModern.jButton_6) {
                    menuModern.setHoverButton(false, "dark", menuModern.jButton_6);
                }
                if (e.getSource() == menuModern.jButton_7) {
                    menuModern.setHoverButton(false, "dark", menuModern.jButton_7);
                }
                if (e.getSource() == menuModern.jButton_8) {
                    menuModern.setHoverButton(false, "dark", menuModern.jButton_8);
                }
                if (e.getSource() == menuModern.jButton_9) {
                    menuModern.setHoverButton(false, "dark", menuModern.jButton_9);
                }
            } else {
                if (e.getSource() == menuModern.jButton_4) {
                    menuModern.setHoverButton(false, "light", menuModern.jButton_4);
                }
                if (e.getSource() == menuModern.jButton_5) {
                    menuModern.setHoverButton(false, "light", menuModern.jButton_5);
                }
                if (e.getSource() == menuModern.jButton_6) {
                    menuModern.setHoverButton(false, "light", menuModern.jButton_6);
                }
                if (e.getSource() == menuModern.jButton_7) {
                    menuModern.setHoverButton(false, "light", menuModern.jButton_7);
                }
                if (e.getSource() == menuModern.jButton_8) {
                    menuModern.setHoverButton(false, "light", menuModern.jButton_8);
                }
                if (e.getSource() == menuModern.jButton_9) {
                    menuModern.setHoverButton(false, "light", menuModern.jButton_9);
                }
            }
        }
    }
}

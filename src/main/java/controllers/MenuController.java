package controllers;

import java.util.Arrays;
import java.util.List;
import utils.UIUtils;
import constants.ResourcePaths;
import utils.AudioHandler;
import views.MenuView;
import views.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;

public class MenuController implements MouseListener, ActionListener {

    public static MenuView menuView;
    public static MenuView.MenuModern menuModern;
    private boolean isMenuModern = true;
    private AudioHandler audioHandler;

    private List<JButton> jButtonList;

    public MenuController(MenuView menuView) {
        this.menuView = menuView;
        this.audioHandler = new AudioHandler();
        this.jButtonList = Arrays.asList(
            menuView.jButton_1,
            menuView.jButton_2,
            menuView.jButton_3);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == menuView.jButton_1) {
            EventQueue.invokeLater(() -> {
                menuView.dispose();
                new Snake("Classic").startGame();
            });
        }
        if (e.getSource() == menuView.jButton_2) {
            EventQueue.invokeLater(() -> {

                if (isMenuModern) {
                    menuModern = menuView.new MenuModern();
                    isMenuModern = true;
//                    menuView.dispose();
                    menuModern.setVisible(true);
                } else {
//                    menuView.dispose();
                    isMenuModern = false;
                }
            });
        }
        if (e.getSource() == menuView.jButton_3) {
            EventQueue.invokeLater(() -> {
//                menuView.dispose();
//                new Snake("Campaign").startGame();

                prepareUnsupportFeature();
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
            jButtonList.stream()
                .filter(button -> e.getSource() == button)
                .forEach(button -> {
                    if (menuView.getStatusToggle()) {
                        menuView.setHoverButton(true, "dark", button);
                    } else {
                        menuView.setHoverButton(true, "light", button);
                    }
                });
        }
    }


    @Override
    public void mouseExited(MouseEvent e) {
        if (menuView.isActive()) {
            jButtonList.stream()
                .filter(button -> e.getSource() == button)
                .forEach(button -> {
                    if (menuView.getStatusToggle()) {
                        menuView.setHoverButton(false, "dark", button);
                    } else {
                        menuView.setHoverButton(false, "light", button);
                    }
                });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Off")) {
            System.out.println("user click Off sound");
            menuView.setAudio(false);
        } else if (e.getActionCommand().equals("On")) {
            menuView.setAudio(true);
        }
    }

    private void prepareUnsupportFeature() {
        InputStream unsupported = getClass().getResourceAsStream(ResourcePaths.URL_EATING);
        audioHandler.playAudio(unsupported);
        UIUtils.IS_NOT_SUPPORT();
    }

    public static class MenuModernController extends JFrame implements MouseListener {

        private MenuView.MenuModern menuModern;
        private AudioHandler audioHandler;

        public MenuModernController(MenuView.MenuModern menuModern) {
            this.menuModern = menuModern;
            this.audioHandler = new AudioHandler();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == menuModern.jButton_4) {
                EventQueue.invokeLater(() -> {
                    menuView.dispose();
                    new Snake("NoMaze").startGame();
                });
            }
            if (e.getSource() == menuModern.jButton_5) {
                EventQueue.invokeLater(() -> {
                    menuView.dispose();
                    new Snake("Box").startGame();
                });
            }
            if (e.getSource() == menuModern.jButton_6) {
                EventQueue.invokeLater(() -> {
                    menuView.dispose();
                    new Snake("Tunnel").startGame();
                });
            }
            if (e.getSource() == menuModern.jButton_7) {
                EventQueue.invokeLater(() -> {
                    menuView.dispose();
                    new Snake("Mill").startGame();
                });
            }
            if (e.getSource() == menuModern.jButton_8) {
                EventQueue.invokeLater(() -> {
                    menuView.dispose();
                    new Snake("Rails").startGame();
                });
            }
            if (e.getSource() == menuModern.jButton_9) {
                EventQueue.invokeLater(() -> {
                    menuView.dispose();
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

        private void prepareUnsupportFeature() {
            InputStream unsupported = getClass().getResourceAsStream(ResourcePaths.URL_EATING);
            audioHandler.playAudio(unsupported);
            UIUtils.IS_NOT_SUPPORT();
        }
    }
}

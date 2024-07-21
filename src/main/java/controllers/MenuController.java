package controllers;

import java.util.Arrays;
import java.util.List;
import styles.UIHovers;
import views.AbstractView;
import views.UIPrompts;
import constants.ResourcePaths;
import modules.sound.AudioHandler;
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
    private final AudioHandler audioHandler;
    private UIHovers<MenuView> uiHovers;

    private final List<JButton> jButtonList;
//    private UIHovers<MenuView> uiHovers;

    public MenuController(MenuView menuView) {
        MenuController.menuView = menuView;
        this.audioHandler = new AudioHandler();
        this.jButtonList = Arrays.asList(
            menuView.jButton_Mode_Classic,
            menuView.jButton_Mode_Modern,
            menuView.jButton_Mode_Campaign);
        this.uiHovers = new UIHovers<>(menuView);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == menuView.jButton_Mode_Classic) {
            EventQueue.invokeLater(() -> {
                menuView.dispose();
                new Snake("Classic").startGame();
            });
        }
        if (e.getSource() == menuView.jButton_Mode_Modern) {
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
        if (e.getSource() == menuView.jButton_Mode_Campaign) {
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
                        uiHovers.setHoverButton(true, "dark", button);
                    } else {
                        uiHovers.setHoverButton(true, "light", button);
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
                        uiHovers.setHoverButton(false, "dark", button);
                    } else {
                        uiHovers.setHoverButton(false, "light", button);
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
        UIPrompts.IS_NOT_SUPPORT();
    }

    public static class MenuModernController extends AbstractView implements MouseListener {

        private final MenuView.MenuModern menuModern;
        private final AudioHandler audioHandler;
        private UIHovers<MenuView.MenuModern> uiHovers;

        public MenuModernController(MenuView.MenuModern menuModern) {
            this.menuModern = menuModern;
            this.audioHandler = new AudioHandler();
            this.uiHovers = new UIHovers<>(menuModern);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == menuModern.jButton_NoMaze) {
                EventQueue.invokeLater(() -> {
                    menuView.dispose();
                    new Snake("NoMaze").startGame();
                });
            }
            if (e.getSource() == menuModern.jButton_Box) {
                EventQueue.invokeLater(() -> {
                    menuView.dispose();
                    new Snake("Box").startGame();
                });
            }
            if (e.getSource() == menuModern.jButton_Tunnel) {
                EventQueue.invokeLater(() -> {
                    menuView.dispose();
                    new Snake("Tunnel").startGame();
                });
            }
            if (e.getSource() == menuModern.jButton_Mill) {
                EventQueue.invokeLater(() -> {
                    menuView.dispose();
                    new Snake("Mill").startGame();
                });
            }
            if (e.getSource() == menuModern.jButton_Rails) {
                EventQueue.invokeLater(() -> {
                    menuView.dispose();
                    new Snake("Rails").startGame();
                });
            }
            if (e.getSource() == menuModern.jButton_Apartment) {
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
                if (e.getSource() == menuModern.jButton_NoMaze) {
                    uiHovers.setHoverButton(true, "dark", menuModern.jButton_NoMaze);
                }
                if (e.getSource() == menuModern.jButton_Box) {
                    uiHovers.setHoverButton(true, "dark", menuModern.jButton_Box);
                }
                if (e.getSource() == menuModern.jButton_Tunnel) {
                    uiHovers.setHoverButton(true, "dark", menuModern.jButton_Tunnel);
                }
                if (e.getSource() == menuModern.jButton_Mill) {
                    uiHovers.setHoverButton(true, "dark", menuModern.jButton_Mill);
                }
                if (e.getSource() == menuModern.jButton_Rails) {
                    uiHovers.setHoverButton(true, "dark", menuModern.jButton_Rails);
                }
                if (e.getSource() == menuModern.jButton_Apartment) {
                    uiHovers.setHoverButton(true, "dark", menuModern.jButton_Apartment);
                }
            } else {
                if (e.getSource() == menuModern.jButton_NoMaze) {
                    uiHovers.setHoverButton(true, "light", menuModern.jButton_NoMaze);
                }
                if (e.getSource() == menuModern.jButton_Box) {
                    uiHovers.setHoverButton(true, "light", menuModern.jButton_Box);
                }
                if (e.getSource() == menuModern.jButton_Tunnel) {
                    uiHovers.setHoverButton(true, "light", menuModern.jButton_Tunnel);
                }
                if (e.getSource() == menuModern.jButton_Mill) {
                    uiHovers.setHoverButton(true, "light", menuModern.jButton_Mill);
                }
                if (e.getSource() == menuModern.jButton_Rails) {
                    uiHovers.setHoverButton(true, "light", menuModern.jButton_Rails);
                }
                if (e.getSource() == menuModern.jButton_Apartment) {
                    uiHovers.setHoverButton(true, "light", menuModern.jButton_Apartment);
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (menuModern.getStatusToggle()) {
                if (e.getSource() == menuModern.jButton_NoMaze) {
                    uiHovers.setHoverButton(false, "dark", menuModern.jButton_NoMaze);
                }
                if (e.getSource() == menuModern.jButton_Box) {
                    uiHovers.setHoverButton(false, "dark", menuModern.jButton_Box);
                }
                if (e.getSource() == menuModern.jButton_Tunnel) {
                    uiHovers.setHoverButton(false, "dark", menuModern.jButton_Tunnel);
                }
                if (e.getSource() == menuModern.jButton_Mill) {
                    uiHovers.setHoverButton(false, "dark", menuModern.jButton_Mill);
                }
                if (e.getSource() == menuModern.jButton_Rails) {
                    uiHovers.setHoverButton(false, "dark", menuModern.jButton_Rails);
                }
                if (e.getSource() == menuModern.jButton_Apartment) {
                    uiHovers.setHoverButton(false, "dark", menuModern.jButton_Apartment);
                }
            } else {
                if (e.getSource() == menuModern.jButton_NoMaze) {
                    uiHovers.setHoverButton(false, "light", menuModern.jButton_NoMaze);
                }
                if (e.getSource() == menuModern.jButton_Box) {
                    uiHovers.setHoverButton(false, "light", menuModern.jButton_Box);
                }
                if (e.getSource() == menuModern.jButton_Tunnel) {
                    uiHovers.setHoverButton(false, "light", menuModern.jButton_Tunnel);
                }
                if (e.getSource() == menuModern.jButton_Mill) {
                    uiHovers.setHoverButton(false, "light", menuModern.jButton_Mill);
                }
                if (e.getSource() == menuModern.jButton_Rails) {
                    uiHovers.setHoverButton(false, "light", menuModern.jButton_Rails);
                }
                if (e.getSource() == menuModern.jButton_Apartment) {
                    uiHovers.setHoverButton(false, "light", menuModern.jButton_Apartment);
                }
            }
        }

        private void prepareUnsupportFeature() {
            InputStream unsupported = getClass().getResourceAsStream(ResourcePaths.URL_EATING);
            audioHandler.playAudio(unsupported);
            UIPrompts.IS_NOT_SUPPORT();
        }

        @Override
        public void initComponents() {

        }

        @Override
        public void doAction() {

        }
    }

}

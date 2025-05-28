package controllers;

import enums.Hover;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import models.UserScore;
import styles.UIHovers;
import views.base.AppComponent;
import views.UIPrompts;
import constants.ResourcePaths;
import utils.AudioHandler;
import views.MenuView;
import views.game.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;

@Slf4j
public class MenuController implements MouseListener, ActionListener {

    public static MenuView menuView;
    public static MenuView.MenuModern menuModern;
    private boolean isMenuModern = true;
    private final AudioHandler audioHandler;
    private UIHovers<MenuView> uiHovers;
    private final UserScore currentUser; // Store the authenticated user

    private final List<JButton> jButtonList;

    public MenuController(MenuView menuView) {
        this(menuView, null); // Call the other constructor with null user
    }

    public MenuController(MenuView menuView, UserScore user) {
        MenuController.menuView = menuView;
        this.audioHandler = new AudioHandler();
        this.currentUser = user; // Store the user
        this.jButtonList = Arrays.asList(
            menuView.jButton_Mode_Classic,
            menuView.jButton_Mode_Modern,
            menuView.jButton_Mode_Campaign);
        this.uiHovers = new UIHovers<>(menuView);

        // Register this controller as listener
        menuView.jButton_Mode_Classic.addMouseListener(this);
        menuView.jButton_Mode_Modern.addMouseListener(this);
        // Add other registrations

        log.info("MenuController initialized with user: " +
                          (user != null ? user.getUsername() : "null"));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == menuView.jButton_Mode_Classic) {
            log.info("Starting Classic mode with user: " +
                              (currentUser != null ? currentUser.getUsername() : "null"));
            menuView.dispose();
            Snake snake = new Snake("Classic", currentUser);
            snake.startGame();
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
                        uiHovers.setHoverButton(Hover.ENABLE.isStatus(), "dark", button);
                    } else {
                        uiHovers.setHoverButton(Hover.ENABLE.isStatus(), "light", button);
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
                        uiHovers.setHoverButton(Hover.DISABLE.isStatus(), "dark", button);
                    } else {
                        uiHovers.setHoverButton(Hover.DISABLE.isStatus(), "light", button);
                    }
                });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Off")) {
            log.info("user click Off sound");
            menuView.setAudio(false);
        } else if (e.getActionCommand().equals("On")) {
            menuView.setAudio(Hover.ENABLE.isStatus());
        }
    }

    private void prepareUnsupportFeature() {
        InputStream unsupported = getClass().getResourceAsStream(ResourcePaths.URL_EATING);
        audioHandler.playAudio(unsupported);
        UIPrompts.IS_NOT_SUPPORT();
    }

    public static class MenuModernController extends AppComponent implements MouseListener {

        private final MenuView.MenuModern menuModern;
        private final AudioHandler audioHandler;
        private UIHovers<MenuView.MenuModern> uiHovers;
        private final UserScore currentUser; // Store the authenticated user

        public MenuModernController(MenuView.MenuModern menuModern) {
            this(menuModern, null); // Call the other constructor with null user
        }

        public MenuModernController(MenuView.MenuModern menuModern, UserScore user) {
            this.menuModern = menuModern;
            this.audioHandler = new AudioHandler();
            this.currentUser = user; // Store the user
            this.uiHovers = new UIHovers<>(menuModern);

            // Register listeners
            menuModern.getJButton_NoMaze().addMouseListener(this);
            // Add other registrations
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == menuModern.getJButton_NoMaze()) {
                log.info("Starting NoMaze mode with user: " +
                                  (currentUser != null ? currentUser.getUsername() : "null"));
                menuView.dispose();
                Snake snake = new Snake("NoMaze", currentUser);
                snake.startGame();
            }
            if (e.getSource() == menuModern.getJButton_Box()) {
                EventQueue.invokeLater(() -> {
                    menuView.dispose();
                    new Snake("Box", currentUser).startGame();
                });
            }
            if (e.getSource() == menuModern.getJButton_Tunnel()) {
                EventQueue.invokeLater(() -> {
                    menuView.dispose();
                    new Snake("Tunnel", currentUser).startGame();
                });
            }
            if (e.getSource() == menuModern.getJButton_Mill()) {
                EventQueue.invokeLater(() -> {
                    menuView.dispose();
                    new Snake("Mill", currentUser).startGame();
                });
            }
            if (e.getSource() == menuModern.getJButton_Rails()) {
                EventQueue.invokeLater(() -> {
                    menuView.dispose();
                    new Snake("Rails", currentUser).startGame();
                });
            }
            if (e.getSource() == menuModern.getJButton_Apartment()) {
                EventQueue.invokeLater(() -> {
                    menuView.dispose();
                    new Snake("Apartment", currentUser).startGame();
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
                if (e.getSource() == menuModern.getJButton_NoMaze()) {
                    uiHovers.setHoverButton(Hover.ENABLE.isStatus(), "dark",
                                            menuModern.getJButton_NoMaze());
                }
                if (e.getSource() == menuModern.getJButton_Box()) {
                    uiHovers.setHoverButton(Hover.ENABLE.isStatus(), "dark",
                                            menuModern.getJButton_Box());
                }
                if (e.getSource() == menuModern.getJButton_Tunnel()) {
                    uiHovers.setHoverButton(Hover.ENABLE.isStatus(), "dark",
                                            menuModern.getJButton_Tunnel());
                }
                if (e.getSource() == menuModern.getJButton_Mill()) {
                    uiHovers.setHoverButton(Hover.ENABLE.isStatus(), "dark",
                                            menuModern.getJButton_Mill());
                }
                if (e.getSource() == menuModern.getJButton_Rails()) {
                    uiHovers.setHoverButton(Hover.ENABLE.isStatus(), "dark",
                                            menuModern.getJButton_Rails());
                }
                if (e.getSource() == menuModern.getJButton_Apartment()) {
                    uiHovers.setHoverButton(Hover.ENABLE.isStatus(), "dark",
                                            menuModern.getJButton_Apartment());
                }
            } else {
                if (e.getSource() == menuModern.getJButton_NoMaze()) {
                    uiHovers.setHoverButton(Hover.ENABLE.isStatus(), "light",
                                            menuModern.getJButton_NoMaze());
                }
                if (e.getSource() == menuModern.getJButton_Box()) {
                    uiHovers.setHoverButton(Hover.ENABLE.isStatus(), "light",
                                            menuModern.getJButton_Box());
                }
                if (e.getSource() == menuModern.getJButton_Tunnel()) {
                    uiHovers.setHoverButton(Hover.ENABLE.isStatus(), "light",
                                            menuModern.getJButton_Tunnel());
                }
                if (e.getSource() == menuModern.getJButton_Mill()) {
                    uiHovers.setHoverButton(Hover.ENABLE.isStatus(), "light",
                                            menuModern.getJButton_Mill());
                }
                if (e.getSource() == menuModern.getJButton_Rails()) {
                    uiHovers.setHoverButton(Hover.ENABLE.isStatus(), "light",
                                            menuModern.getJButton_Rails());
                }
                if (e.getSource() == menuModern.getJButton_Apartment()) {
                    uiHovers.setHoverButton(Hover.ENABLE.isStatus(), "light",
                                            menuModern.getJButton_Apartment());
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (menuModern.getStatusToggle()) {
                if (e.getSource() == menuModern.getJButton_NoMaze()) {
                    uiHovers.setHoverButton(Hover.DISABLE.isStatus(), "dark",
                                            menuModern.getJButton_NoMaze());
                }
                if (e.getSource() == menuModern.getJButton_Box()) {
                    uiHovers.setHoverButton(Hover.DISABLE.isStatus(), "dark",
                                            menuModern.getJButton_Box());
                }
                if (e.getSource() == menuModern.getJButton_Tunnel()) {
                    uiHovers.setHoverButton(Hover.DISABLE.isStatus(), "dark",
                                            menuModern.getJButton_Tunnel());
                }
                if (e.getSource() == menuModern.getJButton_Mill()) {
                    uiHovers.setHoverButton(Hover.DISABLE.isStatus(), "dark",
                                            menuModern.getJButton_Mill());
                }
                if (e.getSource() == menuModern.getJButton_Rails()) {
                    uiHovers.setHoverButton(Hover.DISABLE.isStatus(), "dark",
                                            menuModern.getJButton_Rails());
                }
                if (e.getSource() == menuModern.getJButton_Apartment()) {
                    uiHovers.setHoverButton(Hover.DISABLE.isStatus(), "dark",
                                            menuModern.getJButton_Apartment());
                }
            } else {
                if (e.getSource() == menuModern.getJButton_NoMaze()) {
                    uiHovers.setHoverButton(Hover.DISABLE.isStatus(), "light",
                                            menuModern.getJButton_NoMaze());
                }
                if (e.getSource() == menuModern.getJButton_Box()) {
                    uiHovers.setHoverButton(Hover.DISABLE.isStatus(), "light",
                                            menuModern.getJButton_Box());
                }
                if (e.getSource() == menuModern.getJButton_Tunnel()) {
                    uiHovers.setHoverButton(Hover.DISABLE.isStatus(), "light",
                                            menuModern.getJButton_Tunnel());
                }
                if (e.getSource() == menuModern.getJButton_Mill()) {
                    uiHovers.setHoverButton(Hover.DISABLE.isStatus(), "light",
                                            menuModern.getJButton_Mill());
                }
                if (e.getSource() == menuModern.getJButton_Rails()) {
                    uiHovers.setHoverButton(Hover.DISABLE.isStatus(), "light",
                                            menuModern.getJButton_Rails());
                }
                if (e.getSource() == menuModern.getJButton_Apartment()) {
                    uiHovers.setHoverButton(Hover.DISABLE.isStatus(), "light",
                                            menuModern.getJButton_Apartment());
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

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MenuView menuView = new MenuView();
            MenuController menuController = new MenuController(menuView);
            menuView.setVisible(true);
        });
    }

}

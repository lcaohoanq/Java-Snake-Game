package controllers;

import constants.ResourcePaths;
import enums.Hover;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import models.UserScore;
import styles.UIHovers;
import utils.AudioHandler;
import views.MenuModernView;
import views.MenuView;
import views.UIPrompts;
import views.game.Snake;

@Slf4j
@Getter
public class MenuController implements MouseListener, ActionListener {

    private MenuView menuView;
    public MenuModernView menuModern;
    private boolean isMenuModern = true;
    private final AudioHandler audioHandler;
    private UIHovers<MenuView> uiHovers;
    private final UserScore currentUser;
    private final List<JButton> jButtonList;

    private static MenuController instance;

    public MenuController(MenuView menuView, UserScore user) {
        if (instance != null) {
            throw new IllegalStateException("MenuController is already initialized.");
        }
        instance = this;
        this.menuView = menuView;
        this.currentUser = user;
        this.audioHandler = new AudioHandler();
        this.jButtonList = Arrays.asList(
            menuView.jButton_Mode_Classic,
            menuView.jButton_Mode_Modern,
            menuView.jButton_Mode_Campaign
        );
        this.uiHovers = new UIHovers<>(menuView);

        // Register events
        menuView.jButton_Mode_Classic.addMouseListener(this);
        menuView.jButton_Mode_Modern.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == menuView.jButton_Mode_Classic) {
            log.info("Starting Classic mode with user: " +
                         (currentUser != null ? currentUser.getUsername() : "null"));
            menuView.dispose();
            Snake.getInstance("Classic", currentUser).startGame();
        }

        // Change this in MenuController.mouseClicked()
        if (e.getSource() == menuView.jButton_Mode_Modern) {
            EventQueue.invokeLater(() -> {
                // If menu already exists, close it
                if (menuModern != null && menuModern.isVisible()) {
                    menuModern.dispose();
                    menuModern = null;
                } else {
                    // Otherwise create a new one
                    menuModern = new MenuModernView();
                    new MenuModernController(menuView, menuModern, currentUser);
                    menuModern.setVisible(true);
                }
            });
        }

        if (e.getSource() == menuView.jButton_Mode_Campaign) {
            EventQueue.invokeLater(this::prepareUnsupportFeature);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        if (menuView.isActive()) {
            jButtonList.stream()
                .filter(button -> e.getSource() == button)
                .forEach(button -> uiHovers.setHoverButton(Hover.ENABLE.isStatus(), "light", button));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (menuView.isActive()) {
            jButtonList.stream()
                .filter(button -> e.getSource() == button)
                .forEach(button -> uiHovers.setHoverButton(Hover.DISABLE.isStatus(), "light", button));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Off")) {
            log.info("User clicked Off sound");
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

}

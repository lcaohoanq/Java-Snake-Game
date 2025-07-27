package controllers;

import constants.ResourcePaths;
import enums.Hover;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import models.UserScore;
import styles.UIHovers;
import utils.AudioHandler;
import views.MenuModernView;
import views.MenuView;
import views.UIPrompts;
import views.base.AppComponent;
import views.game.Snake;

@Slf4j
public class MenuModernController extends AppComponent implements MouseListener {

    private final MenuView menuView; // ✅ Needed to avoid static reference
    private final MenuModernView menuModern;
    private final AudioHandler audioHandler;
    private final UIHovers<MenuModernView> uiHovers;
    private final UserScore currentUser;

    public MenuModernController(MenuView menuView, MenuModernView menuModern, UserScore user) {
        this.menuView = menuView;
        this.menuModern = menuModern;
        this.currentUser = user;
        this.audioHandler = new AudioHandler();
        this.uiHovers = new UIHovers<>(menuModern);

        // Register listeners
        menuModern.getJButton_NoMaze().addMouseListener(this);
        menuModern.getJButton_Box().addMouseListener(this);
        menuModern.getJButton_Tunnel().addMouseListener(this);
        menuModern.getJButton_Mill().addMouseListener(this);
        menuModern.getJButton_Rails().addMouseListener(this);
        menuModern.getJButton_Apartment().addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == menuModern.getJButton_NoMaze()) {
            startGame("NoMaze");
        } else if (e.getSource() == menuModern.getJButton_Box()) {
            startGame("Box");
        } else if (e.getSource() == menuModern.getJButton_Tunnel()) {
            startGame("Tunnel");
        } else if (e.getSource() == menuModern.getJButton_Mill()) {
            startGame("Mill");
        } else if (e.getSource() == menuModern.getJButton_Rails()) {
            startGame("Rails");
        } else if (e.getSource() == menuModern.getJButton_Apartment()) {
            startGame("Apartment");
        }
    }

    private void startGame(String mode) {
        log.info("Starting " + mode + " mode with user: " +
                     (currentUser != null ? currentUser.getUsername() : "null"));
        if (menuView != null) {
            menuView.dispose();
        }
        Snake.getInstance(mode, currentUser).startGame();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        handleHover(e, true);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        handleHover(e, false);
    }

    private void handleHover(MouseEvent e, boolean enable) {
        String style = "light";
        boolean status = enable ? Hover.ENABLE.isStatus() : Hover.DISABLE.isStatus();
        if (e.getSource() == menuModern.getJButton_NoMaze()) {
            uiHovers.setHoverButton(status, style, menuModern.getJButton_NoMaze());
        } else if (e.getSource() == menuModern.getJButton_Box()) {
            uiHovers.setHoverButton(status, style, menuModern.getJButton_Box());
        } else if (e.getSource() == menuModern.getJButton_Tunnel()) {
            uiHovers.setHoverButton(status, style, menuModern.getJButton_Tunnel());
        } else if (e.getSource() == menuModern.getJButton_Mill()) {
            uiHovers.setHoverButton(status, style, menuModern.getJButton_Mill());
        } else if (e.getSource() == menuModern.getJButton_Rails()) {
            uiHovers.setHoverButton(status, style, menuModern.getJButton_Rails());
        } else if (e.getSource() == menuModern.getJButton_Apartment()) {
            uiHovers.setHoverButton(status, style, menuModern.getJButton_Apartment());
        }
    }

    @Override
    public void initComponents() {
    }

    @Override
    public void doAction() {
    }
}

package views.game;

import javax.swing.*;

import controllers.MenuController;
import controllers.PlayController;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import models.UserScore;
import styles.UIBorders;
import styles.UIImages;
import styles.UIColors;

import java.awt.*;

@Slf4j
@Getter
public class Snake extends JFrame {
    private final JMenuBar jMenuBar = new JMenuBar();
    private final JMenu jMenu = new JMenu("HELP");
    private final JMenuItem jMenuItem_Back_To_Main_Menu = new JMenuItem("Back to main menu");
    private final UserScore currentUser;

    public Snake(String mode, UserScore user) {
        this.currentUser = user;
        log.info("Snake created with user: " +
                          (user != null ? user.getUsername() : "null"));
        initMenu();
        initUI(mode);
    }

    private void initUI(String mode) {
        checkMode(mode);
        setResizable(false);
        pack();
        setTitle("Snake");
        setIconImage(UIImages.icon);
        setJMenuBar(jMenuBar);
        MenuController.menuView.dispose();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
    }

    private void initMenu() {
        jMenuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        jMenuBar.setBorder(UIBorders.LINE_MENU_BAR);

        // Add user info to menu bar
        if (currentUser != null) {
            JMenu userMenu = new JMenu("User: " + currentUser.getUsername());
            userMenu.setForeground(UIColors.TEXT_COLOR_L);
            jMenuBar.add(userMenu);
        }

        jMenuBar.add(jMenu);
        jMenu.add(jMenuItem_Back_To_Main_Menu);
        jMenuItem_Back_To_Main_Menu.addActionListener(new PlayController(this));
        this.setJMenuBar(jMenuBar);
    }

    public UserScore getCurrentUser() {
        return currentUser;
    }

    private void checkMode(String mode) {
        log.info("Creating game board with user: " +
                          (currentUser != null ? currentUser.getUsername() : "null"));

        // Always explicitly pass the user to the board
        if (mode.equals("Classic")) {
            add(new NoMaze(currentUser));
        }
        if (mode.equals("NoMaze")) {
            add(new NoMaze(currentUser));
        }
        if (mode.equals("Box")) {
            add(new Box(currentUser));
        }
        if (mode.equals("Tunnel")) {
            add(new Tunnel(currentUser));
        }
        if (mode.equals("Mill")) {
            add(new Mill(currentUser));
        }
        if (mode.equals("Rails")) {
            add(new Rails(currentUser));
        }
        if (mode.equals("Apartment")) {
            add(new Apartment(currentUser));
        }
        if (mode.equals("Campaign")) {
            add(new Campaign(currentUser));
        }
    }

    public void startGame() {
        setVisible(true);
    }

    public void stopGame() {
        dispose();
    }
}

package views;

import javax.swing.*;

import controllers.MenuController;
import controllers.PlayController;
import modules.board.*;
import modules.board.Apartment;
import modules.board.Box;
import modules.board.Campaign;
import modules.board.Mill;
import modules.board.NoMaze;
import modules.board.Rails;
import modules.board.Tunnel;
import styles.UIBorders;
import styles.UIImages;

import java.awt.*;

public class Snake extends JFrame {
    private final JMenuBar jMenuBar = new JMenuBar();
    private final JMenu jMenu = new JMenu("HELP");
    private final JMenuItem jMenuItem_Back_To_Main_Menu = new JMenuItem("Back to main menu");

    public Snake(String mode) {
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
        jMenuBar.add(jMenu);
        jMenu.add(jMenuItem_Back_To_Main_Menu);
        jMenuItem_Back_To_Main_Menu.addActionListener(new PlayController(this));
        this.setJMenuBar(jMenuBar);
    }

    private void checkMode(String mode) {
        if (mode.equals("Classic")) {
            add(new NoMaze()); // NoMaze is the default mode
        }
        if (mode.equals("NoMaze")) {
            add(new NoMaze());  // NoMaze is the default mode
        }
        if (mode.equals("Box")) {
            add(new Box());
        }
        if (mode.equals("Tunnel")) {
            add(new Tunnel());
        }
        if (mode.equals("Mill")) {
            add(new Mill());
        }
        if (mode.equals("Rails")) {
            add(new Rails());
        }
        if (mode.equals("Apartment")) {
            add(new Apartment());
        }
        if (mode.equals("Campaign")) {
            add(new Campaign());
        }
    }

    public void startGame() {
        setVisible(true);
    }

    public void stopGame() {
        dispose();
    }
}

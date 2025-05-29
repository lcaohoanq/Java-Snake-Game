package views.game;

import controllers.MenuController;
import controllers.PlayController;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import models.UserScore;
import styles.UIBorders;
import styles.UIColors;
import styles.UIImages;

@Slf4j
@Getter
public class Snake extends JFrame {

    private final JMenuBar jMenuBar = new JMenuBar();
    private final JMenu jMenu = new JMenu("HELP");
    private final JMenuItem jMenuItem_Back_To_Main_Menu = new JMenuItem("Back to main menu");
    private final UserScore currentUser;

    private static final Map<String, Function<UserScore, JPanel>> gameBoardMap = new HashMap<>();

    static {
        gameBoardMap.put("Classic", NoMaze::new);
        gameBoardMap.put("NoMaze", NoMaze::new);
        gameBoardMap.put("Box", Box::new);
        gameBoardMap.put("Tunnel", Tunnel::new);
        gameBoardMap.put("Mill", Mill::new);
        gameBoardMap.put("Rails", Rails::new);
        gameBoardMap.put("Apartment", Apartment::new);
        gameBoardMap.put("Campaign", Campaign::new);
    }

    private static Snake instance;

    public static Snake getInstance(String mode, UserScore user) {
        if (instance != null) {
            instance.dispose(); // cleanup old instance
        }
        instance = new Snake(mode, user);
        return instance;
    }

    public Snake(String mode, UserScore user) {
        this.currentUser = user;
        log.info("Snake created with user: {}", user != null ? user.getUsername() : "null");
        initMenuBar();
        initGameUI(mode);
    }

    private void initMenuBar() {
        jMenuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        jMenuBar.setBorder(UIBorders.LINE_MENU_BAR);

        if (currentUser != null) {
            JMenu userMenu = new JMenu("User: " + currentUser.getUsername());
            userMenu.setForeground(UIColors.TEXT_COLOR_L);
            jMenuBar.add(userMenu);
        }

        jMenuItem_Back_To_Main_Menu.addActionListener(new PlayController(this));
        jMenu.add(jMenuItem_Back_To_Main_Menu);
        jMenuBar.add(jMenu);

        setJMenuBar(jMenuBar);
    }

    private void initGameUI(String mode) {
        setResizable(false);
        setTitle("Snake");
        setIconImage(UIImages.icon);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        addGameBoard(mode);

        pack();
        setVisible(false); // Controlled via startGame()
    }

    private void addGameBoard(String mode) {
        log.info("Creating game board for mode: {} with user: {}", mode,
                 currentUser != null ? currentUser.getUsername() : "null");

        Function<UserScore, JPanel> boardCreator = gameBoardMap.get(mode);
        if (boardCreator != null) {
            add(boardCreator.apply(currentUser));
        } else {
            log.warn("Unknown game mode: {}. Falling back to Classic.", mode);
            add(new NoMaze(currentUser));
        }
    }

    public void startGame() {
        setVisible(true);
    }

    public void stopGame() {
        dispose();
    }
}

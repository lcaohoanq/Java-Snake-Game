package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import constants.Info;
import constants.Path;
import constants.Size;
import controllers.ScoreFormController;
import styles.BorderHandler;
import styles.ColorHandler;
import styles.FontHandler;
import styles.ImageHandler;
import utils.AudioHandler;
import utils.DataHandler;

public class SelectFormView extends JFrame {
    // Components
    private JLabel jLabel_Title;
    private JButton jButton_SignIn;
    private JButton jButton_SignUp;
    private JButton jButton_ShowScore;
    private JButton jButton_PlayWithoutSignIn;
    private JLabel logoLabel;
    private JPanel jPanel_icon;
    private JPanel jPanel_TopZone;
    private JPanel jPanel_MiddleZone;
    private JPanel jPanel_SignUp;
    private JPanel jPanel_SignIn;
    private JPanel jPanel_ShowScore;
    private JPanel jPanel_PlayWithoutSignIn;
    private JPanel jPanel_Container;

    // MenuBar
    private JMenuBar jMenuBar;
    private JMenu jMenu = new JMenu("HELP");
    private JMenuItem jMenuItem_AboutMe = new JMenuItem("About me");

    // Other
    private LoginFormView loginFormView;
    private RegisterFormView registerFormView;
    private ScoreFormView scoreFormView = new ScoreFormView();
    private ScoreFormController scoreFormController;
    private Snake snake;

    // Action
    ActionListener ac;

    public SelectFormView() {
        // Frame setup
        setSize(Size.WIDTH_MAIN_FRAME, Size.HEIGHT_MAIN_FRAME);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        // Setting up UI elements
        setUpMenuBar();
        setUpTopZone();
        setUpMiddleZone();
        setUpContainer();
        AudioHandler.playAudio("src/resources/intro.wav");
        DataHandler.readScore(Path.urlScore); // Read score from file
        doAction();
    }

    private void setUpMenuBar() {
        // Menu bar setup
        jMenuBar = new JMenuBar();
        jMenuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        jMenuBar.setBorder(BorderHandler.LINE_BORDER_MENU_BAR);
        jMenuBar.add(jMenu);
        jMenu.add(jMenuItem_AboutMe);
        this.setJMenuBar(jMenuBar);
    }

    private void setUpTopZone() {
        // Top zone setup
        jLabel_Title = new JLabel("Snake Game", JLabel.CENTER);
        jLabel_Title.setFont(FontHandler.LOGO);
        jLabel_Title.setBackground(ColorHandler.PRIMARY_COLOR);
        jLabel_Title.setForeground(ColorHandler.TEXT_COLOR);
        jLabel_Title.setBorder(BorderHandler.TITLE_SELECT_FORM);
        jPanel_icon = new JPanel();
        jPanel_icon.setPreferredSize(new Dimension(100, 100));
        jPanel_icon.setBackground(ColorHandler.PRIMARY_COLOR);
        logoLabel = new JLabel(
                new ImageIcon(ImageHandler.logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        // logoLabel.setBorder(); // Add border to the logo label
        jPanel_icon.add(logoLabel);
        jPanel_TopZone = new JPanel(new BorderLayout());
        jPanel_TopZone.setBackground(ColorHandler.PRIMARY_COLOR);
        jPanel_TopZone.add(jPanel_icon, BorderLayout.CENTER);
        jPanel_TopZone.add(jLabel_Title, BorderLayout.SOUTH);
    }

    private void setUpMiddleZone() {
        // Middle zone setup
        jButton_SignIn = createButton("Sign In");
        jButton_SignUp = createButton("Sign Up");
        jButton_ShowScore = createButton("Show Score");
        jButton_PlayWithoutSignIn = createButton("Play without Sign In");

        jPanel_SignIn = createPanelWithButton(jButton_SignIn);
        jPanel_SignUp = createPanelWithButton(jButton_SignUp);
        jPanel_ShowScore = createPanelWithButton(jButton_ShowScore);
        jPanel_PlayWithoutSignIn = createPanelWithButton(jButton_PlayWithoutSignIn);

        jPanel_MiddleZone = new JPanel(new GridLayout(4, 1));
        jPanel_MiddleZone.setBackground(ColorHandler.PRIMARY_COLOR);
        jPanel_MiddleZone.add(jPanel_SignIn);
        jPanel_MiddleZone.add(jPanel_SignUp);
        jPanel_MiddleZone.add(jPanel_ShowScore);
        jPanel_MiddleZone.add(jPanel_PlayWithoutSignIn);
    }

    private JButton createButton(String text) {
        // Button creation
        JButton button = new JButton(text);
        button.setPreferredSize(Size.SIZE_BUTTON);
        button.setFont(FontHandler.JBUTTON);
        button.setBackground(ColorHandler.TEXT_COLOR);
        button.setForeground(ColorHandler.PRIMARY_COLOR);
        return button;
    }

    private JPanel createPanelWithButton(JButton button) {
        // Panel creation containing a button
        JPanel panel = new JPanel();
        panel.setBackground(ColorHandler.PRIMARY_COLOR);
        panel.setBorder(BorderHandler.MIDDLE_ZONE_SELECT_FORM);
        panel.add(button);
        return panel;
    }

    private void setUpContainer() {
        // Container setup
        jPanel_Container = new JPanel(new BorderLayout());
        jPanel_Container.setBackground(ColorHandler.PRIMARY_COLOR);
        jPanel_Container.setBorder(BorderHandler.CONTAINER_SELECT_FORM);
        jPanel_Container.add(jPanel_TopZone, BorderLayout.NORTH);
        jPanel_Container.add(jPanel_MiddleZone, BorderLayout.CENTER);
        this.add(jPanel_Container);
    }

    private void doAction() {
        // Action listeners for buttons
        jMenuItem_AboutMe.addActionListener(new Info());
        jButton_SignIn.addActionListener(e -> {
            loginFormView = new LoginFormView();
            loginFormView.setVisible(true);
            this.dispose();
        });

        jButton_SignUp.addActionListener(e -> {
            registerFormView = new RegisterFormView();
            registerFormView.setVisible(true);
            this.dispose();
        });
        jButton_ShowScore.addActionListener(new ScoreFormController(scoreFormView));

        jButton_PlayWithoutSignIn.addActionListener(e -> {
            Snake snakeGame = new Snake();
            snakeGame.startGame();
            this.dispose();
        });
    }
}

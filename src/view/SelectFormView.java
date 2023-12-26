package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import constants.ColorsHandling;
import constants.Info;
import constants.Path;
import constants.Size;
import controller.ScoreFormController;
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

    // Dimensions and Styles
    private Dimension dimension_button = new Dimension(300, 50);
    Border border_color = BorderFactory.createLineBorder(Color.WHITE, 1);
    private EmptyBorder emptyBorder_logo = new EmptyBorder(50, 0, 0, 0);
    private EmptyBorder emptyBorder_title = new EmptyBorder(20, 0, 30, 0);
    private EmptyBorder emptyBorder_container = new EmptyBorder(0, 30, 0, 30);
    private EmptyBorder emptyBorder_middleZone = new EmptyBorder(10, 0, 10, 0);
    private Font font_button = new Font("Dialog", Font.BOLD, 20);
    private Font font_title = new Font("Dialog", Font.BOLD, 50);
    // Logo
    private URL url = SelectFormView.class.getResource("/resources/logo.png");
    private Image logo = Toolkit.getDefaultToolkit().getImage(url);
    private ImageIcon logoIcon = new ImageIcon(logo);

    // MenuBar
    private JMenuBar jMenuBar;
    private JMenu jMenu = new JMenu("HELP");
    private JMenuItem jMenuItem_AboutMe = new JMenuItem("About me");

    // Other
    LoginFormView loginFormView;
    RegisterFormView registerFormView;
    ScoreFormView scoreFormView = new ScoreFormView();
    ScoreFormController scoreFormController;
    Snake snake;

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
        jMenuBar.setBorder(border_color);
        jMenuBar.add(jMenu);
        jMenu.add(jMenuItem_AboutMe);
        this.setJMenuBar(jMenuBar);
    }

    private void setUpTopZone() {
        // Top zone setup
        jLabel_Title = new JLabel("Snake Game", JLabel.CENTER);
        jLabel_Title.setFont(font_title);
        jLabel_Title.setBackground(ColorsHandling.PRIMARY_COLOR);
        jLabel_Title.setForeground(ColorsHandling.OTHER_OPTIONS);
        jLabel_Title.setBorder(emptyBorder_title);

        logoIcon = new ImageIcon(logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)); // Set the size

        jPanel_icon = new JPanel();
        // jPanel_icon.setLayout();
        jPanel_icon.setPreferredSize(new Dimension(100, 100));
        // jPanel_icon.setBorder(BorderFactory.createLineBorder(Color.RED, 1)); // Add
        // border to jPanel_icon
        jPanel_icon.setBackground(ColorsHandling.PRIMARY_COLOR);

        logoLabel = new JLabel(logoIcon);
        // logoLabel.setBorder(); // Add border to the logo label
        jPanel_icon.add(logoLabel);

        jPanel_TopZone = new JPanel(new BorderLayout());
        jPanel_TopZone.setBackground(ColorsHandling.PRIMARY_COLOR);
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
        jPanel_MiddleZone.setBackground(ColorsHandling.PRIMARY_COLOR);
        jPanel_MiddleZone.add(jPanel_SignIn);
        jPanel_MiddleZone.add(jPanel_SignUp);
        jPanel_MiddleZone.add(jPanel_ShowScore);
        jPanel_MiddleZone.add(jPanel_PlayWithoutSignIn);
    }

    private JButton createButton(String text) {
        // Button creation
        JButton button = new JButton(text);
        button.setPreferredSize(dimension_button);
        button.setFont(font_button);
        button.setBackground(ColorsHandling.TEXT_COLOR);
        button.setForeground(ColorsHandling.PRIMARY_COLOR);
        return button;
    }

    private JPanel createPanelWithButton(JButton button) {
        // Panel creation containing a button
        JPanel panel = new JPanel();
        panel.setBackground(ColorsHandling.PRIMARY_COLOR);
        panel.setBorder(emptyBorder_middleZone);
        panel.add(button);
        return panel;
    }

    private void setUpContainer() {
        // Container setup
        jPanel_Container = new JPanel(new BorderLayout());
        jPanel_Container.setBackground(ColorsHandling.PRIMARY_COLOR);
        jPanel_Container.setBorder(emptyBorder_container);
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

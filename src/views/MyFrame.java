package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import constants.Info;
import constants.Paths;
import constants.Sizes;
import controllers.LoginController;
import styles.Borders;
import styles.Colors;
import styles.Fonts;
import styles.Images;
import utils.DataHandler;

public abstract class MyFrame extends JFrame {

  protected JPanel jPanel_Container;
  private JPanel jPanel_Left;
  private JLabel jLabel_Left_Tittle;
  private JPanel jPanel_Left_Icon;
  private JLabel jLabel_Left_Icon;
  private JButton jButton_Left_Play;
  protected JPanel jPanel_Right;
  protected JPanel jPanel_Right_Top_Tittle;
  protected JLabel jLabel_Right_Top_Tittle;

  protected JPanel jPanel_Right_Middle_Username;
  protected JLabel jLabel_Right_Middle_Username;
  public static JTextField jTextField_Right_Middle_Username; // 20 is the number of columns
  protected JPanel jPanel_Right_Middle_Password;
  protected JLabel jLabel_Right_Middle_Password;
  public static JPasswordField jPasswordField_Right_Middle_Password;
  protected JLabel jLabel_Right_Middle_Confirm_Password;
  public static JPasswordField jPasswordField_Right_Middle_Confirm_Password;
  protected JPanel jPanel_Right_Middle_Confirm_Password;

  protected JPanel jPanel_Right_Middle_Data;
  protected JButton jButton_Right_Play;

  public static JPanel jPanel_Right_Bottom_Button;
  public static JButton jButton_Right_Bottom_Submit;
  protected JButton jButton_Right_Bottom_Others;
  protected JPanel jPanel_Right_Bottom_Option;
  protected JLabel jLabel_Right_Bottom_Option;

  private LoginController ac;
  private DataHandler dataHandler = new DataHandler();

  // MenuBar
  private JMenuBar jMenuBar;
  private JMenu jMenu = new JMenu("HELP");
  private JMenuItem jMenuItem_AboutMe = new JMenuItem("About me");
  private JMenuItem jMenuItem_Score = new JMenuItem("Show Score");

  public MyFrame() {
    setTitle("Snake Game");
    setSize(Sizes.WIDTH_MY_FRAME, Sizes.HEIGHT_MY_FRAME);
    setIconImage(Images.icon);
    setLocationRelativeTo(null);
    setResizable(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    dataHandler.readFile(Paths.URL_ACCOUNT);
    initUI();
  }

  protected void initUI() {
    initMenu();
    initLeft();
    initRight();
    initContainer();
    doAction();
  }

  private void initMenu() {
    // Menu bar setup
    jMenuBar = new JMenuBar();
    jMenuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
    jMenuBar.setBorder(Borders.LINE_MENU_BAR);
    jMenuBar.add(jMenu);
    jMenu.add(jMenuItem_AboutMe);
    jMenu.add(jMenuItem_Score);
    this.setJMenuBar(jMenuBar);
  }

  private void initLeft() {
    jPanel_Left = new JPanel(new BorderLayout());
    jLabel_Left_Tittle = new JLabel("Snake Game", JLabel.CENTER);
    jLabel_Left_Tittle.setForeground(Colors.PRIMARY_COLOR);
    jLabel_Left_Tittle.setFont(Fonts.LEFT_TITLE);

    jPanel_Left_Icon = new JPanel(new BorderLayout());
    jPanel_Left_Icon.setPreferredSize(new Dimension(100, 100));
    jPanel_Left_Icon.setBackground(Colors.TEXT_COLOR);

    // Use the new image "250-250.png"
    jLabel_Left_Icon = new JLabel(
        new ImageIcon(Images.logoIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH)));
    jPanel_Left_Icon.add(jLabel_Left_Icon, BorderLayout.CENTER);

    jButton_Left_Play = new JButton("Play Now");
    jButton_Left_Play.setBackground(Colors.PRIMARY_COLOR);
    jButton_Left_Play.setForeground(Colors.TEXT_COLOR);
    jButton_Left_Play.setFont(Fonts.BUTTON);

    jPanel_Left.setPreferredSize(new Dimension(Sizes.WIDTH_MY_LEFT_FRAME, Sizes.HEIGHT_MY_LEFT_FRAME));
    jPanel_Left.setBackground(Colors.TEXT_COLOR);
    jPanel_Left.setBorder(Borders.TITLE);

    jPanel_Left.add(jLabel_Left_Tittle, BorderLayout.NORTH);
    jPanel_Left.add(jPanel_Left_Icon, BorderLayout.CENTER);
    jPanel_Left.add(jButton_Left_Play, BorderLayout.SOUTH);
  }

  public abstract void initRight();

  public abstract void initRightTop();

  public abstract void initRightMiddle();

  public abstract void initRightBottom();

  protected void initContainer() {
    jPanel_Container = new JPanel();
    jPanel_Container.setLayout(new BorderLayout());
    jPanel_Container.setBackground(Colors.PRIMARY_COLOR);
    jPanel_Container.add(jPanel_Left, BorderLayout.WEST);
    jPanel_Container.add(jPanel_Right, BorderLayout.CENTER);
    this.add(jPanel_Container);
  }

  protected class ClickOtherOption implements ActionListener {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
      if (jButton_Right_Bottom_Others.getText().equals("Sign in here")) {
        LoginView loginView = new LoginView();
        loginView.setVisible(true);
        dispose();
      } else if (jButton_Right_Bottom_Others.getText().equals("Sign up here")) {
        RegisterView registerView = new RegisterView();
        registerView.setVisible(true);
        dispose();
      }

    }
  }

  protected class PressEnter implements ActionListener {

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
      jButton_Right_Bottom_Submit.doClick();
    }

  }

  protected class ClickPlayNow implements ActionListener {

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
      Snake snakeGame = new Snake();
      snakeGame.startGame();
      dispose();
    }
  }

  protected void doAction() {
    jButton_Left_Play.addActionListener(new ClickPlayNow());
    // jPasswordField_Right_Middle_Password.addActionListener(new PressEnter());
    jMenuItem_AboutMe.addActionListener(new Info());
  }

}

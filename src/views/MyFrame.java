package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import constants.Size;
import controllers.LoginFormController;
import styles.BorderHandler;
import styles.ColorHandler;
import styles.FontHandler;
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

  protected JPanel jPanel_Right_Bottom_Button;
  protected JButton jButton_Right_Bottom_Submit;
  protected JButton jButton_Right_Bottom_Others;
  protected JPanel jPanel_Right_Bottom_Option;
  protected JLabel jLabel_Right_Bottom_Option;

  private LoginFormController ac;
  private DataHandler dataHandler = new DataHandler();
  private URL logoURL;
  private Image logoImage;
  private ImageIcon logoIcon;

  public MyFrame() {
    setTitle("Snake Game");
    setSize(Size.WIDTH_MY_FRAME, Size.HEIGHT_MY_FRAME);
    setLocationRelativeTo(null);
    setResizable(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    initUI();
  }

  protected void initUI() {
    initLeft();
    initRight();
    initContainer();
    doAction();
  }

  private void initLeft() {
    jPanel_Left = new JPanel(new BorderLayout());
    jLabel_Left_Tittle = new JLabel("Snake Game", JLabel.CENTER);
    jLabel_Left_Tittle.setForeground(ColorHandler.PRIMARY_COLOR);
    jLabel_Left_Tittle.setFont(FontHandler.INPUT);

    jPanel_Left_Icon = new JPanel(new BorderLayout());
    jPanel_Left_Icon.setPreferredSize(new Dimension(100, 100));
    jPanel_Left_Icon.setBackground(ColorHandler.TEXT_COLOR);

    // Use the new image "250-250.png"
    logoURL = getClass().getResource("/resources/250-250.png");
    logoImage = Toolkit.getDefaultToolkit().getImage(logoURL);
    logoIcon = new ImageIcon(logoImage);

    jLabel_Left_Icon = new JLabel(logoIcon);
    jPanel_Left_Icon.add(jLabel_Left_Icon, BorderLayout.CENTER);

    jButton_Left_Play = new JButton("Play Now");
    jButton_Left_Play.setBackground(ColorHandler.PRIMARY_COLOR);
    jButton_Left_Play.setForeground(ColorHandler.TEXT_COLOR);
    jButton_Left_Play.setFont(FontHandler.JBUTTON);

    jPanel_Left.setPreferredSize(new Dimension(Size.WIDTH_MY_LEFT_FRAME, Size.HEIGHT_MY_LEFT_FRAME));
    jPanel_Left.setBackground(ColorHandler.TEXT_COLOR);
    jPanel_Left.setBorder(BorderHandler.TITLE);

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
    jPanel_Container.setBackground(ColorHandler.PRIMARY_COLOR);
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

    jPasswordField_Right_Middle_Password.addActionListener(new PressEnter());
  }

}

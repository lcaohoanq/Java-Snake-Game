package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import constants.Path;
import constants.Size;
import controllers.LoginFormController;
import controllers.PlayController;
import styles.BorderHandler;
import styles.ColorHandler;
import styles.FontHandler;
import styles.ImageHandler;
import utils.DataHandler;

public class LoginFormView extends JFrame {
  // Components for Login Form
  private JLabel jLabel_Logo = new JLabel("Login", JLabel.CENTER);
  private JLabel jLabel_Username = new JLabel("Username: ");
  private JLabel jLabel_Password = new JLabel("Password: ");

  // Text fields and button
  public static JTextField jTextField_Username = new JTextField(10);
  public static JPasswordField jPasswordField_Password = new JPasswordField(10);
  public static JButton jButton_LoginButton = new JButton("Login");
  public static JButton jButton_PlayButton = new JButton("Play");
  private JPanel jPanel_emptyJPanel = new JPanel();

  // Additional label for other options
  private JLabel jLabel_OtherOptions_Label = new JLabel("Do not have an account?");
  private JButton jButton_OtherOptions_Button = new JButton("Sign up here");

  // Panels for organizing components
  private JPanel jPanel_container = new JPanel();
  private JPanel jPanel_TopZone = new JPanel();
  private JPanel jPanel_Username = new JPanel(); // Panel for username components
  private JPanel jPanel_Password = new JPanel(); // Panel for password components
  private JPanel jPanel_MiddleZone = new JPanel(); // Middle zone combining username and password
  public static JPanel jPanel_Button = new JPanel(); // Panel for login button
  private JPanel jPanel_OtherOptions = new JPanel(new FlowLayout()); // Panel for other options
  private JPanel jPanel_BottomZone = new JPanel(new BorderLayout()); // Panel for login button and other options

  // Others
  public static CardLayout cardLayout;
  private DataHandler dataHandler = new DataHandler();
  private ActionListener ac;

  public LoginFormView() {
    setTitle("Login");
    setSize(Size.WIDTH_MAIN_FRAME, Size.HEIGHT_MAIN_FRAME);
    setIconImage(ImageHandler.img);
    // pack();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);

    dataHandler.readFile(Path.url); // load data account
    initForm();
    setVisible(true);
  }

  private void initForm() {

    // Setting up title label
    setUpTopZone();

    // Setting up username components
    setUpUsername();

    // Setting up password components
    setUpPassword();

    // Middle zone setup (combining username and password)
    setUpMiddleZone();

    // Bottom zone setup
    setUpBottomZone();

    // Container setup
    setUpContainer();

    // Action
    doAction();
  }

  private class ClickOtherOption implements ActionListener {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
      dispose();
      new RegisterFormView();
    }
  }

  private class PressEnter implements ActionListener {

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
      jButton_LoginButton.doClick();
    }

  }

  private void setUpTopZone() {
    jLabel_Logo.setFont(FontHandler.FONT_LOGO);
    // jLabel_Logo.setPreferredSize(sizeText);
    jLabel_Logo.setForeground(ColorHandler.TEXT_COLOR);
    jLabel_Logo.setBackground(ColorHandler.PRIMARY_COLOR);
    jPanel_TopZone.setLayout(new BorderLayout());
    jPanel_TopZone.setBackground(ColorHandler.PRIMARY_COLOR);
    jPanel_TopZone.setBorder(BorderHandler.LOGO_BORDER);
    jPanel_TopZone.add(jLabel_Logo, BorderLayout.CENTER);
  }

  private void setUpUsername() {
    jLabel_Username.setFont(FontHandler.FONT_TEXT_JLABEL);
    // jLabel_Username.setPreferredSize(sizeText);
    jLabel_Username.setBorder(BorderHandler.JLABEL_BORDER);
    jLabel_Username.setForeground(ColorHandler.TEXT_COLOR);
    jLabel_Username.setBackground(ColorHandler.PRIMARY_COLOR);
    // jTextField_Username.setBorder(border);
    jTextField_Username.setBorder(BorderHandler.LINE_BORDER_INPUT_FIELD);
    jTextField_Username.setFont(FontHandler.FONT_TEXT_JTEXTFIELD);
    jTextField_Username.setBackground(ColorHandler.SECONDARY_COLOR);
    jTextField_Username.setForeground(ColorHandler.TEXT_COLOR);
    jPanel_Username.setBorder(BorderHandler.USERNAME_PANEL_BORDER);
    jPanel_Username.setBackground(ColorHandler.PRIMARY_COLOR);
    jPanel_Username.setLayout(new BoxLayout(jPanel_Username, BoxLayout.Y_AXIS));
    jPanel_Username.add(jLabel_Username);
    jPanel_Username.add(jTextField_Username);
  }

  private void setUpPassword() {
    jLabel_Password.setFont(FontHandler.FONT_TEXT_JLABEL);
    jLabel_Password.setBorder(BorderHandler.JLABEL_BORDER);
    // jLabel_Password.setPreferredSize(sizeText);
    jLabel_Password.setForeground(ColorHandler.TEXT_COLOR);
    jLabel_Password.setBackground(ColorHandler.PRIMARY_COLOR);
    // jTextField_Password.setBorder(border);
    jPasswordField_Password.setFont(FontHandler.FONT_TEXT_JTEXTFIELD);
    jPasswordField_Password.setBorder(BorderHandler.LINE_BORDER_INPUT_FIELD);
    jPasswordField_Password.setBackground(ColorHandler.SECONDARY_COLOR);
    jPasswordField_Password.setForeground(ColorHandler.TEXT_COLOR);
    jPanel_Password.setBorder(BorderHandler.PASSWORD_PANEL_BORDER);
    jPanel_Password.setBackground(ColorHandler.PRIMARY_COLOR);
    jPanel_Password.setLayout(new BoxLayout(jPanel_Password, BoxLayout.Y_AXIS));
    jPanel_Password.add(jLabel_Password);
    jPanel_Password.add(jPasswordField_Password);
  }

  private void setUpMiddleZone() {
    // jPanel_MiddleZone.setBorder(dataZoneBorder);
    jPanel_MiddleZone.setBackground(ColorHandler.PRIMARY_COLOR);
    jPanel_MiddleZone.setLayout(new GridLayout(2, 1));
    jPanel_MiddleZone.add(jPanel_Username);
    jPanel_MiddleZone.add(jPanel_Password);
  }

  private void setUpBottomZone() {
    jButton_LoginButton.setPreferredSize(Size.SIZE_BUTTON);
    jButton_LoginButton.setFont(FontHandler.FONT_TEXT_JBUTTON);
    jButton_LoginButton.setForeground(ColorHandler.PRIMARY_COLOR);
    jButton_LoginButton.setBackground(ColorHandler.TEXT_COLOR);
    jPanel_emptyJPanel.setBackground(ColorHandler.PRIMARY_COLOR);
    jButton_PlayButton.setPreferredSize(Size.SIZE_BUTTON);
    jButton_PlayButton.setFont(FontHandler.FONT_TEXT_JBUTTON);
    jButton_PlayButton.setForeground(ColorHandler.PRIMARY_COLOR);
    jButton_PlayButton.setBackground(ColorHandler.TEXT_COLOR);
    jLabel_OtherOptions_Label.setFont(FontHandler.FONT_TEXT_OTHEROPTIONS);
    jLabel_OtherOptions_Label.setBackground(ColorHandler.PRIMARY_COLOR);
    jLabel_OtherOptions_Label.setForeground(ColorHandler.TEXT_COLOR);
    // jButton_LoginButton.setBorder(border);
    jButton_OtherOptions_Button.setFont(FontHandler.FONT_TEXT_OTHEROPTIONS);
    jButton_OtherOptions_Button.setBorder(null);
    // jButton_OtherOption.setFocusPainted(false); //tat di trang thai hover
    jButton_OtherOptions_Button.setRolloverEnabled(false);
    jButton_OtherOptions_Button.setForeground(ColorHandler.OTHER_OPTIONS); //
    jButton_OtherOptions_Button.setBackground(ColorHandler.PRIMARY_COLOR);
    jPanel_BottomZone.setBackground(ColorHandler.PRIMARY_COLOR);
    // jPanel_BottomZone.setLayout(new BoxLayout(jPanel_BottomZone,
    // BoxLayout.Y_AXIS));
    cardLayout = new CardLayout();
    jPanel_Button.setLayout(cardLayout);
    jPanel_Button.add(jButton_LoginButton, "Card1");
    jPanel_Button.add(jButton_PlayButton, "Card2");
    jPanel_Button.setBackground(ColorHandler.PRIMARY_COLOR);
    jPanel_Button.setBorder(BorderHandler.BUTTON_BORDER);
    jPanel_OtherOptions.add(jLabel_OtherOptions_Label);
    jPanel_OtherOptions.add(jButton_OtherOptions_Button);
    jPanel_OtherOptions.setBackground(ColorHandler.PRIMARY_COLOR);
    jPanel_OtherOptions.setBorder(BorderHandler.OTHER_OPTIONS_BORDER);
    jPanel_BottomZone.add(jPanel_Button, BorderLayout.NORTH);

    jPanel_BottomZone.add(jPanel_OtherOptions, BorderLayout.SOUTH);
  }

  private void setUpContainer() {
    jPanel_container.setLayout(new BorderLayout());
    jPanel_container.setBackground(ColorHandler.PRIMARY_COLOR);
    jPanel_container.setBorder(BorderHandler.CONTAINER_BORDER);
    jPanel_container.add(jPanel_TopZone, BorderLayout.NORTH);
    jPanel_container.add(jPanel_MiddleZone, BorderLayout.CENTER);
    jPanel_container.add(jPanel_BottomZone, BorderLayout.SOUTH);
    this.add(jPanel_container);
  }

  private void doAction() {
    ac = new LoginFormController(dataHandler);
    jButton_PlayButton.addActionListener(new PlayController(this));
    jButton_LoginButton.addActionListener(ac);
    jButton_OtherOptions_Button.addActionListener(new ClickOtherOption());
    jPasswordField_Password.addActionListener(new PressEnter());
  }
}

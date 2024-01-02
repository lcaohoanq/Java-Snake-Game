package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import constants.Size;
import controllers.RegisterFormController;
import models.Account;
import styles.BorderHandler;
import styles.ColorHandler;
import styles.FontHandler;
import styles.ImageHandler;
import utils.DataHandler;

public class RegisterFormView extends JFrame {
  // Components for Login Form
  private JLabel jLabel_Logo = new JLabel("Register", JLabel.CENTER);
  private JLabel jLabel_Username = new JLabel("Username: ");
  private JLabel jLabel_Password = new JLabel("Password: ");
  private JLabel jLabel_ConfirmPassword = new JLabel("Confirm Password: ");

  // Text fields and button
  public static JTextField jTextField_Username = new JTextField(10);
  public static JPasswordField jPasswordField_Password = new JPasswordField(10);
  public static JPasswordField jPasswordField_ConfirmPassword = new JPasswordField(10);
  private JButton jButton_RegisterButton = new JButton("Register");

  // Additional label for other options
  private JLabel jLabel_OtherOptions_Label = new JLabel("Already have an account?");
  private JButton jButton_OtherOptions_Button = new JButton("Sign in here");

  // Panels for organizing components
  private JPanel jPanel_container = new JPanel();
  private JPanel jPanel_TopZone = new JPanel();
  private JPanel jPanel_Username = new JPanel(); // Panel for username components
  private JPanel jPanel_Password = new JPanel(); // Panel for password components
  private JPanel jPanel_ConfirmPassword = new JPanel(); // Panel for confirm password components
  private JPanel jPanel_MiddleZone = new JPanel(); // Middle zone combining username and password
  private JPanel jPanel_Button = new JPanel(); // Panel for login button
  private JPanel jPanel_OtherOptions = new JPanel(); // Panel for other options
  private JPanel jPanel_BottomZone = new JPanel(); // Panel for login button and other options

  // Others
  private DataHandler dataHandler = new DataHandler();
  public static ArrayList<Account> accountList = new ArrayList<>();
  private ActionListener ac;

  public RegisterFormView() {
    setTitle("Register");
    setSize(Size.WIDTH_MAIN_FRAME, Size.HEIGHT_MAIN_FRAME);
    setIconImage(ImageHandler.img);
    // pack();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);
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

    // Setting up confirm password components
    setUpConfirmPassword();

    // Middle zone setup
    setUpMiddleZone();

    // Bottom zone setup
    setUpBottomZone();

    // Container setup
    setUpContainer();

    // Action
    doAction();
  }

  private class PressEnter implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      jButton_RegisterButton.doClick();
    }
  }

  private class ClickOtherOption implements ActionListener {

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
      dispose();
      new LoginFormView();
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
    jLabel_Username.setBorder(BorderHandler.JLABEL_BORDER);
    // jLabel_Username.setPreferredSize(sizeText);
    jLabel_Username.setForeground(ColorHandler.TEXT_COLOR);
    jLabel_Username.setBackground(ColorHandler.PRIMARY_COLOR);
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
    jPasswordField_Password.setBorder(BorderHandler.LINE_BORDER_INPUT_FIELD);
    jPasswordField_Password.setFont(FontHandler.FONT_TEXT_JTEXTFIELD);
    jPasswordField_Password.setBackground(ColorHandler.SECONDARY_COLOR);
    jPasswordField_Password.setForeground(ColorHandler.TEXT_COLOR);
    jPanel_Password.setBorder(BorderHandler.PASSWORD_PANEL_BORDER);
    jPanel_Password.setBackground(ColorHandler.PRIMARY_COLOR);
    jPanel_Password.setLayout(new BoxLayout(jPanel_Password, BoxLayout.Y_AXIS));
    jPanel_Password.add(jLabel_Password);
    jPanel_Password.add(jPasswordField_Password);
  }

  private void setUpConfirmPassword() {
    jLabel_ConfirmPassword.setFont(FontHandler.FONT_TEXT_JLABEL);
    jLabel_ConfirmPassword.setBorder(BorderHandler.JLABEL_BORDER);
    // jLabel_ConfirmPassword.setPreferredSize(sizeText);
    jLabel_ConfirmPassword.setForeground(ColorHandler.TEXT_COLOR);
    jLabel_ConfirmPassword.setBackground(ColorHandler.PRIMARY_COLOR);
    // jTextField_ConfirmPassword.setBorder(border);
    jPasswordField_ConfirmPassword.setFont(FontHandler.FONT_TEXT_JTEXTFIELD);
    jPasswordField_ConfirmPassword.setBorder(BorderHandler.LINE_BORDER_INPUT_FIELD);
    jPasswordField_ConfirmPassword.setBackground(ColorHandler.SECONDARY_COLOR);
    jPasswordField_ConfirmPassword.setForeground(ColorHandler.TEXT_COLOR);
    jPanel_ConfirmPassword.setBorder(BorderHandler.PASSWORD_PANEL_BORDER);
    jPanel_ConfirmPassword.setBackground(ColorHandler.PRIMARY_COLOR);
    jPanel_ConfirmPassword.setLayout(new BoxLayout(jPanel_ConfirmPassword, BoxLayout.Y_AXIS));
    jPanel_ConfirmPassword.add(jLabel_ConfirmPassword);
    jPanel_ConfirmPassword.add(jPasswordField_ConfirmPassword);
  }

  private void setUpMiddleZone() {
    jPanel_MiddleZone.setLayout(new BorderLayout());
    // jPanel_MiddleZone.setBorder(dataZoneBorder);
    jPanel_MiddleZone.setBackground(ColorHandler.PRIMARY_COLOR);
    jPanel_MiddleZone.setLayout(new GridLayout(3, 1));
    jPanel_MiddleZone.add(jPanel_Username);
    jPanel_MiddleZone.add(jPanel_Password);
    jPanel_MiddleZone.add(jPanel_ConfirmPassword);
  }

  private void setUpBottomZone() {
    jButton_RegisterButton.setPreferredSize(Size.SIZE_BUTTON);
    jButton_RegisterButton.setFont(FontHandler.FONT_TEXT_JBUTTON);
    jButton_RegisterButton.setForeground(ColorHandler.PRIMARY_COLOR);
    jButton_RegisterButton.setBackground(ColorHandler.TEXT_COLOR);
    // jButton_LoginButton.setBorder(border);

    jLabel_OtherOptions_Label.setFont(FontHandler.FONT_TEXT_OTHEROPTIONS);
    jLabel_OtherOptions_Label.setForeground(ColorHandler.TEXT_COLOR);
    jLabel_OtherOptions_Label.setBackground(ColorHandler.PRIMARY_COLOR);

    jButton_OtherOptions_Button.setFont(FontHandler.FONT_TEXT_OTHEROPTIONS);
    jButton_OtherOptions_Button.setBorder(null);
    // jButton_OtherOption.setFocusPainted(false); //tat di trang thai hover
    jButton_OtherOptions_Button.setRolloverEnabled(false);
    jButton_OtherOptions_Button.setForeground(ColorHandler.OTHER_OPTIONS);
    jButton_OtherOptions_Button.setBackground(ColorHandler.PRIMARY_COLOR);
    jPanel_BottomZone.setBackground(ColorHandler.PRIMARY_COLOR);
    // jPanel_BottomZone.setLayout(new BoxLayout(jPanel_BottomZone,
    // BoxLayout.Y_AXIS));
    jPanel_BottomZone.setLayout(new BorderLayout());
    jPanel_Button.add(jButton_RegisterButton);
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
    ac = new RegisterFormController(dataHandler);
    jButton_RegisterButton.addActionListener(ac);
    jPasswordField_ConfirmPassword.addActionListener(new PressEnter());
    jButton_OtherOptions_Button.addActionListener(new ClickOtherOption());
  }
}

package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import constants.ColorsHandling;
import constants.Path;
import constants.Size;
import controller.LoginFormController;
import controller.PlayController;
import utils.DataHandler;

public class LoginFormView extends JFrame {
  // Components for Login Form
  private JLabel jLabel_Logo = new JLabel("Login", JLabel.CENTER);
  private JLabel jLabel_Username = new JLabel("Username: ");
  private JLabel jLabel_Password = new JLabel("Password: ");

  // Text fields and button
  public static JTextField jTextField_Username = new JTextField(10);
  public static JTextField jTextField_Password = new JTextField(10);
  public static JButton jButton_LoginButton = new JButton("Login");
  public static JButton jButton_PlayButton = new JButton("Play");
  private JPanel jPanel_emptyJPanel = new JPanel();

  // Additional label for other options
  private JLabel jLabel_OtherOptions_Label = new JLabel("Do not have an account?");
  private JButton jButton_OtherOptions_Button = new JButton("Sign up here");

  // Fonts and dimensions
  Font font_logo = new Font("Dialog", Font.BOLD, 50);
  Font font_text_jLabel = new Font("Dialog", Font.PLAIN, 20);
  Font font_text_JTextField = new Font("Dialog", Font.PLAIN, 35);
  Font font_button = new Font("Dialog", Font.BOLD, 20);
  Font font_otherOptions = new Font("Dialog", Font.PLAIN, 15);
  // Dimension sizeText = new Dimension(100, 30);
  Dimension sizeInputField = new Dimension(50, 10);
  Dimension sizeButton = new Dimension(300, 50);

  // Borders and styling
  EmptyBorder containerBorder = new EmptyBorder(0, 30, 0, 30);
  EmptyBorder logoBorder = new EmptyBorder(50, 0, 0, 0);
  EmptyBorder userNamePanelBorder = new EmptyBorder(50, 0, 50, 0);
  EmptyBorder jLabelBorder = new EmptyBorder(0, 0, 10, 0);
  EmptyBorder userNameFieldBorder = new EmptyBorder(0, 10, 0, 10);
  EmptyBorder passwordPanelBorder = new EmptyBorder(50, 0, 50, 0);
  EmptyBorder passwordFieldBorder = new EmptyBorder(0, 10, 0, 10);
  // EmptyBorder dataZoneBorder = new EmptyBorder(150, 20, 150, 20);
  EmptyBorder buttonBorder = new EmptyBorder(30, 0, 20, 0);
  EmptyBorder otherOptionsBorder = new EmptyBorder(0, 0, 15, 0);
  Border border = BorderFactory.createLineBorder(Color.WHITE, 1);
  URL iconURL = LoginFormView.class.getResource("/resources/key.png");
  Image img = Toolkit.getDefaultToolkit().createImage(iconURL);

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
  ActionListener ac;

  public LoginFormView() {
    setTitle("Login");
    setSize(Size.WIDTH_MAIN_FRAME, Size.HEIGHT_MAIN_FRAME);
    setIconImage(img);
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
    jLabel_Logo.setFont(font_logo);
    // jLabel_Logo.setPreferredSize(sizeText);
    jLabel_Logo.setForeground(ColorsHandling.TEXT_COLOR);
    jLabel_Logo.setBackground(ColorsHandling.PRIMARY_COLOR);
    jPanel_TopZone.setLayout(new BorderLayout());
    jPanel_TopZone.setBackground(ColorsHandling.PRIMARY_COLOR);
    jPanel_TopZone.setBorder(logoBorder);
    jPanel_TopZone.add(jLabel_Logo, BorderLayout.CENTER);
  }

  private void setUpUsername() {
    jLabel_Username.setFont(font_text_jLabel);
    // jLabel_Username.setPreferredSize(sizeText);
    jLabel_Username.setBorder(jLabelBorder);
    jLabel_Username.setForeground(ColorsHandling.TEXT_COLOR);
    jLabel_Username.setBackground(ColorsHandling.PRIMARY_COLOR);
    // jTextField_Username.setBorder(border);
    jTextField_Username.setFont(font_text_JTextField);
    jTextField_Username.setBackground(ColorsHandling.SECONDARY_COLOR);
    jTextField_Username.setForeground(ColorsHandling.TEXT_COLOR);
    jPanel_Username.setBorder(userNamePanelBorder);
    jPanel_Username.setBackground(ColorsHandling.PRIMARY_COLOR);
    jPanel_Username.setLayout(new BoxLayout(jPanel_Username, BoxLayout.Y_AXIS));
    jPanel_Username.add(jLabel_Username);
    jPanel_Username.add(jTextField_Username);
  }

  private void setUpPassword() {
    jLabel_Password.setFont(font_text_jLabel);
    jLabel_Password.setBorder(jLabelBorder);
    // jLabel_Password.setPreferredSize(sizeText);
    jLabel_Password.setForeground(ColorsHandling.TEXT_COLOR);
    jLabel_Password.setBackground(ColorsHandling.PRIMARY_COLOR);
    // jTextField_Password.setBorder(border);
    jTextField_Password.setFont(font_text_JTextField);
    jTextField_Password.setBorder(border);
    jTextField_Password.setBackground(ColorsHandling.SECONDARY_COLOR);
    jTextField_Password.setForeground(ColorsHandling.TEXT_COLOR);

    jPanel_Password.setBorder(passwordPanelBorder);
    jPanel_Password.setBackground(ColorsHandling.PRIMARY_COLOR);
    jPanel_Password.setLayout(new BoxLayout(jPanel_Password, BoxLayout.Y_AXIS));
    jPanel_Password.add(jLabel_Password);
    jPanel_Password.add(jTextField_Password);
  }

  private void setUpMiddleZone() {
    jPanel_MiddleZone.setLayout(new BorderLayout());
    // jPanel_MiddleZone.setBorder(dataZoneBorder);
    jPanel_MiddleZone.setBackground(ColorsHandling.PRIMARY_COLOR);
    jPanel_MiddleZone.setLayout(new GridLayout(2, 1));
    jPanel_MiddleZone.add(jPanel_Username);
    jPanel_MiddleZone.add(jPanel_Password);
  }

  private void setUpBottomZone() {
    jButton_LoginButton.setPreferredSize(sizeButton);
    jButton_LoginButton.setFont(font_button);
    jButton_LoginButton.setForeground(ColorsHandling.PRIMARY_COLOR);
    jButton_LoginButton.setBackground(ColorsHandling.TEXT_COLOR);

    jPanel_emptyJPanel.setBackground(ColorsHandling.PRIMARY_COLOR);

    jButton_PlayButton.setPreferredSize(sizeButton);
    jButton_PlayButton.setFont(font_button);
    jButton_PlayButton.setForeground(ColorsHandling.PRIMARY_COLOR);
    jButton_PlayButton.setBackground(ColorsHandling.TEXT_COLOR);

    jLabel_OtherOptions_Label.setFont(font_otherOptions);
    jLabel_OtherOptions_Label.setBackground(ColorsHandling.PRIMARY_COLOR);
    jLabel_OtherOptions_Label.setForeground(ColorsHandling.TEXT_COLOR);

    // jButton_LoginButton.setBorder(border);
    jButton_OtherOptions_Button.setFont(font_otherOptions);
    jButton_OtherOptions_Button.setBorder(null);
    // jButton_OtherOption.setFocusPainted(false); //tat di trang thai hover
    jButton_OtherOptions_Button.setRolloverEnabled(false);
    jButton_OtherOptions_Button.setForeground(ColorsHandling.OTHER_OPTIONS); //
    jButton_OtherOptions_Button.setBackground(ColorsHandling.PRIMARY_COLOR);

    jPanel_BottomZone.setBackground(ColorsHandling.PRIMARY_COLOR);
    // jPanel_BottomZone.setLayout(new BoxLayout(jPanel_BottomZone,
    // BoxLayout.Y_AXIS));

    cardLayout = new CardLayout();
    jPanel_Button.setLayout(cardLayout);
    jPanel_Button.add(jButton_LoginButton, "Card1");
    jPanel_Button.add(jButton_PlayButton, "Card2");
    jPanel_Button.setBackground(ColorsHandling.PRIMARY_COLOR);
    jPanel_Button.setBorder(buttonBorder);
    jPanel_OtherOptions.add(jLabel_OtherOptions_Label);
    jPanel_OtherOptions.add(jButton_OtherOptions_Button);
    jPanel_OtherOptions.setBackground(ColorsHandling.PRIMARY_COLOR);
    jPanel_OtherOptions.setBorder(otherOptionsBorder);
    jPanel_BottomZone.add(jPanel_Button, BorderLayout.NORTH);
    jPanel_BottomZone.add(jPanel_OtherOptions, BorderLayout.SOUTH);
  }

  private void setUpContainer() {
    jPanel_container.setLayout(new BorderLayout());
    jPanel_container.setBackground(ColorsHandling.PRIMARY_COLOR);
    jPanel_container.setBorder(containerBorder);
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
    jTextField_Password.addActionListener(new PressEnter());
  }
}

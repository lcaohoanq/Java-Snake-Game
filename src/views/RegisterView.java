package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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

public class RegisterView extends MyFrame {

  public RegisterView() {
    super();
  }

  public static void main(String[] args) {
    RegisterView registerView = new RegisterView();
    registerView.setVisible(true);
  }

  @Override
  public void initRight() {
    initRightTop();
    initRightMiddle();
    initRightBottom();

    jPanel_Right = new JPanel(new BorderLayout());
    jPanel_Right.setPreferredSize(new Dimension(Size.WIDTH_MY_RIGHT_FRAME, Size.HEIGHT_MY_RIGHT_FRAME));
    jPanel_Right.setBackground(ColorHandler.PRIMARY_COLOR);
    jPanel_Right.setBorder(BorderHandler.MID_FIELD);

    jPanel_Right.add(jPanel_Right_Top_Tittle, BorderLayout.NORTH);
    jPanel_Right.add(jPanel_Right_Middle_Data, BorderLayout.CENTER);
    jPanel_Right.add(jPanel_Right_Bottom_Option, BorderLayout.SOUTH);

  }

  @Override
  public void initRightTop() {
    jPanel_Right_Top_Tittle = new JPanel();
    jLabel_Right_Top_Tittle = new JLabel("Register", JLabel.CENTER);
    jLabel_Right_Top_Tittle.setForeground(ColorHandler.TEXT_COLOR);
    jLabel_Right_Top_Tittle.setFont(FontHandler.LOGO);

    jPanel_Right_Top_Tittle.setBackground(ColorHandler.PRIMARY_COLOR);
    jPanel_Right_Top_Tittle.add(jLabel_Right_Top_Tittle);
    jPanel_Right_Top_Tittle.setBorder(BorderHandler.TITLE);
  }

  @Override
  public void initRightMiddle() {
    jPanel_Right_Middle_Data = new JPanel(new GridLayout(0, 1));
    jPanel_Right_Middle_Username = new JPanel();
    jPanel_Right_Middle_Password = new JPanel();
    jPanel_Right_Middle_Confirm_Password = new JPanel();
    jPanel_Right_Bottom_Button = new JPanel();

    jLabel_Right_Middle_Username = new JLabel("Username");
    jLabel_Right_Middle_Password = new JLabel("Password");
    jLabel_Right_Middle_Confirm_Password = new JLabel("Confirm Password");

    jTextField_Right_Middle_Username = new JTextField(20);
    jPasswordField_Right_Middle_Password = new JPasswordField(20);
    jPasswordField_Right_Middle_Confirm_Password = new JPasswordField(20);

    jButton_Right_Bottom_Submit = new JButton("Submit");

    jLabel_Right_Middle_Username.setForeground(ColorHandler.TEXT_COLOR);
    jLabel_Right_Middle_Username.setFont(FontHandler.JLABEL);
    jLabel_Right_Middle_Username.setBorder(BorderHandler.MID_LABEL);
    jTextField_Right_Middle_Username.setFont(FontHandler.INPUT_SMALL);
    jTextField_Right_Middle_Username.setBackground(ColorHandler.PRIMARY_COLOR);
    jTextField_Right_Middle_Username.setForeground(ColorHandler.TEXT_COLOR);

    jLabel_Right_Middle_Password.setForeground(ColorHandler.TEXT_COLOR);
    jLabel_Right_Middle_Password.setFont(FontHandler.JLABEL);
    jLabel_Right_Middle_Password.setBorder(BorderHandler.MID_LABEL);
    jPasswordField_Right_Middle_Password.setFont(FontHandler.INPUT_SMALL);
    jPasswordField_Right_Middle_Password.setBackground(ColorHandler.PRIMARY_COLOR);
    jPasswordField_Right_Middle_Password.setForeground(ColorHandler.TEXT_COLOR);

    jLabel_Right_Middle_Confirm_Password.setForeground(ColorHandler.TEXT_COLOR);
    jLabel_Right_Middle_Confirm_Password.setFont(FontHandler.JLABEL);
    jLabel_Right_Middle_Confirm_Password.setBorder(BorderHandler.MID_LABEL);
    jPasswordField_Right_Middle_Confirm_Password.setFont(FontHandler.INPUT_SMALL);
    jPasswordField_Right_Middle_Confirm_Password.setBackground(ColorHandler.PRIMARY_COLOR);
    jPasswordField_Right_Middle_Confirm_Password.setForeground(ColorHandler.TEXT_COLOR);

    jPanel_Right_Middle_Username.setLayout(new BoxLayout(jPanel_Right_Middle_Username, BoxLayout.Y_AXIS));
    jPanel_Right_Middle_Username.setBackground(ColorHandler.PRIMARY_COLOR);
    jPanel_Right_Middle_Username.add(jLabel_Right_Middle_Username);
    jPanel_Right_Middle_Username.add(jTextField_Right_Middle_Username);
    jPanel_Right_Middle_Username.setBorder(BorderHandler.MIDDLE);

    jPanel_Right_Middle_Password.setLayout(new BoxLayout(jPanel_Right_Middle_Password, BoxLayout.Y_AXIS));
    jPanel_Right_Middle_Password.setBackground(ColorHandler.PRIMARY_COLOR);
    jPanel_Right_Middle_Password.add(jLabel_Right_Middle_Password);
    jPanel_Right_Middle_Password.add(jPasswordField_Right_Middle_Password);
    jPanel_Right_Middle_Password.setBorder(BorderHandler.MIDDLE);

    jPanel_Right_Middle_Confirm_Password
        .setLayout(new BoxLayout(jPanel_Right_Middle_Confirm_Password, BoxLayout.Y_AXIS));
    jPanel_Right_Middle_Confirm_Password.setBackground(ColorHandler.PRIMARY_COLOR);
    jPanel_Right_Middle_Confirm_Password.add(jLabel_Right_Middle_Confirm_Password);
    jPanel_Right_Middle_Confirm_Password.add(jPasswordField_Right_Middle_Confirm_Password);
    jPanel_Right_Middle_Confirm_Password.setBorder(BorderHandler.MIDDLE);

    jButton_Right_Bottom_Submit.setBackground(ColorHandler.TEXT_COLOR);
    jButton_Right_Bottom_Submit.setForeground(ColorHandler.PRIMARY_COLOR);
    jButton_Right_Bottom_Submit.setFont(FontHandler.JBUTTON);
    jButton_Right_Bottom_Submit.setPreferredSize(Size.SIZE_BUTTON);
    jPanel_Right_Bottom_Button.setBackground(ColorHandler.PRIMARY_COLOR);
    jPanel_Right_Bottom_Button.add(jButton_Right_Bottom_Submit);
    jPanel_Right_Bottom_Button.setBorder(BorderHandler.BUTTON);

    // Add jPanel_Right_Middle_Username and jPanel_Right_Middle_Password directly to
    // jPanel_Right

    jPanel_Right_Middle_Data.setBackground(ColorHandler.PRIMARY_COLOR);
    jPanel_Right_Middle_Data.add(jPanel_Right_Middle_Username);
    jPanel_Right_Middle_Data.add(jPanel_Right_Middle_Password);
    jPanel_Right_Middle_Data.add(jPanel_Right_Middle_Confirm_Password);
    jPanel_Right_Middle_Data.add(jPanel_Right_Bottom_Button);
  }

  @Override
  public void initRightBottom() {
    jLabel_Right_Bottom_Option = new JLabel("Already have account?");
    jButton_Right_Bottom_Others = new JButton("Sign in here");
    jPanel_Right_Bottom_Option = new JPanel();

    jLabel_Right_Bottom_Option.setForeground(ColorHandler.TEXT_COLOR);
    jLabel_Right_Bottom_Option.setFont(FontHandler.OTHERS);

    jButton_Right_Bottom_Others.setBackground(ColorHandler.PRIMARY_COLOR);
    jButton_Right_Bottom_Others.setForeground(ColorHandler.OTHER_OPTIONS);
    jButton_Right_Bottom_Others.setFont(FontHandler.OTHERS);
    jButton_Right_Bottom_Others.setBorder(null);

    jPanel_Right_Bottom_Option.setBackground(ColorHandler.PRIMARY_COLOR);
    jPanel_Right_Bottom_Option.add(jLabel_Right_Bottom_Option);
    jPanel_Right_Bottom_Option.add(jButton_Right_Bottom_Others);

  }

  @Override
  protected void doAction() {
    // TODO Auto-generated method stub
    super.doAction();
    DataHandler dataHandler = new DataHandler();
    jButton_Right_Bottom_Submit.addActionListener(new LoginFormController(dataHandler));
    jButton_Right_Bottom_Others.addActionListener(new ClickOtherOption());
  }

}

package views;

import constants.Sizes;
import constants.Titles;
import controllers.RegisterController;
import models.Account;
import styles.Borders;
import styles.Colors;
import styles.Fonts;

import javax.swing.*;
import java.awt.*;

public class RegisterView extends MyFrame {

    public RegisterView() {
        super();
    }

    @Override
    public void initRight() {
        initRightTop();
        initRightMiddle();
        initRightBottom();

        jPanel_Right = new JPanel(new BorderLayout());
        jPanel_Right.setPreferredSize(new Dimension(Sizes.WIDTH_MY_RIGHT_FRAME, Sizes.HEIGHT_MY_RIGHT_FRAME));
        jPanel_Right.setBackground(Colors.PRIMARY_COLOR);
        jPanel_Right.setBorder(Borders.MID_FIELD);

        jPanel_Right.add(jPanel_Right_Top_Tittle, BorderLayout.NORTH);
        jPanel_Right.add(jPanel_Right_Middle_Data, BorderLayout.CENTER);
        jPanel_Right.add(jPanel_Right_Bottom_Option, BorderLayout.SOUTH);

    }

    @Override
    public void initRightTop() {
        jPanel_Right_Top_Tittle = new JPanel();
        jLabel_Right_Top_Tittle = new JLabel(Titles.REGISTER, JLabel.CENTER);
        jLabel_Right_Top_Tittle.setForeground(Colors.TEXT_COLOR);
        jLabel_Right_Top_Tittle.setFont(Fonts.RIGHT_TITLE);

        jPanel_Right_Top_Tittle.setBackground(Colors.PRIMARY_COLOR);
        jPanel_Right_Top_Tittle.add(jLabel_Right_Top_Tittle);
        jPanel_Right_Top_Tittle.setBorder(Borders.TITLE);
    }

    @Override
    public void initRightMiddle() {
        jPanel_Right_Middle_Data = new JPanel(new GridLayout(0, 1));
        jPanel_Right_Middle_Username = new JPanel();
        jPanel_Right_Middle_Password = new JPanel();
        jPanel_Right_Middle_Confirm_Password = new JPanel();
        jPanel_Right_Bottom_Button = new JPanel();

        jLabel_Right_Middle_Username = new JLabel(Titles.USERNAME);
        jLabel_Right_Middle_Password = new JLabel(Titles.PASSWORD);
        jLabel_Right_Middle_Confirm_Password = new JLabel(Titles.CONFIRM_PASSWORD);

        jTextField_Right_Middle_Username = new JTextField(20);
        jPasswordField_Right_Middle_Password = new JPasswordField(20);
        jPasswordField_Right_Middle_Confirm_Password = new JPasswordField(20);

        jButton_Right_Bottom_Submit = new JButton(Titles.SUBMIT);

        jLabel_Right_Middle_Username.setForeground(Colors.TEXT_COLOR);
        jLabel_Right_Middle_Username.setFont(Fonts.LABEL);
        jLabel_Right_Middle_Username.setBorder(Borders.MID_LABEL);
        jTextField_Right_Middle_Username.setFont(Fonts.INPUT_SMALL);
        jTextField_Right_Middle_Username.setBackground(Colors.SECONDARY_COLOR);
        jTextField_Right_Middle_Username.setForeground(Colors.TEXT_COLOR);

        jLabel_Right_Middle_Password.setForeground(Colors.TEXT_COLOR);
        jLabel_Right_Middle_Password.setFont(Fonts.LABEL);
        jLabel_Right_Middle_Password.setBorder(Borders.MID_LABEL);
        jPasswordField_Right_Middle_Password.setFont(Fonts.INPUT_SMALL);
        jPasswordField_Right_Middle_Password.setBackground(Colors.SECONDARY_COLOR);
        jPasswordField_Right_Middle_Password.setForeground(Colors.TEXT_COLOR);

        jLabel_Right_Middle_Confirm_Password.setForeground(Colors.TEXT_COLOR);
        jLabel_Right_Middle_Confirm_Password.setFont(Fonts.LABEL);
        jLabel_Right_Middle_Confirm_Password.setBorder(Borders.MID_LABEL);
        jPasswordField_Right_Middle_Confirm_Password.setFont(Fonts.INPUT_SMALL);
        jPasswordField_Right_Middle_Confirm_Password.setBackground(Colors.SECONDARY_COLOR);
        jPasswordField_Right_Middle_Confirm_Password.setForeground(Colors.TEXT_COLOR);

        jPanel_Right_Middle_Username.setLayout(new BoxLayout(jPanel_Right_Middle_Username, BoxLayout.Y_AXIS));
        jPanel_Right_Middle_Username.setBackground(Colors.PRIMARY_COLOR);
        jPanel_Right_Middle_Username.add(jLabel_Right_Middle_Username);
        jPanel_Right_Middle_Username.add(jTextField_Right_Middle_Username);
        jPanel_Right_Middle_Username.setBorder(Borders.MIDDLE);

        jPanel_Right_Middle_Password.setLayout(new BoxLayout(jPanel_Right_Middle_Password, BoxLayout.Y_AXIS));
        jPanel_Right_Middle_Password.setBackground(Colors.PRIMARY_COLOR);
        jPanel_Right_Middle_Password.add(jLabel_Right_Middle_Password);
        jPanel_Right_Middle_Password.add(jPasswordField_Right_Middle_Password);
        jPanel_Right_Middle_Password.setBorder(Borders.MIDDLE);

        jPanel_Right_Middle_Confirm_Password
                .setLayout(new BoxLayout(jPanel_Right_Middle_Confirm_Password, BoxLayout.Y_AXIS));
        jPanel_Right_Middle_Confirm_Password.setBackground(Colors.PRIMARY_COLOR);
        jPanel_Right_Middle_Confirm_Password.add(jLabel_Right_Middle_Confirm_Password);
        jPanel_Right_Middle_Confirm_Password.add(jPasswordField_Right_Middle_Confirm_Password);
        jPanel_Right_Middle_Confirm_Password.setBorder(Borders.MIDDLE);

        jButton_Right_Bottom_Submit.setBackground(Colors.TEXT_COLOR);
        jButton_Right_Bottom_Submit.setForeground(Colors.PRIMARY_COLOR);
        jButton_Right_Bottom_Submit.setFont(Fonts.BUTTON);
        jButton_Right_Bottom_Submit.setPreferredSize(Sizes.SIZE_BUTTON);
        jPanel_Right_Bottom_Button.setBackground(Colors.PRIMARY_COLOR);
        jPanel_Right_Bottom_Button.add(jButton_Right_Bottom_Submit);
        jPanel_Right_Bottom_Button.setBorder(Borders.BUTTON);

        // Add jPanel_Right_Middle_Username and jPanel_Right_Middle_Password directly to
        // jPanel_Right

        jPanel_Right_Middle_Data.setBackground(Colors.PRIMARY_COLOR);
        jPanel_Right_Middle_Data.add(jPanel_Right_Middle_Username);
        jPanel_Right_Middle_Data.add(jPanel_Right_Middle_Password);
        jPanel_Right_Middle_Data.add(jPanel_Right_Middle_Confirm_Password);
        jPanel_Right_Middle_Data.add(jPanel_Right_Bottom_Button);
    }

    @Override
    public void initRightBottom() {
        jLabel_Right_Bottom_Option = new JLabel(Titles.HAVE_ACCOUNT);
        jButton_Right_Bottom_Others = new JButton(Titles.SIGN_IN_HERE);
        jPanel_Right_Bottom_Option = new JPanel();

        jLabel_Right_Bottom_Option.setForeground(Colors.TEXT_COLOR);
        jLabel_Right_Bottom_Option.setFont(Fonts.OTHERS);

        jButton_Right_Bottom_Others.setBackground(Colors.PRIMARY_COLOR);
        jButton_Right_Bottom_Others.setForeground(Colors.OTHER_OPTIONS);
        jButton_Right_Bottom_Others.setFont(Fonts.OTHERS);
        jButton_Right_Bottom_Others.setBorder(null);

        jPanel_Right_Bottom_Option.setBackground(Colors.PRIMARY_COLOR);
        jPanel_Right_Bottom_Option.add(jLabel_Right_Bottom_Option);
        jPanel_Right_Bottom_Option.add(jButton_Right_Bottom_Others);

    }

    @Override
    protected void doAction() {
        // TODO Auto-generated method stub
        super.doAction();
        jTextField_Right_Middle_Username.addMouseListener(new RegisterController(this));
        jPasswordField_Right_Middle_Password.addMouseListener(new RegisterController(this));
        jPasswordField_Right_Middle_Confirm_Password.addMouseListener(new RegisterController(this));
        jButton_Right_Bottom_Submit.addMouseListener(new RegisterController(this));
        jButton_Right_Bottom_Others.addMouseListener(new RegisterController(this));
        jButton_Right_Bottom_Submit.addActionListener(new RegisterController(this));
        jPasswordField_Right_Middle_Confirm_Password.addActionListener(new PressEnter());
        jButton_Right_Bottom_Others.addActionListener(new ClickOtherOption());
    }

    //xu li cac ham o day
    public Account getRegister() {
        String username = jTextField_Right_Middle_Username.getText();
        String password = String.valueOf(jPasswordField_Right_Middle_Password.getPassword());
        String confirmPassword = String.valueOf(jPasswordField_Right_Middle_Confirm_Password.getPassword());
        return new Account(username, password, confirmPassword);
    }

    public void setHoverButton(boolean isInside) {
        if (isInside) {
            jPasswordField_Right_Middle_Password.setBackground(Colors.PRIMARY_COLOR);
            jButton_Right_Bottom_Submit.setBackground(Colors.TEXT_COLOR_HOVER);
        } else {
            jPasswordField_Right_Middle_Password.setBackground(Colors.SECONDARY_COLOR);
            jButton_Right_Bottom_Submit.setBackground(Colors.TEXT_COLOR);
        }
    }

    public void setHoverUsername(boolean isInside) {
        if (isInside) {
            jTextField_Right_Middle_Username.setBackground(Colors.PRIMARY_COLOR);
        } else {
            jTextField_Right_Middle_Username.setBackground(Colors.SECONDARY_COLOR);
        }
    }

    public void setHoverPassword(boolean isInside) {
        if (isInside) {
            jPasswordField_Right_Middle_Password.setBackground(Colors.PRIMARY_COLOR);
        } else {
            jPasswordField_Right_Middle_Password.setBackground(Colors.SECONDARY_COLOR);
        }
    }

    public void setHoverConfirmPassword(boolean isInside) {
        if (isInside) {
            jPasswordField_Right_Middle_Confirm_Password.setBackground(Colors.PRIMARY_COLOR);
        } else {
            jPasswordField_Right_Middle_Confirm_Password.setBackground(Colors.SECONDARY_COLOR);
        }
    }

    public void setHoverOther(boolean isInside) {
        if (isInside) {
            jButton_Right_Bottom_Others.setFont(Fonts.OTHERS_HOVER);
        } else {
            jButton_Right_Bottom_Others.setFont(Fonts.OTHERS);
        }
    }
}

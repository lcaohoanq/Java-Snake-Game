package views;

import styles.UISizes;
import styles.UILabels;
import controllers.RegisterController;
import modules.user.UserDTO;
import models.RegisterModel;
import styles.UIBorders;
import styles.UIColors;
import styles.UIFonts;
import controllers.ToggleHandler;

import javax.swing.*;
import java.awt.*;

public non-sealed class RegisterView extends MyFrame implements ToggleHandler, HoverHandler, RegisterData {

    private String email_phone;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
    private RegisterModel registerModel;

    public RegisterView() {
        super();
        this.registerModel = new RegisterModel();
    }

    @Override
    public void initRight() {
        initRightTop();
        initRightMiddle();
        initRightBottom();

        jPanel_Right = new JPanel(new BorderLayout());
        jPanel_Right.setPreferredSize(new Dimension(UISizes.WIDTH_MY_RIGHT_FRAME, UISizes.HEIGHT_MY_RIGHT_FRAME));
        jPanel_Right.setBackground(UIColors.PRIMARY_COLOR_L);
        jPanel_Right.setBorder(UIBorders.MID_FIELD);

        jPanel_Right.add(jPanel_Right_Top_Tittle, BorderLayout.NORTH);
        jPanel_Right.add(jPanel_Right_Middle_Data, BorderLayout.CENTER);
        jPanel_Right.add(jPanel_Right_Bottom_Option, BorderLayout.SOUTH);

    }

    @Override
    public void initRightTop() {
        jPanel_Right_Top_Tittle = new JPanel();
        jLabel_Right_Top_Tittle = new JLabel(UILabels.REGISTER, JLabel.CENTER);
        jLabel_Right_Top_Tittle.setForeground(UIColors.TEXT_COLOR_L);
        jLabel_Right_Top_Tittle.setFont(UIFonts.RIGHT_TITLE);

        jPanel_Right_Top_Tittle.setBackground(UIColors.PRIMARY_COLOR_L);
        jPanel_Right_Top_Tittle.add(jLabel_Right_Top_Tittle);
        jPanel_Right_Top_Tittle.setBorder(UIBorders.TITLE);
    }

    @Override
    public void initRightMiddle() {
        jPanel_Right_Middle_Data = new JPanel(new GridLayout(0, 1));
        jPanel_Right_Middle_Email = new JPanel();
        jPanel_Right_Middle_FirstName = new JPanel();
        jPanel_Right_Middle_LastName = new JPanel();
        jPanel_Right_Middle_Password = new JPanel();
        jPanel_Right_Middle_Confirm_Password = new JPanel();
        jPanel_Right_Bottom_Button = new JPanel();

        jLabel_Right_Middle_Email = new JLabel(UILabels.EMAIL);
        jLabel_Right_Middle_FirstName = new JLabel(UILabels.FIRST_NAME);
        jLabel_Right_Middle_LastName = new JLabel(UILabels.LAST_NAME);
        jLabel_Right_Middle_Password = new JLabel(UILabels.PASSWORD);
        jLabel_Right_Middle_Confirm_Password = new JLabel(UILabels.CONFIRM_PASSWORD);

        jTextField_Right_Middle_Email = new JTextField(20);
        jTextField_Right_Middle_FirstName = new JTextField(20);
        jTextField_Right_Middle_LastName = new JTextField(20);
        jPasswordField_Right_Middle_Password = new JPasswordField(20);
        jPasswordField_Right_Middle_Confirm_Password = new JPasswordField(20);

        jButton_Right_Bottom_Submit = new JButton(UILabels.SUBMIT);

        jLabel_Right_Middle_Email.setForeground(UIColors.TEXT_COLOR_L);
        jLabel_Right_Middle_Email.setFont(UIFonts.LABEL);
        jLabel_Right_Middle_Email.setBorder(UIBorders.MID_LABEL);
        jTextField_Right_Middle_Email.setFont(UIFonts.TEXT_FIELD_REGISTER);
        jTextField_Right_Middle_Email.setBorder(UIBorders.DATA_FIELD);
        jTextField_Right_Middle_Email.setBackground(UIColors.SECONDARY_COLOR_L);
        jTextField_Right_Middle_Email.setForeground(UIColors.TEXT_COLOR_L);

        jLabel_Right_Middle_FirstName.setForeground(UIColors.TEXT_COLOR_L);
        jLabel_Right_Middle_FirstName.setFont(UIFonts.LABEL);
        jLabel_Right_Middle_FirstName.setBorder(UIBorders.MID_LABEL);
        jTextField_Right_Middle_FirstName.setFont(UIFonts.TEXT_FIELD_REGISTER);
        jTextField_Right_Middle_FirstName.setBorder(UIBorders.DATA_FIELD);
        jTextField_Right_Middle_FirstName.setBackground(UIColors.SECONDARY_COLOR_L);
        jTextField_Right_Middle_FirstName.setForeground(UIColors.TEXT_COLOR_L);

        jLabel_Right_Middle_LastName.setForeground(UIColors.TEXT_COLOR_L);
        jLabel_Right_Middle_LastName.setFont(UIFonts.LABEL);
        jLabel_Right_Middle_LastName.setBorder(UIBorders.MID_LABEL);
        jTextField_Right_Middle_LastName.setFont(UIFonts.TEXT_FIELD_REGISTER);
        jTextField_Right_Middle_LastName.setBorder(UIBorders.DATA_FIELD);
        jTextField_Right_Middle_LastName.setBackground(UIColors.SECONDARY_COLOR_L);
        jTextField_Right_Middle_LastName.setForeground(UIColors.TEXT_COLOR_L);

        jLabel_Right_Middle_Password.setForeground(UIColors.TEXT_COLOR_L);
        jLabel_Right_Middle_Password.setFont(UIFonts.LABEL);
        jLabel_Right_Middle_Password.setBorder(UIBorders.MID_LABEL);
        jPasswordField_Right_Middle_Password.setFont(UIFonts.TEXT_FIELD_REGISTER);
        jPasswordField_Right_Middle_Password.setBorder(UIBorders.DATA_FIELD);
        jPasswordField_Right_Middle_Password.setBackground(UIColors.SECONDARY_COLOR_L);
        jPasswordField_Right_Middle_Password.setForeground(UIColors.TEXT_COLOR_L);

        jLabel_Right_Middle_Confirm_Password.setForeground(UIColors.TEXT_COLOR_L);
        jLabel_Right_Middle_Confirm_Password.setFont(UIFonts.LABEL);
        jLabel_Right_Middle_Confirm_Password.setBorder(UIBorders.MID_LABEL);
        jPasswordField_Right_Middle_Confirm_Password.setFont(UIFonts.TEXT_FIELD_REGISTER);
        jPasswordField_Right_Middle_Confirm_Password.setBorder(UIBorders.DATA_FIELD);
        jPasswordField_Right_Middle_Confirm_Password.setBackground(UIColors.SECONDARY_COLOR_L);
        jPasswordField_Right_Middle_Confirm_Password.setForeground(UIColors.TEXT_COLOR_L);

        jPanel_Right_Middle_Email.setLayout(new BoxLayout(jPanel_Right_Middle_Email, BoxLayout.Y_AXIS));
        jPanel_Right_Middle_Email.setBackground(UIColors.PRIMARY_COLOR_L);
        jPanel_Right_Middle_Email.add(jLabel_Right_Middle_Email);
        jPanel_Right_Middle_Email.add(jTextField_Right_Middle_Email);
        jPanel_Right_Middle_Email.setBorder(UIBorders.MIDDLE);

        jPanel_Right_Middle_FirstName.setLayout(new BoxLayout(jPanel_Right_Middle_FirstName, BoxLayout.Y_AXIS));
        jPanel_Right_Middle_FirstName.setBackground(UIColors.PRIMARY_COLOR_L);
        jPanel_Right_Middle_FirstName.add(jLabel_Right_Middle_FirstName);
        jPanel_Right_Middle_FirstName.add(jTextField_Right_Middle_FirstName);
        jPanel_Right_Middle_FirstName.setBorder(UIBorders.MIDDLE);

        jPanel_Right_Middle_LastName.setLayout(new BoxLayout(jPanel_Right_Middle_LastName, BoxLayout.Y_AXIS));
        jPanel_Right_Middle_LastName.setBackground(UIColors.PRIMARY_COLOR_L);
        jPanel_Right_Middle_LastName.add(jLabel_Right_Middle_LastName);
        jPanel_Right_Middle_LastName.add(jTextField_Right_Middle_LastName);
        jPanel_Right_Middle_LastName.setBorder(UIBorders.MIDDLE);

        jPanel_Right_Middle_Password.setLayout(new BoxLayout(jPanel_Right_Middle_Password, BoxLayout.Y_AXIS));
        jPanel_Right_Middle_Password.setBackground(UIColors.PRIMARY_COLOR_L);
        jPanel_Right_Middle_Password.add(jLabel_Right_Middle_Password);
        jPanel_Right_Middle_Password.add(jPasswordField_Right_Middle_Password);
        jPanel_Right_Middle_Password.setBorder(UIBorders.MIDDLE);

        jPanel_Right_Middle_Confirm_Password
                .setLayout(new BoxLayout(jPanel_Right_Middle_Confirm_Password, BoxLayout.Y_AXIS));
        jPanel_Right_Middle_Confirm_Password.setBackground(UIColors.PRIMARY_COLOR_L);
        jPanel_Right_Middle_Confirm_Password.add(jLabel_Right_Middle_Confirm_Password);
        jPanel_Right_Middle_Confirm_Password.add(jPasswordField_Right_Middle_Confirm_Password);
        jPanel_Right_Middle_Confirm_Password.setBorder(UIBorders.MIDDLE);

        jButton_Right_Bottom_Submit.setBackground(UIColors.TEXT_COLOR_L);
        jButton_Right_Bottom_Submit.setForeground(UIColors.PRIMARY_COLOR_L);
        jButton_Right_Bottom_Submit.setFont(UIFonts.BUTTON);
        jButton_Right_Bottom_Submit.setPreferredSize(UISizes.SIZE_BUTTON);

        jButton_Right_Bottom_Submit.setCursor(cursor);

        jPanel_Right_Bottom_Button.setBackground(UIColors.PRIMARY_COLOR_L);
        jPanel_Right_Bottom_Button.add(jButton_Right_Bottom_Submit);
        jPanel_Right_Bottom_Button.setBorder(UIBorders.BUTTON);

        // Add jPanel_Right_Middle_Username and jPanel_Right_Middle_Password directly to
        // jPanel_Right

        jPanel_Right_Middle_Data.setBackground(UIColors.PRIMARY_COLOR_L);
        jPanel_Right_Middle_Data.add(jPanel_Right_Middle_Email);
        jPanel_Right_Middle_Data.add(jPanel_Right_Middle_FirstName);
        jPanel_Right_Middle_Data.add(jPanel_Right_Middle_LastName);
        jPanel_Right_Middle_Data.add(jPanel_Right_Middle_Password);
        jPanel_Right_Middle_Data.add(jPanel_Right_Middle_Confirm_Password);
        jPanel_Right_Middle_Data.add(jPanel_Right_Bottom_Button);
    }

    @Override
    public void initRightBottom() {
        jLabel_Right_Bottom_Option = new JLabel(UILabels.HAVE_ACCOUNT);
        jButton_Right_Bottom_Others = new JButton(UILabels.SIGN_IN_HERE);
        jPanel_Right_Bottom_Option = new JPanel();

        jLabel_Right_Bottom_Option.setForeground(UIColors.TEXT_COLOR_L);
        jLabel_Right_Bottom_Option.setFont(UIFonts.OTHERS);

        jButton_Right_Bottom_Others.setBackground(UIColors.PRIMARY_COLOR_L);
        jButton_Right_Bottom_Others.setForeground(UIColors.OTHER_OPTIONS_L);
        jButton_Right_Bottom_Others.setFont(UIFonts.OTHERS);
        jButton_Right_Bottom_Others.setBorder(null);

        jButton_Right_Bottom_Others.setCursor(cursor);

        jPanel_Right_Bottom_Option.setBackground(UIColors.PRIMARY_COLOR_L);
        jPanel_Right_Bottom_Option.add(jLabel_Right_Bottom_Option);
        jPanel_Right_Bottom_Option.add(jButton_Right_Bottom_Others);

    }

    @Override
    public void initToggle() {
        super.initToggle();
        toggleButton.addEventSelected(selected -> {
            if (selected) {
                jLabel_Right_Middle_Confirm_Password.setForeground(UIColors.TEXT_COLOR_D);
                jLabel_Right_Middle_Confirm_Password.setBackground(UIColors.PRIMARY_COLOR_D);
                jPasswordField_Right_Middle_Confirm_Password.setForeground(UIColors.TEXT_COLOR_D);
                jPasswordField_Right_Middle_Confirm_Password.setBackground(UIColors.SECONDARY_COLOR_D);
                jPanel_Right_Middle_Confirm_Password.setBackground(UIColors.PRIMARY_COLOR_D);
            } else {
                jLabel_Right_Middle_Confirm_Password.setForeground(UIColors.TEXT_COLOR_L);
                jLabel_Right_Middle_Confirm_Password.setBackground(UIColors.PRIMARY_COLOR_L);
                jPasswordField_Right_Middle_Confirm_Password.setForeground(UIColors.TEXT_COLOR_L);
                jPasswordField_Right_Middle_Confirm_Password.setBackground(UIColors.SECONDARY_COLOR_L);
                jPanel_Right_Middle_Confirm_Password.setBackground(UIColors.PRIMARY_COLOR_L);
            }
        });
    }

    @Override
    protected void doAction() {
        // TODO Auto-generated method stub
        super.doAction();
        jTextField_Right_Middle_Email.addMouseListener(new RegisterController(this));
        jTextField_Right_Middle_FirstName.addMouseListener(new RegisterController(this));
        jTextField_Right_Middle_LastName.addMouseListener(new RegisterController(this));
        jPasswordField_Right_Middle_Password.addMouseListener(new RegisterController(this));
        jPasswordField_Right_Middle_Confirm_Password.addMouseListener(new RegisterController(this));
        jButton_Right_Bottom_Submit.addMouseListener(new RegisterController(this));
        jButton_Right_Bottom_Others.addMouseListener(new RegisterController(this));
        jButton_Right_Bottom_Submit.addActionListener(new RegisterController(this));
        jPasswordField_Right_Middle_Confirm_Password.addActionListener(new PressEnter());
        jButton_Right_Bottom_Others.addActionListener(new ClickOtherOption());
    }

    public boolean isMatchingPattern() {
        boolean isMatching = false;
        //matching accept both email and phonenumber from Vietnam
        if(!this.registerModel.isEmailFormat(this.getRegister().email())){
            UIPrompts.IS_WRONG_FORMAT_EMAIL();
        } else if (!this.registerModel.isNameFormat(this.getRegister().firstName())) {
            UIPrompts.IS_WRONG_FORMAT_NAME();
        } else if (!this.registerModel.isNameFormat(this.getRegister().lastName())) {
            UIPrompts.IS_WRONG_FORMAT_NAME();
        } else if (!this.registerModel.isPasswordFormat(this.getRegister().password())) {
            UIPrompts.IS_WRONG_FORMAT_PASSWORD();
        } else {
            isMatching = true;
        }
        return isMatching;
    }

    public boolean isMatchingPasswordAndConfirmPassword() {
        return this.registerModel.isMatching(this.getRegister().password(), this.getRegister().confirmPassword());
    }

    @Override
    public boolean isDuplicateEmail() {
        return this.registerModel.isDuplicateEmail(this.getRegister().email());
    }

    @Override
    public boolean isEmpty() {
        return this.registerModel.isEmpty(this.getRegister().email(), this.getRegister().firstName() ,this.getRegister().lastName(), this.getRegister().password(), this.getRegister().confirmPassword());
    }

    //xu li cac ham o day
    public UserDTO getRegister() {
        email_phone = jTextField_Right_Middle_Email.getText();
        firstName = jTextField_Right_Middle_FirstName.getText();
        lastName = jTextField_Right_Middle_LastName.getText();
        password = String.valueOf(jPasswordField_Right_Middle_Password.getPassword());
        confirmPassword = String.valueOf(jPasswordField_Right_Middle_Confirm_Password.getPassword());
        return new UserDTO(email_phone, firstName, lastName, password, confirmPassword);
    }

    public void insertMail() {
        registerModel.insertMail(this.getRegister().email(), this.getRegister().firstName(), this.getRegister().lastName(), this.getRegister().password());
    }

    public void handleSuccess() {
        UIPrompts.IS_REGISTER_SUCCESS();
    }

    public void handleEmpty() {
        UIPrompts.IS_EMPTY_FIELD();
    }

    public void handleNotMatchingPasswordAndConfirmPassword() {
        UIPrompts.IS_NOT_MATCH_PASSWORD_AND_CONFIRM_PASSWORD();
    }



    public void handleDuplicateEmail() {
        UIPrompts.IS_EXISTED_EMAIL();
    }

    //this method for test getRegister above
    public void setRegister(String email, String firstName, String lastName, String password, String confirmPassword) {
        jTextField_Right_Middle_Email.setText(email);
        jTextField_Right_Middle_FirstName.setText(firstName);
        jTextField_Right_Middle_LastName.setText(lastName);
        jPasswordField_Right_Middle_Password.setText(password);
        jPasswordField_Right_Middle_Confirm_Password.setText(confirmPassword);
    }

    public void setHoverConfirmPassword(boolean isInside, String mode) {
        if (isInside) {
            if (mode.equals("light")) {
                jPasswordField_Right_Middle_Confirm_Password.setBackground(UIColors.SECONDARY_COLOR_L_HOVER);
            } else {
                jPasswordField_Right_Middle_Confirm_Password.setBackground(UIColors.SECONDARY_COLOR_D_HOVER);
            }
        } else {
            if (mode.equals("light")) {
                jPasswordField_Right_Middle_Confirm_Password.setBackground(UIColors.SECONDARY_COLOR_L);
            } else {
                jPasswordField_Right_Middle_Confirm_Password.setBackground(UIColors.SECONDARY_COLOR_D);
            }
        }
    }

    @Override
    public void setHoverButton(boolean isInside, String mode, JButton button) {

    }

    @Override
    public void changeColorBaseOnToggle() {

    }

}

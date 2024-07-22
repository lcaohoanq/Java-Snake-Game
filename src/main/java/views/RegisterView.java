package views;

import controllers.RegisterController;
import controllers.ToggleHandler;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import lombok.Getter;
import models.RegisterModel;
import modules.user.UserEntity;
import styles.UIBorders;
import styles.UIColors;
import styles.UIFonts;
import styles.UILabels;

@Getter
public non-sealed class RegisterView extends MyFrame implements ToggleHandler {

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
        initRightPanel();
    }

    @Override
    public void initRightTop() {
        jLabel_Right_Top_Title = new JLabel(UILabels.REGISTER, JLabel.CENTER);
        jLabel_Right_Top_Title.setForeground(UIColors.TEXT_COLOR_L);
        jLabel_Right_Top_Title.setFont(UIFonts.RIGHT_TITLE);
        jPanel_Right_Top_Title.add(jLabel_Right_Top_Title);
    }

    @Override
    public void initRightMiddle() {
        jPanel_Right_Middle_Data = new JPanel(new GridLayout(0, 1));

        jLabel_Right_Middle_FirstName = new JLabel(UILabels.FIRST_NAME);
        jLabel_Right_Middle_LastName = new JLabel(UILabels.LAST_NAME);
        jLabel_Right_Middle_Confirm_Password = new JLabel(UILabels.CONFIRM_PASSWORD);

        jTextField_Right_Middle_Email.setFont(UIFonts.TEXT_FIELD_REGISTER);

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

        jPasswordField_Right_Middle_Password.setFont(UIFonts.TEXT_FIELD_REGISTER);

        jLabel_Right_Middle_Confirm_Password.setForeground(UIColors.TEXT_COLOR_L);
        jLabel_Right_Middle_Confirm_Password.setFont(UIFonts.LABEL);
        jLabel_Right_Middle_Confirm_Password.setBorder(UIBorders.MID_LABEL);
        jPasswordField_Right_Middle_Confirm_Password.setFont(UIFonts.TEXT_FIELD_REGISTER);
        jPasswordField_Right_Middle_Confirm_Password.setBorder(UIBorders.DATA_FIELD);
        jPasswordField_Right_Middle_Confirm_Password.setBackground(UIColors.SECONDARY_COLOR_L);
        jPasswordField_Right_Middle_Confirm_Password.setForeground(UIColors.TEXT_COLOR_L);

        jPanel_Right_Middle_Email.add(jLabel_Right_Middle_Email);
        jPanel_Right_Middle_Email.add(jTextField_Right_Middle_Email);

        jPanel_Right_Middle_FirstName.setLayout(
            new BoxLayout(jPanel_Right_Middle_FirstName, BoxLayout.Y_AXIS));
        jPanel_Right_Middle_FirstName.setBackground(UIColors.PRIMARY_COLOR_L);
        jPanel_Right_Middle_FirstName.add(jLabel_Right_Middle_FirstName);
        jPanel_Right_Middle_FirstName.add(jTextField_Right_Middle_FirstName);
        jPanel_Right_Middle_FirstName.setBorder(UIBorders.MIDDLE);

        jPanel_Right_Middle_LastName.setLayout(
            new BoxLayout(jPanel_Right_Middle_LastName, BoxLayout.Y_AXIS));
        jPanel_Right_Middle_LastName.setBackground(UIColors.PRIMARY_COLOR_L);
        jPanel_Right_Middle_LastName.add(jLabel_Right_Middle_LastName);
        jPanel_Right_Middle_LastName.add(jTextField_Right_Middle_LastName);
        jPanel_Right_Middle_LastName.setBorder(UIBorders.MIDDLE);

        jPanel_Right_Middle_Password.add(jLabel_Right_Middle_Password);
        jPanel_Right_Middle_Password.add(jPasswordField_Right_Middle_Password);

        jPanel_Right_Middle_Confirm_Password
            .setLayout(new BoxLayout(jPanel_Right_Middle_Confirm_Password, BoxLayout.Y_AXIS));
        jPanel_Right_Middle_Confirm_Password.setBackground(UIColors.PRIMARY_COLOR_L);
        jPanel_Right_Middle_Confirm_Password.add(jLabel_Right_Middle_Confirm_Password);
        jPanel_Right_Middle_Confirm_Password.add(jPasswordField_Right_Middle_Confirm_Password);
        jPanel_Right_Middle_Confirm_Password.setBorder(UIBorders.MIDDLE);

        jPanel_Right_Bottom_Button.add(jButton_Right_Bottom_Submit);
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

        jLabel_Right_Bottom_Option.setForeground(UIColors.TEXT_COLOR_L);
        jLabel_Right_Bottom_Option.setFont(UIFonts.OTHERS);
        jPanel_Right_Bottom_Option.setBackground(UIColors.PRIMARY_COLOR_L);

        jButton_Right_Bottom_Others.setBackground(UIColors.PRIMARY_COLOR_L);
        jButton_Right_Bottom_Others.setForeground(UIColors.OTHER_OPTIONS_L);
        jButton_Right_Bottom_Others.setFont(UIFonts.OTHERS);
        jButton_Right_Bottom_Others.setBorder(null);
        jButton_Right_Bottom_Others.setCursor(cursor);

        jPanel_Right_Bottom_Option.add(jLabel_Right_Bottom_Option);
        jPanel_Right_Bottom_Option.add(jButton_Right_Bottom_Others);
    }

    @Override
    public void initRightPanel() {
        jPanel_Right.add(jPanel_Right_Top_Title, BorderLayout.NORTH);
        jPanel_Right.add(jPanel_Right_Middle_Data, BorderLayout.CENTER);
        jPanel_Right.add(jPanel_Right_Bottom_Option, BorderLayout.SOUTH);
    }

    @Override
    public void initToggle() {
        super.initToggle();
        toggleButton.addEventSelected(selected -> {
            if (selected) {
                jLabel_Right_Middle_Confirm_Password.setForeground(UIColors.TEXT_COLOR_D);
                jLabel_Right_Middle_Confirm_Password.setBackground(UIColors.PRIMARY_COLOR_D);
                jPasswordField_Right_Middle_Confirm_Password.setForeground(UIColors.TEXT_COLOR_D);
                jPasswordField_Right_Middle_Confirm_Password.setBackground(
                    UIColors.SECONDARY_COLOR_D);
                jPanel_Right_Middle_Confirm_Password.setBackground(UIColors.PRIMARY_COLOR_D);
            } else {
                jLabel_Right_Middle_Confirm_Password.setForeground(UIColors.TEXT_COLOR_L);
                jLabel_Right_Middle_Confirm_Password.setBackground(UIColors.PRIMARY_COLOR_L);
                jPasswordField_Right_Middle_Confirm_Password.setForeground(UIColors.TEXT_COLOR_L);
                jPasswordField_Right_Middle_Confirm_Password.setBackground(
                    UIColors.SECONDARY_COLOR_L);
                jPanel_Right_Middle_Confirm_Password.setBackground(UIColors.PRIMARY_COLOR_L);
            }
        });
    }

    @Override
    public void doAction() {
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
        if (!this.registerModel.isEmailFormat(this.getDataWhenRegister().getEmail())) {
            UIPrompts.IS_WRONG_FORMAT_EMAIL();
        } else if (!this.registerModel.isNameFormat(this.getDataWhenRegister().getFirstName())) {
            UIPrompts.IS_WRONG_FORMAT_NAME();
        } else if (!this.registerModel.isNameFormat(this.getDataWhenRegister().getLastName())) {
            UIPrompts.IS_WRONG_FORMAT_NAME();
        } else if (!this.registerModel.isPasswordFormat(this.getDataWhenRegister().getPassword())) {
            UIPrompts.IS_WRONG_FORMAT_PASSWORD();
        } else {
            isMatching = true;
        }
        return isMatching;
    }

    public boolean isMatchingPasswordAndConfirmPassword() {
        return this.registerModel.isMatching(this.getDataWhenRegister().getPassword(),
            this.getDataWhenRegister().getConfirmPassword());
    }

    public boolean isDuplicateEmail() {
        return this.registerModel.isDuplicateEmail(this.getDataWhenRegister().getEmail());
    }

    public boolean isEmpty() {
        return this.registerModel.isEmpty(this.getDataWhenRegister().getEmail(),
            this.getDataWhenRegister().getFirstName(), this.getDataWhenRegister().getLastName(),
            this.getDataWhenRegister().getPassword(),
            this.getDataWhenRegister().getConfirmPassword());
    }

    //xu li cac ham o day
    public UserEntity getDataWhenRegister() {
        return new UserEntity(jTextField_Right_Middle_Email.getText(),
            jTextField_Right_Middle_FirstName.getText(),
            jTextField_Right_Middle_LastName.getText(),
            String.valueOf(jPasswordField_Right_Middle_Password.getPassword()),
            String.valueOf(
                jPasswordField_Right_Middle_Confirm_Password.getPassword()));
    }

    public void insertMail() {
        registerModel.insertMail(this.getDataWhenRegister().getEmail(),
            this.getDataWhenRegister().getFirstName(), this.getDataWhenRegister().getLastName(),
            this.getDataWhenRegister().getPassword());
    }

    public void handleNotMatchingPasswordAndConfirmPassword() {
        UIPrompts.IS_NOT_MATCH_PASSWORD_AND_CONFIRM_PASSWORD();
    }

    //this method for test getRegister above
    public void setRegister(String email, String firstName, String lastName, String password,
        String confirmPassword) {
        jTextField_Right_Middle_Email.setText(email);
        jTextField_Right_Middle_FirstName.setText(firstName);
        jTextField_Right_Middle_LastName.setText(lastName);
        jPasswordField_Right_Middle_Password.setText(password);
        jPasswordField_Right_Middle_Confirm_Password.setText(confirmPassword);
    }

    public void setHoverConfirmPassword(boolean isInside, String mode) {
        if (isInside) {
            if (mode.equals("light")) {
                jPasswordField_Right_Middle_Confirm_Password.setBackground(
                    UIColors.SECONDARY_COLOR_L_HOVER);
            } else {
                jPasswordField_Right_Middle_Confirm_Password.setBackground(
                    UIColors.SECONDARY_COLOR_D_HOVER);
            }
        } else {
            if (mode.equals("light")) {
                jPasswordField_Right_Middle_Confirm_Password.setBackground(
                    UIColors.SECONDARY_COLOR_L);
            } else {
                jPasswordField_Right_Middle_Confirm_Password.setBackground(
                    UIColors.SECONDARY_COLOR_D);
            }
        }
    }

    @Override
    public void changeColorBaseOnToggle() {

    }

}

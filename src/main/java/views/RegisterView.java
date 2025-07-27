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
import models.UserScore;
import styles.UIBorders;
import styles.UIColors;
import styles.UIFonts;
import styles.UILabels;
import views.base.MyFrame;

@Getter
public class RegisterView extends MyFrame implements ToggleHandler {

    // Singleton instance
    private static RegisterView instance;
    
    private RegisterModel registerModel;

    private RegisterView() {
        super();
        this.registerModel = new RegisterModel();
    }
    
    /**
     * Get the singleton instance of RegisterView
     * If an instance already exists, it will be disposed and a new one created
     */
    public static synchronized RegisterView getInstance() {
        if (instance != null) {
            instance.dispose();
        }
        instance = new RegisterView();
        return instance;
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

        jLabel_Right_Middle_UserName = new JLabel(UILabels.FIRST_NAME);
        jLabel_Right_Middle_Confirm_Password = new JLabel(UILabels.CONFIRM_PASSWORD);

        jTextField_Right_Middle_Email.setFont(UIFonts.TEXT_FIELD_REGISTER);

        jLabel_Right_Middle_UserName.setForeground(UIColors.TEXT_COLOR_L);
        jLabel_Right_Middle_UserName.setFont(UIFonts.LABEL);
        jLabel_Right_Middle_UserName.setBorder(UIBorders.MID_LABEL);
        jTextField_Right_Middle_UserName.setFont(UIFonts.TEXT_FIELD_REGISTER);
        jTextField_Right_Middle_UserName.setBorder(UIBorders.DATA_FIELD);
        jTextField_Right_Middle_UserName.setBackground(UIColors.SECONDARY_COLOR_L);
        jTextField_Right_Middle_UserName.setForeground(UIColors.TEXT_COLOR_L);

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

        jPanel_Right_Middle_UserName.setLayout(
            new BoxLayout(jPanel_Right_Middle_UserName, BoxLayout.Y_AXIS));
        jPanel_Right_Middle_UserName.setBackground(UIColors.PRIMARY_COLOR_L);
        jPanel_Right_Middle_UserName.add(jLabel_Right_Middle_UserName);
        jPanel_Right_Middle_UserName.add(jTextField_Right_Middle_UserName);
        jPanel_Right_Middle_UserName.setBorder(UIBorders.MIDDLE);

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
        jPanel_Right_Middle_Data.add(jPanel_Right_Middle_UserName);
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
        jPasswordField_Right_Middle_Password.addMouseListener(new RegisterController(this));
        jPasswordField_Right_Middle_Confirm_Password.addMouseListener(new RegisterController(this));
        jButton_Right_Bottom_Submit.addMouseListener(new RegisterController(this));
        jButton_Right_Bottom_Others.addMouseListener(new RegisterController(this));
        jButton_Right_Bottom_Submit.addActionListener(new RegisterController(this));
        jPasswordField_Right_Middle_Confirm_Password.addActionListener(new PressEnter());
    }

    public boolean isMatchingPattern() {
        boolean isMatching = true;
        //matching accept both email and phonenumber from Vietnam
//        if (!this.registerModel.isEmailFormat(this.getDataWhenRegister().getEmail())) {
//            UIPrompts.IS_WRONG_FORMAT_EMAIL();
//        } else if (!this.registerModel.isNameFormat(this.getDataWhenRegister().getFirstName())) {
//            UIPrompts.IS_WRONG_FORMAT_NAME();
//        } else if (!this.registerModel.isNameFormat(this.getDataWhenRegister().getLastName())) {
//            UIPrompts.IS_WRONG_FORMAT_NAME();
//        } else if (!this.registerModel.isPasswordFormat(this.getDataWhenRegister().getPassword())) {
//            UIPrompts.IS_WRONG_FORMAT_PASSWORD();
//        } else {
//            isMatching = true;
//        }
        return isMatching;
    }

    public boolean isMatchingPasswordAndConfirmPassword() {
        return this.registerModel.isMatching(
            String.valueOf(jPasswordField_Right_Middle_Password.getPassword()),
            String.valueOf(jPasswordField_Right_Middle_Confirm_Password.getPassword())
        );
    }

    public boolean isDuplicateEmail() {
        return this.registerModel.isDuplicateEmail(jTextField_Right_Middle_Email.getText());
    }

    public boolean isEmpty() {
        return this.registerModel.isEmpty(
            jTextField_Right_Middle_Email.getText(),
            String.valueOf(jPasswordField_Right_Middle_Password.getPassword()),
            String.valueOf(jPasswordField_Right_Middle_Confirm_Password.getPassword())
        );
    }

    // Get registration data
    public UserScore getDataWhenRegister() {
        // Note: This is just for data collection, not creating a full UserScore
        return new UserScore(
            jTextField_Right_Middle_Email.getText(),
            String.valueOf(jPasswordField_Right_Middle_Password.getPassword())
        );
    }

    // Register the user
    public UserScore registerUser() {
        String fullName = jTextField_Right_Middle_UserName.getText();
        String email = jTextField_Right_Middle_Email.getText();
        String password = String.valueOf(jPasswordField_Right_Middle_Password.getPassword());

        return registerModel.registerUser(fullName, email, password);
    }

    public void handleNotMatchingPasswordAndConfirmPassword() {
        UIPrompts.IS_NOT_MATCH_PASSWORD_AND_CONFIRM_PASSWORD();
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

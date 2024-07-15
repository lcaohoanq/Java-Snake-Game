package views;

import constants.ResourcePaths;
import java.awt.event.ActionListener;
import models.RegisterModel;
import modules.email.EmailCategories;
import modules.email.EmailUtils;
import modules.otp.OTPUtils;
import styles.UISizes;
import styles.UILabels;
import controllers.LoginController;
import controllers.PlayController;
import modules.user.UserDTO;
import models.LoginModel;
import styles.UIBorders;
import styles.UIColors;
import styles.UIFonts;
import controllers.ToggleHandler;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public non-sealed class LoginView extends MyFrame implements ToggleHandler, HoverHandler,
    LoginData {

    public static CardLayout cardLayout;
    private LoginModel loginModel;

    private String username;
    private String password;

    public LoginView() {
        super();
        this.loginModel = new LoginModel();
        InputStream inputStream = getClass().getResourceAsStream(ResourcePaths.URL_INTRO);
        audioHandler.playAudio(inputStream);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void initRight() {
        initRightTop();
        initRightMiddle();
        initRightBottom();

        jPanel_Right = new JPanel(new BorderLayout());
        jPanel_Right.setPreferredSize(
            new Dimension(UISizes.WIDTH_MY_RIGHT_FRAME, UISizes.HEIGHT_MY_RIGHT_FRAME));
        jPanel_Right.setBackground(UIColors.PRIMARY_COLOR_L);
        jPanel_Right.setBorder(UIBorders.MID_FIELD);

        jPanel_Right.add(jPanel_Right_Top_Tittle, BorderLayout.NORTH);
        jPanel_Right.add(jPanel_Right_Middle_Data, BorderLayout.CENTER);
        jPanel_Right.add(jPanel_Right_Bottom_Option, BorderLayout.SOUTH);

    }

    @Override
    public void initRightTop() {
        jPanel_Right_Top_Tittle = new JPanel();
        jLabel_Right_Top_Tittle = new JLabel(UILabels.LOGIN, JLabel.CENTER);
        jLabel_Right_Top_Tittle.setForeground(UIColors.TEXT_COLOR_L);
        jLabel_Right_Top_Tittle.setFont(UIFonts.RIGHT_TITLE);

        jPanel_Right_Top_Tittle.setBackground(UIColors.PRIMARY_COLOR_L);
        jPanel_Right_Top_Tittle.add(jLabel_Right_Top_Tittle);
        jPanel_Right_Top_Tittle.setBorder(UIBorders.TITLE);
    }

    @Override
    public void initRightMiddle() {
        jPanel_Right_Middle_Data = new JPanel(new GridLayout(3, 1));
        jPanel_Right_Middle_Email = new JPanel();
        jPanel_Right_Middle_Password = new JPanel();
        jPanel_Right_Bottom_Button = new JPanel();
        cardLayout = new CardLayout();

        jLabel_Right_Middle_Email = new JLabel(UILabels.EMAIL);
        jLabel_Right_Middle_Password = new JLabel(UILabels.PASSWORD);

        jTextField_Right_Middle_Email = new JTextField(20);
        jPasswordField_Right_Middle_Password = new JPasswordField(20);

        jButton_Right_Bottom_Submit = new JButton(UILabels.SUBMIT);
        jButton_Right_Play = new JButton(UILabels.PLAY);

        jLabel_Right_Middle_Email.setForeground(UIColors.TEXT_COLOR_L);
        jLabel_Right_Middle_Email.setFont(UIFonts.LABEL);
        jLabel_Right_Middle_Email.setBorder(UIBorders.MID_LABEL);
        jTextField_Right_Middle_Email.setFont(UIFonts.INPUT_LARGE);
        jTextField_Right_Middle_Email.setBorder(UIBorders.DATA_FIELD);
        jTextField_Right_Middle_Email.setBackground(UIColors.SECONDARY_COLOR_L);
        jTextField_Right_Middle_Email.setForeground(UIColors.TEXT_COLOR_L);

        jLabel_Right_Middle_Password.setForeground(UIColors.TEXT_COLOR_L);
        jLabel_Right_Middle_Password.setFont(UIFonts.LABEL);
        jLabel_Right_Middle_Password.setBorder(UIBorders.MID_LABEL);
        jPasswordField_Right_Middle_Password.setBorder(UIBorders.DATA_FIELD);
        jPasswordField_Right_Middle_Password.setFont(UIFonts.INPUT_LARGE);
        jPasswordField_Right_Middle_Password.setBackground(UIColors.SECONDARY_COLOR_L);
        jPasswordField_Right_Middle_Password.setForeground(UIColors.TEXT_COLOR_L);

        jPanel_Right_Middle_Email.setLayout(
            new BoxLayout(jPanel_Right_Middle_Email, BoxLayout.Y_AXIS));
        jPanel_Right_Middle_Email.setBackground(UIColors.PRIMARY_COLOR_L);
        jPanel_Right_Middle_Email.add(jLabel_Right_Middle_Email);
        jPanel_Right_Middle_Email.add(jTextField_Right_Middle_Email);
        jPanel_Right_Middle_Email.setBorder(UIBorders.MIDDLE);

        jPanel_Right_Middle_Password.setLayout(
            new BoxLayout(jPanel_Right_Middle_Password, BoxLayout.Y_AXIS));
        jPanel_Right_Middle_Password.setBackground(UIColors.PRIMARY_COLOR_L);
        jPanel_Right_Middle_Password.add(jLabel_Right_Middle_Password);
        jPanel_Right_Middle_Password.add(jPasswordField_Right_Middle_Password);
        jPanel_Right_Middle_Password.setBorder(UIBorders.MIDDLE);

        jButton_Right_Bottom_Submit.setBackground(UIColors.TEXT_COLOR_L);
        jButton_Right_Bottom_Submit.setForeground(UIColors.PRIMARY_COLOR_L);
        jButton_Right_Bottom_Submit.setFont(UIFonts.BUTTON);
        jButton_Right_Bottom_Submit.setPreferredSize(UISizes.SIZE_BUTTON);
        jButton_Right_Play.setBackground(UIColors.TEXT_COLOR_L);
        jButton_Right_Play.setForeground(UIColors.PRIMARY_COLOR_L);
        jButton_Right_Play.setFont(UIFonts.BUTTON);
        jButton_Right_Play.setPreferredSize(UISizes.SIZE_BUTTON);

        jButton_Right_Bottom_Submit.setCursor(cursor);
        jButton_Right_Play.setCursor(cursor);

        jPanel_Right_Bottom_Button.setLayout(cardLayout);
        jPanel_Right_Bottom_Button.add(jButton_Right_Bottom_Submit, "Card1");
        jPanel_Right_Bottom_Button.add(jButton_Right_Play, "Card2");
        jPanel_Right_Bottom_Button.setBackground(UIColors.PRIMARY_COLOR_L);
        jPanel_Right_Bottom_Button.setBorder(UIBorders.BUTTON);

        // Add jPanel_Right_Middle_Username and jPanel_Right_Middle_Password directly to
        // jPanel_Right

        jPanel_Right_Middle_Data.setBackground(UIColors.PRIMARY_COLOR_L);
        jPanel_Right_Middle_Data.add(jPanel_Right_Middle_Email);
        jPanel_Right_Middle_Data.add(jPanel_Right_Middle_Password);
        jPanel_Right_Middle_Data.add(jPanel_Right_Bottom_Button);
    }

    @Override
    public void initRightBottom() {
        jLabel_Right_Bottom_Option = new JLabel(UILabels.DONT_HAVE_ACCOUNT);
        jButton_Right_Bottom_Others = new JButton(UILabels.SIGN_UP_HERE);
        jButton_Right_Bottom_Forgot_Password = new JButton(UILabels.FORGOT_PASSWORD);
        jPanel_Right_Bottom_Option = new JPanel();

        jLabel_Right_Bottom_Option.setForeground(UIColors.TEXT_COLOR_L);
        jLabel_Right_Bottom_Option.setFont(UIFonts.OTHERS);

        jButton_Right_Bottom_Others.setBackground(UIColors.PRIMARY_COLOR_L);
        jButton_Right_Bottom_Others.setForeground(UIColors.OTHER_OPTIONS_L);
        jButton_Right_Bottom_Others.setFont(UIFonts.OTHERS);
        jButton_Right_Bottom_Others.setBorder(null);

        jButton_Right_Bottom_Others.setCursor(cursor);

        jButton_Right_Bottom_Forgot_Password.setBackground(UIColors.PRIMARY_COLOR_L);
        jButton_Right_Bottom_Forgot_Password.setForeground(UIColors.OTHER_OPTIONS_L);
        jButton_Right_Bottom_Forgot_Password.setFont(UIFonts.OTHERS);
        jButton_Right_Bottom_Forgot_Password.setBorder(null);

        jButton_Right_Bottom_Forgot_Password.setCursor(cursor);

        jPanel_Right_Bottom_Option.setBackground(UIColors.PRIMARY_COLOR_L);
        jPanel_Right_Bottom_Option.add(jLabel_Right_Bottom_Option);
        jPanel_Right_Bottom_Option.add(jButton_Right_Bottom_Others);
        jPanel_Right_Bottom_Option.add(jButton_Right_Bottom_Forgot_Password);
    }

    @Override
    public void initToggle() {
        super.initToggle();
        toggleButton.addEventSelected(selected -> {
            if (selected) {
                jButton_Right_Play.setForeground(UIColors.PRIMARY_COLOR_D);
                jButton_Right_Play.setBackground(UIColors.TEXT_COLOR_D);
            } else {
                jButton_Right_Play.setBackground(UIColors.TEXT_COLOR_L);
                jButton_Right_Play.setForeground(UIColors.PRIMARY_COLOR_L);
            }
        });
    }

    @Override
    protected void doAction() {
        // TODO Auto-generated method stub
        super.doAction();
        jTextField_Right_Middle_Email.addMouseListener(new LoginController(this));
        jPasswordField_Right_Middle_Password.addMouseListener(new LoginController(this));
        jButton_Right_Bottom_Submit.addMouseListener(new LoginController(this));
        jButton_Right_Bottom_Others.addMouseListener(new LoginController(this));
        jButton_Right_Bottom_Submit.addActionListener(new LoginController(this));
        jButton_Right_Play.addActionListener(new PlayController(this));
        jPasswordField_Right_Middle_Password.addActionListener(new PressEnter());
        jButton_Right_Bottom_Others.addActionListener(new ClickOtherOption());
        jButton_Right_Bottom_Forgot_Password.addActionListener(new ClickForgotPassword());
    }

    //xu li cac ham o day
    public UserDTO getLogin() {
        username = jTextField_Right_Middle_Email.getText();
        password = String.valueOf(jPasswordField_Right_Middle_Password.getPassword());
        return new UserDTO(username, password);
    }

    public boolean isEmpty() {
        return this.loginModel.isEmpty(this.getLogin().email(), this.getLogin().password());
    }

    public void handleEmpty() {
        UIPrompts.IS_EMPTY_USERNAME_OR_PASSWORD();
    }

    public void handleNotMatchingPasswordAndConfirmPassword() {
        UIPrompts.IS_WRONG_USERNAME_OR_PASSWORD();
    }

    public boolean isAdmin() {
        return this.loginModel.isAdmin(this.getLogin().email(), this.getLogin().password());
    }

    public boolean isMatching() {
        return this.loginModel.isMatching(this.getLogin().email(), this.getLogin().password());
    }

    public void handleSuccess() {
        UIPrompts.IS_LOGIN_SUCCESS();
        // Switch to the play button card using static methods
        CardLayout cardLayout = LoginView.cardLayout;
        cardLayout.next(LoginView.jPanel_Right_Bottom_Button);
        //hidden the username and password input field
        this.setStatusInputData(false);
    }

    //this method for test getLogin above
    public void setLogin(String username, String password) {
        jTextField_Right_Middle_Email.setText(username);
        jPasswordField_Right_Middle_Password.setText(password);
    }

    public void setStatusInputData(boolean status) {
        jTextField_Right_Middle_Email.setEnabled(status);
        jPasswordField_Right_Middle_Password.setEnabled(status);
    }

    @Override
    public void setHoverConfirmPassword(boolean isInside, String mode) {
    }

    @Override
    public void setHoverButton(boolean isInside, String mode, JButton button) {

    }

    @Override
    public void changeColorBaseOnToggle() {

    }

    public class ClickForgotPassword implements ActionListener {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            if (jButton_Right_Bottom_Forgot_Password.getText().equals("Forgot password?")) {
                String data = jTextField_Right_Middle_Email.getText();
                if (data.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter your email", "Error",
                        JOptionPane.ERROR_MESSAGE);
                } else {
                    // need to check if the user is in the database or not
                    if (!new RegisterModel().isDuplicateEmail(data)) {
                        JOptionPane.showMessageDialog(null, "Account not existed", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please check your email for OTP",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                        new EmailUtils().checkEmailIsValidAndSendEmail(
                            EmailCategories.FORGOT_PASSWORD.getType(), data, "Hoang");
                    }
                }
            }
        }
    }
}

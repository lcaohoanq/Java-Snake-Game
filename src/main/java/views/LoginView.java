package views;

import constants.ResourcePaths;
import controllers.LoginController;
import controllers.PlayController;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import lombok.Getter;
import models.LoginModel;
import models.UserScore;
import styles.UIColors;
import styles.UIFonts;
import styles.UILabels;
import styles.UISizes;
import views.base.MyFrame;

@Getter
public class LoginView extends MyFrame {

    private CardLayout cardLayout;
    private final LoginModel loginModel;
    private LoginController loginController;

    public LoginView() {
        super();
        this.loginModel = new LoginModel();
        InputStream inputStream = getClass().getResourceAsStream(ResourcePaths.URL_INTRO);
        audioHandler.playAudio(inputStream);
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
        jLabel_Right_Top_Title = new JLabel(UILabels.LOGIN, JLabel.CENTER);
        jLabel_Right_Top_Title.setForeground(UIColors.TEXT_COLOR_L);
        jLabel_Right_Top_Title.setFont(UIFonts.RIGHT_TITLE);
        jPanel_Right_Top_Title.add(jLabel_Right_Top_Title);
    }

    @Override
    public void initRightMiddle() {
        jPanel_Right_Middle_Data = new JPanel(new GridLayout(3, 1));

        cardLayout = new CardLayout();

        jTextField_Right_Middle_Email.setFont(UIFonts.INPUT_LARGE);

        jPasswordField_Right_Middle_Password.setFont(UIFonts.INPUT_LARGE);

        jPanel_Right_Middle_Email.add(jLabel_Right_Middle_Email);
        jPanel_Right_Middle_Email.add(jTextField_Right_Middle_Email);

        jPanel_Right_Middle_Password.add(jLabel_Right_Middle_Password);
        jPanel_Right_Middle_Password.add(jPasswordField_Right_Middle_Password);

        jButton_Right_Play.setBackground(UIColors.TEXT_COLOR_L);
        jButton_Right_Play.setForeground(UIColors.PRIMARY_COLOR_L);
        jButton_Right_Play.setFont(UIFonts.BUTTON);
        jButton_Right_Play.setPreferredSize(UISizes.SIZE_BUTTON);

        jButton_Right_Play.setCursor(cursor);

        jPanel_Right_Bottom_Button.setLayout(cardLayout);
        jPanel_Right_Bottom_Button.add(jButton_Right_Bottom_Submit, "submit");
        jPanel_Right_Bottom_Button.add(jButton_Right_Play, "play");

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

        jButton_Right_Bottom_Forgot_Password.setBackground(UIColors.PRIMARY_COLOR_L);
        jButton_Right_Bottom_Forgot_Password.setForeground(UIColors.OTHER_OPTIONS_L);
        jButton_Right_Bottom_Forgot_Password.setFont(UIFonts.OTHERS);
        jButton_Right_Bottom_Forgot_Password.setBorder(null);

        jButton_Right_Bottom_Forgot_Password.setCursor(cursor);

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
        jPanel_Right_Bottom_Option.add(jButton_Right_Bottom_Forgot_Password);
    }

    @Override
    public void initRightPanel(){
        jPanel_Right.add(jPanel_Right_Top_Title, BorderLayout.NORTH);
        jPanel_Right.add(jPanel_Right_Middle_Data, BorderLayout.CENTER);
        jPanel_Right.add(jPanel_Right_Bottom_Option, BorderLayout.SOUTH);
    }

    @Override
    public void doAction() {
        // TODO Auto-generated method stub
        super.doAction();

        loginController = new LoginController(this);

        jTextField_Right_Middle_Email.addMouseListener(loginController);
        jPasswordField_Right_Middle_Password.addMouseListener(loginController);
        jButton_Right_Bottom_Submit.addMouseListener(loginController);
        jButton_Right_Bottom_Others.addMouseListener(loginController);
        jButton_Right_Bottom_Submit.addActionListener(loginController);
        
        jPasswordField_Right_Middle_Password.addActionListener(new PressEnter());
        jButton_Right_Bottom_Others.addActionListener(new ClickOtherOption());
        jButton_Right_Bottom_Forgot_Password.addMouseListener(loginController);
    }

    // Authentication methods
    public UserScore getDataWhenLogin() {
        return new UserScore(
            jTextField_Right_Middle_Email.getText(), 
            String.valueOf(jPasswordField_Right_Middle_Password.getPassword())
        );
    }

    public boolean isEmpty() {
        String email = jTextField_Right_Middle_Email.getText();
        String password = String.valueOf(jPasswordField_Right_Middle_Password.getPassword());
        return this.loginModel.isEmpty(email, password);
    }

    public boolean isAdmin() {
        String email = jTextField_Right_Middle_Email.getText();
        String password = String.valueOf(jPasswordField_Right_Middle_Password.getPassword());
        return this.loginModel.isAdmin(email, password);
    }

    public UserScore login() {
        String email = jTextField_Right_Middle_Email.getText();
        String password = String.valueOf(jPasswordField_Right_Middle_Password.getPassword());
        return this.loginModel.login(email, password);
    }

    public void handleSuccess() {
        UIPrompts.IS_LOGIN_SUCCESS();
        // Switch to play button using explicit card name
        this.getCardLayout().show(jPanel_Right_Bottom_Button, "play");
        this.setStatusInputData(false);
    }

    // Utility methods
    public void setLogin(String username, String password) {
        jTextField_Right_Middle_Email.setText(username);
        jPasswordField_Right_Middle_Password.setText(password);
    }

    public void setStatusInputData(boolean status) {
        jTextField_Right_Middle_Email.setEnabled(status);
        jPasswordField_Right_Middle_Password.setEnabled(status);
    }

    private class ClickOtherOption implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose(); // Properly dispose the current frame
            // Create and show the RegisterView, but disable the toggle button action initially
            RegisterView registerView = new RegisterView();
            // Make sure to initialize all components before showing the frame
            registerView.setVisible(true);
        }
    }
}

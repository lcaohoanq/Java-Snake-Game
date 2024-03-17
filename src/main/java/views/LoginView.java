package views;

import utils.UIUtils;
import constants.ResourcePaths;
import constants.UISizes;
import constants.UILabels;
import controllers.LoginController;
import controllers.PlayController;
import models.data.Account;
import models.data.LoginData;
import models.ui.LoginModel;
import styles.Borders;
import styles.Colors;
import styles.Fonts;
import utils.HoverHandler;
import utils.ToggleHandler;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public non-sealed class LoginView extends MyFrame implements ToggleHandler, HoverHandler, LoginData {

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
        jPanel_Right.setPreferredSize(new Dimension(UISizes.WIDTH_MY_RIGHT_FRAME, UISizes.HEIGHT_MY_RIGHT_FRAME));
        jPanel_Right.setBackground(Colors.PRIMARY_COLOR_L);
        jPanel_Right.setBorder(Borders.MID_FIELD);

        jPanel_Right.add(jPanel_Right_Top_Tittle, BorderLayout.NORTH);
        jPanel_Right.add(jPanel_Right_Middle_Data, BorderLayout.CENTER);
        jPanel_Right.add(jPanel_Right_Bottom_Option, BorderLayout.SOUTH);

    }

    @Override
    public void initRightTop() {
        jPanel_Right_Top_Tittle = new JPanel();
        jLabel_Right_Top_Tittle = new JLabel(UILabels.LOGIN, JLabel.CENTER);
        jLabel_Right_Top_Tittle.setForeground(Colors.TEXT_COLOR_L);
        jLabel_Right_Top_Tittle.setFont(Fonts.RIGHT_TITLE);

        jPanel_Right_Top_Tittle.setBackground(Colors.PRIMARY_COLOR_L);
        jPanel_Right_Top_Tittle.add(jLabel_Right_Top_Tittle);
        jPanel_Right_Top_Tittle.setBorder(Borders.TITLE);
    }

    @Override
    public void initRightMiddle() {
        jPanel_Right_Middle_Data = new JPanel(new GridLayout(3, 1));
        jPanel_Right_Middle_Username = new JPanel();
        jPanel_Right_Middle_Password = new JPanel();
        jPanel_Right_Bottom_Button = new JPanel();
        cardLayout = new CardLayout();

        jLabel_Right_Middle_Username = new JLabel(UILabels.USERNAME);
        jLabel_Right_Middle_Password = new JLabel(UILabels.PASSWORD);

        jTextField_Right_Middle_Username = new JTextField(20);
        jPasswordField_Right_Middle_Password = new JPasswordField(20);

        jButton_Right_Bottom_Submit = new JButton(UILabels.SUBMIT);
        jButton_Right_Play = new JButton(UILabels.PLAY);

        jLabel_Right_Middle_Username.setForeground(Colors.TEXT_COLOR_L);
        jLabel_Right_Middle_Username.setFont(Fonts.LABEL);
        jLabel_Right_Middle_Username.setBorder(Borders.MID_LABEL);
        jTextField_Right_Middle_Username.setFont(Fonts.INPUT_LARGE);
        jTextField_Right_Middle_Username.setBorder(Borders.DATA_FIELD);
        jTextField_Right_Middle_Username.setBackground(Colors.SECONDARY_COLOR_L);
        jTextField_Right_Middle_Username.setForeground(Colors.TEXT_COLOR_L);

        jLabel_Right_Middle_Password.setForeground(Colors.TEXT_COLOR_L);
        jLabel_Right_Middle_Password.setFont(Fonts.LABEL);
        jLabel_Right_Middle_Password.setBorder(Borders.MID_LABEL);
        jPasswordField_Right_Middle_Password.setBorder(Borders.DATA_FIELD);
        jPasswordField_Right_Middle_Password.setFont(Fonts.INPUT_LARGE);
        jPasswordField_Right_Middle_Password.setBackground(Colors.SECONDARY_COLOR_L);
        jPasswordField_Right_Middle_Password.setForeground(Colors.TEXT_COLOR_L);

        jPanel_Right_Middle_Username.setLayout(new BoxLayout(jPanel_Right_Middle_Username, BoxLayout.Y_AXIS));
        jPanel_Right_Middle_Username.setBackground(Colors.PRIMARY_COLOR_L);
        jPanel_Right_Middle_Username.add(jLabel_Right_Middle_Username);
        jPanel_Right_Middle_Username.add(jTextField_Right_Middle_Username);
        jPanel_Right_Middle_Username.setBorder(Borders.MIDDLE);

        jPanel_Right_Middle_Password.setLayout(new BoxLayout(jPanel_Right_Middle_Password, BoxLayout.Y_AXIS));
        jPanel_Right_Middle_Password.setBackground(Colors.PRIMARY_COLOR_L);
        jPanel_Right_Middle_Password.add(jLabel_Right_Middle_Password);
        jPanel_Right_Middle_Password.add(jPasswordField_Right_Middle_Password);
        jPanel_Right_Middle_Password.setBorder(Borders.MIDDLE);

        jButton_Right_Bottom_Submit.setBackground(Colors.TEXT_COLOR_L);
        jButton_Right_Bottom_Submit.setForeground(Colors.PRIMARY_COLOR_L);
        jButton_Right_Bottom_Submit.setFont(Fonts.BUTTON);
        jButton_Right_Bottom_Submit.setPreferredSize(UISizes.SIZE_BUTTON);
        jButton_Right_Play.setBackground(Colors.TEXT_COLOR_L);
        jButton_Right_Play.setForeground(Colors.PRIMARY_COLOR_L);
        jButton_Right_Play.setFont(Fonts.BUTTON);
        jButton_Right_Play.setPreferredSize(UISizes.SIZE_BUTTON);
        jPanel_Right_Bottom_Button.setLayout(cardLayout);
        jPanel_Right_Bottom_Button.add(jButton_Right_Bottom_Submit, "Card1");
        jPanel_Right_Bottom_Button.add(jButton_Right_Play, "Card2");
        jPanel_Right_Bottom_Button.setBackground(Colors.PRIMARY_COLOR_L);
        jPanel_Right_Bottom_Button.setBorder(Borders.BUTTON);

        // Add jPanel_Right_Middle_Username and jPanel_Right_Middle_Password directly to
        // jPanel_Right

        jPanel_Right_Middle_Data.setBackground(Colors.PRIMARY_COLOR_L);
        jPanel_Right_Middle_Data.add(jPanel_Right_Middle_Username);
        jPanel_Right_Middle_Data.add(jPanel_Right_Middle_Password);
        jPanel_Right_Middle_Data.add(jPanel_Right_Bottom_Button);
    }

    @Override
    public void initRightBottom() {
        jLabel_Right_Bottom_Option = new JLabel(UILabels.DONT_HAVE_ACCOUNT);
        jButton_Right_Bottom_Others = new JButton(UILabels.SIGN_UP_HERE);
        jPanel_Right_Bottom_Option = new JPanel();

        jLabel_Right_Bottom_Option.setForeground(Colors.TEXT_COLOR_L);
        jLabel_Right_Bottom_Option.setFont(Fonts.OTHERS);

        jButton_Right_Bottom_Others.setBackground(Colors.PRIMARY_COLOR_L);
        jButton_Right_Bottom_Others.setForeground(Colors.OTHER_OPTIONS_L);
        jButton_Right_Bottom_Others.setFont(Fonts.OTHERS);
        jButton_Right_Bottom_Others.setBorder(null);

        jPanel_Right_Bottom_Option.setBackground(Colors.PRIMARY_COLOR_L);
        jPanel_Right_Bottom_Option.add(jLabel_Right_Bottom_Option);
        jPanel_Right_Bottom_Option.add(jButton_Right_Bottom_Others);
    }

    @Override
    public void initToggle() {
        super.initToggle();
        toggleButton.addEventSelected(selected -> {
            if (selected) {
                jButton_Right_Play.setForeground(Colors.PRIMARY_COLOR_D);
                jButton_Right_Play.setBackground(Colors.TEXT_COLOR_D);
            } else {
                jButton_Right_Play.setBackground(Colors.TEXT_COLOR_L);
                jButton_Right_Play.setForeground(Colors.PRIMARY_COLOR_L);
            }
        });
    }

    @Override
    protected void doAction() {
        // TODO Auto-generated method stub
        super.doAction();
        jTextField_Right_Middle_Username.addMouseListener(new LoginController(this));
        jPasswordField_Right_Middle_Password.addMouseListener(new LoginController(this));
        jButton_Right_Bottom_Submit.addMouseListener(new LoginController(this));
        jButton_Right_Bottom_Others.addMouseListener(new LoginController(this));
        jButton_Right_Bottom_Submit.addActionListener(new LoginController(this));
        jButton_Right_Play.addActionListener(new PlayController(this));
        jPasswordField_Right_Middle_Password.addActionListener(new PressEnter());
        jButton_Right_Bottom_Others.addActionListener(new ClickOtherOption());
    }

    //xu li cac ham o day
    public Account getLogin() {
        username = jTextField_Right_Middle_Username.getText();
        password = String.valueOf(jPasswordField_Right_Middle_Password.getPassword());
        return new Account(username, password);
    }

    public boolean isEmpty() {
        return this.loginModel.isEmpty(this.getLogin().username(), this.getLogin().password());
    }

    public void handleEmpty() {
        UIUtils.IS_EMPTY_USERNAME_OR_PASSWORD();
    }

    public void handleWrong() {
        UIUtils.IS_WRONG_USERNAME_OR_PASSWORD();
    }

    public boolean isAdmin() {
        return this.loginModel.isAdmin(this.getLogin().username(), this.getLogin().password());
    }

    public boolean isMatching() {
        return this.loginModel.isMatching(this.getLogin().username(), this.getLogin().password());
    }

    public void handleSuccess() {
        UIUtils.IS_LOGIN_SUCCESS();
        // Switch to the play button card using static methods
        CardLayout cardLayout = LoginView.cardLayout;
        cardLayout.next(LoginView.jPanel_Right_Bottom_Button);
        //hidden the username and password input field
        this.setStatusInputData(false);
    }

    //this method for test getLogin above
    public void setLogin(String username, String password) {
        jTextField_Right_Middle_Username.setText(username);
        jPasswordField_Right_Middle_Password.setText(password);
    }

    public void setStatusInputData(boolean status) {
        jTextField_Right_Middle_Username.setEnabled(status);
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
}

package views;

import constants.Info;
import constants.ResourcePaths;
import styles.UISizes;
import styles.UILabels;
import controllers.ScoreController;
import styles.UIBorders;
import styles.UIColors;
import styles.UIFonts;
import styles.UIImages;
import modules.sound.AudioHandler;
import controllers.ToggleHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract sealed class MyFrame extends JFrame implements ToggleHandler, HoverHandler permits
    LoginView, RegisterView {

    public static JPanel jPanel_Right_Bottom_Button;
    private final JMenu jMenu = new JMenu("HELP");
    private final JMenu jMenu_Play_Here = new JMenu("Play here");
    private final JMenuItem jMenuItem_AboutMe = new JMenuItem("About me");
    private final JMenuItem jMenuItem_Score = new JMenuItem("Show Score");
    private final JMenuItem jMenuItem_Go = new JMenuItem("Go!!!");
    public JTextField jTextField_Right_Middle_Email; // 20 is the number of columns
    public JTextField jTextField_Right_Middle_FirstName; // 20 is the number of columns
    public JTextField jTextField_Right_Middle_LastName; // 20 is the number of columns
    public JPasswordField jPasswordField_Right_Middle_Password;
    public JPasswordField jPasswordField_Right_Middle_Confirm_Password;
    public JButton jButton_Right_Bottom_Submit;
    public JButton jButton_Right_Bottom_Forgot_Password;
    public JButton jButton_Right_Bottom_Others;
    protected JPanel jPanel_Container;
    protected JPanel jPanel_Right;
    protected JPanel jPanel_Right_Top_Tittle;
    protected JLabel jLabel_Right_Top_Tittle;
    protected JPanel jPanel_Right_Middle_Email;
    protected JPanel jPanel_Right_Middle_FirstName;
    protected JPanel jPanel_Right_Middle_LastName;
    protected JLabel jLabel_Right_Middle_Email;
    protected JLabel jLabel_Right_Middle_FirstName;
    protected JLabel jLabel_Right_Middle_LastName;
    protected JPanel jPanel_Right_Middle_Password;
    protected JLabel jLabel_Right_Middle_Password;
    protected JLabel jLabel_Right_Middle_Confirm_Password;
    protected JPanel jPanel_Right_Middle_Confirm_Password;
    protected JPanel jPanel_Right_Middle_Data;
    protected JButton jButton_Right_Play;
    protected JPanel jPanel_Right_Bottom_Option;
    protected JLabel jLabel_Right_Bottom_Option;
    protected JPanel jPanel_Left_Icon;
    protected AudioHandler audioHandler = new AudioHandler();
    private JPanel jPanel_Left;
    private JLabel jLabel_Left_Icon;
    private JMenuBar jMenuBar;
    protected Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

    public MyFrame() {
        setTitle(UILabels.WINDOW);
        setSize(UISizes.WIDTH_MY_FRAME, UISizes.HEIGHT_MY_FRAME);
        setIconImage(UIImages.icon);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
    }

    protected void initUI() {
        initMenu();
        initLeft();
        initRight();
        initToggle();
        initContainer();
        doAction();
    }

    private void initMenu() {
        // Menu bar setup
        jMenuBar = new JMenuBar();
        jMenu_Play_Here.add(jMenuItem_Go);
        jMenuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        jMenuBar.setBorder(UIBorders.LINE_MENU_BAR);

        jMenu.setCursor(cursor);
        jMenu_Play_Here.setCursor(cursor);

        jMenuBar.add(jMenu);
        jMenuBar.add(jMenu_Play_Here);
        jMenu.add(jMenuItem_AboutMe);
        jMenu.add(jMenuItem_Score);
        this.setJMenuBar(jMenuBar);
    }

    private void initLeft() {
        jPanel_Left = new JPanel(new BorderLayout());

        jPanel_Left_Icon = new JPanel(new BorderLayout());
        jPanel_Left_Icon.setPreferredSize(new Dimension(100, 100));
        jPanel_Left_Icon.setBackground(UIColors.TEXT_COLOR_L);

        // Use the new image "250-250.png"
        jLabel_Left_Icon = new JLabel(
                new ImageIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(MyFrame.class.getResource(ResourcePaths.URL_SNAKE_LOGO))).getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH)));
        jPanel_Left_Icon.add(jLabel_Left_Icon, BorderLayout.CENTER);

        jPanel_Left.setPreferredSize(new Dimension(UISizes.WIDTH_MY_LEFT_FRAME, UISizes.HEIGHT_MY_LEFT_FRAME));
        jPanel_Left.setBackground(UIColors.TEXT_COLOR_L);
        jPanel_Left.setBorder(UIBorders.TITLE);

        jPanel_Left.add(jPanel_Left_Icon, BorderLayout.CENTER);
    }

    public abstract void initRight();

    public abstract void initRightTop();

    public abstract void initRightMiddle();

    public abstract void initRightBottom();

    protected void initToggle() {
        toggleButton.addEventSelected(selected -> {
            if (selected) {
                jLabel_Right_Middle_Email.setForeground(UIColors.TEXT_COLOR_D);
                jLabel_Right_Middle_FirstName.setForeground(UIColors.TEXT_COLOR_D);
                jLabel_Right_Middle_LastName.setForeground(UIColors.TEXT_COLOR_D);
                jTextField_Right_Middle_Email.setForeground(UIColors.TEXT_COLOR_D);
                jTextField_Right_Middle_Email.setBackground(UIColors.SECONDARY_COLOR_D);
                jTextField_Right_Middle_FirstName.setForeground(UIColors.TEXT_COLOR_D);
                jTextField_Right_Middle_FirstName.setBackground(UIColors.SECONDARY_COLOR_D);
                jTextField_Right_Middle_LastName.setForeground(UIColors.TEXT_COLOR_D);
                jTextField_Right_Middle_LastName.setBackground(UIColors.SECONDARY_COLOR_D);
                jLabel_Right_Middle_Password.setForeground(UIColors.TEXT_COLOR_D);
                jLabel_Right_Top_Tittle.setForeground(UIColors.TEXT_COLOR_D);
                jPasswordField_Right_Middle_Password.setBackground(UIColors.SECONDARY_COLOR_D);
                jPasswordField_Right_Middle_Password.setForeground(UIColors.TEXT_COLOR_D);
                jButton_Right_Bottom_Submit.setForeground(UIColors.PRIMARY_COLOR_D);
                jButton_Right_Bottom_Submit.setBackground(UIColors.TEXT_COLOR_D);
                jButton_Right_Bottom_Others.setForeground(UIColors.OTHER_OPTIONS_D);
                jButton_Right_Bottom_Others.setBackground(UIColors.PRIMARY_COLOR_D);
                jPanel_Right_Middle_Email.setBackground(UIColors.PRIMARY_COLOR_D);
                jPanel_Right_Middle_FirstName.setBackground(UIColors.PRIMARY_COLOR_D);
                jPanel_Right_Middle_LastName.setBackground(UIColors.PRIMARY_COLOR_D);
                jPanel_Right_Middle_Password.setBackground(UIColors.PRIMARY_COLOR_D);
                jPanel_Right_Bottom_Button.setBackground(UIColors.PRIMARY_COLOR_D);
                jPanel_Right_Middle_Data.setBackground(UIColors.PRIMARY_COLOR_D);
                jLabel_Right_Bottom_Option.setForeground(UIColors.TEXT_COLOR_D);
                jLabel_Right_Bottom_Option.setBackground(UIColors.PRIMARY_COLOR_D);
                jPanel_Right_Bottom_Option.setBackground(UIColors.PRIMARY_COLOR_D);
                jLabel_Right_Top_Tittle.setBackground(UIColors.PRIMARY_COLOR_D);
                jPanel_Right_Top_Tittle.setBackground(UIColors.PRIMARY_COLOR_D);
                jPanel_Left_Icon.setBackground(UIColors.TEXT_COLOR_D);
                jPanel_Left.setBackground(UIColors.TEXT_COLOR_D);
                jPanel_Container.setBackground(UIColors.TEXT_COLOR_D);
                jPanel_Right.setBackground(UIColors.PRIMARY_COLOR_D);
            } else {
                jLabel_Right_Middle_Email.setForeground(UIColors.TEXT_COLOR_D);
                jLabel_Right_Middle_FirstName.setForeground(UIColors.TEXT_COLOR_D);
                jLabel_Right_Middle_LastName.setForeground(UIColors.TEXT_COLOR_D);
                jTextField_Right_Middle_Email.setForeground(UIColors.TEXT_COLOR_L);
                jTextField_Right_Middle_Email.setBackground(UIColors.SECONDARY_COLOR_L);
                jTextField_Right_Middle_FirstName.setForeground(UIColors.TEXT_COLOR_L);
                jTextField_Right_Middle_FirstName.setBackground(UIColors.SECONDARY_COLOR_L);
                jTextField_Right_Middle_LastName.setForeground(UIColors.TEXT_COLOR_L);
                jTextField_Right_Middle_LastName.setBackground(UIColors.SECONDARY_COLOR_L);
                jLabel_Right_Middle_Password.setForeground(UIColors.TEXT_COLOR_L);
                jPasswordField_Right_Middle_Password.setBackground(UIColors.SECONDARY_COLOR_L);
                jPasswordField_Right_Middle_Password.setForeground(UIColors.TEXT_COLOR_L);
                jPanel_Right_Middle_Email.setBackground(UIColors.PRIMARY_COLOR_L);
                jPanel_Right_Middle_FirstName.setBackground(UIColors.PRIMARY_COLOR_L);
                jPanel_Right_Middle_LastName.setBackground(UIColors.PRIMARY_COLOR_L);
                jPanel_Right_Middle_Password.setBackground(UIColors.PRIMARY_COLOR_L);
                jButton_Right_Bottom_Submit.setForeground(UIColors.PRIMARY_COLOR_L);
                jButton_Right_Bottom_Submit.setBackground(UIColors.TEXT_COLOR_L);
                jPanel_Right_Bottom_Button.setBackground(UIColors.PRIMARY_COLOR_L);
                jPanel_Right_Middle_Data.setBackground(UIColors.PRIMARY_COLOR_L);
                jButton_Right_Bottom_Others.setForeground(UIColors.OTHER_OPTIONS_L);
                jButton_Right_Bottom_Others.setBackground(UIColors.PRIMARY_COLOR_L);
                jPanel_Right_Bottom_Option.setBackground(UIColors.PRIMARY_COLOR_L);
                jLabel_Right_Top_Tittle.setForeground(UIColors.TEXT_COLOR_L);
                jLabel_Right_Bottom_Option.setForeground(UIColors.TEXT_COLOR_L);
                jLabel_Right_Bottom_Option.setBackground(UIColors.PRIMARY_COLOR_L);
                jPanel_Right_Top_Tittle.setBackground(UIColors.PRIMARY_COLOR_L);
                jPanel_Left_Icon.setBackground(UIColors.TEXT_COLOR_L);
                jPanel_Left.setBackground(UIColors.TEXT_COLOR_L);
                jPanel_Container.setBackground(UIColors.TEXT_COLOR_L);
                jPanel_Right.setBackground(UIColors.PRIMARY_COLOR_L);
            }
        });
        jPanel_Right_Bottom_Option.add(toggleButton, FlowLayout.RIGHT);
    }

    public void setHoverEmail(boolean isInside, String mode) {
        if (isInside) {
            if (mode.equals("light")) {
                jTextField_Right_Middle_Email.setBackground(UIColors.SECONDARY_COLOR_L_HOVER);
            } else {
                jTextField_Right_Middle_Email.setBackground(UIColors.SECONDARY_COLOR_D_HOVER);
            }
        } else {
            if (mode.equals("light")) {
                jTextField_Right_Middle_Email.setBackground(UIColors.SECONDARY_COLOR_L);
            } else {
                jTextField_Right_Middle_Email.setBackground(UIColors.SECONDARY_COLOR_D);
            }
        }
    }

    public void setHoverFirstName(boolean isInside, String mode) {
        if (isInside) {
            if (mode.equals("light")) {
                jTextField_Right_Middle_FirstName.setBackground(UIColors.SECONDARY_COLOR_L_HOVER);
            } else {
                jTextField_Right_Middle_FirstName.setBackground(UIColors.SECONDARY_COLOR_D_HOVER);
            }
        } else {
            if (mode.equals("light")) {
                jTextField_Right_Middle_FirstName.setBackground(UIColors.SECONDARY_COLOR_L);
            } else {
                jTextField_Right_Middle_FirstName.setBackground(UIColors.SECONDARY_COLOR_D);
            }
        }
    }

    public void setHoverLastName(boolean isInside, String mode) {
        if (isInside) {
            if (mode.equals("light")) {
                jTextField_Right_Middle_LastName.setBackground(UIColors.SECONDARY_COLOR_L_HOVER);
            } else {
                jTextField_Right_Middle_LastName.setBackground(UIColors.SECONDARY_COLOR_D_HOVER);
            }
        } else {
            if (mode.equals("light")) {
                jTextField_Right_Middle_LastName.setBackground(UIColors.SECONDARY_COLOR_L);
            } else {
                jTextField_Right_Middle_LastName.setBackground(UIColors.SECONDARY_COLOR_D);
            }
        }
    }

    public void setHoverPassword(boolean isInside, String mode) {
        if (isInside) {
            if (mode.equals("light")) {
                jPasswordField_Right_Middle_Password.setBackground(UIColors.SECONDARY_COLOR_L_HOVER);
            } else {
                jPasswordField_Right_Middle_Password.setBackground(UIColors.SECONDARY_COLOR_D_HOVER);
            }
        } else {
            if (mode.equals("light")) {
                jPasswordField_Right_Middle_Password.setBackground(UIColors.SECONDARY_COLOR_L);
            } else {
                jPasswordField_Right_Middle_Password.setBackground(UIColors.SECONDARY_COLOR_D);
            }
        }
    }

    public void setHoverButton(boolean isInside, String mode) {
        if (isInside) {
            if (mode.equals("light")) {
                jButton_Right_Bottom_Submit.setBackground(UIColors.TEXT_COLOR_L_HOVER);
            } else {
                jButton_Right_Bottom_Submit.setBackground(UIColors.TEXT_COLOR_D_HOVER);
            }
        } else {
            if (mode.equals("light")) {
                jButton_Right_Bottom_Submit.setBackground(UIColors.TEXT_COLOR_L);
            } else {
                jButton_Right_Bottom_Submit.setBackground(UIColors.TEXT_COLOR_D);
            }
        }
    }

    public void setHoverOther(boolean isInside) {
        if (isInside) {
            jButton_Right_Bottom_Others.setFont(UIFonts.OTHERS_HOVER);
        } else {
            jButton_Right_Bottom_Others.setFont(UIFonts.OTHERS);
        }
    }

    public void setHoverForgotPassword(boolean isInside) {
        if (isInside) {
            jButton_Right_Bottom_Forgot_Password.setFont(UIFonts.OTHERS_HOVER);
        } else {
            jButton_Right_Bottom_Forgot_Password.setFont(UIFonts.OTHERS);
        }
    }

    protected void initContainer() {
        jPanel_Container = new JPanel();
        jPanel_Container.setLayout(new BorderLayout());
        jPanel_Container.setBackground(UIColors.PRIMARY_COLOR_L);
        jPanel_Container.add(jPanel_Left, BorderLayout.WEST);
        jPanel_Container.add(jPanel_Right, BorderLayout.CENTER);
        this.add(jPanel_Container);
    }

    protected void doAction() {
        jMenuItem_Go.addActionListener(new ClickPlayNow());
        jMenuItem_AboutMe.addActionListener(new Info());
        jMenuItem_Score.addActionListener(new ScoreController(new ScoreView()));
    }

    public class ClickOtherOption implements ActionListener {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            if (jButton_Right_Bottom_Others.getText().equals("Sign in here")) {
                LoginView loginView = new LoginView();
                loginView.setVisible(true);
                dispose();
            } else if (jButton_Right_Bottom_Others.getText().equals("Sign up here")) {
                RegisterView registerView = new RegisterView();
                registerView.setVisible(true);
                dispose();
            }

        }
    }

    public class PressEnter implements ActionListener {

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            jButton_Right_Bottom_Submit.doClick();
        }

    }

    protected class ClickPlayNow implements ActionListener {

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            new MenuView().setVisible(true);
            dispose();
        }
    }

}

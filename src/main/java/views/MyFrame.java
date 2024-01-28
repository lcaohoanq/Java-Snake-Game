package views;

import constants.Info;
import constants.Paths;
import constants.Sizes;
import constants.Titles;
import controllers.ScoreController;
import styles.Borders;
import styles.Colors;
import styles.Fonts;
import styles.Images;
import utils.HoverHandler;
import utils.ToggleHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class MyFrame extends JFrame implements ToggleHandler, HoverHandler {

    public static JPanel jPanel_Right_Bottom_Button;
    public JTextField jTextField_Right_Middle_Username; // 20 is the number of columns
    public JPasswordField jPasswordField_Right_Middle_Password;
    public JPasswordField jPasswordField_Right_Middle_Confirm_Password;
    public JButton jButton_Right_Bottom_Submit;
    public JButton jButton_Right_Bottom_Others;
    protected JPanel jPanel_Container;
    protected JPanel jPanel_Right;
    protected JPanel jPanel_Right_Top_Tittle;
    protected JLabel jLabel_Right_Top_Tittle;
    protected JPanel jPanel_Right_Middle_Username;
    protected JLabel jLabel_Right_Middle_Username;
    protected JPanel jPanel_Right_Middle_Password;
    protected JLabel jLabel_Right_Middle_Password;
    protected JLabel jLabel_Right_Middle_Confirm_Password;
    protected JPanel jPanel_Right_Middle_Confirm_Password;
    protected JPanel jPanel_Right_Middle_Data;
    protected JButton jButton_Right_Play;
    protected JPanel jPanel_Right_Bottom_Option;
    protected JLabel jLabel_Right_Bottom_Option;
    protected JPanel jPanel_Left_Icon;
    private JPanel jPanel_Left;
    private JLabel jLabel_Left_Icon;
    // MenuBar
    private JMenuBar jMenuBar;
    private JMenu jMenu = new JMenu("HELP");
    private JMenuItem jMenuItem_AboutMe = new JMenuItem("About me");
    private JMenuItem jMenuItem_Score = new JMenuItem("Show Score");
    private JMenuItem jMenuItem_PlayNow = new JMenuItem("Play Now");

    public MyFrame() {
        setTitle(Titles.WINDOW);
        setSize(Sizes.WIDTH_MY_FRAME, Sizes.HEIGHT_MY_FRAME);
        setIconImage(Images.icon);
        setLocationRelativeTo(null);
        setResizable(false);
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
        jMenuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        jMenuBar.setBorder(Borders.LINE_MENU_BAR);
        jMenuBar.add(jMenu);
        jMenu.add(jMenuItem_AboutMe);
        jMenu.add(jMenuItem_Score);
        jMenu.add(jMenuItem_PlayNow);
        this.setJMenuBar(jMenuBar);
    }

    private void initLeft() {
        jPanel_Left = new JPanel(new BorderLayout());

        jPanel_Left_Icon = new JPanel(new BorderLayout());
        jPanel_Left_Icon.setPreferredSize(new Dimension(100, 100));
        jPanel_Left_Icon.setBackground(Colors.TEXT_COLOR_L);

        // Use the new image "250-250.png"
        jLabel_Left_Icon = new JLabel(
                new ImageIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(MyFrame.class.getResource(Paths.URL_SNAKE_LOGO))).getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH)));
        jPanel_Left_Icon.add(jLabel_Left_Icon, BorderLayout.CENTER);

        jPanel_Left.setPreferredSize(new Dimension(Sizes.WIDTH_MY_LEFT_FRAME, Sizes.HEIGHT_MY_LEFT_FRAME));
        jPanel_Left.setBackground(Colors.TEXT_COLOR_L);
        jPanel_Left.setBorder(Borders.TITLE);

        jPanel_Left.add(jPanel_Left_Icon, BorderLayout.CENTER);
    }

    public abstract void initRight();

    public abstract void initRightTop();

    public abstract void initRightMiddle();

    public abstract void initRightBottom();

    protected void initToggle() {
        toggleButton.addEventSelected(selected -> {
            if (selected) {
                jLabel_Right_Middle_Username.setForeground(Colors.TEXT_COLOR_D);
                jTextField_Right_Middle_Username.setForeground(Colors.TEXT_COLOR_D);
                jTextField_Right_Middle_Username.setBackground(Colors.SECONDARY_COLOR_D);
                jLabel_Right_Middle_Password.setForeground(Colors.TEXT_COLOR_D);
                jLabel_Right_Top_Tittle.setForeground(Colors.TEXT_COLOR_D);
                jPasswordField_Right_Middle_Password.setBackground(Colors.SECONDARY_COLOR_D);
                jPasswordField_Right_Middle_Password.setForeground(Colors.TEXT_COLOR_D);
                jButton_Right_Bottom_Submit.setForeground(Colors.PRIMARY_COLOR_D);
                jButton_Right_Bottom_Submit.setBackground(Colors.TEXT_COLOR_D);
                jButton_Right_Bottom_Others.setForeground(Colors.OTHER_OPTIONS_D);
                jButton_Right_Bottom_Others.setBackground(Colors.PRIMARY_COLOR_D);
                jPanel_Right_Middle_Username.setBackground(Colors.PRIMARY_COLOR_D);
                jPanel_Right_Middle_Password.setBackground(Colors.PRIMARY_COLOR_D);
                jPanel_Right_Bottom_Button.setBackground(Colors.PRIMARY_COLOR_D);
                jPanel_Right_Middle_Data.setBackground(Colors.PRIMARY_COLOR_D);
                jLabel_Right_Bottom_Option.setForeground(Colors.TEXT_COLOR_D);
                jLabel_Right_Bottom_Option.setBackground(Colors.PRIMARY_COLOR_D);
                jPanel_Right_Bottom_Option.setBackground(Colors.PRIMARY_COLOR_D);
                jLabel_Right_Top_Tittle.setBackground(Colors.PRIMARY_COLOR_D);
                jPanel_Right_Top_Tittle.setBackground(Colors.PRIMARY_COLOR_D);
                jPanel_Left_Icon.setBackground(Colors.TEXT_COLOR_D);
                jPanel_Left.setBackground(Colors.TEXT_COLOR_D);
                jPanel_Container.setBackground(Colors.TEXT_COLOR_D);
                jPanel_Right.setBackground(Colors.PRIMARY_COLOR_D);
            } else {
                jLabel_Right_Middle_Username.setForeground(Colors.TEXT_COLOR_L);
                jTextField_Right_Middle_Username.setForeground(Colors.TEXT_COLOR_L);
                jTextField_Right_Middle_Username.setBackground(Colors.SECONDARY_COLOR_L);
                jLabel_Right_Middle_Password.setForeground(Colors.TEXT_COLOR_L);
                jPasswordField_Right_Middle_Password.setBackground(Colors.SECONDARY_COLOR_L);
                jPasswordField_Right_Middle_Password.setForeground(Colors.TEXT_COLOR_L);
                jPanel_Right_Middle_Username.setBackground(Colors.PRIMARY_COLOR_L);
                jPanel_Right_Middle_Password.setBackground(Colors.PRIMARY_COLOR_L);
                jButton_Right_Bottom_Submit.setForeground(Colors.PRIMARY_COLOR_L);
                jButton_Right_Bottom_Submit.setBackground(Colors.TEXT_COLOR_L);
                jPanel_Right_Bottom_Button.setBackground(Colors.PRIMARY_COLOR_L);
                jPanel_Right_Middle_Data.setBackground(Colors.PRIMARY_COLOR_L);
                jButton_Right_Bottom_Others.setForeground(Colors.OTHER_OPTIONS_L);
                jButton_Right_Bottom_Others.setBackground(Colors.PRIMARY_COLOR_L);
                jPanel_Right_Bottom_Option.setBackground(Colors.PRIMARY_COLOR_L);
                jLabel_Right_Top_Tittle.setForeground(Colors.TEXT_COLOR_L);
                jLabel_Right_Bottom_Option.setForeground(Colors.TEXT_COLOR_L);
                jLabel_Right_Bottom_Option.setBackground(Colors.PRIMARY_COLOR_L);
                jPanel_Right_Top_Tittle.setBackground(Colors.PRIMARY_COLOR_L);
                jPanel_Left_Icon.setBackground(Colors.TEXT_COLOR_L);
                jPanel_Left.setBackground(Colors.TEXT_COLOR_L);
                jPanel_Container.setBackground(Colors.TEXT_COLOR_L);
                jPanel_Right.setBackground(Colors.PRIMARY_COLOR_L);
            }
        });
        jPanel_Right_Bottom_Option.add(toggleButton, FlowLayout.RIGHT);
    }

    public void setHoverUsername(boolean isInside, String mode) {
        if (isInside) {
            if (mode.equals("light")) {
                jTextField_Right_Middle_Username.setBackground(Colors.SECONDARY_COLOR_L_HOVER);
            } else {
                jTextField_Right_Middle_Username.setBackground(Colors.SECONDARY_COLOR_D_HOVER);
            }
        } else {
            if (mode.equals("light")) {
                jTextField_Right_Middle_Username.setBackground(Colors.SECONDARY_COLOR_L);
            } else {
                jTextField_Right_Middle_Username.setBackground(Colors.SECONDARY_COLOR_D);
            }
        }
    }

    public void setHoverPassword(boolean isInside, String mode) {
        if (isInside) {
            if (mode.equals("light")) {
                jPasswordField_Right_Middle_Password.setBackground(Colors.SECONDARY_COLOR_L_HOVER);
            } else {
                jPasswordField_Right_Middle_Password.setBackground(Colors.SECONDARY_COLOR_D_HOVER);
            }
        } else {
            if (mode.equals("light")) {
                jPasswordField_Right_Middle_Password.setBackground(Colors.SECONDARY_COLOR_L);
            } else {
                jPasswordField_Right_Middle_Password.setBackground(Colors.SECONDARY_COLOR_D);
            }
        }
    }

    public void setHoverButton(boolean isInside, String mode) {
        if (isInside) {
            if (mode.equals("light")) {
                jButton_Right_Bottom_Submit.setBackground(Colors.TEXT_COLOR_L_HOVER);
            } else {
                jButton_Right_Bottom_Submit.setBackground(Colors.TEXT_COLOR_D_HOVER);
            }
        } else {
            if (mode.equals("light")) {
                jButton_Right_Bottom_Submit.setBackground(Colors.TEXT_COLOR_L);
            } else {
                jButton_Right_Bottom_Submit.setBackground(Colors.TEXT_COLOR_D);
            }
        }
    }

    public void setHoverOther(boolean isInside) {
        if (isInside) {
            jButton_Right_Bottom_Others.setFont(Fonts.OTHERS_HOVER);
        } else {
            jButton_Right_Bottom_Others.setFont(Fonts.OTHERS);
        }
    }

//    public boolean getStatusToggle() {
//        return toggleButton.isSelected();
//    }

    protected void initContainer() {
        jPanel_Container = new JPanel();
        jPanel_Container.setLayout(new BorderLayout());
        jPanel_Container.setBackground(Colors.PRIMARY_COLOR_L);
        jPanel_Container.add(jPanel_Left, BorderLayout.WEST);
        jPanel_Container.add(jPanel_Right, BorderLayout.CENTER);
        this.add(jPanel_Container);
    }

    protected void doAction() {
        jMenuItem_PlayNow.addActionListener(new ClickPlayNow());
        jMenuItem_AboutMe.addActionListener(new Info());
        jMenuItem_Score.addActionListener(new ScoreController(new ScoreView()));
    }

    protected class ClickOtherOption implements ActionListener {
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

    protected class PressEnter implements ActionListener {

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

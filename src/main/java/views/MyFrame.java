package views;

import constants.Info;
import constants.ResourcePaths;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
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

@Getter
public abstract sealed class MyFrame extends JFrame implements ToggleHandler permits
    LoginView, RegisterView {

    public static JPanel jPanel_Right_Bottom_Button;
    private final JMenu jMenu = new JMenu("HELP");
    private final JMenu jMenu_Play_Here = new JMenu("Play here");
    private final JMenuItem jMenuItem_AboutMe = new JMenuItem("About me");
    private final JMenuItem jMenuItem_Score = new JMenuItem("Show Score");
    private final JMenuItem jMenuItem_Go = new JMenuItem("Go!!!");
    protected JTextField jTextField_Right_Middle_Email; // 20 is the number of columns
    protected JTextField jTextField_Right_Middle_FirstName; // 20 is the number of columns
    protected JTextField jTextField_Right_Middle_LastName; // 20 is the number of columns
    protected JPasswordField jPasswordField_Right_Middle_Password;
    protected JPasswordField jPasswordField_Right_Middle_Confirm_Password;
    protected JButton jButton_Right_Bottom_Submit;
    protected JButton jButton_Right_Bottom_Forgot_Password;
    protected JButton jButton_Right_Bottom_Others;
    protected JPanel jPanel_Container;
    protected JPanel jPanel_Right;
    protected JPanel jPanel_Right_Top_Title;
    protected JLabel jLabel_Right_Top_Title;
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

    public abstract void initRightPanel();

    protected void initToggle() {
        toggleButton.addEventSelected(selected -> {
            Map<JComponent, Color[]> lightMode = createLightModeMap();
            Map<JComponent, Color[]> darkMode = createDarkModeMap();
            updateUI(selected ? darkMode : lightMode);
        });
        jPanel_Right_Bottom_Option.add(toggleButton, FlowLayout.RIGHT);
    }

    //foreground, background
    private Map<JComponent, Color[]> createLightModeMap() {
        Map<JComponent, Color[]> lightMode = new HashMap<>();
        lightMode.put(jLabel_Right_Middle_Email, new Color[]{UIColors.TEXT_COLOR_L, UIColors.PRIMARY_COLOR_L});
        lightMode.put(jLabel_Right_Middle_FirstName, new Color[]{UIColors.TEXT_COLOR_L, UIColors.PRIMARY_COLOR_L});
        lightMode.put(jLabel_Right_Middle_LastName, new Color[]{UIColors.TEXT_COLOR_L, UIColors.PRIMARY_COLOR_L});
        lightMode.put(jTextField_Right_Middle_Email, new Color[]{UIColors.SECONDARY_COLOR_L, UIColors.PRIMARY_COLOR_L});
        lightMode.put(jTextField_Right_Middle_FirstName, new Color[]{UIColors.SECONDARY_COLOR_L, UIColors.PRIMARY_COLOR_L});
        lightMode.put(jTextField_Right_Middle_LastName, new Color[]{UIColors.SECONDARY_COLOR_L, UIColors.PRIMARY_COLOR_L});
        lightMode.put(jLabel_Right_Middle_Password, new Color[]{UIColors.TEXT_COLOR_L, UIColors.PRIMARY_COLOR_L});
        lightMode.put(jPasswordField_Right_Middle_Password, new Color[]{UIColors.SECONDARY_COLOR_L, UIColors.PRIMARY_COLOR_L});
        lightMode.put(jPanel_Right_Middle_Email, new Color[]{UIColors.PRIMARY_COLOR_L, UIColors.PRIMARY_COLOR_L});
        lightMode.put(jPanel_Right_Middle_FirstName, new Color[]{UIColors.PRIMARY_COLOR_L, UIColors.PRIMARY_COLOR_L});
        lightMode.put(jPanel_Right_Middle_LastName, new Color[]{UIColors.PRIMARY_COLOR_L, UIColors.PRIMARY_COLOR_L});
        lightMode.put(jPanel_Right_Middle_Password, new Color[]{UIColors.PRIMARY_COLOR_L, UIColors.PRIMARY_COLOR_L});
        lightMode.put(jButton_Right_Bottom_Submit, new Color[]{UIColors.TEXT_COLOR_L, UIColors.PRIMARY_COLOR_L});
        lightMode.put(jPanel_Right_Bottom_Button, new Color[]{UIColors.PRIMARY_COLOR_L, UIColors.PRIMARY_COLOR_L});
        lightMode.put(jPanel_Right_Middle_Data, new Color[]{UIColors.PRIMARY_COLOR_L, UIColors.PRIMARY_COLOR_L});
        lightMode.put(jButton_Right_Bottom_Others, new Color[]{UIColors.PRIMARY_COLOR_L, UIColors.PRIMARY_COLOR_L});
        lightMode.put(jPanel_Right_Bottom_Option, new Color[]{UIColors.PRIMARY_COLOR_L, UIColors.PRIMARY_COLOR_L});
        lightMode.put(jLabel_Right_Top_Title, new Color[]{UIColors.TEXT_COLOR_L, UIColors.PRIMARY_COLOR_L});
        lightMode.put(jLabel_Right_Bottom_Option, new Color[]{UIColors.TEXT_COLOR_L, UIColors.PRIMARY_COLOR_L});
        lightMode.put(jPanel_Right_Top_Title, new Color[]{UIColors.PRIMARY_COLOR_L, UIColors.PRIMARY_COLOR_L});
        lightMode.put(jPanel_Left_Icon, new Color[]{UIColors.TEXT_COLOR_L, UIColors.PRIMARY_COLOR_L});
        lightMode.put(jPanel_Left, new Color[]{UIColors.TEXT_COLOR_L, UIColors.PRIMARY_COLOR_L});
        lightMode.put(jPanel_Container, new Color[]{UIColors.TEXT_COLOR_L, UIColors.PRIMARY_COLOR_L});
        lightMode.put(jPanel_Right, new Color[]{UIColors.PRIMARY_COLOR_L,UIColors.PRIMARY_COLOR_L});
        return lightMode;
    }

    private Map<JComponent, Color[]> createDarkModeMap() {
        Map<JComponent, Color[]> darkMode = new HashMap<>();
        darkMode.put(jLabel_Right_Middle_Email, new Color[]{UIColors.TEXT_COLOR_D, UIColors.PRIMARY_COLOR_D});
        darkMode.put(jLabel_Right_Middle_FirstName, new Color[]{UIColors.TEXT_COLOR_D, UIColors.PRIMARY_COLOR_D});
        darkMode.put(jLabel_Right_Middle_LastName, new Color[]{UIColors.TEXT_COLOR_D, UIColors.PRIMARY_COLOR_D});
        darkMode.put(jTextField_Right_Middle_Email, new Color[]{UIColors.SECONDARY_COLOR_D, UIColors.PRIMARY_COLOR_D});
        darkMode.put(jTextField_Right_Middle_FirstName, new Color[]{UIColors.SECONDARY_COLOR_D, UIColors.PRIMARY_COLOR_D});
        darkMode.put(jTextField_Right_Middle_LastName, new Color[]{UIColors.SECONDARY_COLOR_D, UIColors.PRIMARY_COLOR_D});
        darkMode.put(jLabel_Right_Middle_Password, new Color[]{UIColors.TEXT_COLOR_D, UIColors.PRIMARY_COLOR_D});
        darkMode.put(jPasswordField_Right_Middle_Password, new Color[]{UIColors.SECONDARY_COLOR_D, UIColors.PRIMARY_COLOR_D});
        darkMode.put(jPanel_Right_Middle_Email, new Color[]{UIColors.PRIMARY_COLOR_D, UIColors.PRIMARY_COLOR_D});
        darkMode.put(jPanel_Right_Middle_FirstName, new Color[]{UIColors.PRIMARY_COLOR_D, UIColors.PRIMARY_COLOR_D});
        darkMode.put(jPanel_Right_Middle_LastName, new Color[]{UIColors.PRIMARY_COLOR_D, UIColors.PRIMARY_COLOR_D});
        darkMode.put(jPanel_Right_Middle_Password, new Color[]{UIColors.PRIMARY_COLOR_D, UIColors.PRIMARY_COLOR_D});
        darkMode.put(jButton_Right_Bottom_Submit, new Color[]{UIColors.TEXT_COLOR_D, UIColors.PRIMARY_COLOR_D});
        darkMode.put(jPanel_Right_Bottom_Button, new Color[]{UIColors.PRIMARY_COLOR_D, UIColors.PRIMARY_COLOR_D});
        darkMode.put(jPanel_Right_Middle_Data, new Color[]{UIColors.PRIMARY_COLOR_D, UIColors.PRIMARY_COLOR_D});
        darkMode.put(jButton_Right_Bottom_Others, new Color[]{UIColors.PRIMARY_COLOR_D, UIColors.PRIMARY_COLOR_D});
        darkMode.put(jPanel_Right_Bottom_Option, new Color[]{UIColors.PRIMARY_COLOR_D, UIColors.PRIMARY_COLOR_D});
        darkMode.put(jLabel_Right_Top_Title, new Color[]{UIColors.TEXT_COLOR_D, UIColors.PRIMARY_COLOR_D});
        darkMode.put(jLabel_Right_Bottom_Option, new Color[]{UIColors.TEXT_COLOR_D, UIColors.PRIMARY_COLOR_D});
        darkMode.put(jPanel_Right_Top_Title, new Color[]{UIColors.PRIMARY_COLOR_D, UIColors.PRIMARY_COLOR_D});
        darkMode.put(jPanel_Left_Icon, new Color[]{UIColors.TEXT_COLOR_D, UIColors.PRIMARY_COLOR_D});
        darkMode.put(jPanel_Left, new Color[]{UIColors.TEXT_COLOR_D, UIColors.PRIMARY_COLOR_D});
        darkMode.put(jPanel_Container, new Color[]{UIColors.TEXT_COLOR_D, UIColors.PRIMARY_COLOR_D});
        darkMode.put(jPanel_Right, new Color[]{UIColors.PRIMARY_COLOR_D, UIColors.PRIMARY_COLOR_D});
        return darkMode;
    }

    protected void updateUI(Map<JComponent, Color[]> mode) {
        if(mode != null){
            mode.forEach((component, colors) -> {
                component.setForeground(colors[0]);
                component.setBackground(colors[1]);
            });
        }else{
            System.out.println("Error in change theme");
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

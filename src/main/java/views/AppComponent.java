package views;

import constants.ResourcePaths;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import lombok.Getter;
import modules.sound.AudioHandler;
import styles.UIBorders;
import styles.UIColors;
import styles.UIFonts;
import styles.UILabels;
import styles.UISizes;

@Getter
public abstract class AppComponent extends JFrame {

    public static JPanel jPanel_Right_Bottom_Button = new JPanel();;
    protected final JMenu jMenu = new JMenu("HELP");
    protected final JMenu jMenu_Play_Here = new JMenu("Play here");
    protected final JMenuItem jMenuItem_AboutMe = new JMenuItem("About me");
    protected final JMenuItem jMenuItem_Score = new JMenuItem("Show Score");
    protected final JMenuItem jMenuItem_Go = new JMenuItem("Go!!!");
    protected JTextField jTextField_Right_Middle_Email = new JTextField(20); // 20 is the number of columns
    protected JTextField  jTextField_Right_Middle_FirstName = new JTextField(20);; // 20 is the number of columns
    protected JTextField jTextField_Right_Middle_LastName = new JTextField(20);; // 20 is the number of columns
    protected JPasswordField jPasswordField_Right_Middle_Password = new JPasswordField(20);;
    protected JPasswordField jPasswordField_Right_Middle_Confirm_Password = new JPasswordField(20);;
    protected JButton jButton_Right_Bottom_Submit = new JButton(UILabels.SUBMIT);;
    protected JButton jButton_Right_Bottom_Forgot_Password = new JButton(UILabels.FORGOT_PASSWORD);;
    protected JButton jButton_Right_Bottom_Others;
    protected JPanel jPanel_Container = new JPanel();
    protected JPanel jPanel_Right = new JPanel(new BorderLayout());
    protected JPanel jPanel_Right_Top_Title = new JPanel();;
    protected JLabel jLabel_Right_Top_Title;
    protected JPanel jPanel_Right_Middle_Email = new JPanel();
    protected JPanel jPanel_Right_Middle_FirstName = new JPanel();
    protected JPanel jPanel_Right_Middle_LastName = new JPanel();
    protected JLabel jLabel_Right_Middle_Email;
    protected JLabel jLabel_Right_Middle_FirstName;
    protected JLabel jLabel_Right_Middle_LastName;
    protected JPanel jPanel_Right_Middle_Password = new JPanel();;
    protected JLabel jLabel_Right_Middle_Password;
    protected JLabel jLabel_Right_Middle_Confirm_Password;
    protected JPanel jPanel_Right_Middle_Confirm_Password = new JPanel();
    protected JPanel jPanel_Right_Middle_Data;
    protected JButton jButton_Right_Play = new JButton(UILabels.PLAY);;
    protected JPanel jPanel_Right_Bottom_Option = new JPanel();;
    protected JLabel jLabel_Right_Bottom_Option;
    protected JPanel jPanel_Left_Icon = new JPanel(new BorderLayout());;
    protected AudioHandler audioHandler = new AudioHandler();
    protected JPanel jPanel_Left = new JPanel(new BorderLayout());;
    protected JLabel jLabel_Left_Icon = new JLabel(
        new ImageIcon(new ImageIcon(
            Toolkit.getDefaultToolkit().getImage(MyFrame.class.getResource(ResourcePaths.URL_SNAKE_LOGO))).getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH)));;
    protected JMenuBar jMenuBar = new JMenuBar();;
    protected Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

    // MenuView
    public JButton jButton_Mode_Classic = new JButton("Classic");;
    public JButton jButton_Mode_Modern = new JButton("Modern");;
    public JButton jButton_Mode_Campaign = new JButton("Campaign");;
    protected JPanel jPanel_Container_MenuView = new JPanel(new BorderLayout());;
    protected JLabel jLabel_Title_MenuView = new JLabel("Snake Game", JLabel.CENTER);;
    protected JPanel jPanel_Button_MenuView = new JPanel(new GridLayout(3, 1, 30, 20));;
    protected JMenuBar jMenuBar_MenuView;
    protected final JMenu jMenu_Back_To_Main_Menu = new JMenu("Back to");
    protected final JMenuItem jMenuItem_Back_To_Main_Menu = new JMenuItem("main menu");
    protected final JMenu jMenu_Sound = new JMenu("Turn Sound");
    protected final JMenuItem jMenuItem_Sound_On = new JMenuItem("On");
    protected final JMenuItem jMenuItem_Sound_Off = new JMenuItem("Off");

    // MenuModern
    //6 button: No Maze, Box, Tunnel, Mill, Rails, Apartment
    protected JPanel jPanel_Container_MenuModern = new JPanel();
    protected JPanel jPanel_Menu_Modern = new JPanel(new GridLayout(6, 1, 30, 20));
    protected JLabel jLabel_Title_Modern = new JLabel("Snake Game");
    protected JButton jButton_NoMaze = new JButton("No Maze");
    protected JButton jButton_Box = new JButton("Box");
    protected JButton jButton_Tunnel = new JButton("Tunnel");
    protected JButton jButton_Mill = new JButton("Mill");
    protected JButton jButton_Rails = new JButton("Rails");
    protected JButton jButton_Apartment = new JButton("Apartment");

    public AppComponent() {
        super();
        doStyling();
    }
    public abstract void initComponents();
    public abstract void doAction();
    private void doStyling(){
        jPanel_Right_Bottom_Button.setBackground(UIColors.PRIMARY_COLOR_L);
        jPanel_Right_Bottom_Button.setBorder(UIBorders.BUTTON);

        jPanel_Right.setPreferredSize(new Dimension(UISizes.WIDTH_MY_RIGHT_FRAME, UISizes.HEIGHT_MY_RIGHT_FRAME));
        jPanel_Right.setBackground(UIColors.PRIMARY_COLOR_L);
        jPanel_Right.setBorder(UIBorders.MID_FIELD);

        jPanel_Right_Top_Title.setBackground(UIColors.PRIMARY_COLOR_L);

        jPanel_Right_Top_Title.setBorder(UIBorders.TITLE);

        jLabel_Right_Middle_Email = new JLabel(UILabels.EMAIL_PHONE);
        jLabel_Right_Middle_Email.setForeground(UIColors.TEXT_COLOR_L);
        jLabel_Right_Middle_Email.setFont(UIFonts.LABEL);
        jLabel_Right_Middle_Email.setBorder(UIBorders.MID_LABEL);
        jTextField_Right_Middle_Email.setBorder(UIBorders.DATA_FIELD);
        jTextField_Right_Middle_Email.setBackground(UIColors.SECONDARY_COLOR_L);
        jTextField_Right_Middle_Email.setForeground(UIColors.TEXT_COLOR_L);
        jPanel_Right_Middle_Email.setLayout(
            new BoxLayout(jPanel_Right_Middle_Email, BoxLayout.Y_AXIS));
        jPanel_Right_Middle_Email.setBackground(UIColors.PRIMARY_COLOR_L);
        jPanel_Right_Middle_Email.setBorder(UIBorders.MIDDLE);

        jLabel_Right_Middle_Password = new JLabel(UILabels.PASSWORD);
        jLabel_Right_Middle_Password.setForeground(UIColors.TEXT_COLOR_L);
        jLabel_Right_Middle_Password.setFont(UIFonts.LABEL);
        jLabel_Right_Middle_Password.setBorder(UIBorders.MID_LABEL);
        jPasswordField_Right_Middle_Password.setBorder(UIBorders.DATA_FIELD);
        jPasswordField_Right_Middle_Password.setBackground(UIColors.SECONDARY_COLOR_L);
        jPasswordField_Right_Middle_Password.setForeground(UIColors.TEXT_COLOR_L);
        jPanel_Right_Middle_Password.setLayout(
            new BoxLayout(jPanel_Right_Middle_Password, BoxLayout.Y_AXIS));
        jPanel_Right_Middle_Password.setBackground(UIColors.PRIMARY_COLOR_L);
        jPanel_Right_Middle_Password.setBorder(UIBorders.MIDDLE);

        jButton_Right_Bottom_Submit.setBackground(UIColors.TEXT_COLOR_L);
        jButton_Right_Bottom_Submit.setForeground(UIColors.PRIMARY_COLOR_L);
        jButton_Right_Bottom_Submit.setFont(UIFonts.BUTTON);
        jButton_Right_Bottom_Submit.setPreferredSize(UISizes.SIZE_BUTTON);
        jButton_Right_Bottom_Submit.setCursor(cursor);

        stylingMenuView();
        stylingMenuModern();
    }

    private void stylingMenuView(){
        jLabel_Title_MenuView.setFont(UIFonts.RIGHT_TITLE);
        jLabel_Title_MenuView.setBorder(UIBorders.CONTAINER_MENU);

        jButton_Mode_Classic.setFont(UIFonts.BUTTON);
        jButton_Mode_Modern.setFont(UIFonts.BUTTON);
        jButton_Mode_Campaign.setFont(UIFonts.BUTTON);
    }

    private void stylingMenuModern(){
        jLabel_Title_Modern.setFont(UIFonts.RIGHT_TITLE);
        jLabel_Title_Modern.setBackground(UIColors.PRIMARY_COLOR_L);
        jLabel_Title_Modern.setForeground(UIColors.TEXT_COLOR_L);
        jLabel_Title_Modern.setBorder(UIBorders.TITLE);
        jLabel_Title_Modern.setHorizontalAlignment(SwingConstants.CENTER);

        jButton_NoMaze.setFont(UIFonts.BUTTON);
        jButton_NoMaze.setBackground(UIColors.TEXT_COLOR_L);
        jButton_NoMaze.setForeground(UIColors.PRIMARY_COLOR_L);

        jButton_Box.setFont(UIFonts.BUTTON);
        jButton_Box.setBackground(UIColors.TEXT_COLOR_L);
        jButton_Box.setForeground(UIColors.PRIMARY_COLOR_L);

        jButton_Tunnel.setFont(UIFonts.BUTTON);
        jButton_Tunnel.setBackground(UIColors.TEXT_COLOR_L);
        jButton_Tunnel.setForeground(UIColors.PRIMARY_COLOR_L);

        jButton_Mill.setFont(UIFonts.BUTTON);
        jButton_Mill.setBackground(UIColors.TEXT_COLOR_L);
        jButton_Mill.setForeground(UIColors.PRIMARY_COLOR_L);

        jButton_Rails.setFont(UIFonts.BUTTON);
        jButton_Rails.setBackground(UIColors.TEXT_COLOR_L);
        jButton_Rails.setForeground(UIColors.PRIMARY_COLOR_L);

        jButton_Apartment.setFont(UIFonts.BUTTON);
        jButton_Apartment.setBackground(UIColors.TEXT_COLOR_L);
        jButton_Apartment.setForeground(UIColors.PRIMARY_COLOR_L);

        jPanel_Menu_Modern.setBorder(UIBorders.TITLE);
        jPanel_Menu_Modern.setBackground(UIColors.PRIMARY_COLOR_L);

        jPanel_Container_MenuModern.setBackground(UIColors.PRIMARY_COLOR_L);
    }

}

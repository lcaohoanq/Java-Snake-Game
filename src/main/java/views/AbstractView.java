package views;

import java.awt.Cursor;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import lombok.Getter;
import modules.sound.AudioHandler;

@Getter
public abstract class AbstractView extends JFrame {

    public static JPanel jPanel_Right_Bottom_Button;
    protected final JMenu jMenu = new JMenu("HELP");
    protected final JMenu jMenu_Play_Here = new JMenu("Play here");
    protected final JMenuItem jMenuItem_AboutMe = new JMenuItem("About me");
    protected final JMenuItem jMenuItem_Score = new JMenuItem("Show Score");
    protected final JMenuItem jMenuItem_Go = new JMenuItem("Go!!!");
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
    protected JPanel jPanel_Left;
    protected JLabel jLabel_Left_Icon;
    protected JMenuBar jMenuBar;
    protected Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

    // MenuView
    public JButton jButton_Mode_Classic;
    public JButton jButton_Mode_Modern;
    public JButton jButton_Mode_Campaign;
    protected JPanel jPanel_Container_MenuView;
    protected JLabel jLabel_Title_MenuView;
    protected JPanel jPanel_Button_MenuView;
    protected JMenuBar jMenuBar_MenuView;
    protected final JMenu jMenu_Back_To_Main_Menu = new JMenu("Back to");
    protected final JMenuItem jMenuItem_Back_To_Main_Menu = new JMenuItem("main menu");
    protected final JMenu jMenu_Sound = new JMenu("Turn Sound");
    protected final JMenuItem jMenuItem_Sound_On = new JMenuItem("On");
    protected final JMenuItem jMenuItem_Sound_Off = new JMenuItem("Off");

    // MenuModern
    //6 button: No Maze, Box, Tunnel, Mill, Rails, Apartment
    public JPanel jPanel_Container_MenuModern = new JPanel();
    public JPanel jPanel_Menu_Modern = new JPanel(new GridLayout(6, 1, 30, 20));
    public JLabel jLabel_Title_Modern = new JLabel("Snake Game");
    public JButton jButton_NoMaze = new JButton("No Maze");
    public JButton jButton_Box = new JButton("Box");
    public JButton jButton_Tunnel = new JButton("Tunnel");
    public JButton jButton_Mill = new JButton("Mill");
    public JButton jButton_Rails = new JButton("Rails");
    public JButton jButton_Apartment = new JButton("Apartment");

    public AbstractView() {
        super();
    }
    public abstract void initComponents();
    public abstract void doAction();


}

package views;

import constants.Sizes;
import controllers.MenuController;
import styles.Borders;
import styles.Colors;
import styles.Fonts;
import utils.AudioHandler;
import utils.HoverHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends JFrame {
    public JButton jButton_1 = new JButton("NoMaze");
    public JButton jButton_2 = new JButton("Box");
    public JButton jButton_3 = new JButton("Tunnel");
    public JButton jButton_4 = new JButton("4");
    public JButton jButton_5 = new JButton("5");
    public JButton jButton_6 = new JButton("6");
    public JButton jButton_7 = new JButton("7");
    public JButton jButton_8 = new JButton("8");
    public JButton jButton_9 = new JButton("9");
    JPanel jPanel_Container;
    JLabel jLabel_Title = new JLabel("Snake Game", JLabel.CENTER);
    JPanel jPanel_Button = new JPanel(new GridLayout(3, 3, 30, 20));
    private JMenuBar jMenuBar = new JMenuBar();;
    private JMenu jMenu = new JMenu("HELP");
    private JMenuItem jMenuItem_Back_To_Main_Menu = new JMenuItem("Back to main menu");
    private JMenu jMenu_Sound = new JMenu("Sound");
    private JMenuItem jMenuItem_Sound_On = new JMenuItem("On");
    private JMenuItem jMenuItem_Sound_Off = new JMenuItem("Off");
    private AudioHandler audioHandler = new AudioHandler();

    public MenuView() {
        setTitle("Snake Game");
        setSize(Sizes.WIDTH_MY_FRAME, Sizes.HEIGHT_MY_FRAME);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        initMenu();
        initTitle();
        initButton();
        initContainer();
        // changeColorBaseOnToggle();
        doAction();
    }

    private void initMenu() {
        jMenuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        jMenuBar.setBorder(Borders.LINE_MENU_BAR);
        jMenuBar.add(jMenu);
        jMenu_Sound.add(jMenuItem_Sound_On);
        jMenu_Sound.add(jMenuItem_Sound_Off);
        jMenu.add(jMenuItem_Back_To_Main_Menu);
        jMenu.add(jMenu_Sound);
        this.setJMenuBar(jMenuBar);
    }

    private void initTitle() {
        jLabel_Title.setFont(Fonts.RIGHT_TITLE);
        jLabel_Title.setBorder(Borders.CONTAINER_MENU);
    }

    private void initButton() {
        jButton_1.setFont(Fonts.BUTTON);
        jButton_2.setFont(Fonts.BUTTON);
        jButton_3.setFont(Fonts.BUTTON);
        jButton_4.setFont(Fonts.BUTTON);
        jButton_5.setFont(Fonts.BUTTON);
        jButton_6.setFont(Fonts.BUTTON);
        jButton_7.setFont(Fonts.BUTTON);
        jButton_8.setFont(Fonts.BUTTON);
        jButton_9.setFont(Fonts.BUTTON);

        jPanel_Button.add(jButton_1);
        jPanel_Button.add(jButton_2);
        jPanel_Button.add(jButton_3);
        jPanel_Button.add(jButton_4);
        jPanel_Button.add(jButton_5);
        jPanel_Button.add(jButton_6);
        jPanel_Button.add(jButton_7);
        jPanel_Button.add(jButton_8);
        jPanel_Button.add(jButton_9);
    }

    private void initContainer() {
        jPanel_Container = new JPanel(new BorderLayout());

        jPanel_Container.add(jLabel_Title, BorderLayout.NORTH);
        jPanel_Container.add(jPanel_Button, BorderLayout.CENTER);
        add(jPanel_Container);
    }

    // private void changeColorBaseOnToggle() {
    // // if toggle is on, change color to dark
    // if (getStatusToggle()) {
    // jLabel_Title.setForeground(Colors.TEXT_COLOR_D);
    // jButton_1.setBackground(Colors.TEXT_COLOR_D);
    // jButton_1.setForeground(Colors.PRIMARY_COLOR_D);
    // jButton_2.setBackground(Colors.TEXT_COLOR_D);
    // jButton_2.setForeground(Colors.PRIMARY_COLOR_D);
    // jButton_3.setBackground(Colors.TEXT_COLOR_D);
    // jButton_3.setForeground(Colors.PRIMARY_COLOR_D);
    // jButton_4.setBackground(Colors.TEXT_COLOR_D);
    // jButton_4.setForeground(Colors.PRIMARY_COLOR_D);
    // jButton_5.setBackground(Colors.TEXT_COLOR_D);
    // jButton_5.setForeground(Colors.PRIMARY_COLOR_D);
    // jButton_6.setBackground(Colors.TEXT_COLOR_D);
    // jButton_6.setForeground(Colors.PRIMARY_COLOR_D);
    // jButton_7.setBackground(Colors.TEXT_COLOR_D);
    // jButton_7.setForeground(Colors.PRIMARY_COLOR_D);
    // jButton_8.setBackground(Colors.TEXT_COLOR_D);
    // jButton_8.setForeground(Colors.PRIMARY_COLOR_D);
    // jButton_9.setBackground(Colors.TEXT_COLOR_D);
    // jButton_9.setForeground(Colors.PRIMARY_COLOR_D);
    // jPanel_Button.setBackground(Colors.PRIMARY_COLOR_D);
    // jPanel_Container.setBackground(Colors.PRIMARY_COLOR_D);
    // } else {
    // jLabel_Title.setForeground(Colors.TEXT_COLOR_L);
    // jButton_1.setBackground(Colors.TEXT_COLOR_L);
    // jButton_1.setForeground(Colors.PRIMARY_COLOR_L);
    // jButton_2.setBackground(Colors.TEXT_COLOR_L);
    // jButton_2.setForeground(Colors.PRIMARY_COLOR_L);
    // jButton_3.setBackground(Colors.TEXT_COLOR_L);
    // jButton_3.setForeground(Colors.PRIMARY_COLOR_L);
    // jButton_4.setBackground(Colors.TEXT_COLOR_L);
    // jButton_4.setForeground(Colors.PRIMARY_COLOR_L);
    // jButton_5.setBackground(Colors.TEXT_COLOR_L);
    // jButton_5.setForeground(Colors.PRIMARY_COLOR_L);
    // jButton_6.setBackground(Colors.TEXT_COLOR_L);
    // jButton_6.setForeground(Colors.PRIMARY_COLOR_L);
    // jButton_7.setBackground(Colors.TEXT_COLOR_L);
    // jButton_7.setForeground(Colors.PRIMARY_COLOR_L);
    // jButton_8.setBackground(Colors.TEXT_COLOR_L);
    // jButton_8.setForeground(Colors.PRIMARY_COLOR_L);
    // jButton_9.setBackground(Colors.TEXT_COLOR_L);
    // jButton_9.setForeground(Colors.PRIMARY_COLOR_L);
    // jPanel_Button.setBackground(Colors.PRIMARY_COLOR_L);
    // jPanel_Container.setBackground(Colors.PRIMARY_COLOR_L);
    // }
    // }

    private void doAction() {
        jMenuItem_Back_To_Main_Menu.addActionListener(new BackToMainMenu());
        jMenuItem_Sound_On.addActionListener(new MenuController(this));
        jMenuItem_Sound_Off.addActionListener(new MenuController(this));
        jButton_1.addActionListener(new MenuController(this));
        jButton_2.addActionListener(new MenuController(this));
        jButton_3.addActionListener(new MenuController(this));
        jButton_4.addActionListener(new MenuController(this));
        jButton_5.addActionListener(new MenuController(this));
        jButton_7.addActionListener(new MenuController(this));
        jButton_8.addActionListener(new MenuController(this));
        jButton_6.addActionListener(new MenuController(this));
        jButton_9.addActionListener(new MenuController(this));
    }

    private class BackToMainMenu implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            EventQueue.invokeLater(() -> {
                dispose();
                new LoginView().setVisible(true);
            });
        }
    }
}

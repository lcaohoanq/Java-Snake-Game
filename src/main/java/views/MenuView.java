package views;

import constants.Sizes;
import controllers.MenuController;
import models.ui.MenuModel;
import styles.Borders;
import styles.Colors;
import styles.Fonts;
import styles.Images;
import utils.AudioHandler;
import utils.HoverHandler;
import utils.ToggleHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public non-sealed class MenuView extends JFrame implements ToggleHandler, HoverHandler {
    public JButton jButton_1 = new JButton("Classic");
    public JButton jButton_2 = new JButton("Modern");
    public JButton jButton_3 = new JButton("Campaign");

    JPanel jPanel_Container;
    JLabel jLabel_Title = new JLabel("Snake Game", JLabel.CENTER);
    JPanel jPanel_Button = new JPanel(new GridLayout(3, 1, 30, 20));
    boolean isActive;
    private JMenuBar jMenuBar = new JMenuBar();
    //    private JMenu jMenu = new JMenu("HELP");
    //change
    private JMenu jMenu_Back_To_Main_Menu = new JMenu("Back to");
    private JMenuItem jMenuItem_Back_To_Main_Menu = new JMenuItem("main menu");
    private JMenu jMenu_Sound = new JMenu("Turn Sound");
    private JMenuItem jMenuItem_Sound_On = new JMenuItem("On");
    private JMenuItem jMenuItem_Sound_Off = new JMenuItem("Off");
    private MenuModel menuModel;
    private AudioHandler audioHandler = new AudioHandler();

    public MenuView() {
        menuModel = new MenuModel();
        setTitle("Snake Game");
        setSize(Sizes.HEIGHT_MY_FRAME, Sizes.HEIGHT_MY_FRAME);
        setIconImage(Images.icon);
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
        changeColorBaseOnToggle();
        doAction();
    }

    private void initMenu() {
        jMenu_Back_To_Main_Menu.add(jMenuItem_Back_To_Main_Menu);
        jMenu_Sound.add(jMenuItem_Sound_On);
        jMenu_Sound.add(jMenuItem_Sound_Off);
        jMenuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        jMenuBar.setBorder(Borders.LINE_MENU_BAR);
        jMenuBar.add(jMenu_Back_To_Main_Menu);
        jMenuBar.add(jMenu_Sound);
//        jMenu.add(jMenu_Sound);
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
        jPanel_Button.add(jButton_1);
        jPanel_Button.add(jButton_2);
        jPanel_Button.add(jButton_3);
    }

    private void initContainer() {
        jPanel_Container = new JPanel(new BorderLayout());

        jPanel_Container.add(jLabel_Title, BorderLayout.NORTH);
        jPanel_Container.add(jPanel_Button, BorderLayout.CENTER);
        add(jPanel_Container);
    }

    @Override
    public void changeColorBaseOnToggle() {
        //if toggle is on, change color to dark
        if (getStatusToggle()) {
            jLabel_Title.setForeground(Colors.TEXT_COLOR_D);
            jButton_1.setBackground(Colors.TEXT_COLOR_D);
            jButton_1.setForeground(Colors.PRIMARY_COLOR_D);
            jButton_2.setBackground(Colors.TEXT_COLOR_D);
            jButton_2.setForeground(Colors.PRIMARY_COLOR_D);
            jButton_3.setBackground(Colors.TEXT_COLOR_D);
            jButton_3.setForeground(Colors.PRIMARY_COLOR_D);
            jPanel_Button.setBackground(Colors.PRIMARY_COLOR_D);
            jPanel_Container.setBackground(Colors.PRIMARY_COLOR_D);
        } else {
            jLabel_Title.setForeground(Colors.TEXT_COLOR_L);
            jButton_1.setBackground(Colors.TEXT_COLOR_L);
            jButton_1.setForeground(Colors.PRIMARY_COLOR_L);
            jButton_2.setBackground(Colors.TEXT_COLOR_L);
            jButton_2.setForeground(Colors.PRIMARY_COLOR_L);
            jButton_3.setBackground(Colors.TEXT_COLOR_L);
            jButton_3.setForeground(Colors.PRIMARY_COLOR_L);
            jPanel_Button.setBackground(Colors.PRIMARY_COLOR_L);
            jPanel_Container.setBackground(Colors.PRIMARY_COLOR_L);
        }
    }

    private void doAction() {
//        jMenuItem_Back_To_Main_Menu.addActionListener(new BackToMainMenu());
        jMenuItem_Back_To_Main_Menu.addActionListener(new BackToMainMenu());
        jMenuItem_Sound_On.addActionListener(new MenuController(this));
        jMenuItem_Sound_Off.addActionListener(new MenuController(this));
        jButton_1.addMouseListener(new MenuController(this));
        jButton_2.addMouseListener(new MenuController(this));
        jButton_3.addMouseListener(new MenuController(this));
    }

    public void setHoverButton(JButton jButton) {
        jButton.setBackground(Colors.TEXT_COLOR_L_HOVER);
    }

    public void setUnHoverButton(JButton jButton) {
        jButton.setBackground(Colors.TEXT_COLOR_L);
    }

    @Override
    public void setHoverUsername(boolean isInside, String mode) {
    }

    @Override
    public void setHoverPassword(boolean isInside, String mode) {
    }

    @Override
    public void setHoverConfirmPassword(boolean isInside, String mode) {
    }

    @Override
    public void setHoverButton(boolean isInside, String mode) {

    }

    @Override
    public void setHoverButton(boolean isInside, String mode, JButton button) {
        if (isInside) {
            if (mode.equals("light")) {
                button.setBackground(Colors.TEXT_COLOR_L_HOVER);
            } else {
                button.setBackground(Colors.TEXT_COLOR_D_HOVER);
            }
        } else {
            if (mode.equals("light")) {
                button.setBackground(Colors.TEXT_COLOR_L);
            } else {
                button.setBackground(Colors.TEXT_COLOR_D);
            }
        }
    }

    @Override
    public void setHoverOther(boolean isInside) {
    }

    public void setAudio(boolean msg) {
        if (!msg) {
            AudioHandler.path = false;
            System.out.println("path update nek: " + AudioHandler.path);
            System.out.println("set audio nhan gia tri null: " + msg);
        } else {
            AudioHandler.path = true;
            System.out.println("set audio nhan gia tri: " + msg);
        }
    }

    public boolean isActiveMenu(boolean value) {
        return isActive = value;
    }

    public non-sealed class MenuModern extends JFrame implements ActionListener, ToggleHandler, HoverHandler {
        //6 button: No Maze, Box, Tunnel, Mill, Rails, Apartment
        public JPanel jPanel_Container = new JPanel();
        public JPanel jPanel_Menu_Modern = new JPanel(new GridLayout(6, 1, 30, 20));
        public JLabel jLabel_Title = new JLabel("Snake Game");
        public JButton jButton_4 = new JButton("No Maze");
        public JButton jButton_5 = new JButton("Box");
        public JButton jButton_6 = new JButton("Tunnel");
        public JButton jButton_7 = new JButton("Mill");
        public JButton jButton_8 = new JButton("Rails");
        public JButton jButton_9 = new JButton("Apartment");

        public MenuModern() {
            setTitle("Modern Menu");
            setSize(Sizes.WIDTH_SCORE_FORM, Sizes.HEIGHT_MY_FRAME);
            setIconImage(Images.icon);
            setLocationRelativeTo(null);
            setResizable(false);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(false);
            initUIMenuModern();
        }

        private void initUIMenuModern() {
            initLabel();
            initButtonMenuModern();
            initContainerMenuModern();
            changeColorBaseOnToggle();
            doActionMenuModern();
        }

        private void initLabel() {
            jLabel_Title.setFont(Fonts.RIGHT_TITLE);
            jLabel_Title.setBackground(Colors.PRIMARY_COLOR_L);
            jLabel_Title.setForeground(Colors.TEXT_COLOR_L);
            jLabel_Title.setBorder(Borders.TITLE);
            jLabel_Title.setHorizontalAlignment(SwingConstants.CENTER);
        }

        private void initButtonMenuModern() {
            jButton_4.setFont(Fonts.BUTTON);
            jButton_5.setFont(Fonts.BUTTON);
            jButton_6.setFont(Fonts.BUTTON);
            jButton_7.setFont(Fonts.BUTTON);
            jButton_8.setFont(Fonts.BUTTON);
            jButton_9.setFont(Fonts.BUTTON);
            jButton_4.setBackground(Colors.TEXT_COLOR_L);
            jButton_4.setForeground(Colors.PRIMARY_COLOR_L);
            jButton_5.setBackground(Colors.TEXT_COLOR_L);
            jButton_5.setForeground(Colors.PRIMARY_COLOR_L);
            jButton_6.setBackground(Colors.TEXT_COLOR_L);
            jButton_6.setForeground(Colors.PRIMARY_COLOR_L);
            jButton_7.setBackground(Colors.TEXT_COLOR_L);
            jButton_7.setForeground(Colors.PRIMARY_COLOR_L);
            jButton_8.setBackground(Colors.TEXT_COLOR_L);
            jButton_8.setForeground(Colors.PRIMARY_COLOR_L);
            jButton_9.setBackground(Colors.TEXT_COLOR_L);
            jButton_9.setForeground(Colors.PRIMARY_COLOR_L);
            jPanel_Menu_Modern.add(jButton_4);
            jPanel_Menu_Modern.add(jButton_5);
            jPanel_Menu_Modern.add(jButton_6);
            jPanel_Menu_Modern.add(jButton_7);
            jPanel_Menu_Modern.add(jButton_8);
            jPanel_Menu_Modern.add(jButton_9);
        }

        private void initContainerMenuModern() {
            jPanel_Menu_Modern.setBorder(Borders.TITLE);
            jPanel_Menu_Modern.setBackground(Colors.PRIMARY_COLOR_L);
            jPanel_Container.setBackground(Colors.PRIMARY_COLOR_L);
            jPanel_Container.setLayout(new BorderLayout());
            jPanel_Container.add(jLabel_Title, BorderLayout.NORTH);
            jPanel_Container.add(jPanel_Menu_Modern, BorderLayout.CENTER);
            add(jPanel_Container);
        }

        private void doActionMenuModern() {
            jButton_4.addMouseListener(new MenuController.MenuModernController(this));
            jButton_5.addMouseListener(new MenuController.MenuModernController(this));
            jButton_6.addMouseListener(new MenuController.MenuModernController(this));
            jButton_7.addMouseListener(new MenuController.MenuModernController(this));
            jButton_8.addMouseListener(new MenuController.MenuModernController(this));
            jButton_9.addMouseListener(new MenuController.MenuModernController(this));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "No Maze") {
                System.out.println("No Maze");
            }
            if (e.getActionCommand() == "Box") {
                System.out.println("Box");
            }
            if (e.getActionCommand() == "Tunnel") {
                System.out.println("Tunnel");
            }
            if (e.getActionCommand() == "Mill") {
                System.out.println("Mill");
            }
            if (e.getActionCommand() == "Rails") {
                System.out.println("Rails");
            }
            if (e.getActionCommand() == "Apartment") {
                System.out.println("Apartment");
            }
        }

        @Override
        public void setHoverUsername(boolean isInside, String mode) {

        }

        @Override
        public void setHoverPassword(boolean isInside, String mode) {

        }

        @Override
        public void setHoverConfirmPassword(boolean isInside, String mode) {

        }

        @Override
        public void setHoverButton(boolean isInside, String mode) {

        }

        @Override
        public void setHoverButton(boolean isInside, String mode, JButton button) {
            if (isInside) {
                if (mode.equals("light")) {
                    button.setBackground(Colors.TEXT_COLOR_L_HOVER);
                } else {
                    button.setBackground(Colors.TEXT_COLOR_D_HOVER);
                }
            } else {
                if (mode.equals("light")) {
                    button.setBackground(Colors.TEXT_COLOR_L);
                } else {
                    button.setBackground(Colors.TEXT_COLOR_D);
                }
            }
        }

        @Override
        public void setHoverOther(boolean isInside) {

        }

        @Override
        public void changeColorBaseOnToggle() {
            if (MenuView.this.getStatusToggle()) {
                jLabel_Title.setBackground(Colors.PRIMARY_COLOR_D);
                jLabel_Title.setForeground(Colors.TEXT_COLOR_D);
                jButton_4.setBackground(Colors.TEXT_COLOR_D);
                jButton_4.setForeground(Colors.PRIMARY_COLOR_D);
                jButton_5.setBackground(Colors.TEXT_COLOR_D);
                jButton_5.setForeground(Colors.PRIMARY_COLOR_D);
                jButton_6.setBackground(Colors.TEXT_COLOR_D);
                jButton_6.setForeground(Colors.PRIMARY_COLOR_D);
                jButton_7.setBackground(Colors.TEXT_COLOR_D);
                jButton_7.setForeground(Colors.PRIMARY_COLOR_D);
                jButton_8.setBackground(Colors.TEXT_COLOR_D);
                jButton_8.setForeground(Colors.PRIMARY_COLOR_D);
                jButton_9.setBackground(Colors.TEXT_COLOR_D);
                jButton_9.setForeground(Colors.PRIMARY_COLOR_D);
                jPanel_Menu_Modern.setBackground(Colors.PRIMARY_COLOR_D);
                jPanel_Container.setBackground(Colors.PRIMARY_COLOR_D);
            } else {
                jLabel_Title.setBackground(Colors.PRIMARY_COLOR_L);
                jLabel_Title.setForeground(Colors.TEXT_COLOR_L);
                jButton_4.setBackground(Colors.TEXT_COLOR_L);
                jButton_4.setForeground(Colors.PRIMARY_COLOR_L);
                jButton_5.setBackground(Colors.TEXT_COLOR_L);
                jButton_5.setForeground(Colors.PRIMARY_COLOR_L);
                jButton_6.setBackground(Colors.TEXT_COLOR_L);
                jButton_6.setForeground(Colors.PRIMARY_COLOR_L);
                jButton_7.setBackground(Colors.TEXT_COLOR_L);
                jButton_7.setForeground(Colors.PRIMARY_COLOR_L);
                jButton_8.setBackground(Colors.TEXT_COLOR_L);
                jButton_8.setForeground(Colors.PRIMARY_COLOR_L);
                jButton_9.setBackground(Colors.TEXT_COLOR_L);
                jButton_9.setForeground(Colors.PRIMARY_COLOR_L);
                jPanel_Menu_Modern.setBackground(Colors.PRIMARY_COLOR_L);
                jPanel_Container.setBackground(Colors.PRIMARY_COLOR_L);
            }
        }
    }

    private class BackToMainMenu implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            EventQueue.invokeLater(() -> {
                dispose();
                System.out.println("Back to main menu");
                toggleButton.setSelected(false);
                new LoginView().setVisible(true);
            });
        }
    }
}


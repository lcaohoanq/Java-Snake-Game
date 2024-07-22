package views;

import controllers.MenuController;
import controllers.ToggleHandler;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import modules.sound.AudioHandler;
import styles.UIBorders;
import styles.UIColors;
import styles.UIHovers;
import styles.UIImages;
import styles.UISizes;

public non-sealed class MenuView extends AppComponent implements ToggleHandler, HoverHandler {
    boolean isActive;
    private UIHovers<MenuView> uiHovers;
    public MenuView() {
        setTitle("Snake Game");
        setSize(UISizes.HEIGHT_MY_FRAME, UISizes.HEIGHT_MY_FRAME);
        setIconImage(UIImages.icon);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
        this.uiHovers = new UIHovers<>(this);
    }

    @Override
    public void initComponents() {

    }

    private void initUI() {
        initMenu();
        initButton();
        initContainer();
        changeColorBaseOnToggle();
        doAction();
    }

    private void initMenu() {
        jMenu_Back_To_Main_Menu.add(jMenuItem_Back_To_Main_Menu);
        jMenu_Sound.add(jMenuItem_Sound_On);
        jMenu_Sound.add(jMenuItem_Sound_Off);

        jMenuBar_MenuView = new JMenuBar();

        jMenuBar_MenuView.setLayout(new FlowLayout(FlowLayout.LEFT));
        jMenuBar_MenuView.setBorder(UIBorders.LINE_MENU_BAR);
        jMenuBar_MenuView.add(jMenu_Back_To_Main_Menu);
        jMenuBar_MenuView.add(jMenu_Sound);
//        jMenu.add(jMenu_Sound);
        this.setJMenuBar(jMenuBar_MenuView);
    }

    private void initButton() {
        jPanel_Button_MenuView.add(jButton_Mode_Classic);
        jPanel_Button_MenuView.add(jButton_Mode_Modern);
        jPanel_Button_MenuView.add(jButton_Mode_Campaign);
    }

    private void initContainer() {
        jPanel_Container_MenuView.add(jLabel_Title_MenuView, BorderLayout.NORTH);
        jPanel_Container_MenuView.add(jPanel_Button_MenuView, BorderLayout.CENTER);
        add(jPanel_Container_MenuView);
    }

    @Override
    public void changeColorBaseOnToggle() {
        //if toggle is on, change color to dark
        if (getStatusToggle()) {
            jLabel_Title_MenuView.setForeground(UIColors.TEXT_COLOR_D);
            jButton_Mode_Classic.setBackground(UIColors.TEXT_COLOR_D);
            jButton_Mode_Classic.setForeground(UIColors.PRIMARY_COLOR_D);
            jButton_Mode_Modern.setBackground(UIColors.TEXT_COLOR_D);
            jButton_Mode_Modern.setForeground(UIColors.PRIMARY_COLOR_D);
            jButton_Mode_Campaign.setBackground(UIColors.TEXT_COLOR_D);
            jButton_Mode_Campaign.setForeground(UIColors.PRIMARY_COLOR_D);
            jPanel_Button_MenuView.setBackground(UIColors.PRIMARY_COLOR_D);
            jPanel_Container_MenuView.setBackground(UIColors.PRIMARY_COLOR_D);
        } else {
            jLabel_Title_MenuView.setForeground(UIColors.TEXT_COLOR_L);
            jButton_Mode_Classic.setBackground(UIColors.TEXT_COLOR_L);
            jButton_Mode_Classic.setForeground(UIColors.PRIMARY_COLOR_L);
            jButton_Mode_Modern.setBackground(UIColors.TEXT_COLOR_L);
            jButton_Mode_Modern.setForeground(UIColors.PRIMARY_COLOR_L);
            jButton_Mode_Campaign.setBackground(UIColors.TEXT_COLOR_L);
            jButton_Mode_Campaign.setForeground(UIColors.PRIMARY_COLOR_L);
            jPanel_Button_MenuView.setBackground(UIColors.PRIMARY_COLOR_L);
            jPanel_Container_MenuView.setBackground(UIColors.PRIMARY_COLOR_L);
        }
    }

    @Override
    public void doAction() {
//        jMenuItem_Back_To_Main_Menu.addActionListener(new BackToMainMenu());
        jMenuItem_Back_To_Main_Menu.addActionListener(new BackToMainMenu());
        jMenuItem_Sound_On.addActionListener(new MenuController(this));
        jMenuItem_Sound_Off.addActionListener(new MenuController(this));
        jButton_Mode_Classic.addMouseListener(new MenuController(this));
        jButton_Mode_Modern.addMouseListener(new MenuController(this));
        jButton_Mode_Campaign.addMouseListener(new MenuController(this));
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

    public non-sealed class MenuModern extends AppComponent implements ActionListener, ToggleHandler, HoverHandler {

        public MenuModern() {
            setTitle("Modern Menu");
            setSize(UISizes.WIDTH_SCORE_FORM, UISizes.HEIGHT_MY_FRAME);
            setIconImage(UIImages.icon);
            setLocationRelativeTo(null);
            setResizable(false);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(false);
            initComponents();
            doAction();
        }

        @Override
        public void doAction() {
            jButton_NoMaze.addMouseListener(new MenuController.MenuModernController(this));
            jButton_Box.addMouseListener(new MenuController.MenuModernController(this));
            jButton_Tunnel.addMouseListener(new MenuController.MenuModernController(this));
            jButton_Mill.addMouseListener(new MenuController.MenuModernController(this));
            jButton_Rails.addMouseListener(new MenuController.MenuModernController(this));
            jButton_Apartment.addMouseListener(new MenuController.MenuModernController(this));
        }

        @Override
        public void initComponents() {
            initButtonMenuModern();
            initContainerMenuModern();
            changeColorBaseOnToggle();
        }

        private void initButtonMenuModern() {
            jPanel_Menu_Modern.add(jButton_NoMaze);
            jPanel_Menu_Modern.add(jButton_Box);
            jPanel_Menu_Modern.add(jButton_Tunnel);
            jPanel_Menu_Modern.add(jButton_Mill);
            jPanel_Menu_Modern.add(jButton_Rails);
            jPanel_Menu_Modern.add(jButton_Apartment);
        }

        private void initContainerMenuModern() {
            jPanel_Container_MenuModern.setLayout(new BorderLayout());
            jPanel_Container_MenuModern.add(jLabel_Title_Modern, BorderLayout.NORTH);
            jPanel_Container_MenuModern.add(jPanel_Menu_Modern, BorderLayout.CENTER);
            add(jPanel_Container_MenuModern);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (Objects.equals(e.getActionCommand(), "No Maze")) {
                System.out.println("No Maze");
            }
            if (Objects.equals(e.getActionCommand(), "Box")) {
                System.out.println("Box");
            }
            if (Objects.equals(e.getActionCommand(), "Tunnel")) {
                System.out.println("Tunnel");
            }
            if (Objects.equals(e.getActionCommand(), "Mill")) {
                System.out.println("Mill");
            }
            if (Objects.equals(e.getActionCommand(), "Rails")) {
                System.out.println("Rails");
            }
            if (Objects.equals(e.getActionCommand(), "Apartment")) {
                System.out.println("Apartment");
            }
        }

        @Override
        public void changeColorBaseOnToggle() {
            if (MenuView.this.getStatusToggle()) {
                jLabel_Title_Modern.setBackground(UIColors.PRIMARY_COLOR_D);
                jLabel_Title_Modern.setForeground(UIColors.TEXT_COLOR_D);
                jButton_NoMaze.setBackground(UIColors.TEXT_COLOR_D);
                jButton_NoMaze.setForeground(UIColors.PRIMARY_COLOR_D);
                jButton_Box.setBackground(UIColors.TEXT_COLOR_D);
                jButton_Box.setForeground(UIColors.PRIMARY_COLOR_D);
                jButton_Tunnel.setBackground(UIColors.TEXT_COLOR_D);
                jButton_Tunnel.setForeground(UIColors.PRIMARY_COLOR_D);
                jButton_Mill.setBackground(UIColors.TEXT_COLOR_D);
                jButton_Mill.setForeground(UIColors.PRIMARY_COLOR_D);
                jButton_Rails.setBackground(UIColors.TEXT_COLOR_D);
                jButton_Rails.setForeground(UIColors.PRIMARY_COLOR_D);
                jButton_Apartment.setBackground(UIColors.TEXT_COLOR_D);
                jButton_Apartment.setForeground(UIColors.PRIMARY_COLOR_D);
                jPanel_Menu_Modern.setBackground(UIColors.PRIMARY_COLOR_D);
                jPanel_Container_MenuModern.setBackground(UIColors.PRIMARY_COLOR_D);
            } else {
                jLabel_Title_Modern.setBackground(UIColors.PRIMARY_COLOR_L);
                jLabel_Title_Modern.setForeground(UIColors.TEXT_COLOR_L);
                jButton_NoMaze.setBackground(UIColors.TEXT_COLOR_L);
                jButton_NoMaze.setForeground(UIColors.PRIMARY_COLOR_L);
                jButton_Box.setBackground(UIColors.TEXT_COLOR_L);
                jButton_Box.setForeground(UIColors.PRIMARY_COLOR_L);
                jButton_Tunnel.setBackground(UIColors.TEXT_COLOR_L);
                jButton_Tunnel.setForeground(UIColors.PRIMARY_COLOR_L);
                jButton_Mill.setBackground(UIColors.TEXT_COLOR_L);
                jButton_Mill.setForeground(UIColors.PRIMARY_COLOR_L);
                jButton_Rails.setBackground(UIColors.TEXT_COLOR_L);
                jButton_Rails.setForeground(UIColors.PRIMARY_COLOR_L);
                jButton_Apartment.setBackground(UIColors.TEXT_COLOR_L);
                jButton_Apartment.setForeground(UIColors.PRIMARY_COLOR_L);
                jPanel_Menu_Modern.setBackground(UIColors.PRIMARY_COLOR_L);
                jPanel_Container_MenuModern.setBackground(UIColors.PRIMARY_COLOR_L);
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


package views;

import controllers.MenuController;
import controllers.ToggleHandler;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import modules.sound.AudioHandler;
import styles.UIBorders;
import styles.UIColors;
import styles.UIFonts;
import styles.UIImages;
import styles.UISizes;

public non-sealed class MenuView extends AbstractView implements ToggleHandler, HoverHandler {
    boolean isActive;
    public MenuView() {
        setTitle("Snake Game");
        setSize(UISizes.HEIGHT_MY_FRAME, UISizes.HEIGHT_MY_FRAME);
        setIconImage(UIImages.icon);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
    }

    @Override
    public void initComponents() {

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

        jMenuBar_MenuView = new JMenuBar();

        jMenuBar_MenuView.setLayout(new FlowLayout(FlowLayout.LEFT));
        jMenuBar_MenuView.setBorder(UIBorders.LINE_MENU_BAR);
        jMenuBar_MenuView.add(jMenu_Back_To_Main_Menu);
        jMenuBar_MenuView.add(jMenu_Sound);
//        jMenu.add(jMenu_Sound);
        this.setJMenuBar(jMenuBar_MenuView);
    }

    private void initTitle() {
        jLabel_Title_MenuView = new JLabel("Snake Game", JLabel.CENTER);

        jLabel_Title_MenuView.setFont(UIFonts.RIGHT_TITLE);
        jLabel_Title_MenuView.setBorder(UIBorders.CONTAINER_MENU);
    }

    private void initButton() {
        jButton_Mode_Classic = new JButton("Classic");
        jButton_Mode_Modern = new JButton("Modern");
        jButton_Mode_Campaign = new JButton("Campaign");

        jButton_Mode_Classic.setFont(UIFonts.BUTTON);
        jButton_Mode_Modern.setFont(UIFonts.BUTTON);
        jButton_Mode_Campaign.setFont(UIFonts.BUTTON);

        jPanel_Button_MenuView = new JPanel(new GridLayout(3, 1, 30, 20));

        jPanel_Button_MenuView.add(jButton_Mode_Classic);
        jPanel_Button_MenuView.add(jButton_Mode_Modern);
        jPanel_Button_MenuView.add(jButton_Mode_Campaign);
    }

    private void initContainer() {
        jPanel_Container_MenuView = new JPanel(new BorderLayout());

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
            setSize(UISizes.WIDTH_SCORE_FORM, UISizes.HEIGHT_MY_FRAME);
            setIconImage(UIImages.icon);
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
            jLabel_Title.setFont(UIFonts.RIGHT_TITLE);
            jLabel_Title.setBackground(UIColors.PRIMARY_COLOR_L);
            jLabel_Title.setForeground(UIColors.TEXT_COLOR_L);
            jLabel_Title.setBorder(UIBorders.TITLE);
            jLabel_Title.setHorizontalAlignment(SwingConstants.CENTER);
        }

        private void initButtonMenuModern() {
            jButton_4.setFont(UIFonts.BUTTON);
            jButton_5.setFont(UIFonts.BUTTON);
            jButton_6.setFont(UIFonts.BUTTON);
            jButton_7.setFont(UIFonts.BUTTON);
            jButton_8.setFont(UIFonts.BUTTON);
            jButton_9.setFont(UIFonts.BUTTON);
            jButton_4.setBackground(UIColors.TEXT_COLOR_L);
            jButton_4.setForeground(UIColors.PRIMARY_COLOR_L);
            jButton_5.setBackground(UIColors.TEXT_COLOR_L);
            jButton_5.setForeground(UIColors.PRIMARY_COLOR_L);
            jButton_6.setBackground(UIColors.TEXT_COLOR_L);
            jButton_6.setForeground(UIColors.PRIMARY_COLOR_L);
            jButton_7.setBackground(UIColors.TEXT_COLOR_L);
            jButton_7.setForeground(UIColors.PRIMARY_COLOR_L);
            jButton_8.setBackground(UIColors.TEXT_COLOR_L);
            jButton_8.setForeground(UIColors.PRIMARY_COLOR_L);
            jButton_9.setBackground(UIColors.TEXT_COLOR_L);
            jButton_9.setForeground(UIColors.PRIMARY_COLOR_L);
            jPanel_Menu_Modern.add(jButton_4);
            jPanel_Menu_Modern.add(jButton_5);
            jPanel_Menu_Modern.add(jButton_6);
            jPanel_Menu_Modern.add(jButton_7);
            jPanel_Menu_Modern.add(jButton_8);
            jPanel_Menu_Modern.add(jButton_9);
        }

        private void initContainerMenuModern() {
            jPanel_Menu_Modern.setBorder(UIBorders.TITLE);
            jPanel_Menu_Modern.setBackground(UIColors.PRIMARY_COLOR_L);
            jPanel_Container.setBackground(UIColors.PRIMARY_COLOR_L);
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

        public void setHoverButton(boolean isInside, String mode, JButton button) {
            if (isInside) {
                if (mode.equals("light")) {
                    button.setBackground(UIColors.TEXT_COLOR_L_HOVER);
                } else {
                    button.setBackground(UIColors.TEXT_COLOR_D_HOVER);
                }
            } else {
                if (mode.equals("light")) {
                    button.setBackground(UIColors.TEXT_COLOR_L);
                } else {
                    button.setBackground(UIColors.TEXT_COLOR_D);
                }
            }

        }

        @Override
        public void changeColorBaseOnToggle() {
            if (MenuView.this.getStatusToggle()) {
                jLabel_Title.setBackground(UIColors.PRIMARY_COLOR_D);
                jLabel_Title.setForeground(UIColors.TEXT_COLOR_D);
                jButton_4.setBackground(UIColors.TEXT_COLOR_D);
                jButton_4.setForeground(UIColors.PRIMARY_COLOR_D);
                jButton_5.setBackground(UIColors.TEXT_COLOR_D);
                jButton_5.setForeground(UIColors.PRIMARY_COLOR_D);
                jButton_6.setBackground(UIColors.TEXT_COLOR_D);
                jButton_6.setForeground(UIColors.PRIMARY_COLOR_D);
                jButton_7.setBackground(UIColors.TEXT_COLOR_D);
                jButton_7.setForeground(UIColors.PRIMARY_COLOR_D);
                jButton_8.setBackground(UIColors.TEXT_COLOR_D);
                jButton_8.setForeground(UIColors.PRIMARY_COLOR_D);
                jButton_9.setBackground(UIColors.TEXT_COLOR_D);
                jButton_9.setForeground(UIColors.PRIMARY_COLOR_D);
                jPanel_Menu_Modern.setBackground(UIColors.PRIMARY_COLOR_D);
                jPanel_Container.setBackground(UIColors.PRIMARY_COLOR_D);
            } else {
                jLabel_Title.setBackground(UIColors.PRIMARY_COLOR_L);
                jLabel_Title.setForeground(UIColors.TEXT_COLOR_L);
                jButton_4.setBackground(UIColors.TEXT_COLOR_L);
                jButton_4.setForeground(UIColors.PRIMARY_COLOR_L);
                jButton_5.setBackground(UIColors.TEXT_COLOR_L);
                jButton_5.setForeground(UIColors.PRIMARY_COLOR_L);
                jButton_6.setBackground(UIColors.TEXT_COLOR_L);
                jButton_6.setForeground(UIColors.PRIMARY_COLOR_L);
                jButton_7.setBackground(UIColors.TEXT_COLOR_L);
                jButton_7.setForeground(UIColors.PRIMARY_COLOR_L);
                jButton_8.setBackground(UIColors.TEXT_COLOR_L);
                jButton_8.setForeground(UIColors.PRIMARY_COLOR_L);
                jButton_9.setBackground(UIColors.TEXT_COLOR_L);
                jButton_9.setForeground(UIColors.PRIMARY_COLOR_L);
                jPanel_Menu_Modern.setBackground(UIColors.PRIMARY_COLOR_L);
                jPanel_Container.setBackground(UIColors.PRIMARY_COLOR_L);
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

    public void setHoverButton(boolean isInside, String mode, JButton button) {
        if (isInside) {
            if (mode.equals("light")) {
                button.setBackground(UIColors.TEXT_COLOR_L_HOVER);
            } else {
                button.setBackground(UIColors.TEXT_COLOR_D_HOVER);
            }
        } else {
            if (mode.equals("light")) {
                button.setBackground(UIColors.TEXT_COLOR_L);
            } else {
                button.setBackground(UIColors.TEXT_COLOR_D);
            }
        }

    }
}


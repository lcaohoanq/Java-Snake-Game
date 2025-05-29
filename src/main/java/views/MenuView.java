package views;

import controllers.MenuController;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import lombok.extern.slf4j.Slf4j;
import models.UserScore;
import styles.UIBorders;
import styles.UIColors;
import styles.UIHovers;
import styles.UIImages;
import styles.UISizes;
import utils.AudioHandler;
import views.base.AppComponent;

@Slf4j
public class MenuView extends AppComponent {
    boolean isActive;
    private UIHovers<MenuView> uiHovers;
    private MenuController controller;
    private final UserScore currentUser;

    private static MenuView instance;

    public MenuView(UserScore currentUser) {
        this.currentUser = currentUser;
        instance = this;
        setTitle("Snake Game");
        setSize(UISizes.HEIGHT_MY_FRAME, UISizes.HEIGHT_MY_FRAME);
        setIconImage(UIImages.icon);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
    }

    public static MenuView getInstance(UserScore currentUser) {
        if (instance == null) {
            instance = new MenuView(currentUser);
        }
        return instance;
    }


    @Override
    public void initComponents() {

    }

    private void initUI() {
        initMenu();
        initButton();
        initContainer();
        doAction();
    }

    private void initMenu() {
        jMenu_Back_To_Main_Menu.add(jMenuItem_Back_To_Main_Menu);
        jMenu_Sound.add(jMenuItem_Sound_On);
        jMenu_Sound.add(jMenuItem_Sound_Off);
        jMenu_Log_Out.add(jMenuItem_Log_Out);

        jMenuBar_MenuView = new JMenuBar();

        jMenuBar_MenuView.setLayout(new FlowLayout(FlowLayout.LEFT));
        jMenuBar_MenuView.setBorder(UIBorders.LINE_MENU_BAR);

        if (currentUser != null) {
            JMenu userMenu = new JMenu("User: " + currentUser.getUsername());
            userMenu.setForeground(UIColors.TEXT_COLOR_L);
            jMenuBar_MenuView.add(new JMenu("User: " + currentUser.getUsername()));
        }

        jMenuBar_MenuView.add(jMenu_Back_To_Main_Menu);
        jMenuBar_MenuView.add(jMenu_Sound);
        jMenuBar_MenuView.add(jMenu_Log_Out);
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
    public void doAction() {
//        jMenuItem_Back_To_Main_Menu.addActionListener(new BackToMainMenu());
        jMenuItem_Back_To_Main_Menu.addActionListener(new BackToMainMenu());
        jMenuItem_Log_Out.addActionListener(new LogOut());

        var controller = new MenuController(this, currentUser);

        jMenuItem_Sound_On.addActionListener(controller);
        jMenuItem_Sound_Off.addActionListener(controller);
    }

    public void setAudio(boolean msg) {
        if (!msg) {
            AudioHandler.path = false;
            log.info("path update nek: " + AudioHandler.path);
            log.info("set audio nhan gia tri null: " + msg);
        } else {
            AudioHandler.path = true;
            log.info("set audio nhan gia tri: " + msg);
        }
    }

    private class BackToMainMenu implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            EventQueue.invokeLater(() -> {
                dispose();
                log.info("Back to main menu");
                new LoginView().setVisible(true);
            });
        }
    }

    private class LogOut implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            EventQueue.invokeLater(() -> {
                dispose();
                new LoginView().setVisible(true);
            });
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MenuView menuView = new MenuView(null);
            menuView.setVisible(true);
            log.info("MenuView is visible");
        });
    }
}


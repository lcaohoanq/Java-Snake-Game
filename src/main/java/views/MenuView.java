package views;

import constants.Sizes;
import controllers.MenuController;
import models.MenuModel;
import styles.Borders;
import styles.Colors;
import styles.Fonts;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JFrame {
    public JButton jButton_1 = new JButton("Classic");
    public JButton jButton_2 = new JButton("Modern");
    public JButton jButton_3 = new JButton("Campaign");
    public JButton jButton_4 = new JButton("4");
    public JButton jButton_5 = new JButton("5");
    public JButton jButton_6 = new JButton("6");
    public JButton jButton_7 = new JButton("7");
    public JButton jButton_8 = new JButton("8");
    public JButton jButton_9 = new JButton("9");
    JPanel jPanel_Container;
    JLabel jLabel_Title;
    JPanel jPanel_Button = new JPanel(new GridLayout(3, 3, 30, 20));
    private MenuModel menuModel;

    public MenuView() {
        menuModel = new MenuModel();
        setTitle("Snake Game");
        setSize(Sizes.WIDTH_MY_FRAME, Sizes.HEIGHT_MY_FRAME);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        jLabel_Title = new JLabel("Snake Game", JLabel.CENTER);
        jLabel_Title.setFont(Fonts.RIGHT_TITLE);
        jLabel_Title.setForeground(Colors.TEXT_COLOR_L);
        jLabel_Title.setBorder(Borders.CONTAINER_MENU);

        jButton_1.setBackground(Colors.TEXT_COLOR_L);
        jButton_1.setForeground(Colors.PRIMARY_COLOR_L);
        jButton_1.setFont(Fonts.BUTTON);
        jButton_2.setBackground(Colors.TEXT_COLOR_L);
        jButton_2.setForeground(Colors.PRIMARY_COLOR_L);
        jButton_2.setFont(Fonts.BUTTON);
        jButton_3.setBackground(Colors.TEXT_COLOR_L);
        jButton_3.setForeground(Colors.PRIMARY_COLOR_L);
        jButton_3.setFont(Fonts.BUTTON);
        jButton_4.setBackground(Colors.TEXT_COLOR_L);
        jButton_4.setForeground(Colors.PRIMARY_COLOR_L);
        jButton_4.setFont(Fonts.BUTTON);
        jButton_5.setBackground(Colors.TEXT_COLOR_L);
        jButton_5.setForeground(Colors.PRIMARY_COLOR_L);
        jButton_5.setFont(Fonts.BUTTON);
        jButton_6.setBackground(Colors.TEXT_COLOR_L);
        jButton_6.setForeground(Colors.PRIMARY_COLOR_L);
        jButton_6.setFont(Fonts.BUTTON);
        jButton_7.setBackground(Colors.TEXT_COLOR_L);
        jButton_7.setForeground(Colors.PRIMARY_COLOR_L);
        jButton_7.setFont(Fonts.BUTTON);
        jButton_8.setBackground(Colors.TEXT_COLOR_L);
        jButton_8.setForeground(Colors.PRIMARY_COLOR_L);
        jButton_8.setFont(Fonts.BUTTON);
        jButton_9.setBackground(Colors.TEXT_COLOR_L);
        jButton_9.setForeground(Colors.PRIMARY_COLOR_L);
        jButton_9.setFont(Fonts.BUTTON);

        jButton_1.addMouseListener(new MenuController(this));
        jButton_2.addMouseListener(new MenuController(this));
        jButton_3.addMouseListener(new MenuController(this));
        jButton_4.addMouseListener(new MenuController(this));
        jButton_5.addMouseListener(new MenuController(this));
        jButton_7.addMouseListener(new MenuController(this));
        jButton_8.addMouseListener(new MenuController(this));
        jButton_6.addMouseListener(new MenuController(this));
        jButton_9.addMouseListener(new MenuController(this));

        jPanel_Button.setBackground(Colors.PRIMARY_COLOR_L);
        jPanel_Button.add(jButton_1);
        jPanel_Button.add(jButton_2);
        jPanel_Button.add(jButton_3);
        jPanel_Button.add(jButton_4);
        jPanel_Button.add(jButton_5);
        jPanel_Button.add(jButton_6);
        jPanel_Button.add(jButton_7);
        jPanel_Button.add(jButton_8);
        jPanel_Button.add(jButton_9);

        jPanel_Container = new JPanel(new BorderLayout());
        jPanel_Container.setBackground(Colors.PRIMARY_COLOR_L);
        jPanel_Container.add(jLabel_Title, BorderLayout.NORTH);
        jPanel_Container.add(jPanel_Button, BorderLayout.CENTER);
        add(jPanel_Container);
    }

    public void setHoverButton(JButton jButton) {
        jButton.setBackground(Colors.TEXT_COLOR_L_HOVER);
    }

    public void setUnHoverButton(JButton jButton) {
        jButton.setBackground(Colors.TEXT_COLOR_L);
    }

}

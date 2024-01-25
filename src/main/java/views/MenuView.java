package views;

import constants.Paths;
import constants.Sizes;
import styles.Borders;
import styles.Colors;
import styles.Fonts;
import utils.AudioHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuView extends JFrame implements MouseListener, ActionListener {
    JPanel jPanel_Container;
    JLabel jLabel_Title;

    JPanel jPanel_Button = new JPanel(new GridLayout(3, 3, 30, 20));
    JButton jButton_1 = new JButton("1");
    JButton jButton_2 = new JButton("2");
    JButton jButton_3 = new JButton("3");
    JButton jButton_4 = new JButton("4");
    JButton jButton_5 = new JButton("5");
    JButton jButton_6 = new JButton("6");
    JButton jButton_7 = new JButton("7");
    JButton jButton_8 = new JButton("8");
    JButton jButton_9 = new JButton("9");

    public MenuView() {
        setTitle("Snake Game");
        setSize(Sizes.WIDTH_MY_FRAME, Sizes.HEIGHT_MY_FRAME);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MenuView menuView = new MenuView();
            menuView.setVisible(true);
        });
    }

    private void initUI() {
        jLabel_Title = new JLabel("Snake Game", JLabel.CENTER);
        jLabel_Title.setFont(Fonts.RIGHT_TITLE);
        jLabel_Title.setForeground(Colors.TEXT_COLOR);
        jLabel_Title.setBorder(Borders.CONTAINER_MENU);

        jButton_1.setBackground(Colors.TEXT_COLOR);
        jButton_1.setForeground(Colors.PRIMARY_COLOR);
        jButton_1.setFont(Fonts.BUTTON);
        jButton_2.setBackground(Colors.TEXT_COLOR);
        jButton_2.setForeground(Colors.PRIMARY_COLOR);
        jButton_2.setFont(Fonts.BUTTON);
        jButton_3.setBackground(Colors.TEXT_COLOR);
        jButton_3.setForeground(Colors.PRIMARY_COLOR);
        jButton_3.setFont(Fonts.BUTTON);
        jButton_4.setBackground(Colors.TEXT_COLOR);
        jButton_4.setForeground(Colors.PRIMARY_COLOR);
        jButton_4.setFont(Fonts.BUTTON);
        jButton_5.setBackground(Colors.TEXT_COLOR);
        jButton_5.setForeground(Colors.PRIMARY_COLOR);
        jButton_5.setFont(Fonts.BUTTON);
        jButton_6.setBackground(Colors.TEXT_COLOR);
        jButton_6.setForeground(Colors.PRIMARY_COLOR);
        jButton_6.setFont(Fonts.BUTTON);
        jButton_7.setBackground(Colors.TEXT_COLOR);
        jButton_7.setForeground(Colors.PRIMARY_COLOR);
        jButton_7.setFont(Fonts.BUTTON);
        jButton_8.setBackground(Colors.TEXT_COLOR);
        jButton_8.setForeground(Colors.PRIMARY_COLOR);
        jButton_8.setFont(Fonts.BUTTON);
        jButton_9.setBackground(Colors.TEXT_COLOR);
        jButton_9.setForeground(Colors.PRIMARY_COLOR);
        jButton_9.setFont(Fonts.BUTTON);

        jButton_1.addMouseListener(this);
        jButton_2.addMouseListener(this);
        jButton_3.addMouseListener(this);
        jButton_4.addMouseListener(this);
        jButton_5.addMouseListener(this);
        jButton_6.addMouseListener(this);
        jButton_7.addMouseListener(this);
        jButton_8.addMouseListener(this);
        jButton_9.addMouseListener(this);

        jButton_1.addActionListener(this);
        jButton_2.addActionListener(this);
        jButton_3.addActionListener(this);
        jButton_4.addActionListener(this);
        jButton_5.addActionListener(this);
        jButton_6.addActionListener(this);
        jButton_7.addActionListener(this);
        jButton_8.addActionListener(this);
        jButton_9.addActionListener(this);

        jPanel_Button.setBackground(Colors.PRIMARY_COLOR);
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
        jPanel_Container.setBackground(Colors.PRIMARY_COLOR);
        jPanel_Container.add(jLabel_Title, BorderLayout.NORTH);
        jPanel_Container.add(jPanel_Button, BorderLayout.CENTER);
        add(jPanel_Container);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AudioHandler.playAudio(Paths.URL_EATING);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == jButton_1) {
            jButton_1.setBackground(Colors.TEXT_COLOR_HOVER);
        }
        if (e.getSource() == jButton_2) {
            jButton_2.setBackground(Colors.TEXT_COLOR_HOVER);
        }
        if (e.getSource() == jButton_3) {
            jButton_3.setBackground(Colors.TEXT_COLOR_HOVER);
        }
        if (e.getSource() == jButton_4) {
            jButton_4.setBackground(Colors.TEXT_COLOR_HOVER);
        }
        if (e.getSource() == jButton_5) {
            jButton_5.setBackground(Colors.TEXT_COLOR_HOVER);
        }
        if (e.getSource() == jButton_6) {
            jButton_6.setBackground(Colors.TEXT_COLOR_HOVER);
        }
        if (e.getSource() == jButton_7) {
            jButton_7.setBackground(Colors.TEXT_COLOR_HOVER);
        }
        if (e.getSource() == jButton_8) {
            jButton_8.setBackground(Colors.TEXT_COLOR_HOVER);
        }
        if (e.getSource() == jButton_9) {
            jButton_9.setBackground(Colors.TEXT_COLOR_HOVER);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == jButton_1) {
            jButton_1.setBackground(Colors.TEXT_COLOR);
        }
        if (e.getSource() == jButton_2) {
            jButton_2.setBackground(Colors.TEXT_COLOR);
        }
        if (e.getSource() == jButton_3) {
            jButton_3.setBackground(Colors.TEXT_COLOR);
        }
        if (e.getSource() == jButton_4) {
            jButton_4.setBackground(Colors.TEXT_COLOR);
        }
        if (e.getSource() == jButton_5) {
            jButton_5.setBackground(Colors.TEXT_COLOR);
        }
        if (e.getSource() == jButton_6) {
            jButton_6.setBackground(Colors.TEXT_COLOR);
        }
        if (e.getSource() == jButton_7) {
            jButton_7.setBackground(Colors.TEXT_COLOR);
        }
        if (e.getSource() == jButton_8) {
            jButton_8.setBackground(Colors.TEXT_COLOR);
        }
        if (e.getSource() == jButton_9) {
            jButton_9.setBackground(Colors.TEXT_COLOR);
        }
    }
}

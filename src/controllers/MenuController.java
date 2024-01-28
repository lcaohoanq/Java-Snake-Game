package controllers;

import constants.Messages;
import constants.Paths;
import utils.AudioHandler;
import views.MenuView;
import views.Snake;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.w3c.dom.events.Event;

public class MenuController implements ActionListener {

    private MenuView menuView;

    public MenuController(MenuView menuView) {
        this.menuView = menuView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuView.jButton_1) {
            EventQueue.invokeLater(() -> {
                Snake snake = new Snake("NoMaze");
                snake.startGame();
            });
        }
        if (e.getSource() == menuView.jButton_2) {
            EventQueue.invokeLater(() -> {
                Snake snake = new Snake("Box");
                snake.startGame();
            });
        }
        if (e.getSource() == menuView.jButton_3) {
            EventQueue.invokeLater(() -> {
                Snake snake = new Snake("Tunnel");
                snake.startGame();
            });
        }
    }
}

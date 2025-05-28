package controllers;

import lombok.extern.slf4j.Slf4j;
import models.UserScore;
import views.MenuView;
import views.LoginView;
import views.game.Snake;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Slf4j
public class PlayController implements ActionListener {

    private final Object source;
    private final UserScore currentUser;

    // Constructor for login to menu transition
    public PlayController(LoginView loginView, UserScore user) {
        this.source = loginView;
        this.currentUser = user;
        log.info("PlayController created with user: " + 
                          (user != null ? user.getUsername() : "null"));
    }

    // Constructor for Snake game
    public PlayController(Snake snake) {
        this.source = snake;
        this.currentUser = snake.getCurrentUser();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (source instanceof LoginView) {
            // From login view to menu
            ((LoginView)source).dispose();
            
            // Create menu and pass the user
            MenuView menuView = new MenuView();
            MenuController menuController = new MenuController(menuView, currentUser);
            log.info("Navigating to MenuView with user: " + 
                              (currentUser != null ? currentUser.getUsername() : "null"));
            menuView.setVisible(true);
        } 
        else if (source instanceof Snake) {
            // Back to menu from game
            ((Snake)source).dispose();
            MenuView menuView = new MenuView();
            MenuController menuController = new MenuController(menuView, currentUser);
            menuView.setVisible(true);
        }
    }
}

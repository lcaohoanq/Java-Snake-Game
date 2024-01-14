package controllers;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import views.LoginView;
import views.Snake;

public class PlayController implements ActionListener {
  private LoginView loginView;

  public PlayController(LoginView loginView) {
    this.loginView = loginView;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    loginView.dispose();
    EventQueue.invokeLater(() -> {
      Snake snake = new Snake();
      snake.startGame();
    });
  }
}

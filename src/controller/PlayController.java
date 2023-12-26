package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.LoginFormView;
import view.Snake;

public class PlayController implements ActionListener {
  private LoginFormView loginFormView;
  public PlayController(LoginFormView loginFormView) {
    this.loginFormView = loginFormView;
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    loginFormView.dispose();
    EventQueue.invokeLater(() -> {
      Snake snake = new Snake();
      snake.startGame();
    });
  }
}

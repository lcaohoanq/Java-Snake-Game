package controllers;

import modules.user.UserDAO;
import views.ScoreView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreController implements ActionListener {

    private final ScoreView scoreView;
    private final UserDAO executeQuery = UserDAO.getInstance();

    public ScoreController(ScoreView scoreView) {
        this.scoreView = scoreView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        scoreView.clearTextArea();
        scoreView.setVisible(true);
        String tmp = executeQuery.selectFirstNameAndScore().toString().substring(1, executeQuery.selectFirstNameAndScore().toString().length() - 1).replaceAll(", ", "\n");
        scoreView.appendTextArea(tmp);
    }
}

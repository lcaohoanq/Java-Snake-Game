package controllers;

import services.DatabaseQuery;
import views.ScoreView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreController implements ActionListener {

    private ScoreView scoreView;
    private DatabaseQuery executeQuery = DatabaseQuery.getInstance();

    public ScoreController(ScoreView scoreView) {
        this.scoreView = scoreView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        scoreView.clearTextArea();
        scoreView.setVisible(true);
        String tmp = executeQuery.selectUsernameAndScore().toString().substring(1, executeQuery.selectUsernameAndScore().toString().length() - 1).replaceAll(", ", "\n");
        scoreView.appendTextArea(tmp);
    }
}

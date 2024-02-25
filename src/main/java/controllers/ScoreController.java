package controllers;

import services.DBServices;
import views.ScoreView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreController implements ActionListener {

    private ScoreView scoreView;

    public ScoreController(ScoreView scoreView) {
        this.scoreView = scoreView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        scoreView.clearTextArea();
        scoreView.setVisible(true);
        String tmp = DBServices.selectUsernameAndScore().toString().substring(1, DBServices.selectUsernameAndScore().toString().length() - 1).replaceAll(", ", "\n");
        scoreView.appendTextArea(tmp);
    }
}

package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import views.ScoreView;

public class ScoreController implements ActionListener {

    private final ScoreView scoreView;

    public ScoreController(ScoreView scoreView) {
        this.scoreView = scoreView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        scoreView.clearTextArea();
        scoreView.setVisible(true);
    }

}

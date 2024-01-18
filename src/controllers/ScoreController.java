package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import utils.DataHandler;
import views.ScoreView;

public class ScoreController implements ActionListener {

    private DataHandler dataHandler;
    private ScoreView scoreView;

    public ScoreController(ScoreView scoreView) {
        this.dataHandler = new DataHandler();
        this.scoreView = scoreView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        scoreView.setVisible(true);
        ScoreView.jTextArea_Score.append(DataHandler.scoreList.toString() + "\n");
        System.out.println("Current Infor: " + DataHandler.scoreList.toString());
    }

}

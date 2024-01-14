package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import utils.DataHandler;
import views.ScoreFormView;

public class ScoreController implements ActionListener {

    private DataHandler dataHandler;
    private ScoreFormView scoreFormView;

    public ScoreController(ScoreFormView scoreFormView) {
        this.dataHandler = new DataHandler();
        this.scoreFormView = scoreFormView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        scoreFormView.setVisible(true);
        ScoreFormView.jTextArea_Score.append(DataHandler.scoreList.toString() + "\n");
        System.out.println("Current Infor: " + DataHandler.scoreList.toString());
    }

}

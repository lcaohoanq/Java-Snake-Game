package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import utils.DataHandler;
import views.ScoreFormView;

public class ScoreFormController implements ActionListener {

    private DataHandler dataHandler;
    private ScoreFormView scoreFormView;

    public ScoreFormController(ScoreFormView scoreFormView) {
        this.dataHandler = dataHandler;
        this.scoreFormView = scoreFormView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        scoreFormView.setVisible(true);
        ScoreFormView.jTextArea_Score.append(DataHandler.scoreList.toString() + "\n");
        System.out.println("Current Infor: " + DataHandler.scoreList.toString());
    }

}

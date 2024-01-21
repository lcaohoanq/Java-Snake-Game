package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import services.DBServices;
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
        String tmp = DBServices.selectUsernameAndScore().toString().substring(1, DBServices.selectUsernameAndScore().toString().length() - 1).replaceAll(", ", "\n");
        //string tokenization
        ScoreView.jTextArea_Score.append(tmp);
        System.out.println("Current Infor: " + (DataHandler.scoreList.toString()));
    }

}

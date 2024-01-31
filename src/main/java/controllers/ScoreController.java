package controllers;

import constants.Regex;
import models.data.DataHandler;
import views.ScoreView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScoreController implements ActionListener {
    String origin;
    String result;
    private ScoreView scoreView;

    public ScoreController(ScoreView scoreView) {
        this.scoreView = scoreView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        scoreView.clearTextArea();
        scoreView.setVisible(true);
        origin = DataHandler.scoreList.toString().substring(1, DataHandler.scoreList.toString().length() - 1);
        result = "";
        Matcher m = Pattern.compile(Regex.SCORE).matcher(origin);
        int count = 0;
        while (m.find()) {
            count++;
            if (count % 2 == 0) {
                result += m.group().substring(1, m.group().length()-1) + "\n"; // remove ' ' character and add new line
            } else{
                result += m.group().substring(1, m.group().length()-1) + ": ";  // remove ' ' character and add :
            }
        }
        scoreView.appendTextArea(result);
    }
}

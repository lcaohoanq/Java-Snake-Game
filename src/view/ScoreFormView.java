package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import constants.Size;
import controller.ScoreFormController;

public class ScoreFormView extends JFrame {

    JPanel jPanel_Container = new JPanel();
    public static JTextArea jTextArea_Score = new JTextArea();
    JScrollPane jScrollPane_Score = new JScrollPane(jTextArea_Score);

    private ScoreFormController scoreFormController;

    public ScoreFormView() {
        this.scoreFormController = new ScoreFormController(this);
        setTitle("Score Board");
        setSize(Size.WIDTH_SCORE_FORM, Size.HEIGHT_SCORE_FORM);

        // setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        jTextArea_Score.setWrapStyleWord(true);
        jTextArea_Score.setLineWrap(true);
        jScrollPane_Score.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane_Score.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel_Container.setLayout(new BorderLayout());
        jPanel_Container.add(jScrollPane_Score, BorderLayout.CENTER);
        this.add(jPanel_Container);
    }

}

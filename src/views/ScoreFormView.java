package views;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import constants.Size;
import controllers.ScoreFormController;
import styles.BorderHandler;
import styles.FontHandler;

public class ScoreFormView extends JFrame {

    JPanel jPanel_Container = new JPanel();
    public static JTextArea jTextArea_Score = new JTextArea();
    JScrollPane jScrollPane_Score = new JScrollPane(jTextArea_Score);
    JLabel jLabel_Title = new JLabel("SCORE BOARD", JLabel.CENTER);

    private ScoreFormController scoreFormController;

    public ScoreFormView() {
        this.scoreFormController = new ScoreFormController(this);
        setSize(Size.WIDTH_SCORE_FORM, Size.HEIGHT_SCORE_FORM);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        initTopZone();
        initMiddleZone();
        initContainer();
    }

    public void initTopZone() {
        jLabel_Title.setFont(FontHandler.FONT_TEXT_JBUTTON);
        jLabel_Title.setBorder(BorderHandler.JLABEL_BORDER);
    }

    public void initMiddleZone() {
        jTextArea_Score.setWrapStyleWord(true);
        jTextArea_Score.setLineWrap(true);
        jScrollPane_Score.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane_Score.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    public void initContainer() {
        jPanel_Container.setBorder(BorderHandler.CONTAINER_BORDER);
        jPanel_Container.setLayout(new BorderLayout());
        jPanel_Container.add(jLabel_Title, BorderLayout.NORTH);
        jPanel_Container.add(jScrollPane_Score, BorderLayout.CENTER);
        this.add(jPanel_Container);
    }

}

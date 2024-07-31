package views;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import lombok.Getter;
import styles.UISizes;
import controllers.ScoreController;
import styles.UIBorders;
import styles.UIColors;
import styles.UIFonts;

@Getter
public class ScoreView extends JFrame {

    public JTextArea jTextArea_Score = new JTextArea();
    JPanel jPanel_Container = new JPanel();
    JScrollPane jScrollPane_Score = new JScrollPane(jTextArea_Score);
    JLabel jLabel_Title = new JLabel("SCORE BOARD", JLabel.CENTER);

    private final ScoreController scoreFormController;

    public ScoreView() {
        this.scoreFormController = new ScoreController(this);
        setSize(UISizes.WIDTH_SCORE_FORM, UISizes.HEIGHT_SCORE_FORM);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        initTopZone();
        initMiddleZone();
        initContainer();
    }

    public void initTopZone() {
        jLabel_Title.setForeground(UIColors.TEXT_COLOR_L);
        jLabel_Title.setFont(UIFonts.BUTTON);
        jLabel_Title.setBorder(UIBorders.MIDDLE);
    }

    public void initMiddleZone() {
        jTextArea_Score.setWrapStyleWord(true);
        jTextArea_Score.setLineWrap(true);
        jTextArea_Score.setEditable(false);
        jTextArea_Score.setFont(UIFonts.OTHERS);
        jScrollPane_Score.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane_Score.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    public void initContainer() {
        jPanel_Container.setBorder(UIBorders.CONTAINER);
        jPanel_Container.setLayout(new BorderLayout());
        jPanel_Container.add(jLabel_Title, BorderLayout.NORTH);
        jPanel_Container.add(jScrollPane_Score, BorderLayout.CENTER);
        this.add(jPanel_Container);
    }

    public void clearTextArea() {
        jTextArea_Score.setText("");
    }

    public void appendTextArea(String text) {
        jTextArea_Score.append(text);
    }
}

package views;

import controllers.MenuModernController;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.JFrame;
import lombok.extern.slf4j.Slf4j;
import styles.UIImages;
import styles.UISizes;
import views.base.AppComponent;

@Slf4j
public class MenuModernView extends AppComponent implements ActionListener {

    public MenuModernView() {
        setTitle("Modern Menu");
        setSize(UISizes.WIDTH_SCORE_FORM, UISizes.HEIGHT_MY_FRAME);
        setIconImage(UIImages.icon);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(false);
        initComponents();
        doAction();
    }

    @Override
    public void doAction() {

    }

    @Override
    public void initComponents() {
        initButtonMenuModernView();
        initContainerMenuModernView();
    }

    private void initButtonMenuModernView() {
        jPanel_Menu_Modern.add(jButton_NoMaze);
        jPanel_Menu_Modern.add(jButton_Box);
        jPanel_Menu_Modern.add(jButton_Tunnel);
        jPanel_Menu_Modern.add(jButton_Mill);
        jPanel_Menu_Modern.add(jButton_Rails);
        jPanel_Menu_Modern.add(jButton_Apartment);
    }

    private void initContainerMenuModernView() {
        jPanel_Container_MenuModern.setLayout(new BorderLayout());
        jPanel_Container_MenuModern.add(jLabel_Title_Modern, BorderLayout.NORTH);
        jPanel_Container_MenuModern.add(jPanel_Menu_Modern, BorderLayout.CENTER);
        add(jPanel_Container_MenuModern);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(e.getActionCommand(), "No Maze")) {
            log.info("No Maze");
        }
        if (Objects.equals(e.getActionCommand(), "Box")) {
            log.info("Box");
        }
        if (Objects.equals(e.getActionCommand(), "Tunnel")) {
            log.info("Tunnel");
        }
        if (Objects.equals(e.getActionCommand(), "Mill")) {
            log.info("Mill");
        }
        if (Objects.equals(e.getActionCommand(), "Rails")) {
            log.info("Rails");
        }
        if (Objects.equals(e.getActionCommand(), "Apartment")) {
            log.info("Apartment");
        }
    }
}

package constants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import utils.UIUtils;
public class Info implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        UIUtils.IS_ABOUT_ME();
    }

}

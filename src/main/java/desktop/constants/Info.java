package desktop.constants;

import desktop.utils.UIUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Info implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        UIUtils.IS_ABOUT_ME();
    }

}

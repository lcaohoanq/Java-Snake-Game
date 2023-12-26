package constants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Info implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Message.IS_ABOUT_ME();
        Message.IS_COPYRIGHT();
    }

}

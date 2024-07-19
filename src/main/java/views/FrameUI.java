package views;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public interface FrameUI {
    JTextField getJTextField_Right_Middle_Email();
    JTextField getJTextField_Right_Middle_FirstName();
    JTextField getJTextField_Right_Middle_LastName();
    JPasswordField getJPasswordField_Right_Middle_Password();
    JButton getJButton_Right_Bottom_Submit();
    JButton getJButton_Right_Bottom_Others();
    JButton getJButton_Right_Bottom_Forgot_Password();
}

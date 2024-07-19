package views;

import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import lombok.extern.slf4j.Slf4j;
import models.RegisterModel;
import modules.user.UserDAO;
import org.jdesktop.swingx.prompt.PromptSupport;
import styles.UIBorders;
import styles.UIColors;
import styles.UIFonts;
import utils.PBKDF2;
@Slf4j
public class ChangePasswordView extends JFrame implements ActionListener {

    private JLabel newPasswordLabel;
    private JPasswordField newPasswordField;
    private JPasswordField confirmNewPasswordField;
    private JButton submitButton;
    private Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
    private JPanel topPanel, midPanel, bottomPanel;
    private String email;

    public ChangePasswordView(String email) {
        this.email = email;
        setTitle("Change Password");
        setSize(450, 250);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 1));
        initComponents();
        doAction();
    }

    private void initComponents() {
        topPanel = new JPanel();
        midPanel = new JPanel();
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
        bottomPanel = new JPanel(new FlowLayout());

        newPasswordLabel = new JLabel("Enter your new password");
        newPasswordLabel.setFont(UIFonts.getFontWithSize(UIFonts.INPUT_LARGE, 30));
        newPasswordLabel.setForeground(UIColors.TEXT_COLOR_L);

        newPasswordField = new JPasswordField(null, 10);
        newPasswordField.setFont(UIFonts.INPUT_SMALL);
        PromptSupport.setPrompt("Enter here", newPasswordField);

        confirmNewPasswordField = new JPasswordField(null, 10);
        confirmNewPasswordField.setFont(UIFonts.INPUT_SMALL);
        PromptSupport.setPrompt("Confirm your password", confirmNewPasswordField);

        submitButton = new JButton("Submit");
        submitButton.setFont(UIFonts.BUTTON);
        submitButton.setBackground(UIColors.TEXT_COLOR_L);
        submitButton.setForeground(UIColors.PRIMARY_COLOR_L);
        submitButton.setCursor(cursor);

        topPanel.setBackground(UIColors.PRIMARY_COLOR_L);
        midPanel.setBackground(UIColors.PRIMARY_COLOR_L);
        midPanel.setBorder(UIBorders.CONTAINER);
        bottomPanel.setBackground(UIColors.PRIMARY_COLOR_L);

        topPanel.add(newPasswordLabel);
        midPanel.add(newPasswordField);
        midPanel.add(confirmNewPasswordField);
        bottomPanel.add(submitButton);
        this.add(topPanel);
        this.add(midPanel);
        this.add(bottomPanel);
    }

    private void doAction() {
        submitButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {

            String newPassword = String.valueOf(newPasswordField.getPassword());
            String confirmNewPassword = String.valueOf(confirmNewPasswordField.getPassword());

            if (newPassword.isEmpty() || confirmNewPassword.isEmpty()) {
                UIPrompts.IS_EMPTY_FIELD();
            } else {
                if (newPassword.equals(confirmNewPassword)) {
                    if (new RegisterModel().isPasswordFormat(newPassword)) {
                        // update password in database
                        UserDAO.getInstance().updatePassword(email, new PBKDF2().hash(newPassword.toCharArray()));
                        UIPrompts.IS_CHANGE_PASSWORD_SUCCESS();
                        log.info("Updated password for user: {}", email);
                    } else {
                        UIPrompts.IS_WRONG_FORMAT_PASSWORD();
                    }
                } else {
                    UIPrompts.IS_NOT_MATCH_PASSWORD_AND_CONFIRM_PASSWORD();
                }
            }
        }
    }

    public static void main(String[] args) {
//        new ChangePasswordView("hoanglcse181513@fpt.edu.vn").setVisible(true);

        System.out.println(UserDAO.getInstance().selectEmailAndPasswordByEmail("hoangdz1604@gmail.com").getPassword());

        UserDAO.getInstance().updatePassword("hoangdz1604@gmail.com", "12345");

        System.out.println(UserDAO.getInstance().selectEmailAndPasswordByEmail("hoangdz1604@gmail.com").getPassword());

//        System.out.println(new PasswordHandler().authenticate("Luucaohoang0612^^".toCharArray(), UserDAO.getInstance().selectEmailAndPasswordByEmail("hoangdz1604@gmail.com").getPassword()));

    }

}

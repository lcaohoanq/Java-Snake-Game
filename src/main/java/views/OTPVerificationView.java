package views;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import styles.UIColors;
import styles.UIFonts;

public class OTPVerificationView extends JFrame implements ActionListener {

    private JLabel otpLabel;
    private JTextField otpField;
    private JButton verifyButton;
    private JButton resendButton;
    private JPanel topPanel, midPanel, bottomPanel;
    private OTPVerificationListener listener;

    private int incorrectOtpAttempts = 0;
    private int resendAttempts = 0;
    private String generatedOtp;

    public OTPVerificationView(String generatedOtp, OTPVerificationListener listener) {
        this.generatedOtp = generatedOtp;
        this.listener = listener;

        setTitle("OTP Verification");
        setSize(350, 170);
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
        bottomPanel = new JPanel(new FlowLayout());

        otpLabel = new JLabel("Enter OTP to verify your account");
        otpLabel.setFont(UIFonts.INPUT_SMALL);
        otpLabel.setForeground(UIColors.TEXT_COLOR_L);

        otpField = new JTextField(null, 20);
        verifyButton = new JButton("Submit");
        resendButton = new JButton("Resend");

        topPanel.add(otpLabel);
        midPanel.add(otpField);
        bottomPanel.add(verifyButton);
        bottomPanel.add(resendButton);
        this.add(topPanel);
        this.add(midPanel);
        this.add(bottomPanel);
    }

    private void doAction() {
        verifyButton.addActionListener(this);
        resendButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == verifyButton) {
            String otp = otpField.getText();
            if (generatedOtp.equals(otp)) {
                listener.onOtpVerified();
                this.dispose();
            } else {
                incorrectOtpAttempts++;
                if (incorrectOtpAttempts == 3) {
                    JOptionPane.showMessageDialog(null,
                        "You have entered the wrong OTP 3 times, please try again later!", "Snake Game",
                        JOptionPane.ERROR_MESSAGE);
                    listener.onBlockUser();
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null,
                        "Incorrect OTP, please try again.", "Snake Game",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == resendButton) {
            resendAttempts++;
            if (resendAttempts == 3) {
                JOptionPane.showMessageDialog(null,
                    "You have requested to resend OTP 3 times, please try again later!", "Snake Game",
                    JOptionPane.ERROR_MESSAGE);
                listener.onBlockUser();
                this.dispose();
            } else {
                listener.onResendOtp();
            }
        }
    }

    public void setGeneratedOtp(String otp) {
        this.generatedOtp = otp;
    }

    public interface OTPVerificationListener {
        void onOtpVerified();
        void onResendOtp();
        void onBlockUser();
    }
}

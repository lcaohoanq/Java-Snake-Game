package views;

import controllers.OTPVerificationListener;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.Instant;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import styles.UIColors;
import styles.UIFonts;

public class OTPVerificationView extends JFrame implements ActionListener {

    private JLabel otpLabel;
    private JTextField otpField;
    private JButton verifyButton;
    private JButton resendButton;
    private Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
    private JPanel topPanel, midPanel, bottomPanel;
    private OTPVerificationListener listener;
    private int incorrectOtpAttempts = 0;
    private int resendAttempts = 0;
    private String generatedOtp;
    private static final long OTP_EXPIRY_DURATION_SECONDS = 5 * 60; // 5 minutes
    private static final long MINIMUM_RESEND_WAIT_SECONDS = 60; // 1 minute
    private final Instant otpGeneratedTime = Instant.now();
    private Instant lastResendTime = Instant.now();

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

    public OTPVerificationView(){
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

        otpField = new JTextField(null, 10);
        otpField.setFont(UIFonts.INPUT_SMALL);

        verifyButton = new JButton("Submit");
        verifyButton.setFont(UIFonts.BUTTON);
        verifyButton.setBackground(UIColors.TEXT_COLOR_L);
        verifyButton.setForeground(UIColors.PRIMARY_COLOR_L);
        verifyButton.setCursor(cursor);

        resendButton = new JButton("Resend");
        resendButton.setFont(UIFonts.OTHERS);
        resendButton.setBorder(null);
        resendButton.setBackground(UIColors.PRIMARY_COLOR_L);
        resendButton.setCursor(cursor);

        topPanel.setBackground(UIColors.PRIMARY_COLOR_L);
        midPanel.setBackground(UIColors.PRIMARY_COLOR_L);
        bottomPanel.setBackground(UIColors.PRIMARY_COLOR_L);

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
            Instant currentTime = Instant.now();
            long otpAgeInSeconds = Duration.between(otpGeneratedTime, currentTime).getSeconds();

            //if OTP is expired show message
            if (otpAgeInSeconds > OTP_EXPIRY_DURATION_SECONDS) {
                JOptionPane.showMessageDialog(null,
                    "OTP has expired. Please request a new OTP.", "OTP Verification",
                    JOptionPane.ERROR_MESSAGE);
            //else OTP is not expired, then we have 2 cases
            //1. OTP is correct -> listener.onOtpVerified()
            //2. OTP is incorrect -> show message and increment incorrectOtpAttempts
            } else if (generatedOtp.equals(otp)) {
                listener.onOtpVerified();
                this.dispose();
            } else {
                incorrectOtpAttempts++;
                if (incorrectOtpAttempts == 3) {
                    JOptionPane.showMessageDialog(null,
                        "You have entered the wrong OTP 3 times, please try again later!",
                        "Snake Game",
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
            Instant currentTime = Instant.now();
            long timeSinceLastResend = Duration.between(lastResendTime, currentTime).getSeconds();

            //if time since last resend is less than MINIMUM_RESEND_WAIT_SECONDS, show message
            if (timeSinceLastResend < MINIMUM_RESEND_WAIT_SECONDS) {
                JOptionPane.showMessageDialog(null,
                    "You can resend OTP only after " + (MINIMUM_RESEND_WAIT_SECONDS - timeSinceLastResend) +
                        " seconds.", "Resend OTP", JOptionPane.WARNING_MESSAGE);
            //else continue to resend and increment resendAttempts
            } else {
                // Proceed with OTP resend logic
                lastResendTime = Instant.now(); // Update last resend time
                resendAttempts++;

                if (resendAttempts == 3) {
                    JOptionPane.showMessageDialog(null,
                        "You have requested to resend OTP 3 times, please try again later!",
                        "Snake Game",
                        JOptionPane.ERROR_MESSAGE);
                    listener.onBlockUser();
                    this.dispose();
                } else {
                    listener.onResendOtp();
                }
            }
        }
    }

    public void setGeneratedOtp(String otp) {
        this.generatedOtp = otp;
    }

    public static void main(String[] args) {
        new OTPVerificationView(null,null).setVisible(true);
    }
}

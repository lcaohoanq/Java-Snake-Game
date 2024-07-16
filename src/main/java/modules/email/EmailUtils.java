package modules.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Properties;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import utils.EnvUtils;
import modules.otp.OTPUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Slf4j
public class EmailUtils {

    private final String eFrom = EnvUtils.get("MAIL_HOST");
    private final String ePass = EnvUtils.get("MAIL_PASS");
    private TemplateEngine templateEngine;

    public EmailUtils() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode("HTML");
        templateResolver.setPrefix("templates/email/");
        templateResolver.setSuffix(".html");
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    public boolean isValidEmail(String email) {
        return Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$",
            Pattern.CASE_INSENSITIVE).matcher(email).matches();
    }

    private Authenticator getAuthenticator() {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(eFrom, ePass);
            }
        };
    }

    public void sendEmail(String subject, String message, String to) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); // Adjust to the correct port
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, getAuthenticator());

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.setFrom(new InternetAddress(eFrom));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject(subject, "UTF-8");
            msg.setContent(message, "text/html; charset=UTF-8");
            Transport.send(msg);
            log.info("Email sent successfully to {}", to);
        } catch (Exception e) {
            log.error("Failed to send email to {}: {}", to, e.getMessage());
        }
    }

    public String emailSendOtp(String name, String otp) {
        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("otp", otp);
        return templateEngine.process("sendOtp", context);
    }

    public String emailSendBlockAccount(String email, String reason) {
        Context context = new Context();
        context.setVariable("email", email);
        context.setVariable("reason", reason);
        return templateEngine.process("blockAccount", context);
    }

    public String emailSendForgotPassword(String name, String otp) {
        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("otp", otp);
        return templateEngine.process("forgotPassword", context);
    }

    public String subjectGreeting(String name) {
        return """
            Java Snake Game Corporation - Welcome %s, thanks for joining us!
            """.formatted(name);
    }

    public static void main(String[] args) {
        EmailUtils emailUtils = new EmailUtils();
        String email = "hoangclw@gmail.com";
        emailUtils.checkEmailIsValidAndSendEmail("sendOtp", email, "Hoang", "123", "12312");
    }

    public void checkEmailIsValidAndSendEmail(String emailType, String email, String name,
        String... reason) {
        if (!isValidEmail(email)) {
            log.error("Invalid email address: {}", email);
            return;
        }

        String subject = subjectGreeting(name);
        String message;

        switch (emailType) {
            case "sendOtp":
                message = emailSendOtp(name, OTPUtils.generateOTP());
                break;
            case "blockAccount":
                message = emailSendBlockAccount(email,
                    reason.length > 0 ? reason[0] : "No specific reason provided.");
                break;
            case "forgotPassword":
                message = emailSendForgotPassword(name, OTPUtils.generateOTP());
                break;
            default:
                log.error("Unknown email type: {}", emailType);
                return;
        }

        sendEmail(subject, message, email);
    }

}

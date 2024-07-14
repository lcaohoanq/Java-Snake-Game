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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.EnvUtils;
import modules.otp.OTPUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public class EmailUtils {

    private static final Logger logger = LoggerFactory.getLogger(EmailUtils.class);

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
        return Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$", Pattern.CASE_INSENSITIVE).matcher(email).matches();
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
            logger.info("Email sent successfully to {}", to);
        } catch (Exception e) {
            logger.error("Failed to send email to {}: {}", to, e.getMessage());
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
        return "Java Snake Game Corporation - Welcome " + name + ", thanks for joining us!";
    }

    public static void main(String[] args) {
        EmailUtils emailUtils = new EmailUtils();
        String email = "hoangclw@gmail.com";
        if (emailUtils.isValidEmail(email)) {
            String subject = emailUtils.subjectGreeting("Hoang");
            String message = emailUtils.emailSendForgotPassword("Hoang", OTPUtils.generateOTP());
            emailUtils.sendEmail(subject, message, email);
        } else {
            logger.error("Invalid email address: {}", email);
        }
    }
}
